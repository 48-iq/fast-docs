import { defineStore } from "pinia"
import { ref } from "vue"

export type Block = {
  id: string,
  x: number,
  y: number,
  blockType: string,
  width: number,
  height: number,
  value: string
}


export type Template = {
  id: string,
  title: string,
  blocks: Block[]
}

export const useTemplatesStore = defineStore('templatesStore', () => {

  const templates = ref<Template[]>([]);

  const removeTemplate = (id: string) => {
    templates.value = localStorage.getItem('templates') ? JSON.parse(localStorage.getItem('templates') as string) : [];
    templates.value = templates.value.filter(t => t.id !== id);
    localStorage.setItem('templates', JSON.stringify(templates.value));
  }

  const saveTemplate = (template: Template) => {
    templates.value = localStorage.getItem('templates') ? JSON.parse(localStorage.getItem('templates') as string) : [];
    if (!templates.value.find(t => t.id === template.id)) templates.value.push(template);
    else templates.value = templates.value.map(t => t.id === template.id ? template : t);
    localStorage.setItem('templates', JSON.stringify(templates.value));
  }


  const fetchTemplates = () => {
    templates.value = localStorage.getItem('templates') ? JSON.parse(localStorage.getItem('templates') as string) : [];
  }

  const fetchTemplate = (id: string) => {
    templates.value = localStorage.getItem('templates') ? JSON.parse(localStorage.getItem('templates') as string) : [];
    return templates.value.find(t => t.id === id);
  }

  return {templates, removeTemplate, saveTemplate, fetchTemplates, fetchTemplate}
})
