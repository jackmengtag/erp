<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="88px">
      <el-form-item label="检验单号" prop="inspectCode">
        <el-input
          v-model="queryParams.inspectCode"
          placeholder="请输入检验单号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="检验类型" prop="inspectType">
        <el-select v-model="queryParams.inspectType" placeholder="请选择检验类型" clearable>
          <el-option
            v-for="item in inspectTypeOptions"
            :key="item.value"
            :label="item.label"
            :value="item.value"
          />
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
          v-hasPermi="['quality:inspect:add']"
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
          v-hasPermi="['quality:inspect:edit']"
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
          v-hasPermi="['quality:inspect:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['quality:inspect:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="inspectList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="检验单ID" align="center" prop="inspectId" />
      <el-table-column label="检验单号" align="center" prop="inspectCode" />
      <el-table-column label="检验类型(IQC/IPQC/FQC/OQC)" align="center" prop="inspectType" />
      <el-table-column label="检验方案ID" align="center" prop="planId" />
      <el-table-column label="物料ID" align="center" prop="materialId" />
      <el-table-column label="批次号" align="center" prop="batchNo" />
      <el-table-column label="来源单据类型" align="center" prop="sourceType" />
      <el-table-column label="来源单据ID" align="center" prop="sourceId" />
      <el-table-column label="检验数量" align="center" prop="inspectQty" />
      <el-table-column label="合格数量" align="center" prop="qualifiedQty" />
      <el-table-column label="不合格数量" align="center" prop="unqualifiedQty" />
      <el-table-column label="检验结果" align="center" prop="inspectResult" />
      <el-table-column label="检验员" align="center" prop="inspector" />
      <el-table-column label="状态" align="center" prop="status" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['quality:inspect:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['quality:inspect:remove']"
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

    <!-- 添加或修改检验单对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="800px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="100px">
        <el-row>
          <el-col :span="12">
            <el-form-item label="检验单号" prop="inspectCode">
              <el-input v-model="form.inspectCode" placeholder="请输入检验单号" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="检验类型" prop="inspectType">
              <el-select v-model="form.inspectType" placeholder="请选择检验类型">
                <el-option
                  v-for="item in inspectTypeOptions"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value"
                />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="检验方案ID" prop="planId">
              <el-input-number v-model="form.planId" :min="0" placeholder="请输入检验方案ID" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="物料ID" prop="materialId">
              <el-input-number v-model="form.materialId" :min="0" placeholder="请输入物料ID" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="批次号" prop="batchNo">
              <el-input v-model="form.batchNo" placeholder="请输入批次号" />
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
            <el-form-item label="检验数量" prop="inspectQty">
              <el-input-number v-model="form.inspectQty" :precision="4" :min="0" placeholder="请输入检验数量" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="合格数量" prop="qualifiedQty">
              <el-input-number v-model="form.qualifiedQty" :precision="4" :min="0" placeholder="请输入合格数量" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="不合格数量" prop="unqualifiedQty">
              <el-input-number v-model="form.unqualifiedQty" :precision="4" :min="0" placeholder="请输入不合格数量" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="检验结果" prop="inspectResult">
              <el-select v-model="form.inspectResult" placeholder="请选择检验结果">
                <el-option
                  v-for="item in inspectResultOptions"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value"
                />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="检验员" prop="inspector">
              <el-input v-model="form.inspector" placeholder="请输入检验员" />
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

        <el-divider content-position="center">检验结果明细信息</el-divider>
        <el-row :gutter="10" class="mb8">
          <el-col :span="1.5">
            <el-button type="primary" icon="el-icon-plus" size="mini" @click="handleAddErpInspectionResult">添加</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button type="danger" icon="el-icon-delete" size="mini" @click="handleDeleteErpInspectionResult">删除</el-button>
          </el-col>
        </el-row>
        <el-table :data="erpInspectionResultList" :row-class-name="rowErpInspectionResultIndex" @selection-change="handleErpInspectionResultSelectionChange" ref="erpInspectionResult">
          <el-table-column type="selection" width="50" align="center" />
          <el-table-column label="序号" align="center" prop="index" width="50"/>
          <el-table-column label="检验项目" prop="itemName" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.itemName" placeholder="请输入检验项目" />
            </template>
          </el-table-column>
          <el-table-column label="规格标准" prop="standardValue" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.standardValue" placeholder="请输入规格标准" />
            </template>
          </el-table-column>
          <el-table-column label="检验值" prop="inspectValue" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.inspectValue" placeholder="请输入检验值" />
            </template>
          </el-table-column>
          <el-table-column label="判定" prop="judgeResult" width="150">
            <template slot-scope="scope">
              <el-select v-model="scope.row.judgeResult" placeholder="请选择判定">
                <el-option
                  v-for="item in judgeResultOptions"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value"
                />
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
import { listErpInspectionOrder, getErpInspectionOrder, delErpInspectionOrder, addErpInspectionOrder, updateErpInspectionOrder } from "@/api/quality/inspect"

export default {
  name: "ErpInspectionOrder",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 子表选中数据
      checkedErpInspectionResult: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 检验单表格数据
      inspectList: [],
      // 检验结果明细表格数据
      erpInspectionResultList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 检验类型选项
      inspectTypeOptions: [
        { value: "IQC", label: "IQC" },
        { value: "IPQC", label: "IPQC" },
        { value: "FQC", label: "FQC" },
        { value: "OQC", label: "OQC" }
      ],
      // 检验结果选项
      inspectResultOptions: [
        { value: "0", label: "待检验" },
        { value: "1", label: "合格" },
        { value: "2", label: "不合格" }
      ],
      // 状态选项
      statusOptions: [
        { value: "0", label: "待检验" },
        { value: "1", label: "检验中" },
        { value: "2", label: "已完成" },
        { value: "3", label: "已关闭" }
      ],
      // 判定选项
      judgeResultOptions: [
        { value: "0", label: "待定" },
        { value: "1", label: "合格" },
        { value: "2", label: "不合格" }
      ],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        inspectCode: null,
        inspectType: null,
        batchNo: null,
        status: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        inspectCode: [
          { required: true, message: "检验单号不能为空", trigger: "blur" }
        ],
        materialId: [
          { required: true, message: "物料ID不能为空", trigger: "blur" }
        ]
      }
    }
  },
  created() {
    this.getList()
  },
  methods: {
    /** 查询检验单列表 */
    getList() {
      this.loading = true
      listErpInspectionOrder(this.queryParams).then(response => {
        this.inspectList = response.rows
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
        inspectId: null,
        inspectCode: null,
        inspectType: null,
        planId: null,
        materialId: null,
        batchNo: null,
        sourceType: null,
        sourceId: null,
        inspectQty: null,
        qualifiedQty: null,
        unqualifiedQty: null,
        inspectResult: null,
        inspector: null,
        status: null,
        remark: null
      }
      this.erpInspectionResultList = []
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
      this.ids = selection.map(item => item.inspectId)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset()
      this.open = true
      this.title = "添加检验单"
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const inspectId = row.inspectId || this.ids
      getErpInspectionOrder(inspectId).then(response => {
        this.form = response.data
        this.erpInspectionResultList = response.data.erpInspectionResultList
        this.open = true
        this.title = "修改检验单"
      })
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          this.form.erpInspectionResultList = this.erpInspectionResultList
          if (this.form.inspectId != null) {
            updateErpInspectionOrder(this.form).then(response => {
              this.$modal.msgSuccess("修改成功")
              this.open = false
              this.getList()
            })
          } else {
            addErpInspectionOrder(this.form).then(response => {
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
      const inspectIds = row.inspectId || this.ids
      this.$modal.confirm('是否确认删除检验单编号为"' + inspectIds + '"的数据项？').then(function() {
        return delErpInspectionOrder(inspectIds)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess("删除成功")
      }).catch(() => {})
    },
    /** 检验结果明细序号 */
    rowErpInspectionResultIndex({ row, rowIndex }) {
      row.index = rowIndex + 1
    },
    /** 检验结果明细添加按钮操作 */
    handleAddErpInspectionResult() {
      let obj = {}
      obj.itemName = ""
      obj.standardValue = ""
      obj.inspectValue = ""
      obj.judgeResult = ""
      this.erpInspectionResultList.push(obj)
    },
    /** 检验结果明细删除按钮操作 */
    handleDeleteErpInspectionResult() {
      if (this.checkedErpInspectionResult.length == 0) {
        this.$modal.msgError("请先选择要删除的检验结果明细数据")
      } else {
        const erpInspectionResultList = this.erpInspectionResultList
        const checkedErpInspectionResult = this.checkedErpInspectionResult
        this.erpInspectionResultList = erpInspectionResultList.filter(function(item) {
          return checkedErpInspectionResult.indexOf(item.index) == -1
        })
      }
    },
    /** 复选框选中数据 */
    handleErpInspectionResultSelectionChange(selection) {
      this.checkedErpInspectionResult = selection.map(item => item.index)
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('quality/inspect/export', {
        ...this.queryParams
      }, `inspect_${new Date().getTime()}.xlsx`)
    }
  }
}
</script>
