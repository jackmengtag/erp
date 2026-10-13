<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="100px">
      <el-form-item label="工单ID" prop="moId">
        <el-input-number v-model="queryParams.moId" :min="0" placeholder="请输入工单ID" />
      </el-form-item>
      <el-form-item label="会计期间" prop="periods">
        <el-input
          v-model="queryParams.periods"
          placeholder="请输入会计期间"
          clearable
          @keyup.enter.native="handleQuery"
        />
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
          v-hasPermi="['finance:productionCost:add']"
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
          v-hasPermi="['finance:productionCost:edit']"
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
          v-hasPermi="['finance:productionCost:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['finance:productionCost:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="productionCostList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="成本ID" align="center" prop="costId" />
      <el-table-column label="工单ID" align="center" prop="moId" />
      <el-table-column label="会计期间" align="center" prop="periods" />
      <el-table-column label="材料成本" align="center" prop="materialCost" />
      <el-table-column label="人工成本" align="center" prop="laborCost" />
      <el-table-column label="制造费用" align="center" prop="overheadCost" />
      <el-table-column label="委外加工费" align="center" prop="outsourceCost" />
      <el-table-column label="成本合计" align="center" prop="totalCost" />
      <el-table-column label="单位成本" align="center" prop="unitCost" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['finance:productionCost:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['finance:productionCost:remove']"
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

    <!-- 添加或修改生产成本对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="800px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="100px">
        <el-row>
          <el-col :span="12">
            <el-form-item label="工单ID" prop="moId">
              <el-input-number v-model="form.moId" :min="0" placeholder="请输入工单ID" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="会计期间" prop="periods">
              <el-input v-model="form.periods" placeholder="请输入会计期间" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="材料成本" prop="materialCost">
              <el-input-number v-model="form.materialCost" :precision="2" :min="0" placeholder="请输入材料成本" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="人工成本" prop="laborCost">
              <el-input-number v-model="form.laborCost" :precision="2" :min="0" placeholder="请输入人工成本" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="制造费用" prop="overheadCost">
              <el-input-number v-model="form.overheadCost" :precision="2" :min="0" placeholder="请输入制造费用" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="委外加工费" prop="outsourceCost">
              <el-input-number v-model="form.outsourceCost" :precision="2" :min="0" placeholder="请输入委外加工费" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="成本合计" prop="totalCost">
              <el-input-number v-model="form.totalCost" :precision="2" :min="0" placeholder="请输入成本合计" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="单位成本" prop="unitCost">
              <el-input-number v-model="form.unitCost" :precision="4" :min="0" placeholder="请输入单位成本" />
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
import { listErpProductionCost, getErpProductionCost, delErpProductionCost, addErpProductionCost, updateErpProductionCost } from "@/api/finance/productionCost"

export default {
  name: "ErpProductionCost",
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
      // 生产成本表格数据
      productionCostList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        moId: null,
        periods: null
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
    /** 查询生产成本列表 */
    getList() {
      this.loading = true
      listErpProductionCost(this.queryParams).then(response => {
        this.productionCostList = response.rows
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
        costId: null,
        moId: null,
        periods: null,
        materialCost: null,
        laborCost: null,
        overheadCost: null,
        outsourceCost: null,
        totalCost: null,
        unitCost: null,
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
      this.ids = selection.map(item => item.costId)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset()
      this.open = true
      this.title = "添加生产成本"
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const costId = row.costId || this.ids
      getErpProductionCost(costId).then(response => {
        this.form = response.data
        this.open = true
        this.title = "修改生产成本"
      })
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.costId != null) {
            updateErpProductionCost(this.form).then(response => {
              this.$modal.msgSuccess("修改成功")
              this.open = false
              this.getList()
            })
          } else {
            addErpProductionCost(this.form).then(response => {
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
      const costIds = row.costId || this.ids
      this.$modal.confirm('是否确认删除生产成本编号为"' + costIds + '"的数据项？').then(function() {
        return delErpProductionCost(costIds)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess("删除成功")
      }).catch(() => {})
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('finance/productionCost/export', {
        ...this.queryParams
      }, `productionCost_${new Date().getTime()}.xlsx`)
    }
  }
}
</script>
