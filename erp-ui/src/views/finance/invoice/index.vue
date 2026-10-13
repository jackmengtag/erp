<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="88px">
      <el-form-item label="发票单号" prop="invoiceCode">
        <el-input
          v-model="queryParams.invoiceCode"
          placeholder="请输入发票单号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="类型" prop="invoiceType">
        <el-select v-model="queryParams.invoiceType" placeholder="请选择类型" clearable>
          <el-option
            v-for="item in invoiceTypeOptions"
            :key="item.value"
            :label="item.label"
            :value="item.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="状态" prop="status">
        <el-select v-model="queryParams.status" placeholder="请选择状态" clearable>
          <el-option
            v-for="item in statusOptions"
            :key="item.value"
            :label="item.label"
            :value="item.value"
          />
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
          v-hasPermi="['finance:invoice:add']"
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
          v-hasPermi="['finance:invoice:edit']"
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
          v-hasPermi="['finance:invoice:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['finance:invoice:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="invoiceList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="发票ID" align="center" prop="invoiceId" />
      <el-table-column label="发票单号" align="center" prop="invoiceCode" />
      <el-table-column label="发票号码" align="center" prop="invoiceNo" />
      <el-table-column label="类型" align="center" prop="invoiceType" />
      <el-table-column label="客户/供应商ID" align="center" prop="partnerId" />
      <el-table-column label="来源单据类型" align="center" prop="sourceType" />
      <el-table-column label="来源单据ID" align="center" prop="sourceId" />
      <el-table-column label="开票日期" align="center" prop="invoiceDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.invoiceDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="金额" align="center" prop="amount" />
      <el-table-column label="税额" align="center" prop="taxAmount" />
      <el-table-column label="状态" align="center" prop="status" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['finance:invoice:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['finance:invoice:remove']"
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

    <!-- 添加或修改发票对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="800px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="110px">
        <el-row>
          <el-col :span="12">
            <el-form-item label="发票单号" prop="invoiceCode">
              <el-input v-model="form.invoiceCode" placeholder="请输入发票单号" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="发票号码" prop="invoiceNo">
              <el-input v-model="form.invoiceNo" placeholder="请输入发票号码" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="类型" prop="invoiceType">
              <el-select v-model="form.invoiceType" placeholder="请选择类型">
                <el-option
                  v-for="item in invoiceTypeOptions"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value"
                />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="客户/供应商ID" prop="partnerId">
              <el-input-number v-model="form.partnerId" :min="0" placeholder="请输入客户/供应商ID" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="来源单据类型" prop="sourceType">
              <el-input v-model="form.sourceType" placeholder="请输入来源单据类型" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="来源单据ID" prop="sourceId">
              <el-input-number v-model="form.sourceId" :min="0" placeholder="请输入来源单据ID" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="开票日期" prop="invoiceDate">
              <el-date-picker clearable
                v-model="form.invoiceDate"
                type="date"
                value-format="yyyy-MM-dd"
                placeholder="请选择开票日期">
              </el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="金额" prop="amount">
              <el-input-number v-model="form.amount" :precision="2" :min="0" placeholder="请输入金额" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="税额" prop="taxAmount">
              <el-input-number v-model="form.taxAmount" :precision="2" :min="0" placeholder="请输入税额" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="状态" prop="status">
              <el-select v-model="form.status" placeholder="请选择状态">
                <el-option
                  v-for="item in statusOptions"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value"
                />
              </el-select>
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
import { listErpInvoice, getErpInvoice, delErpInvoice, addErpInvoice, updateErpInvoice } from "@/api/finance/invoice"

export default {
  name: "ErpInvoice",
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
      // 发票表格数据
      invoiceList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 类型选项
      invoiceTypeOptions: [
        { value: "1", label: "销售发票" },
        { value: "2", label: "采购发票" }
      ],
      // 状态选项
      statusOptions: [
        { value: "0", label: "待开票" },
        { value: "1", label: "已开具" },
        { value: "2", label: "已入账" },
        { value: "3", label: "已作废" },
        { value: "4", label: "已红冲" }
      ],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        invoiceCode: null,
        invoiceType: null,
        status: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        invoiceCode: [
          { required: true, message: "发票单号不能为空", trigger: "blur" }
        ]
      }
    }
  },
  created() {
    this.getList()
  },
  methods: {
    /** 查询发票列表 */
    getList() {
      this.loading = true
      listErpInvoice(this.queryParams).then(response => {
        this.invoiceList = response.rows
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
        invoiceId: null,
        invoiceCode: null,
        invoiceNo: null,
        invoiceType: null,
        partnerId: null,
        sourceType: null,
        sourceId: null,
        invoiceDate: null,
        amount: null,
        taxAmount: null,
        status: null,
        remark: null
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
      this.ids = selection.map(item => item.invoiceId)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset()
      this.open = true
      this.title = "添加发票"
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const invoiceId = row.invoiceId || this.ids
      getErpInvoice(invoiceId).then(response => {
        this.form = response.data
        this.open = true
        this.title = "修改发票"
      })
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.invoiceId != null) {
            updateErpInvoice(this.form).then(response => {
              this.$modal.msgSuccess("修改成功")
              this.open = false
              this.getList()
            })
          } else {
            addErpInvoice(this.form).then(response => {
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
      const invoiceIds = row.invoiceId || this.ids
      this.$modal.confirm('是否确认删除发票编号为"' + invoiceIds + '"的数据项？').then(function() {
        return delErpInvoice(invoiceIds)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess("删除成功")
      }).catch(() => {})
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('finance/invoice/export', {
        ...this.queryParams
      }, `invoice_${new Date().getTime()}.xlsx`)
    }
  }
}
</script>
