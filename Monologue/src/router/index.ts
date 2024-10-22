import type { RouteRecordRaw } from "vue-router";
import { createRouter, createWebHistory } from "vue-router";
import { useAuthStore } from "@/store/comps/auth";
import { validateToken } from "@/lib/util/authFunction";
import { baseRecords, dataRecords, drawRecords, mystRecords, utilRecords } from "@/router/records";
import { MessagePlugin } from "tdesign-vue-next";
import HomeView from "@/pages/base/HomeView.vue";

const records: RouteRecordRaw[] = [
  {
    path: "/",
    name: "Home",
    component: HomeView,
  },
  ...baseRecords,
  ...dataRecords,
  ...mystRecords,
  ...drawRecords,
  ...utilRecords,
  {
    path: "/:pathMatch(.*)",
    name: "404",
    component: () => import("@/pages/base/error/index.vue"),
  },
];

const router = createRouter({
  history: createWebHistory(),
  routes: records,
});

router.beforeEach(async (to, _, next) => {
  if (to.meta.auth) {
    const auth = useAuthStore();
    const { token } = auth;
    if (token.length === 0) {
      next("/");
      await MessagePlugin.warning("不存在Token");
    } else {
      const r = await validateToken(token);
      if (r) {
        next();
      } else {
        next("/");
        await MessagePlugin.warning("校验失败");
      }
    }
  } else {
    next();
  }
});

export default router;
