<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="领料单号" prop="pickingCode">
        <el-input
          v-model="queryParams.pickingCode"
          placeholder="请输入领料单号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="类型" prop="pickingType">
        <el-select v-model="queryParams.pickingType" placeholder="请选择类型" clearable>
          <el-option label="领料" value="1" />
          <el-option label="退料" value="2" />
          <el-option label="补料" value="3" />
        </el-select>
      </el-form-item>
      <el-form-item label="状态" prop="status">
        <el-select v-model="queryParams.status" placeholder="请选择状态" clearable>
          <el-option label="草稿" value="0" />
          <el-option label="待审核" value="1" />
          <el-option label="已审核" value="2" />
          <el-option label="已出库" value="3" />
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
          v-hasPermi="['manufacturing:picking:add']"
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
          v-hasPermi="['manufacturing:picking:edit']"
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
          v-hasPermi="['manufacturing:picking:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['manufacturing:picking:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="pickingList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="领料单号" align="center" prop="pickingCode" />
      <el-table-column label="工单ID" align="center" prop="moId" />
      <el-table-column label="类型" align="center" prop="pickingType">
        <template slot-scope="scope">
          <span v-if="scope.row.pickingType == '1'">领料</span>
          <span v-else-if="scope.row.pickingType == '2'">退料</span>
          <span v-else-if="scope.row.pickingType == '3'">补料</span>
        </template>
      </el-table-column>
      <el-table-column label="仓库ID" align="center" prop="warehouseId" />
      <el-table-column label="领料日期" align="center" prop="pickingDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.pickingDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="状态" align="center" prop="status">
        <template slot-scope="scope">
          <span v-if="scope.row.status == '0'">草稿</span>
          <span v-else-if="scope.row.status == '1'">待审核</span>
          <span v-else-if="scope.row.status == '2'">已审核</span>
          <span v-else-if="scope.row.status == '3'">已出库</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['manufacturing:picking:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['manufacturing:picking:remove']"
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

    <!-- 添加或修改生产领料单对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="1100px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="100px">
        <el-row>
          <el-col :span="12">
            <el-form-item label="领料单号" prop="pickingCode">
              <el-input v-model="form.pickingCode" placeholder="请输入领料单号" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="工单ID" prop="moId">
              <el-input-number v-model="form.moId" :min="0" controls-position="right" placeholder="请输入工单ID" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="类型" prop="pickingType">
              <el-select v-model="form.pickingType" placeholder="请选择类型">
                <el-option label="领料" value="1" />
                <el-option label="退料" value="2" />
                <el-option label="补料" value="3" />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="仓库ID" prop="warehouseId">
              <el-input-number v-model="form.warehouseId" :min="0" controls-position="right" placeholder="请输入仓库ID" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="领料日期" prop="pickingDate">
              <el-date-picker clearable
                v-model="form.pickingDate"
                type="date"
                value-format="yyyy-MM-dd"
                placeholder="请选择领料日期">
              </el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="状态" prop="status">
              <el-select v-model="form.status" placeholder="请选择状态">
                <el-option label="草稿" value="0" />
                <el-option label="待审核" value="1" />
                <el-option label="已审核" value="2" />
                <el-option label="已出库" value="3" />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="备注" prop="remark">
              <el-input v-model="form.remark" type="textarea" placeholder="请输入内容" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-divider content-position="center">生产领料明细信息</el-divider>
        <el-row :gutter="10" class="mb8">
          <el-col :span="1.5">
            <el-button type="primary" icon="el-icon-plus" size="mini" @click="handleAddErpProductionPickingLine">添加</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button type="danger" icon="el-icon-delete" size="mini" @click="handleDeleteErpProductionPickingLine">删除</el-button>
          </el-col>
        </el-row>
        <el-table :data="erpProductionPickingLineList" :row-class-name="rowErpProductionPickingLineIndex" @selection-change="handleErpProductionPickingLineSelectionChange" ref="erpProductionPickingLine">
          <el-table-column type="selection" width="50" align="center" />
          <el-table-column label="序号" align="center" prop="index" width="50"/>
          <el-table-column label="物料ID" prop="materialId" width="150">
            <template slot-scope="scope">
              <el-input-number v-model="scope.row.materialId" :min="0" controls-position="right" placeholder="请输入物料ID" />
            </template>
          </el-table-column>
          <el-table-column label="批次号" prop="batchNo" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.batchNo" placeholder="请输入批次号" />
            </template>
          </el-table-column>
          <el-table-column label="数量" prop="qty" width="150">
            <template slot-scope="scope">
              <el-input-number v-model="scope.row.qty" :precision="2" :min="0" controls-position="right" />
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
import { listErpProductionPicking, getErpProductionPicking, delErpProductionPicking, addErpProductionPicking, updateErpProductionPicking } from "@/api/manufacturing/picking"

export default {
  name: "ErpProductionPicking",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 子表选中数据
      checkedErpProductionPickingLine: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 生产领料单表格数据
      pickingList: [],
      // 生产领料明细表格数据
      erpProductionPickingLineList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        pickingCode: null,
        pickingType: null,
        status: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        pickingCode: [
          { required: true, message: "领料单号不能为空", trigger: "blur" }
        ]
      }
    }
  },
  created() {
    this.getList()
  },
  methods: {
    /** 查询生产领料单列表 */
    getList() {
      this.loading = true
      listErpProductionPicking(this.queryParams).then(response => {
        this.pickingList = response.rows
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
        pickingId: null,
        pickingCode: null,
        moId: null,
        pickingType: null,
        warehouseId: null,
        pickingDate: null,
        status: null,
        remark: null
      }
      this.erpProductionPickingLineList = []
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
      this.ids = selection.map(item => item.pickingId)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset()
      this.open = true
      this.title = "添加生产领料单"
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const pickingId = row.pickingId || this.ids
      getErpProductionPicking(pickingId).then(response => {
        this.form = response.data
        this.erpProductionPickingLineList = response.data.erpProductionPickingLineList
        this.open = true
        this.title = "修改生产领料单"
      })
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          this.form.erpProductionPickingLineList = this.erpProductionPickingLineList
          if (this.form.pickingId != null) {
            updateErpProductionPicking(this.form).then(response => {
              this.$modal.msgSuccess("修改成功")
              this.open = false
              this.getList()
            })
          } else {
            addErpProductionPicking(this.form).then(response => {
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
      const pickingIds = row.pickingId || this.ids
      this.$modal.confirm('是否确认删除生产领料单编号为"' + pickingIds + '"的数据项？').then(function() {
        return delErpProductionPicking(pickingIds)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess("删除成功")
      }).catch(() => {})
    },
    /** 生产领料明细序号 */
    rowErpProductionPickingLineIndex({ row, rowIndex }) {
      row.index = rowIndex + 1
    },
    /** 生产领料明细添加按钮操作 */
    handleAddErpProductionPickingLine() {
      let obj = {}
      obj.materialId = ""
      obj.batchNo = ""
      obj.qty = ""
      this.erpProductionPickingLineList.push(obj)
    },
    /** 生产领料明细删除按钮操作 */
    handleDeleteErpProductionPickingLine() {
      if (this.checkedErpProductionPickingLine.length == 0) {
        this.$modal.msgError("请先选择要删除的生产领料明细数据")
      } else {
        const erpProductionPickingLineList = this.erpProductionPickingLineList
        const checkedErpProductionPickingLine = this.checkedErpProductionPickingLine
        this.erpProductionPickingLineList = erpProductionPickingLineList.filter(function(item) {
          return checkedErpProductionPickingLine.indexOf(item.index) == -1
        })
      }
    },
    /** 复选框选中数据 */
    handleErpProductionPickingLineSelectionChange(selection) {
      this.checkedErpProductionPickingLine = selection.map(item => item.index)
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('manufacturing/picking/export', {
        ...this.queryParams
      }, `picking_${new Date().getTime()}.xlsx`)
    }
  }
}
</script>
