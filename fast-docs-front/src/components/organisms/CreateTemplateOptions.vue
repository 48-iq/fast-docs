<script lang="ts" setup>
import BaseButton from '../atoms/BaseButton.vue';


</script>

<template>
  <div class="create-template-options">
    <form>
      <BaseButton>Отмена</BaseButton>
      <BaseButton>Создать</BaseButton>
    </form>
  </div>
</template>

<style scoped>
  .create-template-options {
    width: 300px;
    background-color: var(--color-light-aqua);
    border: var(--color-blue) 3px solid;
  }
</style>
