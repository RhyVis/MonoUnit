<script lang="tsx" setup>
import type { CardDisplay } from "@/lib/type/typeTarot";
import { computed } from "vue";
import { MessagePlugin } from "tdesign-vue-next";
import { MoonIcon, SunnyIcon } from "tdesign-icons-vue-next";

const props = defineProps<{
  card: CardDisplay;
  index: number;
}>();

const rev = computed(() => props.card.data.rev);
const revClass = computed(() => ({ "r-tarot-img-rev": rev.value }));
const revText = computed(() => (rev.value ? "(逆位)" : "(正位)"));
const revDesc = computed(() => (rev.value ? props.card.data.desc.reverse : props.card.data.desc.upright));

const handleImage = () => {
  props.card.showImg = !props.card.showImg;
};
const handleImageErr = (name: string) => {
  MessagePlugin.error(`加载图片失败: ${name}`);
};
const handleDesc = () => {
  props.card.showDesc = !props.card.showDesc;
};
</script>

<template>
  <t-card :header-bordered="true" class="mb-2">
    <template #title>
      <div class="text-primary r-no-select" @click="handleImage">
        {{ card.data.loc }}
      </div>
    </template>
    <template #actions>
      <i class="small text-black-50 r-no-select">{{ index + 1 }} - {{ revText }} - </i>
      <MoonIcon v-if="rev" />
      <SunnyIcon v-else />
    </template>
    <div v-show="card.showImg">
      <div class="r-tarot-main-div-full" @click="handleDesc">
        <div v-show="!card.showDesc">
          <t-image
            :alt="card.data.name"
            :class="revClass"
            :lazy="true"
            :src="card.data.img"
            :style="{ width: 'fit-content', maxWidth: '100%', height: '360px' }"
            class="r-tarot-img-fit"
            fit="scale-down"
            loading="🤔"
            @error="handleImageErr(card.data.name)"
          />
        </div>
        <div v-show="card.showDesc" style="text-align: left; align-items: center">
          <t-title :content="revDesc" level="h6" />
          <t-text :content="card.data.desc.desc.join('')" />
        </div>
      </div>
    </div>
  </t-card>
</template>

<style scoped>
.r-no-select {
  user-select: none;
}

.r-tarot-img-rev {
  transform: rotate(180deg);
}

.r-tarot-img-fit {
  margin: auto;
}

.r-tarot-main-div-full {
  height: 360px;
  flex: 1;
  text-align: center;
}
</style>
