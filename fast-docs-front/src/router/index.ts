import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import EditorView from '../views/EditorView.vue'
import FixedEditorView from '@/views/FixedEditorView.vue'
import TemplateView from '@/views/TemplateView.vue'
import CreateDocumentView from '@/views/DocumentView.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: HomeView,
    },
    {
      path: '/e',
      name: 'documents',
      component: EditorView,
    },
    {
      path: '/e',
      name: 'templates',
      component: EditorView,
    },
    {
      path:'/editor',
      name:'fixed-view',
      component: FixedEditorView,
      children: [
        {
          path: 'templates/:id?',
          props: true,
          name: 'create-template',
          component: TemplateView
        },
        {
          path: 'documents/:templateId/:id?',
          name: 'create-document',
          props: true,
          component: CreateDocumentView
        }
      ]
    },
    {
      path: '/:catchAll(.*)',
      redirect: '/',
    },
  ],
})

export default router
