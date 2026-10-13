<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="业务类型" prop="txnType">
        <el-select v-model="queryParams.txnType" placeholder="请选择业务类型" clearable>
          <el-option label="采购入库" value="采购入库" />
          <el-option label="销售出库" value="销售出库" />
          <el-option label="生产领料" value="生产领料" />
          <el-option label="生产入库" value="生产入库" />
          <el-option label="调拨" value="调拨" />
          <el-option label="其他" value="其他" />
        </el-select>
      </el-form-item>
      <el-form-item label="批次号" prop="batchNo">
        <el-input
          v-model="queryParams.batchNo"
          placeholder="请输入批次号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="方向" prop="direction">
        <el-select v-model="queryParams.direction" placeholder="请选择方向" clearable>
          <el-option label="入库" value="1" />
          <el-option label="出库" value="-1" />
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['inventory:txn:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['inventory:txn:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['inventory:txn:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['inventory:txn:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="txnList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="业务类型" align="center" prop="txnType" />
      <el-table-column label="物料ID" align="center" prop="materialId" />
      <el-table-column label="仓库ID" align="center" prop="warehouseId" />
      <el-table-column label="库位ID" align="center" prop="locationId" />
      <el-table-column label="批次号" align="center" prop="batchNo" />
      <el-table-column label="方向" align="center" prop="direction">
        <template slot-scope="scope">
          <span v-if="scope.row.direction == '1'">入库</span>
          <span v-else-if="scope.row.direction == '-1'">出库</span>
        </template>
      </el-table-column>
      <el-table-column label="数量" align="center" prop="qty" />
      <el-table-column label="单价" align="center" prop="costPrice" />
      <el-table-column label="金额" align="center" prop="amount" />
      <el-table-column label="来源单据类型" align="center" prop="sourceType" />
      <el-table-column label="来源单据号" align="center" prop="sourceCode" />
      <el-table-column label="操作人" align="center" prop="operBy" />
      <el-table-column label="业务时间" align="center" prop="txnTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.txnTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['inventory:txn:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['inventory:txn:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改库存流水对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="800px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="100px">
        <el-row>
          <el-col :span="12">
            <el-form-item label="业务类型" prop="txnType">
              <el-select v-model="form.txnType" placeholder="请选择业务类型">
                <el-option label="采购入库" value="采购入库" />
                <el-option label="销售出库" value="销售出库" />
                <el-option label="生产领料" value="生产领料" />
                <el-option label="生产入库" value="生产入库" />
                <el-option label="调拨" value="调拨" />
                <el-option label="其他" value="其他" />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="物料ID" prop="materialId">
              <el-input-number v-model="form.materialId" :min="0" controls-position="right" placeholder="请输入物料ID" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="仓库ID" prop="warehouseId">
              <el-input-number v-model="form.warehouseId" :min="0" controls-position="right" placeholder="请输入仓库ID" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="库位ID" prop="locationId">
              <el-input-number v-model="form.locationId" :min="0" controls-position="right" placeholder="请输入库位ID" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="批次号" prop="batchNo">
              <el-input v-model="form.batchNo" placeholder="请输入批次号" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="方向" prop="direction">
              <el-select v-model="form.direction" placeholder="请选择方向">
                <el-option label="入库" value="1" />
                <el-option label="出库" value="-1" />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="数量" prop="qty">
              <el-input-number v-model="form.qty" :precision="2" :min="0" controls-position="right" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="单价" prop="costPrice">
              <el-input-number v-model="form.costPrice" :precision="2" :min="0" controls-position="right" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="金额" prop="amount">
              <el-input-number v-model="form.amount" :precision="2" :min="0" controls-position="right" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="来源单据类型" prop="sourceType">
              <el-input v-model="form.sourceType" placeholder="请输入来源单据类型" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="来源单据ID" prop="sourceId">
              <el-input-number v-model="form.sourceId" :min="0" controls-position="right" placeholder="请输入来源单据ID" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="来源单据号" prop="sourceCode">
              <el-input v-model="form.sourceCode" placeholder="请输入来源单据号" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="操作人" prop="operBy">
              <el-input v-model="form.operBy" placeholder="请输入操作人" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="业务时间" prop="txnTime">
              <el-date-picker clearable
                v-model="form.txnTime"
                type="datetime"
                value-format="yyyy-MM-dd HH:mm:ss"
                placeholder="请选择业务时间">
              </el-date-picker>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listErpInventoryTxn, getErpInventoryTxn, delErpInventoryTxn, addErpInventoryTxn, updateErpInventoryTxn } from "@/api/inventory/txn"

export default {
  name: "ErpInventoryTxn",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 库存流水表格数据
      txnList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        txnType: null,
        batchNo: null,
        direction: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      }
    }
  },
  created() {
    this.getList()
  },
  methods: {
    /** 查询库存流水列表 */
    getList() {
      this.loading = true
      listErpInventoryTxn(this.queryParams).then(response => {
        this.txnList = response.rows
        this.total = response.total
        this.loading = false
      })
    },
    // 取消按钮
    cancel() {
      this.open = false
      this.reset()
    },
    // 表单重置
    reset() {
      this.form = {
        txnId: null,
        txnType: null,
        materialId: null,
        warehouseId: null,
        locationId: null,
        batchNo: null,
        direction: null,
        qty: null,
        costPrice: null,
        amount: null,
        sourceType: null,
        sourceId: null,
        sourceCode: null,
        operBy: null,
        txnTime: null
      }
      this.resetForm("form")
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1
      this.getList()
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm")
      this.handleQuery()
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.txnId)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset()
      this.open = true
      this.title = "添加库存流水"
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const txnId = row.txnId || this.ids
      getErpInventoryTxn(txnId).then(response => {
        this.form = response.data
        this.open = true
        this.title = "修改库存流水"
      })
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.txnId != null) {
            updateErpInventoryTxn(this.form).then(response => {
              this.$modal.msgSuccess("修改成功")
              this.open = false
              this.getList()
            })
          } else {
            addErpInventoryTxn(this.form).then(response => {
              this.$modal.msgSuccess("新增成功")
              this.open = false
              this.getList()
            })
          }
        }
      })
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const txnIds = row.txnId || this.ids
      this.$modal.confirm('是否确认删除库存流水编号为"' + txnIds + '"的数据项？').then(function() {
        return delErpInventoryTxn(txnIds)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess("删除成功")
      }).catch(() => {})
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('inventory/txn/export', {
        ...this.queryParams
      }, `txn_${new Date().getTime()}.xlsx`)
    }
  }
}
</script>
