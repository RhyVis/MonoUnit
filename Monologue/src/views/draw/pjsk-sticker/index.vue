<script lang="tsx" setup>
import { computed, onMounted, reactive, ref } from "vue";
import { MessagePlugin } from "tdesign-vue-next";
import { CopyIcon, DownloadIcon } from "tdesign-icons-vue-next";
import CharacterList from "@/assets/conf/characters.json";
import ContentLayout from "@/layout/frame/ContentLayout.vue";
import StickerCanvas from "@/views/draw/pjsk-sticker/comps/StickerCanvas.vue";
import SelectChara from "@/views/draw/pjsk-sticker/comps/SelectChara.vue";
import { b64ToBlob } from "@/lib/util/imageTransform";
import type { CharacterDefinition, DrawConf } from "@/lib/type/typeSticker";

const subtitle = () => {
  return (
    <t-space size={6} class="text-muted">
      <span>移植自</span>
      <t-link href="https://st.ayaka.one/" suffix-icon={subtitleLinkIcon}>
        Project Sekai Stickers
      </t-link>
    </t-space>
  );
};
const subtitleLinkIcon = () => <t-icon name="jump" />;

const charaList = CharacterList as CharacterDefinition[];

const currentConf = reactive<DrawConf>({
  charaID: 0,
  fontSize: 1,
  spaceSize: 1,
  rotate: 0,
  x: 0,
  y: 0,
  text: "",
  curve: false,
});
const currentConfYProxy = ref(360);

const stickerCanvasRef = ref();
const stickerCanvasKey = ref(0);

const textMultipleLines = computed(() => currentConf.text.includes("\n"));

const updateCurrentConf = (id: number) => {
  const chara = charaList[id];
  currentConf.fontSize = chara.defaultText.s;
  currentConf.rotate = chara.defaultText.r;
  currentConf.x = chara.defaultText.x;
  currentConf.y = chara.defaultText.y;
  currentConfYProxy.value = 360 - chara.defaultText.y;
  currentConf.text = chara.defaultText.text;
};

const proxyDraw = () => {
  stickerCanvasKey.value = new Date().getTime();
};

const handleSelect = (index: number) => {
  currentConf.charaID = index;
  updateCurrentConf(index);
  proxyDraw();
};
const handleYProxy = () => {
  currentConf.y = 360 - currentConfYProxy.value;
  proxyDraw();
};
const handleCopyImage = async () => {
  try {
    const canvas = document.getElementById("sticker-canvas") as HTMLCanvasElement;
    await navigator.clipboard.write([
      new ClipboardItem({
        "image/png": b64ToBlob(canvas?.toDataURL().split(",")[1]),
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
    const canvas = document.getElementById("sticker-canvas") as HTMLCanvasElement;
    const link = document.createElement("a");
    link.download = `${charaList[currentConf.charaID].name}_sticker.png`;
    link.href = canvas.toDataURL();
    link.click();
    await MessagePlugin.success("下载图像成功");
  } catch (e) {
    console.error(e);
    await MessagePlugin.error("下载图像失败");
  }
};

onMounted(() => {
  updateCurrentConf(0);
});
</script>

<template>
  <ContentLayout title="PJSK表情" :subtitle="subtitle">
    <div class="mt-1 mb-3" style="text-align: center">
      <t-space :size="8" direction="vertical">
        <t-space :size="16" direction="horizontal">
          <div style="height: 300px; width: 300px">
            <StickerCanvas :key="stickerCanvasKey" :conf="currentConf" ref="stickerCanvasRef" />
          </div>
          <t-slider v-model="currentConfYProxy" :max="360" layout="vertical" @change="handleYProxy" />
        </t-space>
        <t-slider v-model="currentConf.x" :max="360" layout="horizontal" @change="proxyDraw" />
      </t-space>
    </div>
    <t-form>
      <t-form-item label="操作">
        <t-space>
          <SelectChara @select="handleSelect" />
          <t-space :size="5">
            <t-button shape="square" theme="default" @click="handleCopyImage">
              <CopyIcon />
            </t-button>
            <t-button shape="square" theme="default" @click="handleDownloadImage">
              <DownloadIcon />
            </t-button>
          </t-space>
        </t-space>
      </t-form-item>
      <t-form-item label="显示文字">
        <t-textarea v-model="currentConf.text" :maxlength="30" :autosize="true" @change="proxyDraw" />
      </t-form-item>
      <t-form-item label="字体尺寸">
        <t-slider v-model="currentConf.fontSize" :input-number-props="true" :max="100" :min="5" @change="proxyDraw" />
      </t-form-item>
      <t-form-item label="行间距" v-if="textMultipleLines">
        <t-slider v-model="currentConf.spaceSize" :input-number-props="true" :max="80" :min="1" @change="proxyDraw" />
      </t-form-item>
      <t-form-item label="旋转">
        <t-slider v-model="currentConf.rotate" :input-number-props="true" :max="63" :min="0" @change="proxyDraw" />
      </t-form-item>
      <t-form-item label="曲度" help="非长字符串渲染时使用">
        <t-switch v-model="currentConf.curve" @change="proxyDraw" />
      </t-form-item>
    </t-form>
  </ContentLayout>
</template>
