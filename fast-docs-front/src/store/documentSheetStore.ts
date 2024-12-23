import { defineStore } from "pinia"
import { ref } from "vue"


export type Document = {
  id: string;
  title: string;
  template: {
    id: string;
    title: string;
  }
  blocks: Block[]
}

export type Block = {
  id: string;
  value: string
  templateBlock: {
    id: string
    x: number;
    y: number;
    blockType: string;
    width: number;
    height: number;
    defaultValue: string
  }
}

export const useDocumentSheetStore = defineStore('documentSheetStore', () => {
  const id = ref('')
  const title = ref('')
  const templateId = ref('')
  const templateTitle = ref('')
  const blocks = ref<Block[]>([])

  const setId = (newId: string) => {
    id.value = newId
  }

  const setTitle = (newTitle: string) => {
    title.value = newTitle
  }

  const setDocument = (document: Document) => {
    id.value = document.id;
    title.value = document.title;
    templateId.value = document.template.id;
    templateTitle.value = document.template.title;
    blocks.value = document.blocks;
  }

  const updateBlock = (id: string, value: string) => {
    blocks.value = blocks.value.map(b => b.id === id ? {...b, value} : b)
  }

  return { id, title, templateId, templateTitle, blocks, setDocument, updateBlock, setId, setTitle }
})
