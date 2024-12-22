<script lang="ts" setup>
import DocumentListElement from '@/components/atoms/DocumentListElement.vue';
import { ref } from 'vue';

const documents = ref([
  {
    id: '11111111',
    title: 'Document 1',
    template: {
      id: '222222',
      title: 'Template 1'
    }
  },
  {
    id: '11111112',
    title: 'Document 2',
    template: {
      id: '222223',
      title: 'Template 2'
    }
  },
  {
    id: '11111112',
    title: 'Document 2',
    template: {
      id: '222223',
      title: 'Template 2'
    }
  }
])

</script>
<template>
  <div class="document-list">
    <div class="title">
      <h3 class="h3">Документы</h3>
    </div>
    <ul class="list">
      <li v-for="document in documents" :key="document.id">
        <DocumentListElement  :element="document"/>
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
