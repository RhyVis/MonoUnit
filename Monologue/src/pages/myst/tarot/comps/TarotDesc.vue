<script setup lang="ts">
import type { CardDisplay } from "@/lib/type/typeTarot";

defineProps<{
  card: CardDisplay;
  index: number;
}>();

const revText = (rev: boolean) => (rev ? "逆位" : "正位");
const revDesc = (card: CardDisplay) => (card.data.rev ? card.data.desc.reverse : card.data.desc.upright);
</script>

<template>
  <t-card class="mb-2" :header-bordered="true">
    <template #title>
      <span>{{ index + 1 + "号位 " }}</span>
    </template>
    <template #subtitle>
      <span>
        <span>{{ card.data.loc }}</span>
        <i style="font-size: xx-small"> {{ card.data.name }}</i>
      </span>
    </template>
    <template #actions>
      <t-tag class="r-tarot-desc-tag" theme="primary">{{ revText(card.data.rev) }}</t-tag>
    </template>
    <div>
      <t-title level="h6" :content="revDesc(card)" />
      <t-paragraph v-if="card.data.desc.desc.length > 0">
        <t-text v-for="(line, index) in card.data.desc.desc" :key="index" :content="line" />
      </t-paragraph>
    </div>
  </t-card>
</template>

<style scoped>
.r-tarot-desc-tag {
  font-weight: bold;
  user-select: none;
}
</style>
