<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="预测单号" prop="forecastCode">
        <el-input
          v-model="queryParams.forecastCode"
          placeholder="请输入预测单号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="预测名称" prop="forecastName">
        <el-input
          v-model="queryParams.forecastName"
          placeholder="请输入预测名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="状态" prop="status">
        <el-select v-model="queryParams.status" placeholder="请选择状态" clearable>
          <el-option label="草稿" value="0" />
          <el-option label="已发布" value="1" />
          <el-option label="已冻结" value="2" />
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
          v-hasPermi="['plan:forecast:add']"
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
          v-hasPermi="['plan:forecast:edit']"
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
          v-hasPermi="['plan:forecast:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['plan:forecast:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="forecastList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="预测单号" align="center" prop="forecastCode" />
      <el-table-column label="预测名称" align="center" prop="forecastName" />
      <el-table-column label="预测版本" align="center" prop="forecastVersion" />
      <el-table-column label="期间开始" align="center" prop="periodStart" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.periodStart, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="期间结束" align="center" prop="periodEnd" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.periodEnd, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="状态" align="center" prop="status">
        <template slot-scope="scope">
          <span v-if="scope.row.status == '0'">草稿</span>
          <span v-else-if="scope.row.status == '1'">已发布</span>
          <span v-else-if="scope.row.status == '2'">已冻结</span>
          <span v-else-if="scope.row.status == '3'">已关闭</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['plan:forecast:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['plan:forecast:remove']"
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

    <!-- 添加或修改需求预测对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="1100px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="100px">
        <el-row>
          <el-col :span="12">
            <el-form-item label="预测单号" prop="forecastCode">
              <el-input v-model="form.forecastCode" placeholder="请输入预测单号" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="预测名称" prop="forecastName">
              <el-input v-model="form.forecastName" placeholder="请输入预测名称" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="预测版本" prop="forecastVersion">
              <el-input v-model="form.forecastVersion" placeholder="请输入预测版本" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="状态" prop="status">
              <el-select v-model="form.status" placeholder="请选择状态">
                <el-option label="草稿" value="0" />
                <el-option label="已发布" value="1" />
                <el-option label="已冻结" value="2" />
                <el-option label="已关闭" value="3" />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="期间开始" prop="periodStart">
              <el-date-picker clearable
                v-model="form.periodStart"
                type="date"
                value-format="yyyy-MM-dd"
                placeholder="请选择期间开始">
              </el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="期间结束" prop="periodEnd">
              <el-date-picker clearable
                v-model="form.periodEnd"
                type="date"
                value-format="yyyy-MM-dd"
                placeholder="请选择期间结束">
              </el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="备注" prop="remark">
              <el-input v-model="form.remark" type="textarea" placeholder="请输入内容" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-divider content-position="center">需求预测明细信息</el-divider>
        <el-row :gutter="10" class="mb8">
          <el-col :span="1.5">
            <el-button type="primary" icon="el-icon-plus" size="mini" @click="handleAddErpDemandForecastLine">添加</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button type="danger" icon="el-icon-delete" size="mini" @click="handleDeleteErpDemandForecastLine">删除</el-button>
          </el-col>
        </el-row>
        <el-table :data="erpDemandForecastLineList" :row-class-name="rowErpDemandForecastLineIndex" @selection-change="handleErpDemandForecastLineSelectionChange" ref="erpDemandForecastLine">
          <el-table-column type="selection" width="50" align="center" />
          <el-table-column label="序号" align="center" prop="index" width="50"/>
          <el-table-column label="物料ID" prop="materialId" width="150">
            <template slot-scope="scope">
              <el-input-number v-model="scope.row.materialId" :min="0" controls-position="right" placeholder="请输入物料ID" />
            </template>
          </el-table-column>
          <el-table-column label="期间" prop="periodMonth" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.periodMonth" placeholder="请输入期间(YYYY-MM)" />
            </template>
          </el-table-column>
          <el-table-column label="预测数量" prop="forecastQty" width="150">
            <template slot-scope="scope">
              <el-input-number v-model="scope.row.forecastQty" :precision="2" :min="0" controls-position="right" />
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
import { listErpDemandForecast, getErpDemandForecast, delErpDemandForecast, addErpDemandForecast, updateErpDemandForecast } from "@/api/plan/forecast"

export default {
  name: "ErpDemandForecast",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 子表选中数据
      checkedErpDemandForecastLine: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 需求预测表格数据
      forecastList: [],
      // 需求预测明细表格数据
      erpDemandForecastLineList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        forecastCode: null,
        forecastName: null,
        status: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        forecastCode: [
          { required: true, message: "预测单号不能为空", trigger: "blur" }
        ],
        forecastName: [
          { required: true, message: "预测名称不能为空", trigger: "blur" }
        ]
      }
    }
  },
  created() {
    this.getList()
  },
  methods: {
    /** 查询需求预测列表 */
    getList() {
      this.loading = true
      listErpDemandForecast(this.queryParams).then(response => {
        this.forecastList = response.rows
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
        forecastId: null,
        forecastCode: null,
        forecastName: null,
        forecastVersion: null,
        periodStart: null,
        periodEnd: null,
        status: null,
        remark: null
      }
      this.erpDemandForecastLineList = []
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
      this.ids = selection.map(item => item.forecastId)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset()
      this.open = true
      this.title = "添加需求预测"
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const forecastId = row.forecastId || this.ids
      getErpDemandForecast(forecastId).then(response => {
        this.form = response.data
        this.erpDemandForecastLineList = response.data.erpDemandForecastLineList
        this.open = true
        this.title = "修改需求预测"
      })
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          this.form.erpDemandForecastLineList = this.erpDemandForecastLineList
          if (this.form.forecastId != null) {
            updateErpDemandForecast(this.form).then(response => {
              this.$modal.msgSuccess("修改成功")
              this.open = false
              this.getList()
            })
          } else {
            addErpDemandForecast(this.form).then(response => {
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
      const forecastIds = row.forecastId || this.ids
      this.$modal.confirm('是否确认删除需求预测编号为"' + forecastIds + '"的数据项？').then(function() {
        return delErpDemandForecast(forecastIds)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess("删除成功")
      }).catch(() => {})
    },
    /** 需求预测明细序号 */
    rowErpDemandForecastLineIndex({ row, rowIndex }) {
      row.index = rowIndex + 1
    },
    /** 需求预测明细添加按钮操作 */
    handleAddErpDemandForecastLine() {
      let obj = {}
      obj.materialId = ""
      obj.periodMonth = ""
      obj.forecastQty = ""
      this.erpDemandForecastLineList.push(obj)
    },
    /** 需求预测明细删除按钮操作 */
    handleDeleteErpDemandForecastLine() {
      if (this.checkedErpDemandForecastLine.length == 0) {
        this.$modal.msgError("请先选择要删除的需求预测明细数据")
      } else {
        const erpDemandForecastLineList = this.erpDemandForecastLineList
        const checkedErpDemandForecastLine = this.checkedErpDemandForecastLine
        this.erpDemandForecastLineList = erpDemandForecastLineList.filter(function(item) {
          return checkedErpDemandForecastLine.indexOf(item.index) == -1
        })
      }
    },
    /** 复选框选中数据 */
    handleErpDemandForecastLineSelectionChange(selection) {
      this.checkedErpDemandForecastLine = selection.map(item => item.index)
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('plan/forecast/export', {
        ...this.queryParams
      }, `forecast_${new Date().getTime()}.xlsx`)
    }
  }
}
</script>
