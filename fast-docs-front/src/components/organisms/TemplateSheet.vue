<script setup lang="ts">
import { onMounted, useTemplateRef } from 'vue';
import TemplateBlockInstance from '../moleculs/TemplateBlockInstance.vue';
import { useTemplateSheetStore } from '@/store/templateSheetStore';

const store = useTemplateSheetStore();

const ref = useTemplateRef('template-sheet-ref');

onMounted(() => {
  store.setTemplateSheetRef(ref.value);
})

</script>

<template>
  <div class="template-sheet" ref="template-sheet-ref">
    <TemplateBlockInstance v-for="block in store.blocks"
    :key="block.id"
    :id="block.id"
    :block-type="block.blockType"
    :x="block.x"
    :y="block.y"
    :width="block.width"
    :height="block.height"
    v-model="block.value"
    @move="store.move($event.id, $event.x, $event.y)"/>
  </div>
</template>

<style scoped>
.template-sheet {
  position: relative;
  width: 1200px;
  height: 1800px;
  border: 3px solid var(--color-aqua);
}
</style>
