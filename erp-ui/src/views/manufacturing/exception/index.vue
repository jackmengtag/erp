<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="异常单号" prop="exceptionCode">
        <el-input
          v-model="queryParams.exceptionCode"
          placeholder="请输入异常单号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="类型" prop="exceptionType">
        <el-select v-model="queryParams.exceptionType" placeholder="请选择类型" clearable>
          <el-option label="缺料" value="1" />
          <el-option label="设备停机" value="2" />
          <el-option label="质量异常" value="3" />
          <el-option label="工艺变更" value="4" />
          <el-option label="返工" value="5" />
          <el-option label="报废" value="6" />
          <el-option label="计划延期" value="7" />
        </el-select>
      </el-form-item>
      <el-form-item label="处理状态" prop="handleStatus">
        <el-select v-model="queryParams.handleStatus" placeholder="请选择处理状态" clearable>
          <el-option label="待处理" value="0" />
          <el-option label="处理中" value="1" />
          <el-option label="已关闭" value="2" />
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
          v-hasPermi="['manufacturing:exception:add']"
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
          v-hasPermi="['manufacturing:exception:edit']"
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
          v-hasPermi="['manufacturing:exception:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['manufacturing:exception:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="exceptionList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="异常单号" align="center" prop="exceptionCode" />
      <el-table-column label="工单ID" align="center" prop="moId" />
      <el-table-column label="类型" align="center" prop="exceptionType">
        <template slot-scope="scope">
          <span v-if="scope.row.exceptionType == '1'">缺料</span>
          <span v-else-if="scope.row.exceptionType == '2'">设备停机</span>
          <span v-else-if="scope.row.exceptionType == '3'">质量异常</span>
          <span v-else-if="scope.row.exceptionType == '4'">工艺变更</span>
          <span v-else-if="scope.row.exceptionType == '5'">返工</span>
          <span v-else-if="scope.row.exceptionType == '6'">报废</span>
          <span v-else-if="scope.row.exceptionType == '7'">计划延期</span>
        </template>
      </el-table-column>
      <el-table-column label="异常描述" align="center" prop="exceptionDesc" />
      <el-table-column label="处理措施" align="center" prop="handleMeasure" />
      <el-table-column label="处理状态" align="center" prop="handleStatus">
        <template slot-scope="scope">
          <span v-if="scope.row.handleStatus == '0'">待处理</span>
          <span v-else-if="scope.row.handleStatus == '1'">处理中</span>
          <span v-else-if="scope.row.handleStatus == '2'">已关闭</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['manufacturing:exception:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['manufacturing:exception:remove']"
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

    <!-- 添加或修改生产异常对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="800px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="100px">
        <el-row>
          <el-col :span="12">
            <el-form-item label="异常单号" prop="exceptionCode">
              <el-input v-model="form.exceptionCode" placeholder="请输入异常单号" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="工单ID" prop="moId">
              <el-input-number v-model="form.moId" :min="0" controls-position="right" placeholder="请输入工单ID" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="类型" prop="exceptionType">
              <el-select v-model="form.exceptionType" placeholder="请选择类型">
                <el-option label="缺料" value="1" />
                <el-option label="设备停机" value="2" />
                <el-option label="质量异常" value="3" />
                <el-option label="工艺变更" value="4" />
                <el-option label="返工" value="5" />
                <el-option label="报废" value="6" />
                <el-option label="计划延期" value="7" />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="处理状态" prop="handleStatus">
              <el-select v-model="form.handleStatus" placeholder="请选择处理状态">
                <el-option label="待处理" value="0" />
                <el-option label="处理中" value="1" />
                <el-option label="已关闭" value="2" />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="异常描述" prop="exceptionDesc">
              <el-input v-model="form.exceptionDesc" type="textarea" placeholder="请输入内容" />
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="处理措施" prop="handleMeasure">
              <el-input v-model="form.handleMeasure" type="textarea" placeholder="请输入内容" />
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="备注" prop="remark">
              <el-input v-model="form.remark" type="textarea" placeholder="请输入内容" />
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
import { listErpProductionException, getErpProductionException, delErpProductionException, addErpProductionException, updateErpProductionException } from "@/api/manufacturing/exception"

export default {
  name: "ErpProductionException",
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
      // 生产异常表格数据
      exceptionList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        exceptionCode: null,
        exceptionType: null,
        handleStatus: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        exceptionCode: [
          { required: true, message: "异常单号不能为空", trigger: "blur" }
        ]
      }
    }
  },
  created() {
    this.getList()
  },
  methods: {
    /** 查询生产异常列表 */
    getList() {
      this.loading = true
      listErpProductionException(this.queryParams).then(response => {
        this.exceptionList = response.rows
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
        exceptionId: null,
        exceptionCode: null,
        moId: null,
        exceptionType: null,
        exceptionDesc: null,
        handleMeasure: null,
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
      this.ids = selection.map(item => item.exceptionId)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset()
      this.open = true
      this.title = "添加生产异常"
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const exceptionId = row.exceptionId || this.ids
      getErpProductionException(exceptionId).then(response => {
        this.form = response.data
        this.open = true
        this.title = "修改生产异常"
      })
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.exceptionId != null) {
            updateErpProductionException(this.form).then(response => {
              this.$modal.msgSuccess("修改成功")
              this.open = false
              this.getList()
            })
          } else {
            addErpProductionException(this.form).then(response => {
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
      const exceptionIds = row.exceptionId || this.ids
      this.$modal.confirm('是否确认删除生产异常编号为"' + exceptionIds + '"的数据项？').then(function() {
        return delErpProductionException(exceptionIds)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess("删除成功")
      }).catch(() => {})
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('manufacturing/exception/export', {
        ...this.queryParams
      }, `exception_${new Date().getTime()}.xlsx`)
    }
  }
}
</script>
