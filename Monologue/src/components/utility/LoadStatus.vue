<script lang="tsx" setup>
import { computed } from "vue";
import { CheckCircleIcon, HelpCircleIcon } from "tdesign-icons-vue-next";

const props = defineProps<{
  name: string;
  label: string;
  loading: boolean;
}>();
const id = computed(() => `loading-tag-${props.name}`);
const theme = computed(() => (props.loading ? "default" : "success"));
</script>

<template>
  <div :id="id" style="width: fit-content">
    <t-loading :delay="100" :loading="loading">
      <t-tag :theme="theme" variant="light">
        <template #icon>
          <HelpCircleIcon v-if="loading" />
          <CheckCircleIcon v-else />
        </template>
        <span style="user-select: none">{{ label }}</span>
      </t-tag>
    </t-loading>
  </div>
</template>

<style scoped></style>
