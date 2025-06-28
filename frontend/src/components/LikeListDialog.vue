<template>
  <el-dialog
  :model-value="visible"
  @update:modelValue="$emit('update:visible', $event)"
  :title="isEdit ? '編輯項目' : '新增項目'" 
  width="500px">
    <el-form :model="form" label-width="120px">
      <el-form-item label="User ID"><el-input v-model="form.userId" /></el-form-item>
      <el-form-item label="商品名稱"><el-input v-model="form.productName" /></el-form-item>
      <el-form-item label="價格"><el-input-number v-model="form.price" :min="0" /></el-form-item>
      <el-form-item label="手續費率"><el-input-number v-model="form.feeRate" :min="0" step="0.01" /></el-form-item>
      <el-form-item label="數量"><el-input-number v-model="form.orderAmount" :min="1" /></el-form-item>
      <el-form-item label="帳戶"><el-input v-model="form.account" /></el-form-item>
    </el-form>
    <template #footer>
      <el-button @click="visible = false">取消</el-button>
      <el-button type="primary" @click="submit">確認</el-button>
    </template>
  </el-dialog>
</template>

<script setup>
const props = defineProps(['form', 'isEdit', 'visible'])
const emit = defineEmits(['update:visible', 'submit'])

const submit = () => {
  emit('submit', props.form)
}
</script>