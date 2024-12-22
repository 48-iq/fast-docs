
<script lang="ts" setup>
  const model = defineModel<string|number>()
</script>


<template>
  <input v-model="model" class="base-input"/>
</template>

<style>
  .base-input {
    height: 25px;
  }
</style>
