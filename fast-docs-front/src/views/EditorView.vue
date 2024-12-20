<script setup lang="ts">
import { ref, computed, onMounted } from 'vue';
import draggable from 'vuedraggable';

// Тип для Шаблонов и Документов
interface Item {
    id: number;
    name: string;
    // Поменять string на тип для элементов
    elements: string[];
}

let templates = ref<Item[]>([
    { id: 1, name: 'Шаблон 1', elements: ['Текстовое поле', 'Текстовый блок'] },
    { id: 2, name: 'Шаблон 2', elements: [] },
]);

let documents = ref<Item[]>([
    { id: 1, name: 'Документ 1', elements: [] },
    { id: 2, name: 'Документ 2', elements: ['Текстовое поле'] },
]);

// Определение текущего списка и выбранного элемента
const isTemplates = ref(true);
let currentList = computed(() => (isTemplates.value ? templates.value : documents.value));
let selectedItem = ref<Item | null>(null);

// Список доступных элементов
let availableElements = ref<string[]>(['Текстовое поле', 'Текстовый блок']);

// Методы
const showTemplates = () => {
    isTemplates.value = true;
    selectedItem.value = null;
};

const showDocuments = () => {
    isTemplates.value = false;
    selectedItem.value = null;
};

const openItem = (item: Item) => {
    selectedItem.value = { ...item }; // Копия объекта для редактирования
};

const deleteItem = (id: number) => {
    const list = isTemplates.value ? templates.value : documents.value;
    const index = list.findIndex((item) => item.id === id);
    if (index !== -1) list.splice(index, 1);
};

const addNewItem = () => {
    const list = isTemplates.value ? templates.value : documents.value;
    let id = list.reduce((acc, curr) => acc.id > curr.id ? acc : curr).id + 1;
    const newItem: Item = { id: id, name: 'Безымянный', elements: [] };

    list.push(newItem);
    openItem(newItem);
};

const saveItem = () => {
    if (!selectedItem.value) return;

    const list = isTemplates.value ? templates.value : documents.value;
    const index = list.findIndex((item) => item.id === selectedItem.value?.id);
    if (index !== -1) {
        list[index] = { ...selectedItem.value };
    }
    alert('Изменения сохранены');
};

const cancelChanges = () => {
    selectedItem.value = null;
};

const updateElements = (newElements: string[]) => {
    if (selectedItem.value) {
        selectedItem.value.elements = newElements; // Обновляем элементы
    }
};

// Реакция на перетаскивание
const onDrop = () => {
    console.log('Элемент добавлен');
};
</script>

<template>
    <div class="back">
        <!-- Шапка -->
        <div class="header">
            <button ref="templatesButton" @click="showTemplates">Шаблоны</button>
            <button @click="showDocuments">Документы</button>
        </div>

        <img id="logo" src='@/assets/favicon.png'>

        <div class="content">
            <!-- Список шаблонов или документов -->
            <div class="sidebar">
                <div class="list">
                    <h3>{{ isTemplates ? 'Все Шаблоны' : 'Все Документы' }}</h3>
                    <ul>
                        <li v-for="item in currentList" :key="item.id">
                            <span @click="openItem(item)">{{ item.name }}</span>
                            <button @click="deleteItem(item.id)">Удалить</button>
                        </li>
                    </ul>
                </div>
                <button class="add_button" @click="addNewItem">Добавить</button>
            </div>
            <div class="div_editor">
                <!-- Список элементов для перетаскивания -->
                <div class="element_picker">
                    <h3>Элементы:</h3>
                    <!-- <draggable :list="availableElements" group="elements" class="elements-picker" clone>
                    <template #item="{ element }">
                        <div>
                            {{ element }}
                        </div>
                    </template>
                </draggable> -->
                </div>

                <!-- Поле редактирования -->
                <div class="main">
                    <h2>{{ selectedItem ? selectedItem.name : 'Выберите элемент для редактирования...' }}</h2>
                    <div class="template-editor">
                        <!-- <draggable v-if="selectedItem" v-model="selectedItem.elements" class="elements-list" group="elements"
                        @end="onDrop" @update:modelValue="updateElements">
                        <template #item="{ element }">
                            <div class="template-element">
                                {{ element }}
                            </div>
                        </template>
                    </draggable> -->
                    </div>
                </div>
                <!-- Кнопка сохранения -->
                <div class="footer">
                    <button @click="saveItem" :disabled="!selectedItem">Сохранить</button>
                </div>
            </div>
        </div>


    </div>
</template>

<style scoped>
#logo {
    position: absolute;
    width: 50px;
    height: 50px;
    top: 5px;
    right: 15px;
    z-index: 1;
}

.back {
    display: flex;
    flex-direction: column;
    height: 100vh;
    width: 100vw;
}

.header {
    width: 100%;
    height: 60px;
    display: flex;
    justify-content: flex-start;
    gap: 15px;
    padding: 10px;
    padding-left: 15px;
    background-color: var(--color-blue);
    border-bottom: 1px solid var(--color-light-gray);
}

.header button {
    height: 35px;
    background: none;
    border: none;
    border-bottom: 3px solid transparent;
    font-size: 20px;
    color: var(--color-light-gray);
}

.header button:focus {
    border-bottom: 3px solid var(--color-aqua);
}

.content {
    flex: 1;
    display: flex;
    overflow: hidden;
}

.content button,
.footer button {
    border: none;
    font-size: 20px;
    padding: 5px 15px;
    border-radius: 10px;
    color: var(--color-white);
    transition: .2s linear;
    background: var(--color-aqua);
}

.content button:hover,
.footer button:hover {
    box-shadow: 0px -6px 0 var(--color-dark-aqua) inset;
}

.add_button {
    position: absolute;
    bottom: 15px;
    left: 50%;
    transform: translateX(-50%);
}

.sidebar {
    width: 25%;
    padding: 15px;
    background-color: var(--color-light-gray);
    border-right: 3px solid var(--color-white);
    position: relative;
}

.sidebar h3 {
    font-size: 25px;
    text-transform: uppercase;
    color: var(--color-blue);
    padding-bottom: 10px;
}

.div_editor {
    width: 100%;
}

.list ul {
    list-style: none;
    padding: 0;
}

.list li {
    display: flex;
    justify-content: space-between;
    padding: 5px 0;
    font-size: 20px;
    color: var(--color-dark-aqua);
}

.list li:hover {
    color: var(--color-aqua);
}

.main {
    flex: 1;
    padding: 10px;
    background-color: #fff;
    overflow: auto;
}

.main h2 {
    padding: 15px;
    padding-top: 30px;
    text-align: center;
    color: var(--color-blue);
}

.editor {
    border: 1px dashed #ccc;
    padding: 10px;
    min-height: 300px;
}

.element_picker {
    padding: 15px;
    background-color: var(--color-white);
    border-bottom: 2px dashed var(--color-light-aqua);
    border-left: 2px dashed var(--color-light-aqua);
    border-bottom-left-radius: 15px;
}

.element_picker h3 {
    color: var(--color-dark-blue);
}

.footer {
    width: -webkit-fill-available;
    position: absolute;
    bottom: 0px;
    display: flex;
    justify-content: center;
    padding: 15px;
    background-color: var(--color-light-gray);
    border-top-left-radius: 15px;
}
</style>
