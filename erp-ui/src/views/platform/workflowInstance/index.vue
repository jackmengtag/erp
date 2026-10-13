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
      <el-form-item label="单据标题" prop="bizTitle">
        <el-input
          v-model="queryParams.bizTitle"
          placeholder="请输入单据标题"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="状态" prop="status">
        <el-select v-model="queryParams.status" placeholder="请选择状态" clearable>
          <el-option label="审批中" value="0" />
          <el-option label="通过" value="1" />
          <el-option label="驳回" value="2" />
          <el-option label="撤回" value="3" />
        </el-select>
      </el-form-item>
      <el-form-item label="提交时间">
        <el-date-picker
          v-model="daterangeSubmitTime"
          style="width: 240px"
          value-format="yyyy-MM-dd"
          type="daterange"
          range-separator="-"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
        ></el-date-picker>
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
          v-hasPermi="['platform:workflowInstance:add']"
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
          v-hasPermi="['platform:workflowInstance:edit']"
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
          v-hasPermi="['platform:workflowInstance:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['platform:workflowInstance:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="erpWorkflowInstanceList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="实例ID" align="center" prop="instanceId" />
      <el-table-column label="工作流ID" align="center" prop="wfId" />
      <el-table-column label="业务类型" align="center" prop="bizType" />
      <el-table-column label="业务单据ID" align="center" prop="bizId" />
      <el-table-column label="单据标题" align="center" prop="bizTitle" />
      <el-table-column label="单据金额" align="center" prop="amount" />
      <el-table-column label="当前节点" align="center" prop="currentNode" />
      <el-table-column label="状态" align="center" prop="status">
        <template slot-scope="scope">
          <span v-if="scope.row.status == 0">审批中</span>
          <span v-else-if="scope.row.status == 1">通过</span>
          <span v-else-if="scope.row.status == 2">驳回</span>
          <span v-else-if="scope.row.status == 3">撤回</span>
        </template>
      </el-table-column>
      <el-table-column label="提交人" align="center" prop="submitBy" />
      <el-table-column label="提交时间" align="center" prop="submitTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.submitTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="完成时间" align="center" prop="finishTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.finishTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['platform:workflowInstance:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['platform:workflowInstance:remove']"
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

    <!-- 添加或修改工作流实例对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="800px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="100px">
        <el-row>
          <el-col :span="12">
            <el-form-item label="工作流ID" prop="wfId">
              <el-input-number v-model="form.wfId" :min="0" placeholder="请输入工作流ID" />
            </el-form-item>
          </el-col>
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
            <el-form-item label="单据标题" prop="bizTitle">
              <el-input v-model="form.bizTitle" placeholder="请输入单据标题" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="单据金额" prop="amount">
              <el-input-number v-model="form.amount" :precision="2" :min="0" placeholder="请输入单据金额" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="当前节点" prop="currentNode">
              <el-input v-model="form.currentNode" placeholder="请输入当前节点" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="状态" prop="status">
              <el-select v-model="form.status" placeholder="请选择状态">
                <el-option label="审批中" value="0" />
                <el-option label="通过" value="1" />
                <el-option label="驳回" value="2" />
                <el-option label="撤回" value="3" />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="提交人" prop="submitBy">
              <el-input v-model="form.submitBy" placeholder="请输入提交人" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="提交时间" prop="submitTime">
              <el-date-picker clearable
                v-model="form.submitTime"
                type="date"
                value-format="yyyy-MM-dd"
                placeholder="请选择提交时间">
              </el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="完成时间" prop="finishTime">
              <el-date-picker clearable
                v-model="form.finishTime"
                type="date"
                value-format="yyyy-MM-dd"
                placeholder="请选择完成时间">
              </el-date-picker>
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
import { listErpWorkflowInstance, getErpWorkflowInstance, delErpWorkflowInstance, addErpWorkflowInstance, updateErpWorkflowInstance } from "@/api/platform/workflowInstance"

export default {
  name: "ErpWorkflowInstance",
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
      // 工作流实例表格数据
      erpWorkflowInstanceList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 提交时间时间范围
      daterangeSubmitTime: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        bizType: null,
        bizId: null,
        bizTitle: null,
        status: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        wfId: [
          { required: true, message: "工作流ID不能为空", trigger: "blur" }
        ]
      }
    }
  },
  created() {
    this.getList()
  },
  methods: {
    /** 查询工作流实例列表 */
    getList() {
      this.loading = true
      this.queryParams.params = {}
      if (null != this.daterangeSubmitTime && '' != this.daterangeSubmitTime) {
        this.queryParams.params["beginSubmitTime"] = this.daterangeSubmitTime[0]
        this.queryParams.params["endSubmitTime"] = this.daterangeSubmitTime[1]
      }
      listErpWorkflowInstance(this.queryParams).then(response => {
        this.erpWorkflowInstanceList = response.rows
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
        instanceId: null,
        wfId: null,
        bizType: null,
        bizId: null,
        bizTitle: null,
        amount: null,
        currentNode: null,
        status: null,
        submitBy: null,
        submitTime: null,
        finishTime: null
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
      this.daterangeSubmitTime = []
      this.resetForm("queryForm")
      this.handleQuery()
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.instanceId)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset()
      this.open = true
      this.title = "添加工作流实例"
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const instanceId = row.instanceId || this.ids
      getErpWorkflowInstance(instanceId).then(response => {
        this.form = response.data
        this.open = true
        this.title = "修改工作流实例"
      })
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.instanceId != null) {
            updateErpWorkflowInstance(this.form).then(response => {
              this.$modal.msgSuccess("修改成功")
              this.open = false
              this.getList()
            })
          } else {
            addErpWorkflowInstance(this.form).then(response => {
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
      const instanceIds = row.instanceId || this.ids
      this.$modal.confirm('是否确认删除工作流实例编号为"' + instanceIds + '"的数据项？').then(function() {
        return delErpWorkflowInstance(instanceIds)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess("删除成功")
      }).catch(() => {})
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('platform/workflowInstance/export', {
        ...this.queryParams
      }, `workflowInstance_${new Date().getTime()}.xlsx`)
    }
  }
}
</script>
