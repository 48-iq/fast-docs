<script lang="ts" setup>
import DocumentList from '../moleculs/DocumentList.vue';
import NavButtonsSection from '../moleculs/NavButtonsSection.vue';
import TemplateList from '../moleculs/TemplateList.vue';
import { useEditorNavStore } from '@/store/editoreNavStore';
import BaseButton from '../atoms/BaseButton.vue';

const editorNavStore = useEditorNavStore();
</script>


<template >
  <div class="editor-nav">
    <NavButtonsSection/>
    <DocumentList v-if="editorNavStore.activeTab === 'documents'" />
    <TemplateList v-else />
    <BaseButton class="create-button" v-if="editorNavStore.activeTab === 'templates'">Создать</BaseButton>
  </div>
</template>
<style scoped>
  .editor-nav {

    background-color: var(--color-light-gray);
    width: 250px;
    display: flex;
    flex-direction: column;
    align-items: center;
  }
  .create-button {
    margin: 10px;
    width: 200px;
  }
</style>
