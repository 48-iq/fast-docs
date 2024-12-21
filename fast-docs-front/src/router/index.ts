import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import EditorView from '../views/EditorView.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: HomeView,
    },
    {
      path: '/editor/documents',
      name: 'documents',
      component: EditorView,
    },
    {
      path: '/editor/templates',
      name: 'templates',
      component: EditorView,
    },
    {
      path: '/:catchAll(.*)',
      redirect: '/',
    }
  ],
})

export default router
