<script setup lang="ts">
import ProjectIcon from "@/assets/icon.svg";
import {
  CloseIcon,
  DataBaseIcon,
  FileUnknownIcon,
  HomeIcon,
  Icon,
  PenBrushIcon,
  RelationIcon,
  ToolsIcon,
} from "tdesign-icons-vue-next";
import { computed, onMounted, ref } from "vue";
import { useGlobalStore } from "@/store/global";
import { dataRecords, drawRecords, mystRecords, utilRecords } from "@/router/records";
import { useAuthStore } from "@/store/comps/auth";
import useProxy from "@/lib/util/useProxy";

const fontStyle = ref<Record<string, string>>({
  fontFamily: useProxy().$fontFamily,
  fontSize: "larger",
  userSelect: "none",
});
const global = useGlobalStore();
const auth = useAuthStore();

const visible = computed({
  get() {
    return global.asideVisible;
  },
  set(value) {
    global.asideVisible = value;
  },
});

const handleClose = () => {
  visible.value = false;
};

onMounted(() => {
  global.asideVisible = false;
});
</script>

<template>
  <t-drawer v-model:visible="visible" placement="left" size="232px" :footer="false" class="r-drawer-no-padding">
    <t-menu :expand-mutex="true" style="max-height: 100vh">
      <!-- Head -->
      <template #logo>
        <t-image shape="round" :src="ProjectIcon" alt="MonoUnit" style="width: 30px; height: 30px" />
        <span :style="fontStyle">MonoUnit</span>
      </template>
      <t-menu-item to="/" @click="handleClose">
        <template #icon>
          <HomeIcon />
        </template>
        <span>HomePage</span>
      </t-menu-item>

      <!--Data-->
      <t-submenu value="data">
        <template #icon>
          <DataBaseIcon />
        </template>
        <template #title>
          <span>Data</span>
        </template>
        <div v-for="item in dataRecords" :key="item.name">
          <t-menu-item v-if="!(item.meta.auth && !auth.valid)" :to="item.path" :value="item.name" @click="handleClose">
            <template #icon>
              <icon v-if="item.meta.icon" :name="item.meta.icon" />
            </template>
            <span>{{ item.meta.title }}</span>
          </t-menu-item>
        </div>
      </t-submenu>

      <!--Myst-->
      <t-submenu value="myst">
        <template #icon>
          <RelationIcon />
        </template>
        <template #title>
          <span>Myst</span>
        </template>
        <div v-for="item in mystRecords" :key="item.name">
          <t-menu-item v-if="!(item.meta.auth && !auth.valid)" :to="item.path" :value="item.name" @click="handleClose">
            <template #icon>
              <icon v-if="item.meta.icon" :name="item.meta.icon" />
            </template>
            <span>{{ item.meta.title }}</span>
          </t-menu-item>
        </div>
      </t-submenu>

      <!--Draw-->
      <t-submenu value="draw">
        <template #icon>
          <PenBrushIcon />
        </template>
        <template #title>
          <span>Draw</span>
        </template>
        <div v-for="item in drawRecords" :key="item.name">
          <t-menu-item v-if="!(item.meta.auth && !auth.valid)" :to="item.path" :value="item.name" @click="handleClose">
            <template #icon>
              <icon v-if="item.meta.icon" :name="item.meta.icon" />
            </template>
            <span>{{ item.meta.title }}</span>
          </t-menu-item>
        </div>
      </t-submenu>

      <!--Util-->
      <t-submenu value="util">
        <template #icon>
          <ToolsIcon />
        </template>
        <template #title>
          <span>Util</span>
        </template>
        <div v-for="item in utilRecords" :key="item.name">
          <t-menu-item v-if="!(item.meta.auth && !auth.valid)" :to="item.path" :value="item.name" @click="handleClose">
            <template #icon>
              <icon v-if="item.meta.icon" :name="item.meta.icon" />
            </template>
            <span>{{ item.meta.title }}</span>
          </t-menu-item>
        </div>
      </t-submenu>

      <t-submenu value="extra">
        <template #icon>
          <FileUnknownIcon />
        </template>
        <template #title>
          <span>Extra</span>
        </template>
        <t-menu-item to="/credits" value="credits" @click="handleClose">
          <template #icon>
            <icon name="undertake-delivery" />
          </template>
          <span>Credits</span>
        </t-menu-item>
        <t-menu-item v-if="global.authShow" to="/auth" value="auth" @click="handleClose">
          <template #icon>
            <icon name="lock-on" />
          </template>
          <span>Auth</span>
        </t-menu-item>
      </t-submenu>

      <template #operations>
        <t-button theme="default" shape="circle" variant="outline" @click="handleClose">
          <CloseIcon />
        </t-button>
      </template>
    </t-menu>
  </t-drawer>
</template>

<style scoped>
:global(.r-drawer-no-padding .t-drawer__body) {
  padding: 0;
}
</style>
