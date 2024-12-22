
<script setup lang="ts">
import TemplateBlock from '../atoms/TemplateBlock.vue';


</script>

<template>
  <div class="create-template-header">
    <TemplateBlock block-type="text">Статичный текст</TemplateBlock>
    <TemplateBlock block-type="input">Текстовое поле</TemplateBlock>
    <TemplateBlock block-type="number">Числовое поле</TemplateBlock>
  </div>
</template>
<style scoped>
  .create-template-header {
    width: 100%;
    height: 100px;
    display: flex;
    justify-content: left;
    padding-left: 30px;
    gap: 30px;
    align-items: center;
    background-color: var(--color-light-aqua);
  }

</style>
