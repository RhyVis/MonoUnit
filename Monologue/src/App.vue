<script lang="ts" setup>
import ContentAside from "@/layout/basic/ContentAside.vue";
import ContentFooter from "@/layout/basic/ContentFooter.vue";
import ContentHeader from "@/layout/basic/ContentHeader.vue";
import BaseLayout from "@/layout/frame/BaseLayout.vue";
import { loadFonts } from "@/lib/util/fontLoader";
import { useGlobalStore } from "@/store/global";
import { MessagePlugin } from "tdesign-vue-next";
import { onMounted } from "vue";
import { RouterView } from "vue-router";

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
    console.debug(`Unexpected resize: ${document.body.style.zoom}`);
    document.body.style.zoom = "1.0";
  }
};

onMounted(() => {
  try {
    if (!global.fontLoaded) {
      loadFonts().then(() => (global.fontLoaded = true));
    }
  } catch (e) {
    console.error(e);
    MessagePlugin.error("字体加载失败");
  }
});
</script>

<template>
  <BaseLayout class="r-app-base-layout">
    <template #aside>
      <ContentAside />
    </template>
    <template #header>
      <ContentHeader />
    </template>
    <template #default>
      <RouterView v-slot="{ Component }">
        <Transition name="route" mode="out-in">
          <Component :is="Component" />
        </Transition>
      </RouterView>
    </template>
    <template #footer>
      <ContentFooter />
    </template>
  </BaseLayout>
</template>

<style scoped lang="less">
.r-app-base-layout {
  height: 100vh;
}

.route-enter-active {
  transition: all 0.3s cubic-bezier(1, 0.5, 0.8, 1);
}
.route-leave-active {
  transition: all 0.42s cubic-bezier(1, 0.5, 0.8, 1);
}
.route-enter-from,
.route-leave-to {
  transform: translateX(-20px);
  opacity: 0;
}
</style>
