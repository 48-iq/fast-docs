
<script lang="ts" setup>
import CreateTemplateHeader from '@/components/organisms/CreateTemplateHeader.vue';
import CreateTemplateOptions from '@/components/organisms/CreateTemplateOptions.vue';
import TemplateSheet from '@/components/organisms/TemplateSheet.vue';


</script>

<template>
  <div class="create-template">
    <CreateTemplateHeader/>
    <div class="container">
      <div class="template-sheet-container">
        <TemplateSheet/>
      </div>

      <CreateTemplateOptions/>
    </div>
  </div>
</template>
<style scoped>

  .create-template {
    flex: 1;
    box-sizing: border-box;
    display: flex;
    flex-direction: column;
    width: 100%;
    height: calc(100% - 60px);
  }
  .template-sheet-container {
    display: flex;
    justify-content: center;
    padding: 10px;
    overflow-y: scroll;
    padding: 30px;
    flex :1;
    background-color: var(--color-white);
  }
  .container {
    width: 100%;
    height: calc(100% - 100px);
    width: 100%;
    display: flex;
    justify-content: space-between;
    background-color: var(--color-light-aqua);

  }
</style>
