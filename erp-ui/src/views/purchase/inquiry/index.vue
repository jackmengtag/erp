<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="88px">
      <el-form-item label="询价单号" prop="inquiryCode">
        <el-input
          v-model="queryParams.inquiryCode"
          placeholder="请输入询价单号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="询价日期" prop="inquiryDate">
        <el-date-picker clearable
          v-model="queryParams.inquiryDate"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择询价日期">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="状态" prop="status">
        <el-select v-model="queryParams.status" placeholder="请选择状态" clearable>
          <el-option label="草稿" value="0" />
          <el-option label="询价中" value="1" />
          <el-option label="已定标" value="2" />
          <el-option label="已关闭" value="3" />
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
          v-hasPermi="['purchase:inquiry:add']"
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
          v-hasPermi="['purchase:inquiry:edit']"
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
          v-hasPermi="['purchase:inquiry:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['purchase:inquiry:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="erpPurchaseInquiryList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="询价ID" align="center" prop="inquiryId" />
      <el-table-column label="询价单号" align="center" prop="inquiryCode" />
      <el-table-column label="询价日期" align="center" prop="inquiryDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.inquiryDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="状态" align="center" prop="status" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['purchase:inquiry:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['purchase:inquiry:remove']"
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

    <!-- 添加或修改采购询价对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="1100px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="100px">
        <el-row>
          <el-col :span="8">
            <el-form-item label="询价单号" prop="inquiryCode">
              <el-input v-model="form.inquiryCode" placeholder="请输入询价单号" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="询价日期" prop="inquiryDate">
              <el-date-picker clearable
                v-model="form.inquiryDate"
                type="date"
                value-format="yyyy-MM-dd"
                placeholder="请选择询价日期">
              </el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="状态" prop="status">
              <el-select v-model="form.status" placeholder="请选择状态">
                <el-option label="草稿" value="0" />
                <el-option label="询价中" value="1" />
                <el-option label="已定标" value="2" />
                <el-option label="已关闭" value="3" />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="备注" prop="remark">
              <el-input v-model="form.remark" type="textarea" placeholder="请输入内容" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-divider content-position="center">采购询价明细信息</el-divider>
        <el-row :gutter="10" class="mb8">
          <el-col :span="1.5">
            <el-button type="primary" icon="el-icon-plus" size="mini" @click="handleAddErpPurchaseInquiryLine">添加</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button type="danger" icon="el-icon-delete" size="mini" @click="handleDeleteErpPurchaseInquiryLine">删除</el-button>
          </el-col>
        </el-row>
        <el-table :data="erpPurchaseInquiryLineList" :row-class-name="rowErpPurchaseInquiryLineIndex" @selection-change="handleErpPurchaseInquiryLineSelectionChange" ref="erpPurchaseInquiryLine">
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
          <el-table-column label="询价数量" prop="qty" width="150">
            <template slot-scope="scope">
              <el-input-number v-model="scope.row.qty" :precision="4" :controls="false" placeholder="请输入询价数量" />
            </template>
          </el-table-column>
          <el-table-column label="报价供应商ID" prop="supplierId" width="150">
            <template slot-scope="scope">
              <el-input-number v-model="scope.row.supplierId" controls-position="right" :min="0" />
            </template>
          </el-table-column>
          <el-table-column label="报价单价" prop="quotePrice" width="150">
            <template slot-scope="scope">
              <el-input-number v-model="scope.row.quotePrice" :precision="6" :controls="false" placeholder="请输入报价单价" />
            </template>
          </el-table-column>
          <el-table-column label="税率(%)" prop="taxRate" width="150">
            <template slot-scope="scope">
              <el-input-number v-model="scope.row.taxRate" :precision="4" :controls="false" placeholder="请输入税率(%)" />
            </template>
          </el-table-column>
          <el-table-column label="交货天数" prop="deliveryDays" width="150">
            <template slot-scope="scope">
              <el-input-number v-model="scope.row.deliveryDays" controls-position="right" :min="0" />
            </template>
          </el-table-column>
          <el-table-column label="是否中选" prop="selectedFlag" width="150">
            <template slot-scope="scope">
              <el-select v-model="scope.row.selectedFlag" placeholder="请选择是否中选">
                <el-option label="是" value="1" />
                <el-option label="否" value="0" />
              </el-select>
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
import { listErpPurchaseInquiry, getErpPurchaseInquiry, delErpPurchaseInquiry, addErpPurchaseInquiry, updateErpPurchaseInquiry } from "@/api/purchase/inquiry"

export default {
  name: "ErpPurchaseInquiry",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 子表选中数据
      checkedErpPurchaseInquiryLine: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 采购询价表格数据
      erpPurchaseInquiryList: [],
      // 采购询价明细表格数据
      erpPurchaseInquiryLineList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        inquiryCode: null,
        inquiryDate: null,
        status: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        inquiryCode: [
          { required: true, message: "询价单号不能为空", trigger: "blur" }
        ]
      }
    }
  },
  created() {
    this.getList()
  },
  methods: {
    /** 查询采购询价列表 */
    getList() {
      this.loading = true
      listErpPurchaseInquiry(this.queryParams).then(response => {
        this.erpPurchaseInquiryList = response.rows
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
        inquiryId: null,
        inquiryCode: null,
        inquiryDate: null,
        status: null,
        remark: null
      }
      this.erpPurchaseInquiryLineList = []
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
      this.ids = selection.map(item => item.inquiryId)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset()
      this.open = true
      this.title = "添加采购询价"
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const inquiryId = row.inquiryId || this.ids
      getErpPurchaseInquiry(inquiryId).then(response => {
        this.form = response.data
        this.erpPurchaseInquiryLineList = response.data.erpPurchaseInquiryLineList
        this.open = true
        this.title = "修改采购询价"
      })
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          this.form.erpPurchaseInquiryLineList = this.erpPurchaseInquiryLineList
          if (this.form.inquiryId != null) {
            updateErpPurchaseInquiry(this.form).then(response => {
              this.$modal.msgSuccess("修改成功")
              this.open = false
              this.getList()
            })
          } else {
            addErpPurchaseInquiry(this.form).then(response => {
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
      const inquiryIds = row.inquiryId || this.ids
      this.$modal.confirm('是否确认删除采购询价编号为"' + inquiryIds + '"的数据项？').then(function() {
        return delErpPurchaseInquiry(inquiryIds)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess("删除成功")
      }).catch(() => {})
    },
    /** 采购询价明细序号 */
    rowErpPurchaseInquiryLineIndex({ row, rowIndex }) {
      row.index = rowIndex + 1
    },
    /** 采购询价明细添加按钮操作 */
    handleAddErpPurchaseInquiryLine() {
      let obj = {}
      obj.lineNo = ""
      obj.materialId = ""
      obj.qty = ""
      obj.supplierId = ""
      obj.quotePrice = ""
      obj.taxRate = ""
      obj.deliveryDays = ""
      obj.selectedFlag = ""
      this.erpPurchaseInquiryLineList.push(obj)
    },
    /** 采购询价明细删除按钮操作 */
    handleDeleteErpPurchaseInquiryLine() {
      if (this.checkedErpPurchaseInquiryLine.length == 0) {
        this.$modal.msgError("请先选择要删除的采购询价明细数据")
      } else {
        const erpPurchaseInquiryLineList = this.erpPurchaseInquiryLineList
        const checkedErpPurchaseInquiryLine = this.checkedErpPurchaseInquiryLine
        this.erpPurchaseInquiryLineList = erpPurchaseInquiryLineList.filter(function(item) {
          return checkedErpPurchaseInquiryLine.indexOf(item.index) == -1
        })
      }
    },
    /** 复选框选中数据 */
    handleErpPurchaseInquiryLineSelectionChange(selection) {
      this.checkedErpPurchaseInquiryLine = selection.map(item => item.index)
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('purchase/inquiry/export', {
        ...this.queryParams
      }, `inquiry_${new Date().getTime()}.xlsx`)
    }
  }
}
</script>
