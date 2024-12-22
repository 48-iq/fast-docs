<script lang="ts" setup>
import { ref } from 'vue';
import TemplateListElement from '../atoms/TemplateListElement.vue';

const templates = ref([
  {
    id: '11111111',
    title: 'Template 1',
  },
  {
    id: '11111112',
    title: 'Template 2',
  },
  {
    id: '11111113',
    title: 'Template 3',
  }
])

</script>
<template>
  <div class="template-list">
    <div class="title">
      <h3>Шаблоны</h3>
    </div>
    <ul class="list">
      <li v-for="template in templates" :key="template.id">
        <TemplateListElement :element="template" />
      </li>

    </ul>
  </div>
</template>

<style scoped>
  .template-list {
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
