
<script lang="ts" setup>
import CreateTemplateHeader from '@/components/organisms/CreateTemplateHeader.vue';
import CreateTemplateOptions from '@/components/organisms/CreateTemplateOptions.vue';
import TemplateSheet from '@/components/organisms/TemplateSheet.vue';
import { useTemplateSheetStore } from '@/store/templateSheetStore';
import { useTemplatesStore } from '@/store/templatesStore';
import { watch } from 'vue';
const props = defineProps<{ id: string }>()
const templateSheetStore = useTemplateSheetStore()
const templaetsStore = useTemplatesStore()
if (props.id) {
  let template = templaetsStore.fetchTemplate(props.id as string);
  if (!template)
    template = {
      id: "",
      title: "",
      blocks: []}
  templateSheetStore.setTemplate(template)
}
watch(() => props.id, () => {
  let template = templaetsStore.fetchTemplate(props.id as string);
  if (!template)
    template = {
      id: "",
      title: "",
      blocks: []}
  templateSheetStore.setTemplate(template)
})


</script>

<template>
  <div class="create-template">
    <CreateTemplateHeader />
    <div class="container">
      <div class="template-sheet-container">
        <TemplateSheet />
      </div>

      <CreateTemplateOptions />
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
  flex: 1;
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

::-webkit-scrollbar {
  width: 8px;
}

::-webkit-scrollbar-track {
  background: var(--color-light-gray);
}

::-webkit-scrollbar-thumb {
  background: var(--color-aqua);
  border-radius: 10px;
}

::-webkit-scrollbar-thumb:hover {
  background: var(--color-dark-aqua);
}
</style>
