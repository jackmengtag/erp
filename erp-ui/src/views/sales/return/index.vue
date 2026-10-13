<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="88px">
      <el-form-item label="退货单号" prop="returnCode">
        <el-input
          v-model="queryParams.returnCode"
          placeholder="请输入退货单号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="客户ID" prop="customerId">
        <el-input-number v-model="queryParams.customerId" placeholder="请输入客户ID" controls-position="right" :min="0" />
      </el-form-item>
      <el-form-item label="退货日期" prop="returnDate">
        <el-date-picker clearable
          v-model="queryParams.returnDate"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择退货日期">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="状态" prop="status">
        <el-select v-model="queryParams.status" placeholder="请选择状态" clearable>
          <el-option label="草稿" value="0" />
          <el-option label="待审核" value="1" />
          <el-option label="已审核" value="2" />
          <el-option label="已入库" value="3" />
          <el-option label="已关闭" value="4" />
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
          v-hasPermi="['sales:return:add']"
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
          v-hasPermi="['sales:return:edit']"
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
          v-hasPermi="['sales:return:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['sales:return:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="erpSalesReturnList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="退货单ID" align="center" prop="returnId" />
      <el-table-column label="退货单号" align="center" prop="returnCode" />
      <el-table-column label="客户ID" align="center" prop="customerId" />
      <el-table-column label="原订单ID" align="center" prop="orderId" />
      <el-table-column label="退货仓库ID" align="center" prop="warehouseId" />
      <el-table-column label="退货日期" align="center" prop="returnDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.returnDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="退货原因" align="center" prop="returnReason" />
      <el-table-column label="状态" align="center" prop="status" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['sales:return:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['sales:return:remove']"
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

    <!-- 添加或修改销售退货单对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="1100px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="100px">
        <el-row>
          <el-col :span="8">
            <el-form-item label="退货单号" prop="returnCode">
              <el-input v-model="form.returnCode" placeholder="请输入退货单号" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="客户ID" prop="customerId">
              <el-input-number v-model="form.customerId" placeholder="请输入客户ID" controls-position="right" :min="0" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="原订单ID" prop="orderId">
              <el-input-number v-model="form.orderId" placeholder="请输入原订单ID" controls-position="right" :min="0" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="退货仓库ID" prop="warehouseId">
              <el-input-number v-model="form.warehouseId" placeholder="请输入退货仓库ID" controls-position="right" :min="0" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="退货日期" prop="returnDate">
              <el-date-picker clearable
                v-model="form.returnDate"
                type="date"
                value-format="yyyy-MM-dd"
                placeholder="请选择退货日期">
              </el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="退货原因" prop="returnReason">
              <el-input v-model="form.returnReason" placeholder="请输入退货原因" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="状态" prop="status">
              <el-select v-model="form.status" placeholder="请选择状态">
                <el-option label="草稿" value="0" />
                <el-option label="待审核" value="1" />
                <el-option label="已审核" value="2" />
                <el-option label="已入库" value="3" />
                <el-option label="已关闭" value="4" />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="备注" prop="remark">
              <el-input v-model="form.remark" type="textarea" placeholder="请输入内容" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-divider content-position="center">销售退货单明细信息</el-divider>
        <el-row :gutter="10" class="mb8">
          <el-col :span="1.5">
            <el-button type="primary" icon="el-icon-plus" size="mini" @click="handleAddErpSalesReturnLine">添加</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button type="danger" icon="el-icon-delete" size="mini" @click="handleDeleteErpSalesReturnLine">删除</el-button>
          </el-col>
        </el-row>
        <el-table :data="erpSalesReturnLineList" :row-class-name="rowErpSalesReturnLineIndex" @selection-change="handleErpSalesReturnLineSelectionChange" ref="erpSalesReturnLine">
          <el-table-column type="selection" width="50" align="center" />
          <el-table-column label="序号" align="center" prop="index" width="50"/>
          <el-table-column label="行号" prop="lineNo" width="150">
            <template slot-scope="scope">
              <el-input-number v-model="scope.row.lineNo" controls-position="right" :min="0" />
            </template>
          </el-table-column>
          <el-table-column label="物料ID" prop="materialId" width="150">
            <template slot-scope="scope">
              <el-input-number v-model="scope.row.materialId" controls-position="right" :min="0" />
            </template>
          </el-table-column>
          <el-table-column label="批次号" prop="batchNo" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.batchNo" placeholder="请输入批次号" />
            </template>
          </el-table-column>
          <el-table-column label="退货数量" prop="qty" width="150">
            <template slot-scope="scope">
              <el-input-number v-model="scope.row.qty" :precision="4" :controls="false" placeholder="请输入退货数量" />
            </template>
          </el-table-column>
        </el-table>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listErpSalesReturn, getErpSalesReturn, delErpSalesReturn, addErpSalesReturn, updateErpSalesReturn } from "@/api/sales/return"

export default {
  name: "ErpSalesReturn",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 子表选中数据
      checkedErpSalesReturnLine: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 销售退货单表格数据
      erpSalesReturnList: [],
      // 销售退货单明细表格数据
      erpSalesReturnLineList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        returnCode: null,
        customerId: null,
        returnDate: null,
        status: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        returnCode: [
          { required: true, message: "退货单号不能为空", trigger: "blur" }
        ],
        customerId: [
          { required: true, message: "客户ID不能为空", trigger: "blur" }
        ]
      }
    }
  },
  created() {
    this.getList()
  },
  methods: {
    /** 查询销售退货单列表 */
    getList() {
      this.loading = true
      listErpSalesReturn(this.queryParams).then(response => {
        this.erpSalesReturnList = response.rows
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
        returnId: null,
        returnCode: null,
        customerId: null,
        orderId: null,
        warehouseId: null,
        returnDate: null,
        returnReason: null,
        status: null,
        remark: null
      }
      this.erpSalesReturnLineList = []
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
      this.ids = selection.map(item => item.returnId)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset()
      this.open = true
      this.title = "添加销售退货单"
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const returnId = row.returnId || this.ids
      getErpSalesReturn(returnId).then(response => {
        this.form = response.data
        this.erpSalesReturnLineList = response.data.erpSalesReturnLineList
        this.open = true
        this.title = "修改销售退货单"
      })
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          this.form.erpSalesReturnLineList = this.erpSalesReturnLineList
          if (this.form.returnId != null) {
            updateErpSalesReturn(this.form).then(response => {
              this.$modal.msgSuccess("修改成功")
              this.open = false
              this.getList()
            })
          } else {
            addErpSalesReturn(this.form).then(response => {
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
      const returnIds = row.returnId || this.ids
      this.$modal.confirm('是否确认删除销售退货单编号为"' + returnIds + '"的数据项？').then(function() {
        return delErpSalesReturn(returnIds)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess("删除成功")
      }).catch(() => {})
    },
    /** 销售退货单明细序号 */
    rowErpSalesReturnLineIndex({ row, rowIndex }) {
      row.index = rowIndex + 1
    },
    /** 销售退货单明细添加按钮操作 */
    handleAddErpSalesReturnLine() {
      let obj = {}
      obj.lineNo = ""
      obj.materialId = ""
      obj.batchNo = ""
      obj.qty = ""
      this.erpSalesReturnLineList.push(obj)
    },
    /** 销售退货单明细删除按钮操作 */
    handleDeleteErpSalesReturnLine() {
      if (this.checkedErpSalesReturnLine.length == 0) {
        this.$modal.msgError("请先选择要删除的销售退货单明细数据")
      } else {
        const erpSalesReturnLineList = this.erpSalesReturnLineList
        const checkedErpSalesReturnLine = this.checkedErpSalesReturnLine
        this.erpSalesReturnLineList = erpSalesReturnLineList.filter(function(item) {
          return checkedErpSalesReturnLine.indexOf(item.index) == -1
        })
      }
    },
    /** 复选框选中数据 */
    handleErpSalesReturnLineSelectionChange(selection) {
      this.checkedErpSalesReturnLine = selection.map(item => item.index)
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('sales/return/export', {
        ...this.queryParams
      }, `return_${new Date().getTime()}.xlsx`)
    }
  }
}
</script>
