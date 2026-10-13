<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="完工单号" prop="completionCode">
        <el-input
          v-model="queryParams.completionCode"
          placeholder="请输入完工单号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="成品批次号" prop="batchNo">
        <el-input
          v-model="queryParams.batchNo"
          placeholder="请输入成品批次号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="状态" prop="status">
        <el-select v-model="queryParams.status" placeholder="请选择状态" clearable>
          <el-option label="草稿" value="0" />
          <el-option label="待检验" value="1" />
          <el-option label="已入库" value="2" />
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
          v-hasPermi="['manufacturing:completion:add']"
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
          v-hasPermi="['manufacturing:completion:edit']"
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
          v-hasPermi="['manufacturing:completion:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['manufacturing:completion:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="completionList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="完工单号" align="center" prop="completionCode" />
      <el-table-column label="工单ID" align="center" prop="moId" />
      <el-table-column label="入库仓库ID" align="center" prop="warehouseId" />
      <el-table-column label="成品批次号" align="center" prop="batchNo" />
      <el-table-column label="入库数量" align="center" prop="qty" />
      <el-table-column label="完工日期" align="center" prop="completionDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.completionDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="状态" align="center" prop="status">
        <template slot-scope="scope">
          <span v-if="scope.row.status == '0'">草稿</span>
          <span v-else-if="scope.row.status == '1'">待检验</span>
          <span v-else-if="scope.row.status == '2'">已入库</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['manufacturing:completion:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['manufacturing:completion:remove']"
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

    <!-- 添加或修改完工入库对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="800px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="100px">
        <el-row>
          <el-col :span="12">
            <el-form-item label="完工单号" prop="completionCode">
              <el-input v-model="form.completionCode" placeholder="请输入完工单号" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="工单ID" prop="moId">
              <el-input-number v-model="form.moId" :min="0" controls-position="right" placeholder="请输入工单ID" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="入库仓库ID" prop="warehouseId">
              <el-input-number v-model="form.warehouseId" :min="0" controls-position="right" placeholder="请输入入库仓库ID" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="成品批次号" prop="batchNo">
              <el-input v-model="form.batchNo" placeholder="请输入成品批次号" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="入库数量" prop="qty">
              <el-input-number v-model="form.qty" :precision="2" :min="0" controls-position="right" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="完工日期" prop="completionDate">
              <el-date-picker clearable
                v-model="form.completionDate"
                type="date"
                value-format="yyyy-MM-dd"
                placeholder="请选择完工日期">
              </el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="状态" prop="status">
              <el-select v-model="form.status" placeholder="请选择状态">
                <el-option label="草稿" value="0" />
                <el-option label="待检验" value="1" />
                <el-option label="已入库" value="2" />
              </el-select>
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
import { listErpProductionCompletion, getErpProductionCompletion, delErpProductionCompletion, addErpProductionCompletion, updateErpProductionCompletion } from "@/api/manufacturing/completion"

export default {
  name: "ErpProductionCompletion",
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
      // 完工入库表格数据
      completionList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        completionCode: null,
        batchNo: null,
        status: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        completionCode: [
          { required: true, message: "完工单号不能为空", trigger: "blur" }
        ]
      }
    }
  },
  created() {
    this.getList()
  },
  methods: {
    /** 查询完工入库列表 */
    getList() {
      this.loading = true
      listErpProductionCompletion(this.queryParams).then(response => {
        this.completionList = response.rows
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
        completionId: null,
        completionCode: null,
        moId: null,
        warehouseId: null,
        batchNo: null,
        qty: null,
        completionDate: null,
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
      this.ids = selection.map(item => item.completionId)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset()
      this.open = true
      this.title = "添加完工入库"
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const completionId = row.completionId || this.ids
      getErpProductionCompletion(completionId).then(response => {
        this.form = response.data
        this.open = true
        this.title = "修改完工入库"
      })
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.completionId != null) {
            updateErpProductionCompletion(this.form).then(response => {
              this.$modal.msgSuccess("修改成功")
              this.open = false
              this.getList()
            })
          } else {
            addErpProductionCompletion(this.form).then(response => {
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
      const completionIds = row.completionId || this.ids
      this.$modal.confirm('是否确认删除完工入库编号为"' + completionIds + '"的数据项？').then(function() {
        return delErpProductionCompletion(completionIds)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess("删除成功")
      }).catch(() => {})
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('manufacturing/completion/export', {
        ...this.queryParams
      }, `completion_${new Date().getTime()}.xlsx`)
    }
  }
}
</script>
