<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="88px">
      <el-form-item label="CAPA单号" prop="capaCode">
        <el-input
          v-model="queryParams.capaCode"
          placeholder="请输入CAPA单号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="类型" prop="capaType">
        <el-select v-model="queryParams.capaType" placeholder="请选择类型" clearable>
          <el-option
            v-for="item in capaTypeOptions"
            :key="item.value"
            :label="item.label"
            :value="item.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="责任部门" prop="deptId">
        <el-input-number v-model="queryParams.deptId" :min="0" placeholder="请输入责任部门" />
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
          v-hasPermi="['quality:capa:add']"
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
          v-hasPermi="['quality:capa:edit']"
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
          v-hasPermi="['quality:capa:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['quality:capa:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="capaList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="CAPA ID" align="center" prop="capaId" />
      <el-table-column label="CAPA单号" align="center" prop="capaCode" />
      <el-table-column label="类型" align="center" prop="capaType" />
      <el-table-column label="问题描述" align="center" prop="sourceDesc" />
      <el-table-column label="责任部门" align="center" prop="deptId" />
      <el-table-column label="纠正预防措施" align="center" prop="measure" />
      <el-table-column label="截止日期" align="center" prop="dueDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.dueDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="验证结果" align="center" prop="verifyResult" />
      <el-table-column label="状态" align="center" prop="status" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['quality:capa:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['quality:capa:remove']"
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

    <!-- 添加或修改CAPA与客诉对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="800px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="100px">
        <el-row>
          <el-col :span="12">
            <el-form-item label="CAPA单号" prop="capaCode">
              <el-input v-model="form.capaCode" placeholder="请输入CAPA单号" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="类型" prop="capaType">
              <el-select v-model="form.capaType" placeholder="请选择类型">
                <el-option
                  v-for="item in capaTypeOptions"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value"
                />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="责任部门" prop="deptId">
              <el-input-number v-model="form.deptId" :min="0" placeholder="请输入责任部门" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="截止日期" prop="dueDate">
              <el-date-picker clearable
                v-model="form.dueDate"
                type="date"
                value-format="yyyy-MM-dd"
                placeholder="请选择截止日期">
              </el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="验证结果" prop="verifyResult">
              <el-input v-model="form.verifyResult" placeholder="请输入验证结果" />
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
          <el-col :span="24">
            <el-form-item label="问题描述" prop="sourceDesc">
              <el-input v-model="form.sourceDesc" type="textarea" placeholder="请输入内容" />
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="纠正预防措施" prop="measure">
              <el-input v-model="form.measure" type="textarea" placeholder="请输入内容" />
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
import { listErpCapa, getErpCapa, delErpCapa, addErpCapa, updateErpCapa } from "@/api/quality/capa"

export default {
  name: "ErpCapa",
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
      // CAPA与客诉表格数据
      capaList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 类型选项
      capaTypeOptions: [
        { value: "1", label: "质量问题" },
        { value: "2", label: "客户投诉" }
      ],
      // 状态选项
      statusOptions: [
        { value: "0", label: "待处理" },
        { value: "1", label: "处理中" },
        { value: "2", label: "已验证" },
        { value: "3", label: "已关闭" }
      ],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        capaCode: null,
        capaType: null,
        deptId: null,
        status: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        capaCode: [
          { required: true, message: "CAPA单号不能为空", trigger: "blur" }
        ]
      }
    }
  },
  created() {
    this.getList()
  },
  methods: {
    /** 查询CAPA与客诉列表 */
    getList() {
      this.loading = true
      listErpCapa(this.queryParams).then(response => {
        this.capaList = response.rows
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
        capaId: null,
        capaCode: null,
        capaType: null,
        sourceDesc: null,
        deptId: null,
        measure: null,
        dueDate: null,
        verifyResult: null,
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
      this.ids = selection.map(item => item.capaId)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset()
      this.open = true
      this.title = "添加CAPA与客诉"
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const capaId = row.capaId || this.ids
      getErpCapa(capaId).then(response => {
        this.form = response.data
        this.open = true
        this.title = "修改CAPA与客诉"
      })
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.capaId != null) {
            updateErpCapa(this.form).then(response => {
              this.$modal.msgSuccess("修改成功")
              this.open = false
              this.getList()
            })
          } else {
            addErpCapa(this.form).then(response => {
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
      const capaIds = row.capaId || this.ids
      this.$modal.confirm('是否确认删除CAPA与客诉编号为"' + capaIds + '"的数据项？').then(function() {
        return delErpCapa(capaIds)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess("删除成功")
      }).catch(() => {})
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('quality/capa/export', {
        ...this.queryParams
      }, `capa_${new Date().getTime()}.xlsx`)
    }
  }
}
</script>
