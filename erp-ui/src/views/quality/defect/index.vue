<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="88px">
      <el-form-item label="处理单号" prop="disposalCode">
        <el-input
          v-model="queryParams.disposalCode"
          placeholder="请输入处理单号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="批次号" prop="batchNo">
        <el-input
          v-model="queryParams.batchNo"
          placeholder="请输入批次号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="处理方式" prop="disposalType">
        <el-select v-model="queryParams.disposalType" placeholder="请选择处理方式" clearable>
          <el-option
            v-for="item in disposalTypeOptions"
            :key="item.value"
            :label="item.label"
            :value="item.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="状态" prop="handleStatus">
        <el-select v-model="queryParams.handleStatus" placeholder="请选择状态" clearable>
          <el-option
            v-for="item in handleStatusOptions"
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
          v-hasPermi="['quality:defect:add']"
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
          v-hasPermi="['quality:defect:edit']"
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
          v-hasPermi="['quality:defect:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['quality:defect:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="defectList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="处理ID" align="center" prop="disposalId" />
      <el-table-column label="处理单号" align="center" prop="disposalCode" />
      <el-table-column label="检验单ID" align="center" prop="inspectId" />
      <el-table-column label="物料ID" align="center" prop="materialId" />
      <el-table-column label="批次号" align="center" prop="batchNo" />
      <el-table-column label="不合格数量" align="center" prop="defectQty" />
      <el-table-column label="处理方式" align="center" prop="disposalType" />
      <el-table-column label="原因分析" align="center" prop="reason" />
      <el-table-column label="状态" align="center" prop="handleStatus" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['quality:defect:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['quality:defect:remove']"
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

    <!-- 添加或修改不合格品处理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="800px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="100px">
        <el-row>
          <el-col :span="12">
            <el-form-item label="处理单号" prop="disposalCode">
              <el-input v-model="form.disposalCode" placeholder="请输入处理单号" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="检验单ID" prop="inspectId">
              <el-input-number v-model="form.inspectId" :min="0" placeholder="请输入检验单ID" />
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
            <el-form-item label="不合格数量" prop="defectQty">
              <el-input-number v-model="form.defectQty" :precision="4" :min="0" placeholder="请输入不合格数量" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="处理方式" prop="disposalType">
              <el-select v-model="form.disposalType" placeholder="请选择处理方式">
                <el-option
                  v-for="item in disposalTypeOptions"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value"
                />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="状态" prop="handleStatus">
              <el-select v-model="form.handleStatus" placeholder="请选择状态">
                <el-option
                  v-for="item in handleStatusOptions"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value"
                />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="原因分析" prop="reason">
              <el-input v-model="form.reason" type="textarea" placeholder="请输入内容" />
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
import { listErpDefectDisposal, getErpDefectDisposal, delErpDefectDisposal, addErpDefectDisposal, updateErpDefectDisposal } from "@/api/quality/defect"

export default {
  name: "ErpDefectDisposal",
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
      // 不合格品处理表格数据
      defectList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 处理方式选项
      disposalTypeOptions: [
        { value: "1", label: "隔离" },
        { value: "2", label: "返工" },
        { value: "3", label: "返修" },
        { value: "4", label: "让步接收" },
        { value: "5", label: "报废" },
        { value: "6", label: "退货" }
      ],
      // 状态选项
      handleStatusOptions: [
        { value: "0", label: "待处理" },
        { value: "1", label: "处理中" },
        { value: "2", label: "已关闭" }
      ],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        disposalCode: null,
        batchNo: null,
        disposalType: null,
        handleStatus: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        disposalCode: [
          { required: true, message: "处理单号不能为空", trigger: "blur" }
        ]
      }
    }
  },
  created() {
    this.getList()
  },
  methods: {
    /** 查询不合格品处理列表 */
    getList() {
      this.loading = true
      listErpDefectDisposal(this.queryParams).then(response => {
        this.defectList = response.rows
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
        disposalId: null,
        disposalCode: null,
        inspectId: null,
        materialId: null,
        batchNo: null,
        defectQty: null,
        disposalType: null,
        reason: null,
        handleStatus: null,
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
      this.ids = selection.map(item => item.disposalId)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset()
      this.open = true
      this.title = "添加不合格品处理"
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const disposalId = row.disposalId || this.ids
      getErpDefectDisposal(disposalId).then(response => {
        this.form = response.data
        this.open = true
        this.title = "修改不合格品处理"
      })
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.disposalId != null) {
            updateErpDefectDisposal(this.form).then(response => {
              this.$modal.msgSuccess("修改成功")
              this.open = false
              this.getList()
            })
          } else {
            addErpDefectDisposal(this.form).then(response => {
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
      const disposalIds = row.disposalId || this.ids
      this.$modal.confirm('是否确认删除不合格品处理编号为"' + disposalIds + '"的数据项？').then(function() {
        return delErpDefectDisposal(disposalIds)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess("删除成功")
      }).catch(() => {})
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('quality/defect/export', {
        ...this.queryParams
      }, `defect_${new Date().getTime()}.xlsx`)
    }
  }
}
</script>
