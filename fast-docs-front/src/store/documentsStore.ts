import { defineStore } from "pinia"
import { ref } from "vue"

export type Block = {
  id: string
  value: string
  templateBlock: {
    id: string
    x: number
    y: number
    blockType: string
    width: number
    height: number
    defaultValue: string
  }
}

export type Document = {
  id: string
  title: string
  template: {
    id: string,
    title: string
  }
  blocks: Block[]
}

export const useDocumentsStore = defineStore('documentsStore', () => {

  const documents = ref<Document[]>([]);

  const createDocument = (document: Document) => {
    documents.value = localStorage.getItem('documents') ? JSON.parse(localStorage.getItem('documents') as string) : [];
    documents.value.push(document);
    localStorage.setItem('documents', JSON.stringify(documents.value));
  }

  const updateDocument = (document: Document) => {
    documents.value = localStorage.getItem('documents') ? JSON.parse(localStorage.getItem('documents') as string) : [];
    documents.value = documents.value.map(d => d.id === document.id ? document : d);
    localStorage.setItem('documents', JSON.stringify(documents.value));
  }

  const removeDocument = (id: string) => {
    documents.value = localStorage.getItem('documents') ? JSON.parse(localStorage.getItem('documents') as string) : [];
    documents.value = documents.value.filter(d => d.id !== id);
    localStorage.setItem('documents', JSON.stringify(documents.value));
  }

  const fetchDocuments = () => {
    documents.value = localStorage.getItem('documents') ? JSON.parse(localStorage.getItem('documents') as string) : [];
  }

  const fetchDocument = (id: string) => {
    documents.value = localStorage.getItem('documents') ? JSON.parse(localStorage.getItem('documents') as string) : [];
    return documents.value.find(d => d.id === id);
  }

  return { documents, createDocument, updateDocument, removeDocument, fetchDocuments, fetchDocument }
})
