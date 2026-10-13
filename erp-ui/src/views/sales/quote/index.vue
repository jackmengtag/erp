<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="88px">
      <el-form-item label="报价单号" prop="quoteCode">
        <el-input
          v-model="queryParams.quoteCode"
          placeholder="请输入报价单号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="客户ID" prop="customerId">
        <el-input-number v-model="queryParams.customerId" placeholder="请输入客户ID" controls-position="right" :min="0" />
      </el-form-item>
      <el-form-item label="报价日期" prop="quoteDate">
        <el-date-picker clearable
          v-model="queryParams.quoteDate"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择报价日期">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="状态" prop="status">
        <el-select v-model="queryParams.status" placeholder="请选择状态" clearable>
          <el-option label="草稿" value="0" />
          <el-option label="待审核" value="1" />
          <el-option label="已审核" value="2" />
          <el-option label="已转订单" value="3" />
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
          v-hasPermi="['sales:quote:add']"
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
          v-hasPermi="['sales:quote:edit']"
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
          v-hasPermi="['sales:quote:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['sales:quote:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="erpSalesQuoteList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="报价单ID" align="center" prop="quoteId" />
      <el-table-column label="报价单号" align="center" prop="quoteCode" />
      <el-table-column label="客户ID" align="center" prop="customerId" />
      <el-table-column label="报价日期" align="center" prop="quoteDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.quoteDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="有效期至" align="center" prop="validDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.validDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="币种" align="center" prop="currency" />
      <el-table-column label="价税合计" align="center" prop="amountTax" />
      <el-table-column label="未税金额" align="center" prop="amountNotax" />
      <el-table-column label="状态" align="center" prop="status" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['sales:quote:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['sales:quote:remove']"
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

    <!-- 添加或修改销售报价单对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="1100px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="100px">
        <el-row>
          <el-col :span="8">
            <el-form-item label="报价单号" prop="quoteCode">
              <el-input v-model="form.quoteCode" placeholder="请输入报价单号" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="客户ID" prop="customerId">
              <el-input-number v-model="form.customerId" placeholder="请输入客户ID" controls-position="right" :min="0" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="币种" prop="currency">
              <el-input v-model="form.currency" placeholder="请输入币种" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="报价日期" prop="quoteDate">
              <el-date-picker clearable
                v-model="form.quoteDate"
                type="date"
                value-format="yyyy-MM-dd"
                placeholder="请选择报价日期">
              </el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="有效期至" prop="validDate">
              <el-date-picker clearable
                v-model="form.validDate"
                type="date"
                value-format="yyyy-MM-dd"
                placeholder="请选择有效期至">
              </el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="状态" prop="status">
              <el-select v-model="form.status" placeholder="请选择状态">
                <el-option label="草稿" value="0" />
                <el-option label="待审核" value="1" />
                <el-option label="已审核" value="2" />
                <el-option label="已转订单" value="3" />
                <el-option label="已关闭" value="4" />
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
        <el-divider content-position="center">销售报价单明细信息</el-divider>
        <el-row :gutter="10" class="mb8">
          <el-col :span="1.5">
            <el-button type="primary" icon="el-icon-plus" size="mini" @click="handleAddErpSalesQuoteLine">添加</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button type="danger" icon="el-icon-delete" size="mini" @click="handleDeleteErpSalesQuoteLine">删除</el-button>
          </el-col>
        </el-row>
        <el-table :data="erpSalesQuoteLineList" :row-class-name="rowErpSalesQuoteLineIndex" @selection-change="handleErpSalesQuoteLineSelectionChange" ref="erpSalesQuoteLine">
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
          <el-table-column label="数量" prop="qty" width="150">
            <template slot-scope="scope">
              <el-input-number v-model="scope.row.qty" :precision="4" :controls="false" placeholder="请输入数量" />
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
import { listErpSalesQuote, getErpSalesQuote, delErpSalesQuote, addErpSalesQuote, updateErpSalesQuote } from "@/api/sales/quote"

export default {
  name: "ErpSalesQuote",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 子表选中数据
      checkedErpSalesQuoteLine: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 销售报价单表格数据
      erpSalesQuoteList: [],
      // 销售报价单明细表格数据
      erpSalesQuoteLineList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        quoteCode: null,
        customerId: null,
        quoteDate: null,
        status: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        quoteCode: [
          { required: true, message: "报价单号不能为空", trigger: "blur" }
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
    /** 查询销售报价单列表 */
    getList() {
      this.loading = true
      listErpSalesQuote(this.queryParams).then(response => {
        this.erpSalesQuoteList = response.rows
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
        quoteId: null,
        quoteCode: null,
        customerId: null,
        quoteDate: null,
        validDate: null,
        currency: null,
        amountTax: null,
        amountNotax: null,
        status: null,
        remark: null
      }
      this.erpSalesQuoteLineList = []
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
      this.ids = selection.map(item => item.quoteId)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset()
      this.open = true
      this.title = "添加销售报价单"
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const quoteId = row.quoteId || this.ids
      getErpSalesQuote(quoteId).then(response => {
        this.form = response.data
        this.erpSalesQuoteLineList = response.data.erpSalesQuoteLineList
        this.open = true
        this.title = "修改销售报价单"
      })
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          this.form.erpSalesQuoteLineList = this.erpSalesQuoteLineList
          if (this.form.quoteId != null) {
            updateErpSalesQuote(this.form).then(response => {
              this.$modal.msgSuccess("修改成功")
              this.open = false
              this.getList()
            })
          } else {
            addErpSalesQuote(this.form).then(response => {
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
      const quoteIds = row.quoteId || this.ids
      this.$modal.confirm('是否确认删除销售报价单编号为"' + quoteIds + '"的数据项？').then(function() {
        return delErpSalesQuote(quoteIds)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess("删除成功")
      }).catch(() => {})
    },
    /** 销售报价单明细序号 */
    rowErpSalesQuoteLineIndex({ row, rowIndex }) {
      row.index = rowIndex + 1
    },
    /** 销售报价单明细添加按钮操作 */
    handleAddErpSalesQuoteLine() {
      let obj = {}
      obj.lineNo = ""
      obj.materialId = ""
      obj.qty = ""
      obj.price = ""
      obj.taxRate = ""
      obj.amount = ""
      obj.deliveryDate = ""
      this.erpSalesQuoteLineList.push(obj)
    },
    /** 销售报价单明细删除按钮操作 */
    handleDeleteErpSalesQuoteLine() {
      if (this.checkedErpSalesQuoteLine.length == 0) {
        this.$modal.msgError("请先选择要删除的销售报价单明细数据")
      } else {
        const erpSalesQuoteLineList = this.erpSalesQuoteLineList
        const checkedErpSalesQuoteLine = this.checkedErpSalesQuoteLine
        this.erpSalesQuoteLineList = erpSalesQuoteLineList.filter(function(item) {
          return checkedErpSalesQuoteLine.indexOf(item.index) == -1
        })
      }
    },
    /** 复选框选中数据 */
    handleErpSalesQuoteLineSelectionChange(selection) {
      this.checkedErpSalesQuoteLine = selection.map(item => item.index)
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('sales/quote/export', {
        ...this.queryParams
      }, `quote_${new Date().getTime()}.xlsx`)
    }
  }
}
</script>
