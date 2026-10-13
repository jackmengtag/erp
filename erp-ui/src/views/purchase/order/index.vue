<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="88px">
      <el-form-item label="采购订单号" prop="poCode">
        <el-input
          v-model="queryParams.poCode"
          placeholder="请输入采购订单号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="供应商ID" prop="supplierId">
        <el-input-number v-model="queryParams.supplierId" placeholder="请输入供应商ID" controls-position="right" :min="0" />
      </el-form-item>
      <el-form-item label="订单日期" prop="orderDate">
        <el-date-picker clearable
          v-model="queryParams.orderDate"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择订单日期">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="状态" prop="status">
        <el-select v-model="queryParams.status" placeholder="请选择状态" clearable>
          <el-option label="草稿" value="0" />
          <el-option label="待审核" value="1" />
          <el-option label="已审核" value="2" />
          <el-option label="部分收货" value="3" />
          <el-option label="完全收货" value="4" />
          <el-option label="已关闭" value="5" />
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
          v-hasPermi="['purchase:order:add']"
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
          v-hasPermi="['purchase:order:edit']"
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
          v-hasPermi="['purchase:order:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['purchase:order:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="erpPurchaseOrderList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="采购订单ID" align="center" prop="poId" />
      <el-table-column label="采购订单号" align="center" prop="poCode" />
      <el-table-column label="供应商ID" align="center" prop="supplierId" />
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
      <el-table-column label="币种" align="center" prop="currency" />
      <el-table-column label="价税合计" align="center" prop="amountTax" />
      <el-table-column label="未税金额" align="center" prop="amountNotax" />
      <el-table-column label="三单匹配状态" align="center" prop="matchStatus" />
      <el-table-column label="状态" align="center" prop="status" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['purchase:order:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['purchase:order:remove']"
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

    <!-- 添加或修改采购订单对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="1100px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="100px">
        <el-row>
          <el-col :span="8">
            <el-form-item label="采购订单号" prop="poCode">
              <el-input v-model="form.poCode" placeholder="请输入采购订单号" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="供应商ID" prop="supplierId">
              <el-input-number v-model="form.supplierId" placeholder="请输入供应商ID" controls-position="right" :min="0" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="币种" prop="currency">
              <el-input v-model="form.currency" placeholder="请输入币种" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="订单日期" prop="orderDate">
              <el-date-picker clearable
                v-model="form.orderDate"
                type="date"
                value-format="yyyy-MM-dd"
                placeholder="请选择订单日期">
              </el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="交货日期" prop="deliveryDate">
              <el-date-picker clearable
                v-model="form.deliveryDate"
                type="date"
                value-format="yyyy-MM-dd"
                placeholder="请选择交货日期">
              </el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="三单匹配状态" prop="matchStatus">
              <el-select v-model="form.matchStatus" placeholder="请选择三单匹配状态">
                <el-option label="未匹配" value="0" />
                <el-option label="已匹配" value="1" />
                <el-option label="差异" value="2" />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="状态" prop="status">
              <el-select v-model="form.status" placeholder="请选择状态">
                <el-option label="草稿" value="0" />
                <el-option label="待审核" value="1" />
                <el-option label="已审核" value="2" />
                <el-option label="部分收货" value="3" />
                <el-option label="完全收货" value="4" />
                <el-option label="已关闭" value="5" />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="价税合计" prop="amountTax">
              <el-input-number v-model="form.amountTax" :precision="2" :controls="false" placeholder="请输入价税合计" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="未税金额" prop="amountNotax">
              <el-input-number v-model="form.amountNotax" :precision="2" :controls="false" placeholder="请输入未税金额" />
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="备注" prop="remark">
              <el-input v-model="form.remark" type="textarea" placeholder="请输入内容" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-divider content-position="center">采购订单明细信息</el-divider>
        <el-row :gutter="10" class="mb8">
          <el-col :span="1.5">
            <el-button type="primary" icon="el-icon-plus" size="mini" @click="handleAddErpPurchaseOrderLine">添加</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button type="danger" icon="el-icon-delete" size="mini" @click="handleDeleteErpPurchaseOrderLine">删除</el-button>
          </el-col>
        </el-row>
        <el-table :data="erpPurchaseOrderLineList" :row-class-name="rowErpPurchaseOrderLineIndex" @selection-change="handleErpPurchaseOrderLineSelectionChange" ref="erpPurchaseOrderLine">
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
          <el-table-column label="订购数量" prop="qty" width="150">
            <template slot-scope="scope">
              <el-input-number v-model="scope.row.qty" :precision="4" :controls="false" placeholder="请输入订购数量" />
            </template>
          </el-table-column>
          <el-table-column label="已收货数量" prop="receivedQty" width="150">
            <template slot-scope="scope">
              <el-input-number v-model="scope.row.receivedQty" :precision="4" :controls="false" placeholder="请输入已收货数量" />
            </template>
          </el-table-column>
          <el-table-column label="单价" prop="price" width="150">
            <template slot-scope="scope">
              <el-input-number v-model="scope.row.price" :precision="6" :controls="false" placeholder="请输入单价" />
            </template>
          </el-table-column>
          <el-table-column label="税率(%)" prop="taxRate" width="150">
            <template slot-scope="scope">
              <el-input-number v-model="scope.row.taxRate" :precision="4" :controls="false" placeholder="请输入税率(%)" />
            </template>
          </el-table-column>
          <el-table-column label="金额" prop="amount" width="150">
            <template slot-scope="scope">
              <el-input-number v-model="scope.row.amount" :precision="2" :controls="false" placeholder="请输入金额" />
            </template>
          </el-table-column>
          <el-table-column label="交期" prop="deliveryDate" width="240">
            <template slot-scope="scope">
              <el-date-picker clearable v-model="scope.row.deliveryDate" type="date" value-format="yyyy-MM-dd" placeholder="请选择交期" />
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
import { listErpPurchaseOrder, getErpPurchaseOrder, delErpPurchaseOrder, addErpPurchaseOrder, updateErpPurchaseOrder } from "@/api/purchase/order"

export default {
  name: "ErpPurchaseOrder",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 子表选中数据
      checkedErpPurchaseOrderLine: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 采购订单表格数据
      erpPurchaseOrderList: [],
      // 采购订单明细表格数据
      erpPurchaseOrderLineList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        poCode: null,
        supplierId: null,
        orderDate: null,
        status: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        poCode: [
          { required: true, message: "采购订单号不能为空", trigger: "blur" }
        ],
        supplierId: [
          { required: true, message: "供应商ID不能为空", trigger: "blur" }
        ]
      }
    }
  },
  created() {
    this.getList()
  },
  methods: {
    /** 查询采购订单列表 */
    getList() {
      this.loading = true
      listErpPurchaseOrder(this.queryParams).then(response => {
        this.erpPurchaseOrderList = response.rows
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
        poId: null,
        poCode: null,
        supplierId: null,
        orderDate: null,
        deliveryDate: null,
        currency: null,
        amountTax: null,
        amountNotax: null,
        matchStatus: null,
        status: null,
        remark: null
      }
      this.erpPurchaseOrderLineList = []
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
      this.ids = selection.map(item => item.poId)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset()
      this.open = true
      this.title = "添加采购订单"
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const poId = row.poId || this.ids
      getErpPurchaseOrder(poId).then(response => {
        this.form = response.data
        this.erpPurchaseOrderLineList = response.data.erpPurchaseOrderLineList
        this.open = true
        this.title = "修改采购订单"
      })
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          this.form.erpPurchaseOrderLineList = this.erpPurchaseOrderLineList
          if (this.form.poId != null) {
            updateErpPurchaseOrder(this.form).then(response => {
              this.$modal.msgSuccess("修改成功")
              this.open = false
              this.getList()
            })
          } else {
            addErpPurchaseOrder(this.form).then(response => {
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
      const poIds = row.poId || this.ids
      this.$modal.confirm('是否确认删除采购订单编号为"' + poIds + '"的数据项？').then(function() {
        return delErpPurchaseOrder(poIds)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess("删除成功")
      }).catch(() => {})
    },
    /** 采购订单明细序号 */
    rowErpPurchaseOrderLineIndex({ row, rowIndex }) {
      row.index = rowIndex + 1
    },
    /** 采购订单明细添加按钮操作 */
    handleAddErpPurchaseOrderLine() {
      let obj = {}
      obj.lineNo = ""
      obj.materialId = ""
      obj.qty = ""
      obj.receivedQty = ""
      obj.price = ""
      obj.taxRate = ""
      obj.amount = ""
      obj.deliveryDate = ""
      this.erpPurchaseOrderLineList.push(obj)
    },
    /** 采购订单明细删除按钮操作 */
    handleDeleteErpPurchaseOrderLine() {
      if (this.checkedErpPurchaseOrderLine.length == 0) {
        this.$modal.msgError("请先选择要删除的采购订单明细数据")
      } else {
        const erpPurchaseOrderLineList = this.erpPurchaseOrderLineList
        const checkedErpPurchaseOrderLine = this.checkedErpPurchaseOrderLine
        this.erpPurchaseOrderLineList = erpPurchaseOrderLineList.filter(function(item) {
          return checkedErpPurchaseOrderLine.indexOf(item.index) == -1
        })
      }
    },
    /** 复选框选中数据 */
    handleErpPurchaseOrderLineSelectionChange(selection) {
      this.checkedErpPurchaseOrderLine = selection.map(item => item.index)
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('purchase/order/export', {
        ...this.queryParams
      }, `order_${new Date().getTime()}.xlsx`)
    }
  }
}
</script>
