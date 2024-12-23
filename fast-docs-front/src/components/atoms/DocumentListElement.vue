<script lang="ts" setup>
import OptionsPanel from '@/components/atoms/OptionsPanel.vue'
import { useDocumentsStore } from '@/store/documentsStore'
import { onClickOutside } from '@vueuse/core'
import { ref, useTemplateRef } from 'vue'
import { useRouter } from 'vue-router'


  export type Template = {
    id: string
    title: string
  }

  export type Document = {
    id: string
    title: string
    template: Template
  }
  export type DocumentListElementProps = {
    element: Document
  }

  const optionsParams = ref({
    isOptions: false,
    x: 0,
    y: 0
  })

  const optionsRef = useTemplateRef('options-ref');

  const props = defineProps<DocumentListElementProps>();

  const openOptions = (e: MouseEvent) => {
    e.stopPropagation();
    optionsParams.value.isOptions = true;
    optionsParams.value.x = e.clientX;
    optionsParams.value.y = e.clientY;
  }

  onClickOutside(optionsRef, () => optionsParams.value.isOptions = false);

  const documentsStore = useDocumentsStore();

  const router = useRouter();

  const deleteDocument = () => {
    documentsStore.removeDocument(props.element.id);
  }

  const selectDocument = () => {
    router.push(`/editor/documents/${props.element.template.id}/${props.element.id}`);
  }

  const selectTemplate = () => {
    router.push(`/editor/templates/${props.element.template.id}`);
  }

</script>

<template>
  <div >
    <div class="container" >
      <img class="image" src="@/assets/document-icon.png"/>
      <span>{{ props.element.title }}</span>
      <img class="options" @click="openOptions" src="@/assets/3dots.png"/>
    </div>
    <OptionsPanel ref="options-ref"
      v-if="optionsParams.isOptions" :x="optionsParams.x" :y="optionsParams.y">
      <div @click="selectTemplate" class="option">К шаблону</div>
      <div @click="selectDocument" class="option">Редактировать</div>
      <div @click="deleteDocument" class="delete option" :style="{ color: 'red' }">Удалить</div>
    </OptionsPanel>
  </div>
</template>

<style scoped>
  .container {
    display: flex;
    flex-direction: column;
    width: 230px;
    height: 120px;
    background-color: var(--color-white);
    padding: 10px;
    justify-content: space-between;
    border-radius: 10px;
    position: relative;
  }

  .option {
    width: 100%;
    cursor: pointer;
    height: 32px;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: left;
    padding-left: 10px;
    color: var(--color-dark-blue);
  }


  .option:hover {
    background-color: var(--color-light-gray);
  }


  .image {
    width: 70px;
    height: 70px;
    cursor: pointer;
  }
  .options {
    width: 20px;
    height: 20px;
    cursor: pointer;
    position: absolute;
    top: 10px;
    right: 6px;
  }
</style>
