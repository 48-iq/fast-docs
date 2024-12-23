
<script lang="ts" setup>
import CreateDocumentOptions from '@/components/organisms/CreateDocumentOptions.vue';
import DocumentSheet from '@/components/organisms/DocumentSheet.vue';
import { useDocumentSheetStore } from '@/store/documentSheetStore';
import { useDocumentsStore } from '@/store/documentsStore';
import { useTemplatesStore } from '@/store/templatesStore';
import { watch } from 'vue';

const props = defineProps<{ id: string, templateId: string }>()
const documentSheetStore = useDocumentSheetStore()
const templatesStore = useTemplatesStore()
const documentsStore = useDocumentsStore()
if (props.id) {
  let document = documentsStore.fetchDocument(props.id as string);
  const template = templatesStore.fetchTemplate(props.templateId as string);
  if (!document && template) {
    document = {
      id: "",
      title: "",
      template: {
        id: template.id,
        title: template.title
      },
      blocks: template.blocks.map(block => (
        {
          id: Math.random().toString(36).substring(7),
          title: "",
          templateBlock: {
            id: block.id,
            x: block.x,
            y: block.y,
            width: block.width,
            height: block.height,
            blockType: block.blockType,
            defaultValue: block.value
          },
          value: block.value
        }
      ))}
    documentSheetStore.setDocument(document)
  }
}
watch(() => props.id, () => {
  const template = templatesStore.fetchTemplate(props.templateId as string);
  let document = documentsStore.fetchDocument(props.id as string);
  if (!document && template){
    document = {
      id: "",
      title: "",
      template: {
        id: template.id,
        title: template.title
      },
      blocks: template.blocks.map(block => (
        {
          id: Math.random().toString(36).substring(7),
          title: "",
          templateBlock: {
            id: block.id,
            x: block.x,
            y: block.y,
            width: block.width,
            height: block.height,
            blockType: block.blockType,
            defaultValue: block.value
          },
          value: block.value
        }
      ))}
    documentSheetStore.setDocument(document)
  }

})



</script>

<template>
  <div class="create-document">
    <div class="container">
      <div class="template-sheet-container">
        <DocumentSheet/>
      </div>
      <CreateDocumentOptions/>
    </div>
  </div>
</template>
<style scoped>
.create-document {
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
