import { defineStore } from "pinia"
import { ref } from "vue"

export type Block = {
  id: string;
  x: number;
  y: number;
  width: number;
  height: number;
  value: string
}

export const useTemplateSheetStore = defineStore('templateSheetStore', () => {
  const blocks = ref<Block[]>([])

  const add = (block: Block) => {
    blocks.value.push(block)
  }

  const templateSheetRef = ref<HTMLDivElement | null>(null)

  const setTemplateSheetRef = (el: HTMLDivElement | null) => {
    templateSheetRef.value = el
  }

  const remove = (id: string) => {
    blocks.value = blocks.value.filter(block => block.id !== id)
  }
  const resize = (id: string, width: number, height: number) => {
    blocks.value = blocks.value.map(b => b.id === id ? {...b, width, height} : b)
  }

  const move = (id: string, x: number, y: number) => {
    blocks.value = blocks.value.map(b => b.id === id ? {...b, x, y} : b)
  }

  const update = (newBlock:{id: string, x: number, y: number, width: number, height: number}) => {
    blocks.value = blocks.value.map(b => b.id === newBlock.id ? {...b, ...newBlock} : b)
  }
  return {blocks, add, remove, resize, move, setTemplateSheetRef, templateSheetRef, update}
})
