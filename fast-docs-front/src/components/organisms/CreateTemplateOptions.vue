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
        <span>x:</span>
        <BaseInput v-model="optionsStore.block.x" class="input" type="number"/>

      </div>
      <div class="input-div">
        <span>y:</span>
        <BaseInput v-model="optionsStore.block.y" class="input" type="number"/>

      </div>
      <div class="input-div">
        <span>ширина:</span>
        <BaseInput v-model="optionsStore.block.width" class="input" type="number"/>

      </div>
      <div class="input-div">
        <span>высота:</span>
        <BaseInput v-model="optionsStore.block.height" class="input" type="number"/>

      </div>
      <div class="input-div">
        <span>значение:</span>
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
    background-color: var(--color-light-aqua);
    border: var(--color-blue) 3px solid;
    gap: 30px;
    margin-top: 10px;
    padding: 10px;
    padding-top: 30px;
  }
  .buttons {
    display: flex;
    justify-content: space-around;
  }

  .input {
    width: 200px;
  }
  .input-div {
    display: flex;
    justify-content: right;
    gap: 5px;
  }

</style>
