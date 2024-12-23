<script lang="ts" setup>
import DocumentListElement from '@/components/atoms/DocumentListElement.vue';
import { useDocumentsStore } from '@/store/documentsStore';

const documentsStore = useDocumentsStore();

</script>
<template>
  <div class="document-list">
    <div class="title">
      <h3 class="h3">Документы</h3>
    </div>
    <ul class="list">
      <li v-for="document in documentsStore.documents" :key="document.id">
        <DocumentListElement  :element="{id: document.id,
          title: document.title,
          template: {
            id: document.template.id,
            title: document.template.title
            }
          }"/>
      </li>

    </ul>
  </div>
</template>


<style scoped>
  .h3 {
    margin-top: 8px;
  }
  .document-list {
    display: flex;
    flex-direction: column;
    align-items: center;
    flex: 1;
  }
  .list {
    flex: 1;
    display: flex;
    flex-direction: column;
    gap: 10px;
    padding: 10px;
  }
  .title {
    color: var(--color-blue);
    font-size: 20px;
  }
</style>
