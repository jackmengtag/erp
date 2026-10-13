<template>
  <div v-hasPermi="['bi:orderDelivery:list']" class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="88px">
      <el-form-item label="客户ID" prop="customerId">
        <el-input-number v-model="queryParams.customerId" :min="0" placeholder="请输入客户ID" />
      </el-form-item>
      <el-form-item label="订单状态" prop="status">
        <el-input
          v-model="queryParams.status"
          placeholder="请输入订单状态"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="orderDeliveryList">
      <el-table-column label="订单编号" align="center" prop="orderCode" />
      <el-table-column label="客户名称" align="center" prop="customerName" />
      <el-table-column label="订单日期" align="center" prop="orderDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.orderDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="交货日期" align="center" prop="deliveryDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.deliveryDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="价税合计" align="center" prop="amountTax" />
      <el-table-column label="状态" align="center" prop="status" />
      <el-table-column label="订单数量" align="center" prop="orderQty" />
      <el-table-column label="已交付数量" align="center" prop="deliveredQty" />
    </el-table>
  </div>
</template>

<script>
import { listOrderDelivery } from "@/api/bi/bi"

export default {
  name: "BiOrderDelivery",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 显示搜索条件
      showSearch: true,
      // 订单交付表格数据
      orderDeliveryList: [],
      // 查询参数
      queryParams: {
        customerId: null,
        status: null
      }
    }
  },
  created() {
    this.getList()
  },
  methods: {
    /** 查询订单交付报表 */
    getList() {
      this.loading = true
      listOrderDelivery(this.queryParams).then(response => {
        this.orderDeliveryList = response.data
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
