<template>
  <div v-hasPermi="['bi:inventoryAge:list']" class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="88px">
      <el-form-item label="仓库ID" prop="warehouseId">
        <el-input-number v-model="queryParams.warehouseId" :min="0" placeholder="请输入仓库ID" />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="inventoryAgeList">
      <el-table-column label="物料编码" align="center" prop="materialCode" />
      <el-table-column label="物料名称" align="center" prop="materialName" />
      <el-table-column label="仓库名称" align="center" prop="warehouseName" />
      <el-table-column label="批次号" align="center" prop="batchNo" />
      <el-table-column label="库存数量" align="center" prop="stockQty" />
      <el-table-column label="成本单价" align="center" prop="costPrice" />
      <el-table-column label="库存金额" align="center" prop="amount" />
      <el-table-column label="库龄(天)" align="center" prop="ageDays" />
    </el-table>
  </div>
</template>

<script>
import { listInventoryAge } from "@/api/bi/bi"

export default {
  name: "BiInventoryAge",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 显示搜索条件
      showSearch: true,
      // 库存库龄表格数据
      inventoryAgeList: [],
      // 查询参数
      queryParams: {
        warehouseId: null
      }
    }
  },
  created() {
    this.getList()
  },
  methods: {
    /** 查询库存库龄报表 */
    getList() {
      this.loading = true
      listInventoryAge(this.queryParams).then(response => {
        this.inventoryAgeList = response.data
        this.loading = false
      })
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.getList()
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm")
      this.handleQuery()
    }
  }
}
</script>
