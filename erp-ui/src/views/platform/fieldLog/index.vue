<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="业务类型" prop="bizType">
        <el-input
          v-model="queryParams.bizType"
          placeholder="请输入业务类型"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="业务单据ID" prop="bizId">
        <el-input
          v-model="queryParams.bizId"
          placeholder="请输入业务单据ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="字段名" prop="fieldName">
        <el-input
          v-model="queryParams.fieldName"
          placeholder="请输入字段名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="操作类型" prop="operType">
        <el-select v-model="queryParams.operType" placeholder="请选择操作类型" clearable>
          <el-option label="insert" value="insert" />
          <el-option label="update" value="update" />
          <el-option label="delete" value="delete" />
          <el-option label="audit" value="audit" />
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
          v-hasPermi="['platform:fieldLog:add']"
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
          v-hasPermi="['platform:fieldLog:edit']"
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
          v-hasPermi="['platform:fieldLog:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['platform:fieldLog:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="erpFieldChangeLogList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="日志ID" align="center" prop="logId" />
      <el-table-column label="业务类型" align="center" prop="bizType" />
      <el-table-column label="业务单据ID" align="center" prop="bizId" />
      <el-table-column label="字段名" align="center" prop="fieldName" />
      <el-table-column label="字段说明" align="center" prop="fieldLabel" />
      <el-table-column label="修改前值" align="center" prop="oldValue" />
      <el-table-column label="修改后值" align="center" prop="newValue" />
      <el-table-column label="操作类型" align="center" prop="operType" />
      <el-table-column label="操作人" align="center" prop="operName" />
      <el-table-column label="操作IP" align="center" prop="operIp" />
      <el-table-column label="操作时间" align="center" prop="operTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.operTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="变更原因" align="center" prop="reason" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['platform:fieldLog:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['platform:fieldLog:remove']"
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

    <!-- 添加或修改字段变更审计日志对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="800px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="100px">
        <el-row>
          <el-col :span="12">
            <el-form-item label="业务类型" prop="bizType">
              <el-input v-model="form.bizType" placeholder="请输入业务类型" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="业务单据ID" prop="bizId">
              <el-input v-model="form.bizId" placeholder="请输入业务单据ID" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="字段名" prop="fieldName">
              <el-input v-model="form.fieldName" placeholder="请输入字段名" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="字段说明" prop="fieldLabel">
              <el-input v-model="form.fieldLabel" placeholder="请输入字段说明" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="修改前值" prop="oldValue">
              <el-input v-model="form.oldValue" placeholder="请输入修改前值" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="修改后值" prop="newValue">
              <el-input v-model="form.newValue" placeholder="请输入修改后值" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="操作类型" prop="operType">
              <el-select v-model="form.operType" placeholder="请选择操作类型">
                <el-option label="insert" value="insert" />
                <el-option label="update" value="update" />
                <el-option label="delete" value="delete" />
                <el-option label="audit" value="audit" />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="操作人" prop="operName">
              <el-input v-model="form.operName" placeholder="请输入操作人" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="操作IP" prop="operIp">
              <el-input v-model="form.operIp" placeholder="请输入操作IP" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="操作时间" prop="operTime">
              <el-date-picker clearable
                v-model="form.operTime"
                type="date"
                value-format="yyyy-MM-dd"
                placeholder="请选择操作时间">
              </el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="变更原因" prop="reason">
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
import { listErpFieldChangeLog, getErpFieldChangeLog, delErpFieldChangeLog, addErpFieldChangeLog, updateErpFieldChangeLog } from "@/api/platform/fieldLog"

export default {
  name: "ErpFieldChangeLog",
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
      // 字段变更审计日志表格数据
      erpFieldChangeLogList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        bizType: null,
        bizId: null,
        fieldName: null,
        operType: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      }
    }
  },
  created() {
    this.getList()
  },
  methods: {
    /** 查询字段变更审计日志列表 */
    getList() {
      this.loading = true
      listErpFieldChangeLog(this.queryParams).then(response => {
        this.erpFieldChangeLogList = response.rows
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
        logId: null,
        bizType: null,
        bizId: null,
        fieldName: null,
        fieldLabel: null,
        oldValue: null,
        newValue: null,
        operType: null,
        operName: null,
        operIp: null,
        operTime: null,
        reason: null
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
      this.ids = selection.map(item => item.logId)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset()
      this.open = true
      this.title = "添加字段变更审计日志"
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const logId = row.logId || this.ids
      getErpFieldChangeLog(logId).then(response => {
        this.form = response.data
        this.open = true
        this.title = "修改字段变更审计日志"
      })
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.logId != null) {
            updateErpFieldChangeLog(this.form).then(response => {
              this.$modal.msgSuccess("修改成功")
              this.open = false
              this.getList()
            })
          } else {
            addErpFieldChangeLog(this.form).then(response => {
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
      const logIds = row.logId || this.ids
      this.$modal.confirm('是否确认删除字段变更审计日志编号为"' + logIds + '"的数据项？').then(function() {
        return delErpFieldChangeLog(logIds)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess("删除成功")
      }).catch(() => {})
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('platform/fieldLog/export', {
        ...this.queryParams
      }, `fieldLog_${new Date().getTime()}.xlsx`)
    }
  }
}
</script>
