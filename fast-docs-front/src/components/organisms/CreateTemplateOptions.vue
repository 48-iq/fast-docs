<script lang="ts" setup>
import { useOptionsStore } from '@/store/optionsStore';
import BaseButton from '../atoms/BaseButton.vue';
import BaseInput from '../atoms/BaseInput.vue';
import { useTemplateSheetStore } from '@/store/templateSheetStore';

const optionsStore = useOptionsStore()
const templateSheetStore = useTemplateSheetStore()
const submit = (e: Event) => {
  e.preventDefault();
  templateSheetStore.update({...optionsStore.block});
}

</script>



<template>
  <form class="create-template-options" @submit="submit">
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
      <div class="input-div">
        <span>Значение:</span>
        <BaseInput v-model="optionsStore.block.value" class="input" type="text"/>

      </div>
      <div class="buttons">
        <BaseButton>Сохранить</BaseButton>
        <BaseButton>Отмена</BaseButton>
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
