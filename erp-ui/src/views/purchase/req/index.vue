<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="88px">
      <el-form-item label="申请单号" prop="reqCode">
        <el-input
          v-model="queryParams.reqCode"
          placeholder="请输入申请单号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="来源" prop="reqType">
        <el-select v-model="queryParams.reqType" placeholder="请选择来源" clearable>
          <el-option label="手工" value="1" />
          <el-option label="MRP" value="2" />
          <el-option label="库存预警" value="3" />
          <el-option label="生产工单" value="4" />
        </el-select>
      </el-form-item>
      <el-form-item label="申请日期" prop="reqDate">
        <el-date-picker clearable
          v-model="queryParams.reqDate"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择申请日期">
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
          v-hasPermi="['purchase:req:add']"
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
          v-hasPermi="['purchase:req:edit']"
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
          v-hasPermi="['purchase:req:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['purchase:req:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="erpPurchaseReqList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="申请ID" align="center" prop="reqId" />
      <el-table-column label="申请单号" align="center" prop="reqCode" />
      <el-table-column label="申请日期" align="center" prop="reqDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.reqDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="来源" align="center" prop="reqType" />
      <el-table-column label="申请部门" align="center" prop="deptId" />
      <el-table-column label="状态" align="center" prop="status" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['purchase:req:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['purchase:req:remove']"
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

    <!-- 添加或修改采购申请对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="1100px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="100px">
        <el-row>
          <el-col :span="8">
            <el-form-item label="申请单号" prop="reqCode">
              <el-input v-model="form.reqCode" placeholder="请输入申请单号" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="申请日期" prop="reqDate">
              <el-date-picker clearable
                v-model="form.reqDate"
                type="date"
                value-format="yyyy-MM-dd"
                placeholder="请选择申请日期">
              </el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="来源" prop="reqType">
              <el-select v-model="form.reqType" placeholder="请选择来源">
                <el-option label="手工" value="1" />
                <el-option label="MRP" value="2" />
                <el-option label="库存预警" value="3" />
                <el-option label="生产工单" value="4" />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="申请部门" prop="deptId">
              <el-input-number v-model="form.deptId" placeholder="请输入申请部门" controls-position="right" :min="0" />
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
          <el-col :span="24">
            <el-form-item label="备注" prop="remark">
              <el-input v-model="form.remark" type="textarea" placeholder="请输入内容" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-divider content-position="center">采购申请明细信息</el-divider>
        <el-row :gutter="10" class="mb8">
          <el-col :span="1.5">
            <el-button type="primary" icon="el-icon-plus" size="mini" @click="handleAddErpPurchaseReqLine">添加</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button type="danger" icon="el-icon-delete" size="mini" @click="handleDeleteErpPurchaseReqLine">删除</el-button>
          </el-col>
        </el-row>
        <el-table :data="erpPurchaseReqLineList" :row-class-name="rowErpPurchaseReqLineIndex" @selection-change="handleErpPurchaseReqLineSelectionChange" ref="erpPurchaseReqLine">
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
          <el-table-column label="申请数量" prop="qty" width="150">
            <template slot-scope="scope">
              <el-input-number v-model="scope.row.qty" :precision="4" :controls="false" placeholder="请输入申请数量" />
            </template>
          </el-table-column>
          <el-table-column label="需求日期" prop="needDate" width="240">
            <template slot-scope="scope">
              <el-date-picker clearable v-model="scope.row.needDate" type="date" value-format="yyyy-MM-dd" placeholder="请选择需求日期" />
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
import { listErpPurchaseReq, getErpPurchaseReq, delErpPurchaseReq, addErpPurchaseReq, updateErpPurchaseReq } from "@/api/purchase/req"

export default {
  name: "ErpPurchaseReq",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 子表选中数据
      checkedErpPurchaseReqLine: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 采购申请表格数据
      erpPurchaseReqList: [],
      // 采购申请明细表格数据
      erpPurchaseReqLineList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        reqCode: null,
        reqType: null,
        reqDate: null,
        status: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        reqCode: [
          { required: true, message: "申请单号不能为空", trigger: "blur" }
        ]
      }
    }
  },
  created() {
    this.getList()
  },
  methods: {
    /** 查询采购申请列表 */
    getList() {
      this.loading = true
      listErpPurchaseReq(this.queryParams).then(response => {
        this.erpPurchaseReqList = response.rows
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
        reqId: null,
        reqCode: null,
        reqDate: null,
        reqType: null,
        deptId: null,
        status: null,
        remark: null
      }
      this.erpPurchaseReqLineList = []
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
      this.ids = selection.map(item => item.reqId)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset()
      this.open = true
      this.title = "添加采购申请"
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const reqId = row.reqId || this.ids
      getErpPurchaseReq(reqId).then(response => {
        this.form = response.data
        this.erpPurchaseReqLineList = response.data.erpPurchaseReqLineList
        this.open = true
        this.title = "修改采购申请"
      })
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          this.form.erpPurchaseReqLineList = this.erpPurchaseReqLineList
          if (this.form.reqId != null) {
            updateErpPurchaseReq(this.form).then(response => {
              this.$modal.msgSuccess("修改成功")
              this.open = false
              this.getList()
            })
          } else {
            addErpPurchaseReq(this.form).then(response => {
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
      const reqIds = row.reqId || this.ids
      this.$modal.confirm('是否确认删除采购申请编号为"' + reqIds + '"的数据项？').then(function() {
        return delErpPurchaseReq(reqIds)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess("删除成功")
      }).catch(() => {})
    },
    /** 采购申请明细序号 */
    rowErpPurchaseReqLineIndex({ row, rowIndex }) {
      row.index = rowIndex + 1
    },
    /** 采购申请明细添加按钮操作 */
    handleAddErpPurchaseReqLine() {
      let obj = {}
      obj.lineNo = ""
      obj.materialId = ""
      obj.qty = ""
      obj.needDate = ""
      this.erpPurchaseReqLineList.push(obj)
    },
    /** 采购申请明细删除按钮操作 */
    handleDeleteErpPurchaseReqLine() {
      if (this.checkedErpPurchaseReqLine.length == 0) {
        this.$modal.msgError("请先选择要删除的采购申请明细数据")
      } else {
        const erpPurchaseReqLineList = this.erpPurchaseReqLineList
        const checkedErpPurchaseReqLine = this.checkedErpPurchaseReqLine
        this.erpPurchaseReqLineList = erpPurchaseReqLineList.filter(function(item) {
          return checkedErpPurchaseReqLine.indexOf(item.index) == -1
        })
      }
    },
    /** 复选框选中数据 */
    handleErpPurchaseReqLineSelectionChange(selection) {
      this.checkedErpPurchaseReqLine = selection.map(item => item.index)
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('purchase/req/export', {
        ...this.queryParams
      }, `req_${new Date().getTime()}.xlsx`)
    }
  }
}
</script>
