<template>
  <div v-hasPermi="['bi:dashboard:list']" class="app-container">
    <el-row :gutter="20">
      <el-col :span="6" v-for="item in metrics" :key="item.key" class="metric-col">
        <el-card shadow="hover">
          <div class="metric-title">{{ item.label }}</div>
          <div class="metric-value">{{ item.value }}</div>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import { getDashboard } from "@/api/bi/bi"

export default {
  name: "BiDashboard",
  data() {
    return {
      // 指标卡片数据
      metrics: []
    }
  },
  created() {
    this.getData()
  },
  methods: {
    /** 查询经营驾驶舱核心指标 */
    getData() {
      getDashboard().then(response => {
        const data = response.data || {}
        this.metrics = [
          { key: "salesOrderCount", label: "销售订单数", value: data.salesOrderCount },
          { key: "salesAmount", label: "销售金额", value: data.salesAmount },
          { key: "purchaseOrderCount", label: "采购订单数", value: data.purchaseOrderCount },
          { key: "purchaseAmount", label: "采购金额", value: data.purchaseAmount },
          { key: "inventoryQty", label: "库存数量", value: data.inventoryQty },
          { key: "inventoryAmount", label: "库存金额", value: data.inventoryAmount },
          { key: "productionOrderCount", label: "生产工单数", value: data.productionOrderCount },
          { key: "arAmount", label: "应收余额", value: data.arAmount },
          { key: "apAmount", label: "应付余额", value: data.apAmount }
        ]
      })
    }
  }
}
</script>

<style scoped>
.metric-col {
  margin-bottom: 20px;
}
.metric-title {
  font-size: 14px;
  color: #909399;
}
.metric-value {
  margin-top: 10px;
  font-size: 24px;
  font-weight: bold;
  color: #303133;
}
</style>
