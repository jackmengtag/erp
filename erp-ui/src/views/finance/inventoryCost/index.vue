<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="110px">
      <el-form-item label="物料ID" prop="materialId">
        <el-input-number v-model="queryParams.materialId" :min="0" placeholder="请输入物料ID" />
      </el-form-item>
      <el-form-item label="仓库ID" prop="warehouseId">
        <el-input-number v-model="queryParams.warehouseId" :min="0" placeholder="请输入仓库ID" />
      </el-form-item>
      <el-form-item label="会计期间" prop="periods">
        <el-input
          v-model="queryParams.periods"
          placeholder="请输入会计期间(YYYY-MM)"
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
          v-hasPermi="['finance:inventoryCost:add']"
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
          v-hasPermi="['finance:inventoryCost:edit']"
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
          v-hasPermi="['finance:inventoryCost:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['finance:inventoryCost:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="inventoryCostList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="核算ID" align="center" prop="costId" />
      <el-table-column label="物料ID" align="center" prop="materialId" />
      <el-table-column label="仓库ID" align="center" prop="warehouseId" />
      <el-table-column label="会计期间(YYYY-MM)" align="center" prop="periods" />
      <el-table-column label="期初数量" align="center" prop="beginQty" />
      <el-table-column label="期初金额" align="center" prop="beginAmount" />
      <el-table-column label="入库数量" align="center" prop="inQty" />
      <el-table-column label="入库金额" align="center" prop="inAmount" />
      <el-table-column label="出库数量" align="center" prop="outQty" />
      <el-table-column label="出库金额" align="center" prop="outAmount" />
      <el-table-column label="期末数量" align="center" prop="endQty" />
      <el-table-column label="期末金额" align="center" prop="endAmount" />
      <el-table-column label="结存单价" align="center" prop="unitCost" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['finance:inventoryCost:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['finance:inventoryCost:remove']"
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

    <!-- 添加或修改存货核算对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="800px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="110px">
        <el-row>
          <el-col :span="12">
            <el-form-item label="物料ID" prop="materialId">
              <el-input-number v-model="form.materialId" :min="0" placeholder="请输入物料ID" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="仓库ID" prop="warehouseId">
              <el-input-number v-model="form.warehouseId" :min="0" placeholder="请输入仓库ID" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="会计期间" prop="periods">
              <el-input v-model="form.periods" placeholder="请输入会计期间(YYYY-MM)" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="期初数量" prop="beginQty">
              <el-input-number v-model="form.beginQty" :precision="4" :min="0" placeholder="请输入期初数量" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="期初金额" prop="beginAmount">
              <el-input-number v-model="form.beginAmount" :precision="2" :min="0" placeholder="请输入期初金额" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="入库数量" prop="inQty">
              <el-input-number v-model="form.inQty" :precision="4" :min="0" placeholder="请输入入库数量" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="入库金额" prop="inAmount">
              <el-input-number v-model="form.inAmount" :precision="2" :min="0" placeholder="请输入入库金额" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="出库数量" prop="outQty">
              <el-input-number v-model="form.outQty" :precision="4" :min="0" placeholder="请输入出库数量" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="出库金额" prop="outAmount">
              <el-input-number v-model="form.outAmount" :precision="2" :min="0" placeholder="请输入出库金额" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="期末数量" prop="endQty">
              <el-input-number v-model="form.endQty" :precision="4" :min="0" placeholder="请输入期末数量" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="期末金额" prop="endAmount">
              <el-input-number v-model="form.endAmount" :precision="2" :min="0" placeholder="请输入期末金额" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="结存单价" prop="unitCost">
              <el-input-number v-model="form.unitCost" :precision="4" :min="0" placeholder="请输入结存单价" />
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
import { listErpInventoryCost, getErpInventoryCost, delErpInventoryCost, addErpInventoryCost, updateErpInventoryCost } from "@/api/finance/inventoryCost"

export default {
  name: "ErpInventoryCost",
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
      // 存货核算表格数据
      inventoryCostList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        materialId: null,
        warehouseId: null,
        periods: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        materialId: [
          { required: true, message: "物料ID不能为空", trigger: "blur" }
        ]
      }
    }
  },
  created() {
    this.getList()
  },
  methods: {
    /** 查询存货核算列表 */
    getList() {
      this.loading = true
      listErpInventoryCost(this.queryParams).then(response => {
        this.inventoryCostList = response.rows
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
        materialId: null,
        warehouseId: null,
        periods: null,
        beginQty: null,
        beginAmount: null,
        inQty: null,
        inAmount: null,
        outQty: null,
        outAmount: null,
        endQty: null,
        endAmount: null,
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
      this.title = "添加存货核算"
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const costId = row.costId || this.ids
      getErpInventoryCost(costId).then(response => {
        this.form = response.data
        this.open = true
        this.title = "修改存货核算"
      })
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.costId != null) {
            updateErpInventoryCost(this.form).then(response => {
              this.$modal.msgSuccess("修改成功")
              this.open = false
              this.getList()
            })
          } else {
            addErpInventoryCost(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除存货核算编号为"' + costIds + '"的数据项？').then(function() {
        return delErpInventoryCost(costIds)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess("删除成功")
      }).catch(() => {})
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('finance/inventoryCost/export', {
        ...this.queryParams
      }, `inventoryCost_${new Date().getTime()}.xlsx`)
    }
  }
}
</script>
