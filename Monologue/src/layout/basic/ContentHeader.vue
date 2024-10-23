<script setup lang="ts">
import { MenuFoldIcon, MenuUnfoldIcon } from "tdesign-icons-vue-next";
import { useGlobalStore } from "@/store/global";
import { computed, ref } from "vue";
import ProjectIcon from "@/assets/icon.svg";
import useProxy from "@/lib/util/useProxy";

const proxy = useProxy();
const store = useGlobalStore();
const dev = computed(() => import.meta.env.DEV);
const asideVisible = computed(() => store.asideVisible);
const fontStyle = ref({ fontFamily: proxy.$fontFamily });
const fontStyleSmaller = ref({ fontFamily: proxy.$fontFamily, fontSize: "smaller" });

const handleAside = () => (store.asideVisible = !store.asideVisible);
</script>

<template>
  <t-head-menu class="r-header-icon-margin">
    <template #logo>
      <div style="user-select: none" @click="handleAside">
        <t-image shape="round" :src="ProjectIcon" alt="MonoUnit" style="width: 30px; height: 30px" />
      </div>
    </template>
    <t-divider layout="vertical" />
    <div class="r-header-content">
      <t-space>
        <div>
          <t-space :size="4">
            <span :style="fontStyle">MonoUnit</span>
            <span :style="fontStyleSmaller">Toolbox</span>
            <i style="font-size: smaller">2.0</i>
          </t-space>
        </div>
        <div v-if="dev">
          <i class="text-primary" style="font-size: x-small"> Development Mode </i>
        </div>
      </t-space>
    </div>
    <template #operations>
      <t-space class="r-header-operations">
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

<style scoped>
.r-header-icon-margin :deep(.t-menu__logo) {
  margin-right: 6px;
}
.r-header-content {
  margin: 0;
  user-select: none;
}
.r-header-operations {
  margin-right: 4px;
}
</style>
