<script setup lang="ts">
import CardLayout from "@/layout/frame/ContentLayout.vue";
import { useRouter } from "vue-router";

const router = useRouter();
</script>

<template>
  <CardLayout title="404">
    <div class="mt-2" style="text-align: center">
      <t-space direction="vertical" :size="24">
        <t-text style="font-weight: bold">哎呀，找不到这个页面呢</t-text>
        <t-image
          src="https://api.r10086.com/樱道随机图片api接口.php?图片系列=猫娘1"
          fit="contain"
          shape="round"
          style="width: 400px; min-height: 200px; height: fit-content; max-height: 850px"
        />
        <t-button theme="warning" shape="round" variant="base" @click="router.push('/')">点此返回主页</t-button>
      </t-space>
    </div>
  </CardLayout>
</template>
