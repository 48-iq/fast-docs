<script lang="ts" setup>
import { useOptionsStore } from '@/store/optionsStore';


  const model = defineModel<string>()

  export type DocumentBlockInstanceProps = {
    id: string
    blockType: string
    x: number
    y: number
    width: number
    height: number
  }



  const props = defineProps<DocumentBlockInstanceProps>()

  const optionsStore = useOptionsStore();

  const select = () => {
    optionsStore.setBlock( {...props, value: model.value? model.value : ''} )
    console.log(model.value)
  }



</script>

<template>
  <div class="template-block-instance"
    ref="block-instance-ref"
    :style="{ left: props.x + 'px', top: props.y + 'px', width: props.width + 'px', height: props.height + 'px' }"
    @click="select">

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
