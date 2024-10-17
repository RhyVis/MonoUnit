import "bootstrap/dist/css/bootstrap.min.css";
import "tdesign-vue-next/es/style/index.css";

import { createApp } from "vue";
import { Typography as TTypography } from "tdesign-vue-next";

import App from "@/App.vue";
import router from "@/router";
import store from "@/store";

const app = createApp(App);

app.use(router);
app.use(store);

app.use(TTypography);

app.mount("#app");
