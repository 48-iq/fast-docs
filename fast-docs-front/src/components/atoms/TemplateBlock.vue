<script lang="ts" setup>
import { useTemplateSheetStore } from '@/store/templateSheetStore';
import { useDraggable } from '@vueuse/core';
import { useTemplateRef } from 'vue';


const templateSheetStore = useTemplateSheetStore();

const templateBlockRef = useTemplateRef('template-block-ref');

useDraggable(templateBlockRef, {
  initialValue: { x: 0, y: 0 },
  onEnd: ({ x, y }) => {
    const clientRect = templateSheetStore.templateSheetRef?.getBoundingClientRect()
    const xval = Math.round(x - clientRect!.left)
    const yval = Math.round(y - clientRect!.top)
    templateSheetStore.add({
      id: Math.random().toString(36).substring(7),
      x: xval,
      y: yval,
      width: 120,
      height: 80,
      value: ''
    })
  },

})

</script>

<template>
  <div class="template-block" ref="template-block-ref">
    <slot></slot>
  </div>
</template>

<style scoped>
  .template-block {
    width: 120px;
    height: 80px;
    background-color: var(--color-aqua);
    cursor: pointer;
    user-select: none;
    color: var(--color-white);
    display: flex;
    flex-direction: column;
    justify-content: center;
    font-size: 17px;
    border-radius: 10px;
    border: 3px solid var(--color-aqua);
    text-align: center;
  }
</style>
