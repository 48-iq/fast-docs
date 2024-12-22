
<template>
  <button class="nav-button"><slot></slot></button>
</template>

<style scoped>
.nav-button {
  height: 100%;
  background: none;
  border: none;
  font-size: 20px;
  color: var(--color-light-gray);
  cursor: pointer;
  transition: opacity 0.3s;
  width: 100%;
}

.nav-button:hover {
  opacity: 0.7;
}
</style>
