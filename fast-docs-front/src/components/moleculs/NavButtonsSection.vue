<script setup lang="ts">
import { useEditorNavStore } from '@/store/editoreNavStore';
import NavButton from '../atoms/NavButton.vue';

const editorNavStore = useEditorNavStore();

</script>

<template>
  <div class="section-buttons">
      <NavButton @click="editorNavStore.setActiveTab('templates')"
        :class="editorNavStore.activeTab === 'templates' ? 'active' : ''">Шаблоны</NavButton>
      <NavButton @click="editorNavStore.setActiveTab('documents')"
        :class="editorNavStore.activeTab === 'documents' ? 'active' : ''">Документы</NavButton>
    </div>
</template>
<style scoped>
  .section-buttons {
    display: flex;
    justify-content: space-around;
    align-items: center;
    height: 45px;
    width: 100%;
    background-color: var(--color-blue);
  }
  .active {
    background-color: var(--color-aqua);
  }
</style>
