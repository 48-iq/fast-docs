<script lang="ts" setup>
import { useOptionsStore } from '@/store/optionsStore';
import BaseButton from '../atoms/BaseButton.vue';
import BaseInput from '../atoms/BaseInput.vue';
import { useDocumentSheetStore } from '@/store/documentSheetStore';
import { useDocumentsStore } from '@/store/documentsStore';


const optionsStore = useOptionsStore();
const documentSheetStore = useDocumentSheetStore()
const documentsStore = useDocumentsStore()

const submit = (e: Event) => {
  e.preventDefault();
  documentSheetStore.updateBlock(optionsStore.block.id, optionsStore.block.value);
}


const saveDocument = () => {
  const documentId = documentSheetStore.id? documentSheetStore.id : Math.random().toString(36).substring(7)
  documentSheetStore.setId(documentId)
  const document = {
    id: documentId,
    title: documentSheetStore.title,
    template: {
      id: documentSheetStore.templateId,
      title: documentSheetStore.templateTitle
    },
    blocks: documentSheetStore.blocks
  }
  documentsStore.createDocument(document)

}

</script>



<template>
  <form class="create-template-options" @submit="submit">
    <div></div>


    <div>
      <div class="input-div">
        <span>Заголовок:</span>
        <BaseInput v-model="documentSheetStore.title" class="input" type="text"/>

      </div>
      <div class="buttons">
        <BaseButton @click="saveDocument">Сохранить</BaseButton>
        <BaseButton>Отмена</BaseButton>
      </div>
    </div>
  </form>
</template>

<style scoped>
  .create-template-options {
    display: flex;
    flex-direction: column;
    width: 300px;
    background-color: var(--color-light-gray);
    gap: 30px;
    padding: 10px;
    padding-top: 30px;
    color: var(--color-dark-blue);
  }
  .buttons {
    display: flex;
    justify-content: space-around;
  }

  .input {
    width: 180px;
    border-radius: 5px;
    border: none;
    color: var(--color-dark-aqua);
    padding-left: 5px;
  }
  .input-div {
    display: flex;
    justify-content: right;
    gap: 5px;
  }

</style>
