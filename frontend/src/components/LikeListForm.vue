<template>
  <el-form :model="form" label-width="120px">
    <el-form-item label="User ID">
      <el-input v-model="form.userId" />
    </el-form-item>
    <el-form-item label="商品名稱">
      <el-input v-model="form.productName" />
    </el-form-item>
    <el-form-item label="價格">
      <el-input v-model.number="form.price" />
    </el-form-item>
    <el-form-item label="手續費率">
      <el-input v-model.number="form.feeRate" />
    </el-form-item>
    <el-form-item label="數量">
      <el-input v-model.number="form.orderAmount" />
    </el-form-item>
    <el-form-item label="帳號">
      <el-input v-model="form.account" />
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="submit">新增</el-button>
    </el-form-item>
  </el-form>
</template>

<script setup>
import { reactive } from 'vue'
import { addLikeItem } from '../api/likeList'
import { ElMessage } from 'element-plus'

const form = reactive({
  userId: '',
  productName: '',
  price: 0,
  feeRate: 0,
  orderAmount: 0,
  account: ''
})

const submit = async () => {
  try {
    await addLikeItem(form)
    ElMessage.success('新增成功')
  } catch (e) {
    ElMessage.error('新增失敗')
  }
}
</script>
