<script setup lang="ts">
import { HomeIcon } from "tdesign-icons-vue-next";
import type { TdCardProps } from "tdesign-vue-next";

defineProps<{
  title?: string | TdCardProps["title"];
  subtitle?: string | TdCardProps["subtitle"];
}>();
</script>

<template>
  <div class="mt-sm-1 mt-md-1 mt-lg-1 mt-xl-2 mt-xxl-2 mb-2" style="margin: auto">
    <t-card
      :title="title"
      :subtitle="subtitle"
      :header-bordered="true"
      style="align-content: center; width: 99%; margin: auto"
    >
      <slot />
      <template #actions>
        <slot name="actions">
          <RouterLink to="/">
            <HomeIcon />
          </RouterLink>
        </slot>
      </template>
    </t-card>
  </div>
</template>
