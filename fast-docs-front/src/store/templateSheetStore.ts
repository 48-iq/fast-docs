import { defineStore } from "pinia"
import { ref } from "vue"

export type Template = {
  id: string;
  title: string;
  blocks: Block[]
}

export type Block = {
  id: string;
  x: number;
  y: number;
  blockType: string;
  width: number;
  height: number;
  value: string
}

export const useTemplateSheetStore = defineStore('templateSheetStore', () => {
  const id = ref('')
  const title = ref('')
  const blocks = ref<Block[]>([])

  const setTemplate = (template: Template) => {
    id.value = template.id;
    title.value = template.title;
    blocks.value = template.blocks
  }

  const add = (block: Block) => {
    blocks.value.push(block)
  }

  const setId = (newId: string) => {
    id.value = newId;
  }

  const setTitle = (newTitle: string) => {
    title.value = newTitle;
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
  return {blocks, add, remove, resize, move, setTemplateSheetRef, templateSheetRef, update, id, title, setId, setTitle, setTemplate}
})
