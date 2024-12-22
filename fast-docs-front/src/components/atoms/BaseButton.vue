
<template>
  <button class="base-button"><slot></slot></button>
</template>
<style scoped>

.base-button {
    border: none;
    font-size: 20px;
    padding: 5px 15px;
    border-radius: 10px;
    color: var(--color-white);
    background: var(--color-aqua);
    cursor: pointer;
    border-bottom: 3px solid var(--color-aqua);
}

.base-button:active {
  border-bottom: none
}



</style>
