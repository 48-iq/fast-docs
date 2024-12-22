<!-- npm install vuedraggable@next -->

<script setup lang="ts">
import { ref, computed, onMounted, watch } from 'vue';
import { useRoute, useRouter } from 'vue-router';
import draggable from 'vuedraggable';

// Тип для элементов
interface Element {
    id: number;
    name: string;
    value?: string;
    order?: number | null;
}

// Список доступных элементов
const availableElements = ref<Element[]>([
    { id: 1, name: 'Текстовое поле', value: "BE!" },
    { id: 2, name: 'Текстовый блок', value: "MEW!" },
]);

// Тип для Шаблонов и Документов
interface Item {
    id: number;
    name: string;
    elements: Element[];
}

// Список всех Шаблонов
let templates = ref<Item[]>([
    { id: 1, name: 'Шаблон 1', elements: [availableElements.value[0], availableElements.value[1]] },
    { id: 2, name: 'Шаблон 2', elements: [availableElements.value[0]] },
]);

// Список всех Документов
let documents = ref<Item[]>([
    { id: 1, name: 'Документ 1', elements: [availableElements.value[1], availableElements.value[0], availableElements.value[1]] },
    { id: 2, name: 'Документ 2', elements: [availableElements.value[0], availableElements.value[1]] },
]);

// Определение текущего списка и выбранного элемента
const route = useRoute();
const isTemplates = ref(route.path === '/editor/templates');
let currentList = computed(() => (isTemplates.value ? templates.value : documents.value));
let selectedItem = ref<Item | null>(null);

// Методы
const router = useRouter();

// Смена списка на Шаблоны
const showTemplates = () => {
    isTemplates.value = true;
    selectedItem.value = null;
    router.push('/editor/templates');
};

// Смена списка на Документы
const showDocuments = () => {
    isTemplates.value = false;
    selectedItem.value = null;
    router.push('/editor/documents');
};

// Выбор элемента для окна редактора
const openItem = (item: Item) => {
    selectedItem.value = { ...item }; // Копируем
};

// Добавление элемента в список
const addNewItem = () => {
    const list = isTemplates.value ? templates.value : documents.value;
    let id = list.reduce((acc, curr) => acc.id > curr.id ? acc : curr).id + 1;
    const newItem: Item = { id: id, name: `Безымянный ${id}`, elements: [] };

    list.push(newItem);
    openItem(newItem);
};

// Удаление элемента из списка
let showDeleteConfirmation = ref(false);
let idItemToDelete = ref<number | null>(null);

const confirmDelete = (id: number) => {
    idItemToDelete.value = id;
    showDeleteConfirmation.value = true;
};

const deleteItemConfirmed = () => {
    if (idItemToDelete.value !== null) {
        const list = isTemplates.value ? templates.value : documents.value;
        const index = list.findIndex((item) => item.id === idItemToDelete.value);
        if (index !== -1) list.splice(index, 1);
        idItemToDelete.value = null;
    }
    selectedItem.value = null;
    showDeleteConfirmation.value = false;
};

const cancelDelete = () => {
    idItemToDelete.value = null;
    showDeleteConfirmation.value = false;
};

// Сохранение элемента, существующего в списке
let showSaveConfirmation = ref(false);
let newItemName = ref('Новый');

const confirmSave = () => {
    showSaveConfirmation.value = true;
};

const saveItemConfirmed = () => {
    if (!selectedItem.value) return;

    const list = isTemplates.value ? templates.value : documents.value;
    const index = list.findIndex((item) => item.id === selectedItem.value?.id);
    if (index !== -1) {
        list[index] = { ...selectedItem.value, name: newItemName.value };
    }
    selectedItem.value = list[index];
    showSaveConfirmation.value = false;
};

const cancelSave = () => {
    showSaveConfirmation.value = false;
};

// Функции для элементов
const cloneElement = (element: Element) => {
    return { ...element };
};

const updateElements = (newElements: Element[]) => {
    if (selectedItem.value) {
        selectedItem.value.elements = newElements; // Обновляем элементы
    }
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
                    <h3>{{ isTemplates ? 'Список шаблонов' : 'Список документов' }}</h3>
                    <ul>
                        <li v-for="item in currentList" :key="item.id">
                            <span @click="openItem(item)">{{ item.name }}</span>
                            <button @click="confirmDelete(item.id)">Удалить</button>
                        </li>
                    </ul>
                </div>
                <button class="add_button" @click="addNewItem">Добавить</button>
            </div>
            <div class="div_editor">
                <!-- Список элементов для перетаскивания -->
                <div class="element_picker">
                    <h3>Элементы:</h3>
                    <draggable v-if="availableElements" v-model="availableElements" group="elements" :animation="300"
                        class="list_elements" @clone="cloneElement">
                        <template #item="{ element }">
                            <div class="element">
                                {{ element.name }}
                            </div>
                        </template>
                    </draggable>
                </div>

                <!-- Поле редактирования -->
                <div class="main">
                    <h2>{{ selectedItem ? selectedItem.name : 'Выберите элемент для редактирования...' }}</h2>
                    <div class="template_editor">
                        <draggable v-if="selectedItem" v-model="selectedItem.elements" group="itemElements" :animation="300"
                            class="list_elements" @update:modelValue="updateElements">
                            <template #item="{ element }">
                                <div class="element">
                                    {{ element.name }}
                                </div>
                            </template>
                        </draggable>
                    </div>
                </div>
                <!-- Кнопка сохранения -->
                <div class="footer">
                    <button @click="confirmSave" :disabled="!selectedItem">Сохранить</button>
                </div>
            </div>
        </div>
    </div>

    <!-- Окно для подтверждения удаления -->
    <div class="popup" v-if="showDeleteConfirmation">
        <form @submit.prevent>
            <p>Вы уверены, что хотите удалить этот файл?</p>
            <input type="button" value="Да" @click="deleteItemConfirmed" />
            <input type="button" value="Нет" @click="cancelDelete" />
        </form>
    </div>

    <!-- Окно для подтверждения сохранения -->
    <div class="popup" v-if="showSaveConfirmation">
        <form @submit.prevent>
            <p>Введите имя для сохранения:</p>
            <input type="text" v-model="newItemName" />
            <input type="button" value="Сохранить" @click="saveItemConfirmed" />
            <input type="button" value="Отмена" @click="cancelSave" />
        </form>
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

.list_elements {
    min-height: 50px;
    border: 1px solid #ccc;
    padding: 10px;
    margin-bottom: 10px;
}

.element {
    background-color: #f0f0f0;
    padding: 5px;
    margin: 5px 0;
    border: 1px solid #ccc;
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

.popup {
    position: absolute;
    opacity: 1;
    z-index: 2;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    width: 350px;
    height: 200px;
    padding: 35px;
    background-color: var(--color-light-aqua);
    box-shadow: 0 5px 10px rgb(70 74 77 / 20%);
    border: none;
    border-radius: 50px;
    text-align: center;
    font-size: 25px;
    color: var(--color-blue);
}

.popup input {
    margin: 5px;
    border: none;
    font-size: 18px;
    padding: 5px 15px;
    border-radius: 10px;
    color: var(--color-dark-aqua);
    transition: .2s linear;
    outline: none;
}

.popup input:focus {
    border: none;
}

.popup input[type="submit"] {
    width: 125px;
    background: var(--color-aqua);
    color: var(--color-white);
}

.popup input[type="submit"]:hover {
    box-shadow: 0px -6px 0 var(--color-dark-aqua) inset;
}
</style>
