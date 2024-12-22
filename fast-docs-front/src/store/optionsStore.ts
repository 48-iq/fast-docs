

import { defineStore } from "pinia"
import { ref } from "vue"

export type Block = {
  id: string,
  x: number,
  y: number,
  width: number,
  height: number,
  value: string
}

export const useOptionsStore = defineStore('optionsStore', () => {
  const block = ref<Block>({
    id: '',
    x: 0,
    y: 0,
    width: 0,
    height: 0,
    value: ''
  });

  const setBlock = (newBlock: Block ) => {
    block.value = newBlock;
    console.log(block.value);
  }
  const getX = () => block.value?.x || 0;
  const getY = () => block.value?.y || 0;
  const getWidth = () => block.value?.width || 0;
  const getHeight = () => block.value?.height || 0;
  const getValue = () => block.value?.value || '';
  const setX = (x: number) => {
    if (block.value) {
      block.value.x = x;
    }
  }

  const setY = (y: number) => {
    if (block.value) {
      block.value.y = y;
    }
  }

  const setWidth = (width: number) => {
    if (block.value) {
      block.value.width = width;
    }
  }

  const setHeight = (height: number) => {
    if (block.value) {
      block.value.height = height;
    }
  }

  const setValue = (value: string) => {
    if (block.value) {
      block.value.value = value;
    }
  }

  return {setBlock, block,
    setX, setY, setWidth, setHeight, setValue,
    getX, getY, getWidth, getHeight, getValue}
})
