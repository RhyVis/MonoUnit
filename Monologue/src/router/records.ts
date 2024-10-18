import type { RouteRecordRaw } from "vue-router";

type RouterRecordMeta = {
  title?: string;
  icon?: string;
  auth?: boolean;
};
type RouterRecordAssemble = RouteRecordRaw & {
  meta?: RouterRecordMeta;
};

const baseRecords: RouterRecordAssemble[] = [
  {
    path: "/credits",
    name: "Credits",
    component: () => import("@/views/base/credits/index.vue"),
    meta: {
      title: "Credits",
    },
  },
  {
    path: "/auth",
    name: "Auth",
    component: () => import("@/views/base/auth/index.vue"),
    meta: {
      title: "Auth",
    },
  },
];

const dataRecords: RouterRecordAssemble[] = [
  {
    path: "/spam",
    name: "Spam",
    component: () => import("@/views/data/spam/index.vue"),
    meta: {
      title: "弹药库",
      icon: "loudspeaker",
    },
  },
  {
    path: "/save",
    name: "Save",
    component: () => import("@/views/data/save/index.vue"),
    meta: {
      title: "字符存储",
      icon: "save",
      auth: true,
    },
  },
  {
    path: "/nav",
    name: "Nav",
    component: () => import("@/views/data/nav/index.vue"),
    meta: {
      title: "导航",
      icon: "indicator",
      auth: true,
    },
  },
];

const mystRecords: RouterRecordAssemble[] = [
  {
    path: "/tarot",
    name: "Tarot",
    component: () => import("@/views/myst/tarot/index.vue"),
    meta: {
      title: "电子塔罗",
      icon: "card",
    },
  },
];

const drawRecords: RouterRecordAssemble[] = [
  {
    path: "/pjsk-sticker",
    name: "PJSK-Sticker",
    component: () => import("@/views/draw/pjsk-sticker/index.vue"),
    meta: {
      title: "PJSK表情",
      icon: "edit-2",
    },
  },
  {
    path: "/ba-banner",
    name: "BA-Banner",
    component: () => import("@/views/draw/ba-banner/index.vue"),
    meta: {
      title: "BA横幅",
      icon: "typography",
    },
  },
];

const utilRecords: RouterRecordAssemble[] = [
  {
    path: "/codex",
    name: "Codex",
    component: () => import("@/views/util/codex/index.vue"),
    meta: {
      title: "抽象转义",
      icon: "chat-error",
    },
  },
  {
    path: "/reverse",
    name: "Reverse",
    component: () => import("@/views/util/reverse/index.vue"),
    meta: {
      title: "反转机",
      icon: "refresh",
    },
  },
  {
    path: "/base64",
    name: "Base64",
    component: () => import("@/views/util/base64/index.vue"),
    meta: {
      title: "Base64",
      icon: "file-code-1",
    },
  },
  {
    path: "/radix",
    name: "Radix",
    component: () => import("@/views/util/radix/index.vue"),
    meta: {
      title: "进制转换",
      icon: "calculation-1",
    },
  },
];

export { baseRecords, dataRecords, mystRecords, drawRecords, utilRecords };
export type { RouterRecordAssemble };
