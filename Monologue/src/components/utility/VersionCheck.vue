<script setup lang="tsx">
import { computed, onMounted, ref } from "vue";
import { getVersion } from "@/lib/util/apiMethods";
import { MessagePlugin } from "tdesign-vue-next";
import { ArrowLeftIcon, RefreshIcon } from "tdesign-icons-vue-next";
import { decimalRadixVal } from "@/pages/util/radix/scripts/radix";
import VersionView from "@/assets/local/version.json";
import moment from "moment";

const loading = ref(true);

const vLocal = VersionView.compileTime;
const vRemote = ref(0);

const vState = ref(9);
const vDisplay = computed(() => {
  switch (vState.value) {
    case 0:
      return {
        theme: "success",
        icon: () => <t-icon name="check-circle" />,
        value: `${decimalRadixVal(vLocal, 62)} 最新版本`,
      };
    case 1:
      return {
        theme: "warning",
        icon: () => <t-icon name="info-circle" />,
        value: `${decimalRadixVal(vLocal, 62)} -> ${decimalRadixVal(vRemote.value, 62)} 需要更新`,
      };
    case -1:
      return {
        theme: "danger",
        icon: () => <t-icon name="error-circle" />,
        value: "版本获取失败",
      };
    default:
      return {
        theme: "default",
        icon: () => <t-icon name="help-circle" />,
        value: "等待版本获取",
      };
  }
});
const showDialog = ref(false);

const handleUpdate = () => {
  if (vState.value != 0) {
    fetch(window.location.host, { cache: "no-store" })
      .then(response => response.text())
      .then(html => {
        document.open();
        document.write(html);
        document.close();
      });
  }
  showDialog.value = false;
};

onMounted(async () => {
  try {
    const v = await getVersion();
    if (v != 0) {
      vRemote.value = v;
      console.log(`vR: ${v} | vL: ${vLocal}`);
      if (v != vLocal) {
        vState.value = 1;
        showDialog.value = true;
        await MessagePlugin.warning("当前版本不是最新版本");
      } else {
        vState.value = 0;
      }
    } else {
      vState.value = -1;
      await MessagePlugin.warning("获取版本异常");
    }
  } catch (e) {
    vState.value = -1;
    console.error(e);
    await MessagePlugin.error("与服务器通信获取版本失败");
  } finally {
    loading.value = false;
  }
});
</script>

<template>
  <t-card :hover-shadow="true" :loading="loading" style="width: max-content">
    <t-space direction="vertical" class="r-no-select">
      <div>
        <t-tag :theme="vDisplay.theme" size="small" :icon="vDisplay.icon">
          {{ vDisplay.value }}
        </t-tag>
      </div>
      <div v-if="vState !== 0">
        <t-space>
          <t-button size="small" shape="circle" :theme="vDisplay.theme" @click="handleUpdate">
            <RefreshIcon />
          </t-button>
          <ArrowLeftIcon />
          <t-text :code="true">点击刷新页面以更新</t-text>
        </t-space>
      </div>
    </t-space>
  </t-card>
  <t-dialog v-model:visible="showDialog" header="版本更新" theme="warning" :close-btn="false" @confirm="handleUpdate">
    <t-space direction="vertical" class="r-no-select">
      <div>检查到新版本：</div>
      <div>
        <span>构建版本：</span>
        <t-tag>{{ decimalRadixVal(vRemote, 62) }}</t-tag>
      </div>
      <div>
        <span>构建时间：</span>
        <t-tag>{{ moment(vRemote).format("YYYY/MM/DD HH:mm:ss") }}</t-tag>
      </div>
      <div>是否更新？</div>
    </t-space>
  </t-dialog>
</template>

<style scoped>
.r-no-select {
  user-select: none;
}
</style>
