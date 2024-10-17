<script lang="tsx" setup>
import ContentLayout from "@/layout/frame/ContentLayout.vue";
import BannerCanvas from "@/views/draw/ba-banner/comps/BannerCanvas.vue";
import { ref } from "vue";
import { CopyIcon, DownloadIcon, RefreshIcon } from "tdesign-icons-vue-next";
import { MessagePlugin } from "tdesign-vue-next";
import AdvancedOptContainer from "@/components/menu/AdvancedOptContainer.vue";

const subtitle = () => {
  return (
    <t-space size={6} class="text-muted">
      <span>移植自</span>
      <t-link href="https://tmp.nulla.top/ba-logo/" suffix-icon={subtitleLinkIcon}>
        蔚蓝档案标题生成器
      </t-link>
    </t-space>
  );
};
const subtitleLinkIcon = () => <t-icon name="jump" />;

const canvasKey = ref(0);
const textL = ref("Blue");
const textR = ref("Archive");
const offsetX = ref(-15);
const offsetY = ref(0);
const tBg = ref(false);

const bannerCanvasRef = ref();

const handleUpdate = () => {
  canvasKey.value = new Date().getTime();
};
const handleCopyImage = async () => {
  try {
    const blob = await bannerCanvasRef.value.generateOutputImage();
    await navigator.clipboard.write([
      new ClipboardItem({
        "image/png": blob,
      }),
    ]);
    await MessagePlugin.success("复制图像成功");
  } catch (e) {
    console.error(e);
    await MessagePlugin.error("复制图像失败");
  }
};
const handleDownloadImage = async () => {
  try {
    const blob = await bannerCanvasRef.value.generateOutputImage();
    const a = document.createElement("a");
    a.href = URL.createObjectURL(blob);
    a.download = `${textL.value + textR.value}-ba-banner.png`;
    a.click();
    await MessagePlugin.success("下载图像成功");
  } catch (e) {
    console.error(e);
    await MessagePlugin.error("下载图像失败");
  }
};
</script>

<template>
  <ContentLayout title="BA横幅" :subtitle="subtitle">
    <div class="mt-1 mb-3" style="text-align: center">
      <div>
        <BannerCanvas
          :key="canvasKey"
          :graph-offset="{ X: offsetX, Y: offsetY }"
          :text-left="textL"
          :text-right="textR"
          :transparent-background="tBg"
          ref="bannerCanvasRef"
        />
      </div>
    </div>
    <t-form>
      <t-form-item label="左/右标题">
        <t-space align="center" direction="vertical">
          <t-input v-model="textL" @change="handleUpdate" />
          <t-input v-model="textR" @change="handleUpdate" />
        </t-space>
      </t-form-item>
      <t-form-item label="透明背景">
        <t-switch v-model="tBg" @change="handleUpdate" />
      </t-form-item>
      <t-form-item label="高级选项">
        <AdvancedOptContainer>
          <t-tag content="光环位置" style="user-select: none" />
          <t-input-number v-model="offsetX" size="small" @change="handleUpdate" />
          <t-input-number v-model="offsetY" size="small" @change="handleUpdate" />
        </AdvancedOptContainer>
      </t-form-item>
      <t-form-item label="立即重绘">
        <t-button shape="circle" @click="handleUpdate">
          <RefreshIcon />
        </t-button>
      </t-form-item>
      <t-form-item label="操作">
        <t-space :size="5">
          <t-button shape="square" theme="default" @click="handleCopyImage">
            <CopyIcon />
          </t-button>
          <t-button shape="square" theme="default" @click="handleDownloadImage">
            <DownloadIcon />
          </t-button>
        </t-space>
      </t-form-item>
    </t-form>
  </ContentLayout>
</template>
