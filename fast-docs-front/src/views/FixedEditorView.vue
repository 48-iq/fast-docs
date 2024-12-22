<script lang="ts" setup>
import EditorHeader from '@/components/organisms/EditorHeader.vue';
import EditorNav from '@/components/organisms/EditorNav.vue';


</script>

<template>
  <div class="editor-view">
    <EditorNav/>
    <main class="content">
      <EditorHeader/>
      <RouterView/>
    </main>
  </div>

</template>

<style scoped>
  .editor-view {
    display: flex;
    width: 100%;
    height: 100vh;
  }
  .content {
    flex: 1;
  }
</style>
