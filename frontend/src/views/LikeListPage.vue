<template>
  <div style="padding: 20px">
    <el-input v-model="userId" placeholder="請輸入 User ID" style="width: 300px; margin-bottom: 10px;" />
    <el-button type="primary" @click="fetchList">查詢</el-button>
    <el-button type="success" @click="openAddDialog" style="margin-left: 10px;">新增喜好項目</el-button>
    <like-list-table
      :items="items"
      @edit="openEditDialog"
      @remove="handleDelete"
    />
    <like-list-dialog
      v-model:visible="dialogVisible"
      :form="currentForm"
      :is-edit="isEdit"
      @submit="handleSubmit"
    />
  </div>
</template>

<script setup>
import { ref } from 'vue'
import { ElMessage } from 'element-plus'
import { getLikeList, addLikeItem, updateLikeItem, deleteLikeItem } from '../api/likeList'
import LikeListTable from '../components/LikeListTable.vue'
import LikeListDialog from '../components/LikeListDialog.vue'

const userId = ref('')
const items = ref([])
const dialogVisible = ref(false)
const isEdit = ref(false)
const currentForm = ref({})

const fetchList = async () => {
  try {
    const { data } = await getLikeList(userId.value)
    items.value = data
  } catch (err) {
    ElMessage.error('查詢失敗')
  }
}

const openAddDialog = () => {
  currentForm.value = { userId: userId.value }
  isEdit.value = false
  dialogVisible.value = true
}

const openEditDialog = (row) => {
  currentForm.value = { ...row }
  isEdit.value = true
  dialogVisible.value = true
}

const handleSubmit = async (form) => {
  try {
    if (isEdit.value) {
      await updateLikeItem(form)
      ElMessage.success('更新成功')
    } else {
      await addLikeItem(form)
      ElMessage.success('新增成功')
    }
    dialogVisible.value = false
    fetchList()
  } catch {
    ElMessage.error(isEdit.value ? '更新失敗' : '新增失敗')
  }
}

const handleDelete = async (row) => {
  try {
    await deleteLikeItem(row)
    ElMessage.success('刪除成功')
    fetchList()
  } catch {
    ElMessage.error('刪除失敗')
  }
}
</script>