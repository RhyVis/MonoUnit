<script setup lang="ts">
import ContentLayout from "@/layout/frame/ContentLayout.vue";
import CLinkPri from "@/pages/base/credits/comps/CLinkPri.vue";
import CLinkN from "@/pages/base/credits/comps/CLinkN.vue";
import { useGlobalStore } from "@/store/global";
import { ref } from "vue";
import { HomeIcon } from "tdesign-icons-vue-next";
import { MessagePlugin } from "tdesign-vue-next";

const global = useGlobalStore();
const counter = ref(0);
const counterIncrement = () => {
  if (!global.authShow) {
    if (counter.value > 5) {
      global.authShow = true;
      MessagePlugin.info("Something went wrong...");
    }
    counter.value++;
  }
};
</script>

<template>
  <ContentLayout title="Credits">
    <t-paragraph>
      <t-title level="h4"> MonoUnit </t-title>
      <t-text>
        This project was made possible with the help of various open-source or free-to-use libraries, tools, and
        resources.
      </t-text>
    </t-paragraph>
    <t-paragraph>
      <t-title level="h5"> Frontend </t-title>
      <ul>
        <li>
          Vue.js - Powered by Vue.js for building user interfaces (
          <c-link-pri href="https://mit-license.org">MIT License</c-link-pri>
          )
        </li>
        <li>
          TDesign - Design component library from TDesign (
          <c-link-pri href="https://mit-license.org">MIT License</c-link-pri>
          )
        </li>
        <li>Cloudflare - Free static resource cache</li>
      </ul>
    </t-paragraph>
    <t-paragraph>
      <t-title level="h5"> Backend </t-title>
      <ul>
        <li>
          Kotlin - Programming language by JetBrains (
          <c-link-pri href="https://www.apache.org/licenses/LICENSE-2.0">Apache 2.0</c-link-pri>
          )
        </li>
        <li>
          Spring Boot - Web service framework (
          <c-link-pri href="https://www.apache.org/licenses/LICENSE-2.0">Apache 2.0</c-link-pri>
          )
        </li>
      </ul>
    </t-paragraph>
    <t-paragraph>
      <t-title level="h5"> Tools </t-title>
      <ul>
        <li>IntelliJ IDEA & WebStorm - Development tool by JetBrains</li>
      </ul>
    </t-paragraph>
    <t-divider />
    <t-title level="h4"> Licence </t-title>
    <t-paragraph>
      <t-text>
        The pages you are viewing includes various components and resources licensed under open-source licenses:
      </t-text>
    </t-paragraph>
    <t-paragraph>
      <t-text>
        Most of the project code is licensed under the
        <c-link-pri href="https://www.gnu.org/licenses/gpl-3.0.txt">GPL 3.0 Licence</c-link-pri>
        , but with some contents which need clarification:
      </t-text>
    </t-paragraph>
    <t-paragraph>
      <ul>
        <li>
          Some fonts, used or provided by the pages, are licensed under the
          <c-link-pri href="https://scripts.sil.org/OFL">SIL Open Font 1.1 Licence</c-link-pri>
          :
          <ul>
            <li>
              <c-link-n href="https://fonts.google.com/specimen/Lilita+One/license">LilitaOne</c-link-n>
            </li>
            <li>
              <c-link-n href="https://github.com/Des-Magmeta/ChildFunSans-Fusion">ChildFunSansFusion-Z</c-link-n>
            </li>
            <li>
              <c-link-n href="https://github.com/welai/glow-sans">GlowSansSC</c-link-n>
            </li>
          </ul>
          Other fonts have commercial restrictions, or unclear licence, which is only for personal non-commercial usage:
          <ul>
            <li>
              <c-link-n href="https://lets.fontworks.co.jp/fonts/218">YurukaStd</c-link-n>
            </li>
            <li>
              <c-link-n href="https://www.ssfonts.com/shows/27/43.html">SSFangTangTi</c-link-n>
            </li>
            <li>
              <c-link-n href="https://www.typebank.co.jp">RoGSanSrfStd</c-link-n>
            </li>
          </ul>
        </li>
        <li>
          Other assets, such as images and other media, are licensed under the
          <c-link-pri href="https://creativecommons.org/licenses/by-sa/4.0/">CC BY-SA 4.0 Licence</c-link-pri>
        </li>
      </ul>
    </t-paragraph>
    <template #actions>
      <HomeIcon @click="counterIncrement" style="user-select: none" />
    </template>
  </ContentLayout>
</template>
