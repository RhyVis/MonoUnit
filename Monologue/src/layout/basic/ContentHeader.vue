<script setup lang="ts">
import ProjectIcon from "@/assets/icon.svg";
import { useGlobalStore } from "@/store/global";
import { MenuFoldIcon, MenuUnfoldIcon } from "tdesign-icons-vue-next";
import { computed } from "vue";

const store = useGlobalStore();
const dev = computed(() => import.meta.env.DEV);
const asideVisible = computed(() => store.asideVisible);

const handleAside = () => (store.asideVisible = !store.asideVisible);
</script>

<template>
  <t-head-menu class="r-ct-hd-icon-margin">
    <template #logo>
      <div class="r-no-select" @click="handleAside">
        <t-image shape="round" :src="ProjectIcon" alt="MonoUnit" style="width: 30px; height: 30px" />
      </div>
    </template>
    <t-divider layout="vertical" />
    <div class="r-ct-hd-content">
      <t-space>
        <div>
          <t-space :size="4">
            <span class="r-ct-hd-content-tt1">MonoUnit</span>
            <span class="r-ct-hd-content-tt2">Toolbox</span>
            <i class="r-ct-hd-content-tt tw-font-thin">2.0</i>
          </t-space>
        </div>
        <div v-if="dev">
          <i class="text-primary r-ct-hd-content-dev"> Development Mode </i>
        </div>
      </t-space>
    </div>
    <template #operations>
      <t-space class="r-ct-hd-operations">
        <t-button shape="circle" theme="default" @click="handleAside">
          <template #icon>
            <MenuUnfoldIcon v-if="asideVisible" />
            <MenuFoldIcon v-else />
          </template>
        </t-button>
      </t-space>
    </template>
  </t-head-menu>
</template>

<style scoped lang="less">
@import "@/assets/style/mixin";

.r-ct-hd-icon-margin :deep(.t-menu__logo) {
  margin-right: 6px;
}
.r-ct-hd-content {
  margin: 0;
  .r-no-select();
  .r-ct-hd-content-tt1 {
    .r-font-chain();
  }
  .r-ct-hd-content-tt2 {
    .r-font-chain();
    font-size: smaller;
  }
  .r-ct-hd-content-tt {
    font-size: smaller;
  }
  .r-ct-hd-content-dev {
    font-size: x-small;
  }
}
.r-ct-hd-operations {
  margin-right: 4px;
}
</style>
