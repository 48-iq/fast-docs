
<script setup lang="ts">
import TemplateBlock from '../atoms/TemplateBlock.vue';


</script>

<template>
  <div class="create-template-header">
    <TemplateBlock>Статичный текст</TemplateBlock>
    <TemplateBlock>Текстовое поле</TemplateBlock>
    <TemplateBlock>Числовое поле</TemplateBlock>
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
