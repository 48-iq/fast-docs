<script lang="ts" setup>
import { useDraggable } from '@vueuse/core';
import { useTemplateRef } from 'vue';


  const model = defineModel<string>()

  export type TemplateBlockInstanceProps = {
    id: string
    x: number
    y: number
    width: number
    height: number
  }

  const emit = defineEmits(['move'])

  const props = defineProps<TemplateBlockInstanceProps>()

  const blockInstanceRef = useTemplateRef('block-instance-ref');
  useDraggable(blockInstanceRef, { initialValue: { x: props.x, y: props.y },
    onMove: ({ x, y }) => {
      const clientRect = blockInstanceRef.value?.parentElement?.getBoundingClientRect()
      const xval = x - clientRect!.left
      const yval = y - clientRect!.top
      emit('move', {id: props.id, x: xval, y:yval});
    },
    onEnd: ({ x, y }) => {
      const clientRect = blockInstanceRef.value?.parentElement?.getBoundingClientRect()
      const xval = x - clientRect!.left
      const yval = y - clientRect!.top
      console.log(xval, yval)
    }

  });

</script>

<template>
  <div class="template-block-instance"
    ref="block-instance-ref"
    :style="{ left: props.x + 'px', top: props.y + 'px', width: props.width + 'px', height: props.height + 'px' }">

    <textarea placeholder="Введите текст"
      v-model="model" class="template-block-instance-area">

    </textarea>
  </div>
</template>

<style scoped>
  .template-block-instance {
    position: absolute;
  }
  .template-block-instance-area {
    width: 100%;
    height: 100%;
    resize: none;
    overflow: hidden;
    padding: 5px;
    border: 2px dashed var(--color-dark-aqua);
  }
  .move-handle {
    position: absolute;
    top: 0;
    right: 0;
    width: 10px;
    height: 10px;
    background-color: var(--color-aqua);
    cursor: move;
  }
</style>
