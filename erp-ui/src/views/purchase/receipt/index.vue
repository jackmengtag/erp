<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="88px">
      <el-form-item label="收货单号" prop="receiptCode">
        <el-input
          v-model="queryParams.receiptCode"
          placeholder="请输入收货单号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="供应商ID" prop="supplierId">
        <el-input-number v-model="queryParams.supplierId" placeholder="请输入供应商ID" controls-position="right" :min="0" />
      </el-form-item>
      <el-form-item label="收货日期" prop="receiptDate">
        <el-date-picker clearable
          v-model="queryParams.receiptDate"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择收货日期">
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
          v-hasPermi="['purchase:receipt:add']"
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
          v-hasPermi="['purchase:receipt:edit']"
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
          v-hasPermi="['purchase:receipt:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['purchase:receipt:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="erpGoodsReceiptList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="收货单ID" align="center" prop="receiptId" />
      <el-table-column label="收货单号" align="center" prop="receiptCode" />
      <el-table-column label="采购订单ID" align="center" prop="poId" />
      <el-table-column label="供应商ID" align="center" prop="supplierId" />
      <el-table-column label="收货仓库ID" align="center" prop="warehouseId" />
      <el-table-column label="收货日期" align="center" prop="receiptDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.receiptDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="检验状态" align="center" prop="inspectStatus" />
      <el-table-column label="状态" align="center" prop="status" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['purchase:receipt:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['purchase:receipt:remove']"
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

    <!-- 添加或修改收货单对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="1100px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="100px">
        <el-row>
          <el-col :span="8">
            <el-form-item label="收货单号" prop="receiptCode">
              <el-input v-model="form.receiptCode" placeholder="请输入收货单号" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="采购订单ID" prop="poId">
              <el-input-number v-model="form.poId" placeholder="请输入采购订单ID" controls-position="right" :min="0" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="供应商ID" prop="supplierId">
              <el-input-number v-model="form.supplierId" placeholder="请输入供应商ID" controls-position="right" :min="0" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="收货仓库ID" prop="warehouseId">
              <el-input-number v-model="form.warehouseId" placeholder="请输入收货仓库ID" controls-position="right" :min="0" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="收货日期" prop="receiptDate">
              <el-date-picker clearable
                v-model="form.receiptDate"
                type="date"
                value-format="yyyy-MM-dd"
                placeholder="请选择收货日期">
              </el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="检验状态" prop="inspectStatus">
              <el-select v-model="form.inspectStatus" placeholder="请选择检验状态">
                <el-option label="待检" value="0" />
                <el-option label="合格" value="1" />
                <el-option label="不合格" value="2" />
                <el-option label="让步接收" value="3" />
              </el-select>
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
        <el-divider content-position="center">收货单明细信息</el-divider>
        <el-row :gutter="10" class="mb8">
          <el-col :span="1.5">
            <el-button type="primary" icon="el-icon-plus" size="mini" @click="handleAddErpGoodsReceiptLine">添加</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button type="danger" icon="el-icon-delete" size="mini" @click="handleDeleteErpGoodsReceiptLine">删除</el-button>
          </el-col>
        </el-row>
        <el-table :data="erpGoodsReceiptLineList" :row-class-name="rowErpGoodsReceiptLineIndex" @selection-change="handleErpGoodsReceiptLineSelectionChange" ref="erpGoodsReceiptLine">
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
          <el-table-column label="收货数量" prop="qty" width="150">
            <template slot-scope="scope">
              <el-input-number v-model="scope.row.qty" :precision="4" :controls="false" placeholder="请输入收货数量" />
            </template>
          </el-table-column>
          <el-table-column label="合格数量" prop="qualifiedQty" width="150">
            <template slot-scope="scope">
              <el-input-number v-model="scope.row.qualifiedQty" :precision="4" :controls="false" placeholder="请输入合格数量" />
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
import { listErpGoodsReceipt, getErpGoodsReceipt, delErpGoodsReceipt, addErpGoodsReceipt, updateErpGoodsReceipt } from "@/api/purchase/receipt"

export default {
  name: "ErpGoodsReceipt",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 子表选中数据
      checkedErpGoodsReceiptLine: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 收货单表格数据
      erpGoodsReceiptList: [],
      // 收货单明细表格数据
      erpGoodsReceiptLineList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        receiptCode: null,
        supplierId: null,
        receiptDate: null,
        status: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        receiptCode: [
          { required: true, message: "收货单号不能为空", trigger: "blur" }
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
    /** 查询收货单列表 */
    getList() {
      this.loading = true
      listErpGoodsReceipt(this.queryParams).then(response => {
        this.erpGoodsReceiptList = response.rows
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
        receiptId: null,
        receiptCode: null,
        poId: null,
        supplierId: null,
        warehouseId: null,
        receiptDate: null,
        inspectStatus: null,
        status: null,
        remark: null
      }
      this.erpGoodsReceiptLineList = []
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
      this.ids = selection.map(item => item.receiptId)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset()
      this.open = true
      this.title = "添加收货单"
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const receiptId = row.receiptId || this.ids
      getErpGoodsReceipt(receiptId).then(response => {
        this.form = response.data
        this.erpGoodsReceiptLineList = response.data.erpGoodsReceiptLineList
        this.open = true
        this.title = "修改收货单"
      })
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          this.form.erpGoodsReceiptLineList = this.erpGoodsReceiptLineList
          if (this.form.receiptId != null) {
            updateErpGoodsReceipt(this.form).then(response => {
              this.$modal.msgSuccess("修改成功")
              this.open = false
              this.getList()
            })
          } else {
            addErpGoodsReceipt(this.form).then(response => {
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
      const receiptIds = row.receiptId || this.ids
      this.$modal.confirm('是否确认删除收货单编号为"' + receiptIds + '"的数据项？').then(function() {
        return delErpGoodsReceipt(receiptIds)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess("删除成功")
      }).catch(() => {})
    },
    /** 收货单明细序号 */
    rowErpGoodsReceiptLineIndex({ row, rowIndex }) {
      row.index = rowIndex + 1
    },
    /** 收货单明细添加按钮操作 */
    handleAddErpGoodsReceiptLine() {
      let obj = {}
      obj.lineNo = ""
      obj.materialId = ""
      obj.batchNo = ""
      obj.qty = ""
      obj.qualifiedQty = ""
      this.erpGoodsReceiptLineList.push(obj)
    },
    /** 收货单明细删除按钮操作 */
    handleDeleteErpGoodsReceiptLine() {
      if (this.checkedErpGoodsReceiptLine.length == 0) {
        this.$modal.msgError("请先选择要删除的收货单明细数据")
      } else {
        const erpGoodsReceiptLineList = this.erpGoodsReceiptLineList
        const checkedErpGoodsReceiptLine = this.checkedErpGoodsReceiptLine
        this.erpGoodsReceiptLineList = erpGoodsReceiptLineList.filter(function(item) {
          return checkedErpGoodsReceiptLine.indexOf(item.index) == -1
        })
      }
    },
    /** 复选框选中数据 */
    handleErpGoodsReceiptLineSelectionChange(selection) {
      this.checkedErpGoodsReceiptLine = selection.map(item => item.index)
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('purchase/receipt/export', {
        ...this.queryParams
      }, `receipt_${new Date().getTime()}.xlsx`)
    }
  }
}
</script>
