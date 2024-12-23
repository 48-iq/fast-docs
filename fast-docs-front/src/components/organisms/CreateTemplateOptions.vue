<script lang="ts" setup>
import { useOptionsStore } from '@/store/optionsStore';
import BaseButton from '../atoms/BaseButton.vue';
import BaseInput from '../atoms/BaseInput.vue';
import { useTemplateSheetStore } from '@/store/templateSheetStore';
import { useTemplatesStore } from '@/store/templatesStore';

const optionsStore = useOptionsStore()
const templateSheetStore = useTemplateSheetStore()
const templatesStore = useTemplatesStore()
const submit = (e: Event) => {
  e.preventDefault();
  templateSheetStore.update({...optionsStore.block});
}

const saveTemplate = () => {
  const templateId = templateSheetStore.id? templateSheetStore.id : Math.random().toString(36).substring(7)
  templateSheetStore.setId(templateId)
  const template = {
    id: templateId,
    title: templateSheetStore.title,
    blocks: templateSheetStore.blocks
  }
  console.log(templateSheetStore.id)
  templatesStore.saveTemplate(template)

}

</script>



<template>
  <form class="create-template-options" @submit="submit">
    <div class="inputs">

      <div class="input-div">
        <span>X:</span>
        <BaseInput v-model="optionsStore.block.x" class="input" type="number"/>

      </div>
      <div class="input-div">
        <span>Y:</span>
        <BaseInput v-model="optionsStore.block.y" class="input" type="number"/>

      </div>
      <div class="input-div">
        <span>Ширина:</span>
        <BaseInput v-model="optionsStore.block.width" class="input" type="number"/>

      </div>
      <div class="input-div">
        <span>Высота:</span>
        <BaseInput v-model="optionsStore.block.height" class="input" type="number"/>

      </div>

    </div>
    <div>
      <div class="input-div">
        <span>Заголовок:</span>
        <BaseInput v-model="templateSheetStore.title" class="input" type="text"/>

      </div>
      <div class="buttons">
        <BaseButton @click="saveTemplate">Сохранить</BaseButton>
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
    padding: 10px;
    padding-top: 30px;
    justify-content: space-between;
    color: var(--color-dark-blue);
  }

  .inputs {
    display: flex;
    flex-direction: column;
    gap: 30px;
  }

  .buttons {
    display: flex;
    margin-top: 20px;
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
