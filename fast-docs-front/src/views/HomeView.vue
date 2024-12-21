<script setup lang="ts">
import { useRouter } from 'vue-router';
const router = useRouter();
const navigateToTemplates = () => {
  router.push('/editor/templates');
};
const navigateToDocuments = () => {
  router.push('/editor/documents');
};
</script>

<template>
  <div id="div_background">
    <div id="div_main">
      <div id="div_logo">
        <div id="img_logo"></div>
        <h1>FastDocs</h1>
      </div>
      <p>Выберите, что хотите создать.</p>
      <div id="div_button">
        <button @click="navigateToTemplates">Шаблон</button>
        <button @click="navigateToDocuments">Документ</button>
      </div>
    </div>
  </div>
</template>

<style scoped>
#div_background {
  width: 100vw;
  height: 100vh;
  background: rgb(4, 41, 64);
  background: linear-gradient(350deg, rgba(4, 41, 64, 1) 0%, rgba(53, 128, 134, 1) 55%, rgba(150, 231, 230, 1) 100%);
  display: flex;
  justify-content: center;
  align-items: center;
}

#div_logo {
  display: flex;
  margin: 0 auto;
  gap: 10px;
}

#img_logo {
  width: 85px;
  height: 85px;
  background-image: url('@/assets/logo.png');
  background-size: cover;
  background-position: center;
}

#div_main {
  width: 50%;
  height: 45%;
  background: var(--color-light-gray);
  border-radius: 50px;
  text-align: center;
  display: flex;
  flex-direction: column;
  justify-content: center;
}

#div_main h1 {
  margin-top: -25px;
  font-size: 105px;
  color: var(--color-blue);
  font-weight: 800;
}

#div_main p {
  margin-top: -10px;
  font-size: 22px;
  color: var(--color-dark-aqua);
}

#div_button {
  justify-content: center;
  display: flex;
  gap: 30px;
  padding-top: 25px;
}

#div_button button {
  border: 0;
  border-radius: 15px;
  outline: none;
  padding: 12px 18px;
  background: var(--color-aqua);
  color: var(--color-light-gray);
  font-size: 31px;
  font-weight: 700;
  cursor: pointer;
  transition: transform 600ms ease;
}

#div_button button:hover {
  transform: translateY(-6px);
}
</style>
