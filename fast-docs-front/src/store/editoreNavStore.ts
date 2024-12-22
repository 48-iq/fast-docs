import { defineStore } from "pinia"
import { ref } from "vue"


export const useEditorNavStore = defineStore('editorNav', () => {
  const activeTab = ref('documents')

  const setActiveTab = (tab: string) => {
    activeTab.value = tab
  }

  return { activeTab, setActiveTab }
})
