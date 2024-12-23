<script lang="ts" setup>
import OptionsPanel from '@/components/atoms/OptionsPanel.vue'
import { useTemplatesStore } from '@/store/templatesStore'
import { onClickOutside } from '@vueuse/core'
import { ref, useTemplateRef } from 'vue'
import { useRouter } from 'vue-router'


  export type Template = {
    id: string
    title: string
  }
  export type TemplateListElementProps = {
    element: Template
  }

  const optionsParams = ref({
    isOptions: false,
    x: 0,
    y: 0
  })
  const templatesStore = useTemplatesStore();

  const optionsRef = useTemplateRef('options-ref');

  const props = defineProps<TemplateListElementProps>();

  const router = useRouter();

  const openOptions = (e: MouseEvent) => {
    e.stopPropagation();
    optionsParams.value.isOptions = true;
    optionsParams.value.x = e.clientX;
    optionsParams.value.y = e.clientY;
  }

  onClickOutside(optionsRef, () => optionsParams.value.isOptions = false);

  const deleteTemplate = () => {
    templatesStore.removeTemplate(props.element.id);
  }

  const selectTemplate = () => {
    router.push(`/editor/templates/${props.element.id}`);
  }

  const createDocument = () => {
    router.push(`/editor/documents/${props.element.id}`);
  }

</script>

<template>
  <div >
    <div class="container" >
      <img class="image" @click="selectTemplate" src="@/assets/template-icon.png"/>
      <span>{{ props.element.title }}</span>
      <img class="options" @click="openOptions" src="@/assets/3dots.png"/>
    </div>
    <OptionsPanel ref="options-ref"
      v-if="optionsParams.isOptions" :x="optionsParams.x" :y="optionsParams.y">
      <div @click="createDocument" class="option">Создать документ</div>
      <div @click="selectTemplate" class="option">Редактировать</div>
      <div @click="deleteTemplate" class="delete option" :style="{ color: 'red' }">Удалить</div>
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
