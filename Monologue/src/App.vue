<script lang="ts" setup>
import { RouterView } from "vue-router";
import BaseLayout from "@/layout/frame/BaseLayout.vue";
import ContentHeader from "@/layout/basic/ContentHeader.vue";
import ContentFooter from "@/layout/basic/ContentFooter.vue";
import ContentAside from "@/layout/basic/ContentAside.vue";
import { loadFonts } from "@/lib/util/fontLoader";
import { useGlobalStore } from "@/store/global";
import { MessagePlugin } from "tdesign-vue-next";
import { onMounted } from "vue";

const global = useGlobalStore();

window.onload = () => {
  document.addEventListener("touchstart", function (event) {
    if (event.touches.length > 1) {
      event.preventDefault();
    }
  });
  document.addEventListener("gesturestart", function (event) {
    event.preventDefault();
  });
  let lastTouchEnd = 0;
  document.documentElement.addEventListener(
    "touchend",
    event => {
      const now = Date.now();
      if (now - lastTouchEnd <= 300) {
        event.preventDefault();
      }
      lastTouchEnd = now;
    },
    {
      passive: false,
    },
  );
};

window.onresize = () => {
  if (document.body.style.zoom != "1") {
    console.log(`Unexpected resize: ${document.body.style.zoom}`);
    document.body.style.zoom = "1.0";
  }
};

onMounted(() => {
  try {
    if (!global.fontLoaded) {
      loadFonts().then(() => (global.fontLoaded = true));
    }
  } catch (error) {
    console.log(error);
    MessagePlugin.error("字体加载失败");
  }
});
</script>

<template>
  <BaseLayout style="height: 100vh">
    <template #aside>
      <ContentAside />
    </template>
    <template #header>
      <ContentHeader />
    </template>
    <template #default>
      <RouterView />
    </template>
    <template #footer>
      <ContentFooter />
    </template>
  </BaseLayout>
</template>
