<script setup lang="ts">
 export type DocumentOptionsProps = {
   x: number,
   y: number
 }
  const props = defineProps<DocumentOptionsProps>();
</script>
<template>
  <div class="options-panel" :style="{ left: props.x + 'px', top: props.y + 'px' }">
    <slot>

    </slot>
  </div>
</template>


<style scoped>
  .options-panel {
    display: flex;
    flex-direction: column;
    align-items: center;
    width: 180px;
    position: absolute;
    background-color: var(--color-white);
    border-radius: 10px;
    box-shadow: 0 5px 10px rgb(70 74 77 / 20%);
    z-index: 5;
  }
</style>
