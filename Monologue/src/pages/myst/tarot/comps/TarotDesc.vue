<script setup lang="ts">
import type { CardDisplay } from "@/lib/type/typeTarot";

defineProps<{
  card: CardDisplay;
  index: number;
}>();

const revText = (rev: boolean) => (rev ? "(逆位)" : "(正位)");
const revDesc = (card: CardDisplay) => (card.data.rev ? card.data.desc.reverse : card.data.desc.upright);
</script>

<template>
  <t-card class="mb-2" :header-bordered="true">
    <template #title>
      {{ index + 1 + "号位 " }}
    </template>
    <template #subtitle>
      <span>
        {{ card.data.loc }}
        <i style="font-size: xx-small"> {{ card.data.name }}</i>
      </span>
    </template>
    <template #actions>
      <span class="r-no-select">
        {{ revText(card.data.rev) }}
      </span>
    </template>
    <div>
      <t-title level="h6" :content="revDesc(card)" />
      <div v-if="card.data.desc.desc.length > 0">
        <t-text v-for="(line, index) in card.data.desc.desc" :key="index" :content="line" />
      </div>
    </div>
  </t-card>
</template>

<style scoped>
.r-no-select {
  user-select: none;
}
</style>
