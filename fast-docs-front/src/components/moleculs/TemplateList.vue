<script lang="ts" setup>
import { onMounted } from 'vue';
import TemplateListElement from '../atoms/TemplateListElement.vue';
import { useTemplatesStore } from '@/store/templatesStore';

const templatesStore = useTemplatesStore();

onMounted(() => {
  templatesStore.fetchTemplates();
})

</script>
<template>
  <div class="template-list">
    <div class="title">
      <h3 class="h3">Шаблоны</h3>
    </div>
    <ul class="list">
      <li v-for="template in templatesStore.templates" :key="template.id">
        <TemplateListElement :element="{
          id: template.id,
          title: template.title
        }" />
      </li>

    </ul>
  </div>
</template>

<style scoped>
  .template-list {
    overflow: auto;
    display: flex;
    flex-direction: column;
    align-items: center;
    flex: 1;
  }
  .template-list::-webkit-scrollbar{
    width: 0;
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
  .h3 {
    margin-top: 8px;
  }
</style>
