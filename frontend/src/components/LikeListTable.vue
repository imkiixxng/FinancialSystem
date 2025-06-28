<template>
  <el-table :data="items" style="margin-top: 20px;">
    <el-table-column prop="productName" label="商品名稱" />
    <el-table-column prop="price" label="價格" />
    <el-table-column prop="feeRate" label="手續費率" />
    <el-table-column prop="orderAmount" label="數量" />
    <el-table-column prop="account" label="帳戶" />
    <el-table-column label="操作">
      <template #default="scope">
        <el-button size="small" @click="$emit('edit', scope.row)">編輯</el-button>
        <el-button size="small" type="danger" @click="$emit('remove', scope.row)">刪除</el-button>
      </template>
    </el-table-column>
  </el-table>
</template>

<script setup>
defineProps(['items'])
defineEmits(['edit', 'remove'])
</script>