<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="工单号" prop="moCode">
        <el-input
          v-model="queryParams.moCode"
          placeholder="请输入工单号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="状态" prop="status">
        <el-select v-model="queryParams.status" placeholder="请选择状态" clearable>
          <el-option label="计划" value="0" />
          <el-option label="已下达" value="1" />
          <el-option label="领料中" value="2" />
          <el-option label="生产中" value="3" />
          <el-option label="待检验" value="4" />
          <el-option label="完工入库" value="5" />
          <el-option label="已关闭" value="6" />
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
          v-hasPermi="['manufacturing:mo:add']"
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
          v-hasPermi="['manufacturing:mo:edit']"
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
          v-hasPermi="['manufacturing:mo:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['manufacturing:mo:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="moList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="工单号" align="center" prop="moCode" />
      <el-table-column label="产品物料ID" align="center" prop="materialId" />
      <el-table-column label="BOM ID" align="center" prop="bomId" />
      <el-table-column label="工艺路线ID" align="center" prop="routingId" />
      <el-table-column label="计划数量" align="center" prop="planQty" />
      <el-table-column label="完工数量" align="center" prop="finishQty" />
      <el-table-column label="报废数量" align="center" prop="scrapQty" />
      <el-table-column label="计划开工日期" align="center" prop="planStartDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.planStartDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="计划完工日期" align="center" prop="planEndDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.planEndDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="关联销售订单ID" align="center" prop="orderId" />
      <el-table-column label="完工入库仓库" align="center" prop="warehouseId" />
      <el-table-column label="状态" align="center" prop="status">
        <template slot-scope="scope">
          <span v-if="scope.row.status == '0'">计划</span>
          <span v-else-if="scope.row.status == '1'">已下达</span>
          <span v-else-if="scope.row.status == '2'">领料中</span>
          <span v-else-if="scope.row.status == '3'">生产中</span>
          <span v-else-if="scope.row.status == '4'">待检验</span>
          <span v-else-if="scope.row.status == '5'">完工入库</span>
          <span v-else-if="scope.row.status == '6'">已关闭</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['manufacturing:mo:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['manufacturing:mo:remove']"
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

    <!-- 添加或修改生产工单对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="800px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="110px">
        <el-row>
          <el-col :span="12">
            <el-form-item label="工单号" prop="moCode">
              <el-input v-model="form.moCode" placeholder="请输入工单号" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="产品物料ID" prop="materialId">
              <el-input-number v-model="form.materialId" :min="0" controls-position="right" placeholder="请输入产品物料ID" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="BOM ID" prop="bomId">
              <el-input-number v-model="form.bomId" :min="0" controls-position="right" placeholder="请输入BOM ID" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="工艺路线ID" prop="routingId">
              <el-input-number v-model="form.routingId" :min="0" controls-position="right" placeholder="请输入工艺路线ID" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="计划数量" prop="planQty">
              <el-input-number v-model="form.planQty" :precision="2" :min="0" controls-position="right" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="完工数量" prop="finishQty">
              <el-input-number v-model="form.finishQty" :precision="2" :min="0" controls-position="right" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="报废数量" prop="scrapQty">
              <el-input-number v-model="form.scrapQty" :precision="2" :min="0" controls-position="right" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="关联销售订单ID" prop="orderId">
              <el-input-number v-model="form.orderId" :min="0" controls-position="right" placeholder="请输入关联销售订单ID" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="计划开工日期" prop="planStartDate">
              <el-date-picker clearable
                v-model="form.planStartDate"
                type="date"
                value-format="yyyy-MM-dd"
                placeholder="请选择计划开工日期">
              </el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="计划完工日期" prop="planEndDate">
              <el-date-picker clearable
                v-model="form.planEndDate"
                type="date"
                value-format="yyyy-MM-dd"
                placeholder="请选择计划完工日期">
              </el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="完工入库仓库" prop="warehouseId">
              <el-input-number v-model="form.warehouseId" :min="0" controls-position="right" placeholder="请输入完工入库仓库ID" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="状态" prop="status">
              <el-select v-model="form.status" placeholder="请选择状态">
                <el-option label="计划" value="0" />
                <el-option label="已下达" value="1" />
                <el-option label="领料中" value="2" />
                <el-option label="生产中" value="3" />
                <el-option label="待检验" value="4" />
                <el-option label="完工入库" value="5" />
                <el-option label="已关闭" value="6" />
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
import { listErpProductionOrder, getErpProductionOrder, delErpProductionOrder, addErpProductionOrder, updateErpProductionOrder } from "@/api/manufacturing/mo"

export default {
  name: "ErpProductionOrder",
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
      // 生产工单表格数据
      moList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        moCode: null,
        status: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        moCode: [
          { required: true, message: "工单号不能为空", trigger: "blur" }
        ]
      }
    }
  },
  created() {
    this.getList()
  },
  methods: {
    /** 查询生产工单列表 */
    getList() {
      this.loading = true
      listErpProductionOrder(this.queryParams).then(response => {
        this.moList = response.rows
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
        moId: null,
        moCode: null,
        materialId: null,
        bomId: null,
        routingId: null,
        planQty: null,
        finishQty: null,
        scrapQty: null,
        planStartDate: null,
        planEndDate: null,
        orderId: null,
        warehouseId: null,
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
      this.ids = selection.map(item => item.moId)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset()
      this.open = true
      this.title = "添加生产工单"
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const moId = row.moId || this.ids
      getErpProductionOrder(moId).then(response => {
        this.form = response.data
        this.open = true
        this.title = "修改生产工单"
      })
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.moId != null) {
            updateErpProductionOrder(this.form).then(response => {
              this.$modal.msgSuccess("修改成功")
              this.open = false
              this.getList()
            })
          } else {
            addErpProductionOrder(this.form).then(response => {
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
      const moIds = row.moId || this.ids
      this.$modal.confirm('是否确认删除生产工单编号为"' + moIds + '"的数据项？').then(function() {
        return delErpProductionOrder(moIds)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess("删除成功")
      }).catch(() => {})
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('manufacturing/mo/export', {
        ...this.queryParams
      }, `mo_${new Date().getTime()}.xlsx`)
    }
  }
}
</script>
