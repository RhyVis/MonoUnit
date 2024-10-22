<script setup lang="ts">
import { reactive, ref } from "vue";
import { apiPost } from "@/lib/util/apiMethods";
import ContentLayout from "@/layout/frame/ContentLayout.vue";
import ButtonClear from "@/components/btn/ButtonClear.vue";
import ButtonRead from "@/components/btn/ButtonRead.vue";
import ButtonCopy from "@/components/btn/ButtonCopy.vue";
import { SendIcon } from "tdesign-icons-vue-next";
import { MessagePlugin } from "tdesign-vue-next";

const query = reactive({
  text: "哦牛",
  type: "nmsl",
  decode: false,
});
const loading = ref(false);

const handleSpam = async () => {
  if (query.text.length === 0) {
    await MessagePlugin.warning("不要什么都不输入");
  } else {
    loading.value = true;
    try {
      const r = await apiPost("/api/codex", {
        text: query.text,
        type: query.type,
        encode: !query.decode,
      });
      result.value = r.data as string;
    } catch (e) {
      console.error(e);
      await MessagePlugin.error("与服务器通信失败");
    } finally {
      loading.value = false;
    }
  }
};

const result = ref("");
</script>

<template>
  <ContentLayout title="抽象翻译器" subtitle="玩抽象的这辈子有了">
    <t-form>
      <t-tabs v-model:value="query.type" @change="query.decode = false">
        <!-- 玩抽象的这辈子有了 -->
        <t-tab-panel label="抽象转换" value="nmsl" class="mt-2">
          <t-form-item label="原始文本">
            <t-textarea v-model="query.text" auto-size />
          </t-form-item>
          <t-form-item label="解密" tooltip="解析方法不全，不能完全解析Emoji">
            <t-switch v-model="query.decode" />
          </t-form-item>
        </t-tab-panel>
        <!-- 繁体 -->
        <t-tab-panel label="繁体转换" value="trad" class="mt-2">
          <t-form-item label="原始文本">
            <t-textarea v-model="query.text" auto-size />
          </t-form-item>
          <t-form-item label="解密">
            <t-switch v-model="query.decode" />
          </t-form-item>
        </t-tab-panel>
        <!-- 火星文 -->
        <t-tab-panel label="火星文化" value="sprk" class="mt-2">
          <t-form-item label="原始文本">
            <t-textarea v-model="query.text" auto-size />
          </t-form-item>
          <t-form-item label="解密">
            <t-switch v-model="query.decode" />
          </t-form-item>
        </t-tab-panel>
        <!-- 形近字 -->
        <t-tab-panel label="形近转换" value="diff" class="mt-2">
          <t-form-item label="原始文本">
            <t-textarea v-model="query.text" auto-size />
          </t-form-item>
        </t-tab-panel>
      </t-tabs>
      <div class="mt-4">
        <t-form-item label="输出结果">
          <t-loading size="small" :loading="loading" :delay="400" style="width: 100%">
            <t-textarea :model-value="result" placeholder="返回内容" :autosize="true" :readonly="true" />
          </t-loading>
        </t-form-item>
        <t-form-item label="操作">
          <t-button shape="round" :disabled="loading" @click="handleSpam">
            <SendIcon />
          </t-button>
        </t-form-item>
        <t-form-item label="工具">
          <t-space :size="5">
            <ButtonCopy :target="result" />
            <ButtonRead v-model:target="query.text" />
            <ButtonClear v-model:target="query.text" />
          </t-space>
        </t-form-item>
      </div>
    </t-form>
  </ContentLayout>
</template>
