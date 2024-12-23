<script setup lang="ts">
import { useDocumentSheetStore } from '@/store/documentSheetStore';
import DocumentBlockInstance from '../moleculs/DocumentBlockInstance.vue';

const store = useDocumentSheetStore();

</script>

<template>
  <div class="template-sheet">
    <DocumentBlockInstance v-for="block in store.blocks"
    :key="block.id"
    :id="block.id"
    :block-type="block.templateBlock.blockType"
    :x="block.templateBlock.x"
    :y="block.templateBlock.y"
    :width="block.templateBlock.width"
    :height="block.templateBlock.height"
    v-model="block.value"
   />
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
