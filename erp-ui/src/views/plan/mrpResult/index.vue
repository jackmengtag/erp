<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="运算ID" prop="runId">
        <el-input
          v-model="queryParams.runId"
          placeholder="请输入运算ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="建议类型" prop="suggestType">
        <el-select v-model="queryParams.suggestType" placeholder="请选择建议类型" clearable>
          <el-option label="采购" value="采购" />
          <el-option label="生产" value="生产" />
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
          v-hasPermi="['plan:mrpResult:add']"
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
          v-hasPermi="['plan:mrpResult:edit']"
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
          v-hasPermi="['plan:mrpResult:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['plan:mrpResult:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="mrpResultList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="运算ID" align="center" prop="runId" />
      <el-table-column label="物料ID" align="center" prop="materialId" />
      <el-table-column label="毛需求" align="center" prop="grossRequirement" />
      <el-table-column label="可用库存" align="center" prop="availableQty" />
      <el-table-column label="在途供应" align="center" prop="onHandSupply" />
      <el-table-column label="安全库存" align="center" prop="safeStock" />
      <el-table-column label="净需求" align="center" prop="netRequirement" />
      <el-table-column label="建议类型" align="center" prop="suggestType" />
      <el-table-column label="建议数量" align="center" prop="suggestQty" />
      <el-table-column label="需求日期" align="center" prop="needDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.needDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['plan:mrpResult:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['plan:mrpResult:remove']"
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

    <!-- 添加或修改MRP结果对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="800px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="100px">
        <el-row>
          <el-col :span="12">
            <el-form-item label="运算ID" prop="runId">
              <el-input-number v-model="form.runId" :min="0" controls-position="right" placeholder="请输入运算ID" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="物料ID" prop="materialId">
              <el-input-number v-model="form.materialId" :min="0" controls-position="right" placeholder="请输入物料ID" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="毛需求" prop="grossRequirement">
              <el-input-number v-model="form.grossRequirement" :precision="2" :min="0" controls-position="right" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="可用库存" prop="availableQty">
              <el-input-number v-model="form.availableQty" :precision="2" :min="0" controls-position="right" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="在途供应" prop="onHandSupply">
              <el-input-number v-model="form.onHandSupply" :precision="2" :min="0" controls-position="right" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="安全库存" prop="safeStock">
              <el-input-number v-model="form.safeStock" :precision="2" :min="0" controls-position="right" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="净需求" prop="netRequirement">
              <el-input-number v-model="form.netRequirement" :precision="2" :min="0" controls-position="right" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="建议类型" prop="suggestType">
              <el-select v-model="form.suggestType" placeholder="请选择建议类型">
                <el-option label="采购" value="采购" />
                <el-option label="生产" value="生产" />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="建议数量" prop="suggestQty">
              <el-input-number v-model="form.suggestQty" :precision="2" :min="0" controls-position="right" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="需求日期" prop="needDate">
              <el-date-picker clearable
                v-model="form.needDate"
                type="date"
                value-format="yyyy-MM-dd"
                placeholder="请选择需求日期">
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
import { listErpMrpResult, getErpMrpResult, delErpMrpResult, addErpMrpResult, updateErpMrpResult } from "@/api/plan/mrpResult"

export default {
  name: "ErpMrpResult",
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
      // MRP结果表格数据
      mrpResultList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        runId: null,
        suggestType: null
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
    /** 查询MRP结果列表 */
    getList() {
      this.loading = true
      listErpMrpResult(this.queryParams).then(response => {
        this.mrpResultList = response.rows
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
        resultId: null,
        runId: null,
        materialId: null,
        grossRequirement: null,
        availableQty: null,
        onHandSupply: null,
        safeStock: null,
        netRequirement: null,
        suggestType: null,
        suggestQty: null,
        needDate: null
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
      this.ids = selection.map(item => item.resultId)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset()
      this.open = true
      this.title = "添加MRP结果"
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const resultId = row.resultId || this.ids
      getErpMrpResult(resultId).then(response => {
        this.form = response.data
        this.open = true
        this.title = "修改MRP结果"
      })
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.resultId != null) {
            updateErpMrpResult(this.form).then(response => {
              this.$modal.msgSuccess("修改成功")
              this.open = false
              this.getList()
            })
          } else {
            addErpMrpResult(this.form).then(response => {
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
      const resultIds = row.resultId || this.ids
      this.$modal.confirm('是否确认删除MRP结果编号为"' + resultIds + '"的数据项？').then(function() {
        return delErpMrpResult(resultIds)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess("删除成功")
      }).catch(() => {})
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('plan/mrpResult/export', {
        ...this.queryParams
      }, `mrpResult_${new Date().getTime()}.xlsx`)
    }
  }
}
</script>
