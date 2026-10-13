<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="盘点单号" prop="stocktakeCode">
        <el-input
          v-model="queryParams.stocktakeCode"
          placeholder="请输入盘点单号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="盘点类型" prop="stocktakeType">
        <el-select v-model="queryParams.stocktakeType" placeholder="请选择盘点类型" clearable>
          <el-option label="全盘" value="1" />
          <el-option label="循环盘点" value="2" />
          <el-option label="盲盘" value="3" />
        </el-select>
      </el-form-item>
      <el-form-item label="状态" prop="status">
        <el-select v-model="queryParams.status" placeholder="请选择状态" clearable>
          <el-option label="草稿" value="0" />
          <el-option label="盘点中" value="1" />
          <el-option label="已审核" value="2" />
          <el-option label="已调整" value="3" />
          <el-option label="已关闭" value="4" />
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
          v-hasPermi="['inventory:stocktake:add']"
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
          v-hasPermi="['inventory:stocktake:edit']"
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
          v-hasPermi="['inventory:stocktake:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['inventory:stocktake:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="stocktakeList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="盘点单号" align="center" prop="stocktakeCode" />
      <el-table-column label="仓库ID" align="center" prop="warehouseId" />
      <el-table-column label="盘点类型" align="center" prop="stocktakeType">
        <template slot-scope="scope">
          <span v-if="scope.row.stocktakeType == '1'">全盘</span>
          <span v-else-if="scope.row.stocktakeType == '2'">循环盘点</span>
          <span v-else-if="scope.row.stocktakeType == '3'">盲盘</span>
        </template>
      </el-table-column>
      <el-table-column label="盘点日期" align="center" prop="stocktakeDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.stocktakeDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="状态" align="center" prop="status">
        <template slot-scope="scope">
          <span v-if="scope.row.status == '0'">草稿</span>
          <span v-else-if="scope.row.status == '1'">盘点中</span>
          <span v-else-if="scope.row.status == '2'">已审核</span>
          <span v-else-if="scope.row.status == '3'">已调整</span>
          <span v-else-if="scope.row.status == '4'">已关闭</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['inventory:stocktake:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['inventory:stocktake:remove']"
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

    <!-- 添加或修改盘点单对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="1100px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="100px">
        <el-row>
          <el-col :span="12">
            <el-form-item label="盘点单号" prop="stocktakeCode">
              <el-input v-model="form.stocktakeCode" placeholder="请输入盘点单号" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="仓库ID" prop="warehouseId">
              <el-input-number v-model="form.warehouseId" :min="0" controls-position="right" placeholder="请输入仓库ID" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="盘点类型" prop="stocktakeType">
              <el-select v-model="form.stocktakeType" placeholder="请选择盘点类型">
                <el-option label="全盘" value="1" />
                <el-option label="循环盘点" value="2" />
                <el-option label="盲盘" value="3" />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="盘点日期" prop="stocktakeDate">
              <el-date-picker clearable
                v-model="form.stocktakeDate"
                type="date"
                value-format="yyyy-MM-dd"
                placeholder="请选择盘点日期">
              </el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="状态" prop="status">
              <el-select v-model="form.status" placeholder="请选择状态">
                <el-option label="草稿" value="0" />
                <el-option label="盘点中" value="1" />
                <el-option label="已审核" value="2" />
                <el-option label="已调整" value="3" />
                <el-option label="已关闭" value="4" />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="备注" prop="remark">
              <el-input v-model="form.remark" type="textarea" placeholder="请输入内容" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-divider content-position="center">盘点单明细信息</el-divider>
        <el-row :gutter="10" class="mb8">
          <el-col :span="1.5">
            <el-button type="primary" icon="el-icon-plus" size="mini" @click="handleAddErpStocktakeLine">添加</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button type="danger" icon="el-icon-delete" size="mini" @click="handleDeleteErpStocktakeLine">删除</el-button>
          </el-col>
        </el-row>
        <el-table :data="erpStocktakeLineList" :row-class-name="rowErpStocktakeLineIndex" @selection-change="handleErpStocktakeLineSelectionChange" ref="erpStocktakeLine">
          <el-table-column type="selection" width="50" align="center" />
          <el-table-column label="序号" align="center" prop="index" width="50"/>
          <el-table-column label="物料ID" prop="materialId" width="150">
            <template slot-scope="scope">
              <el-input-number v-model="scope.row.materialId" :min="0" controls-position="right" placeholder="请输入物料ID" />
            </template>
          </el-table-column>
          <el-table-column label="批次号" prop="batchNo" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.batchNo" placeholder="请输入批次号" />
            </template>
          </el-table-column>
          <el-table-column label="账面数量" prop="bookQty" width="150">
            <template slot-scope="scope">
              <el-input-number v-model="scope.row.bookQty" :precision="2" :min="0" controls-position="right" />
            </template>
          </el-table-column>
          <el-table-column label="实盘数量" prop="actualQty" width="150">
            <template slot-scope="scope">
              <el-input-number v-model="scope.row.actualQty" :precision="2" :min="0" controls-position="right" />
            </template>
          </el-table-column>
          <el-table-column label="差异数量" prop="diffQty" width="150">
            <template slot-scope="scope">
              <el-input-number v-model="scope.row.diffQty" :precision="2" controls-position="right" />
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
import { listErpStocktake, getErpStocktake, delErpStocktake, addErpStocktake, updateErpStocktake } from "@/api/inventory/stocktake"

export default {
  name: "ErpStocktake",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 子表选中数据
      checkedErpStocktakeLine: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 盘点单表格数据
      stocktakeList: [],
      // 盘点单明细表格数据
      erpStocktakeLineList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        stocktakeCode: null,
        stocktakeType: null,
        status: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        stocktakeCode: [
          { required: true, message: "盘点单号不能为空", trigger: "blur" }
        ]
      }
    }
  },
  created() {
    this.getList()
  },
  methods: {
    /** 查询盘点单列表 */
    getList() {
      this.loading = true
      listErpStocktake(this.queryParams).then(response => {
        this.stocktakeList = response.rows
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
        stocktakeId: null,
        stocktakeCode: null,
        warehouseId: null,
        stocktakeType: null,
        stocktakeDate: null,
        status: null,
        remark: null
      }
      this.erpStocktakeLineList = []
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
      this.ids = selection.map(item => item.stocktakeId)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset()
      this.open = true
      this.title = "添加盘点单"
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const stocktakeId = row.stocktakeId || this.ids
      getErpStocktake(stocktakeId).then(response => {
        this.form = response.data
        this.erpStocktakeLineList = response.data.erpStocktakeLineList
        this.open = true
        this.title = "修改盘点单"
      })
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          this.form.erpStocktakeLineList = this.erpStocktakeLineList
          if (this.form.stocktakeId != null) {
            updateErpStocktake(this.form).then(response => {
              this.$modal.msgSuccess("修改成功")
              this.open = false
              this.getList()
            })
          } else {
            addErpStocktake(this.form).then(response => {
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
      const stocktakeIds = row.stocktakeId || this.ids
      this.$modal.confirm('是否确认删除盘点单编号为"' + stocktakeIds + '"的数据项？').then(function() {
        return delErpStocktake(stocktakeIds)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess("删除成功")
      }).catch(() => {})
    },
    /** 盘点单明细序号 */
    rowErpStocktakeLineIndex({ row, rowIndex }) {
      row.index = rowIndex + 1
    },
    /** 盘点单明细添加按钮操作 */
    handleAddErpStocktakeLine() {
      let obj = {}
      obj.materialId = ""
      obj.batchNo = ""
      obj.bookQty = ""
      obj.actualQty = ""
      obj.diffQty = ""
      this.erpStocktakeLineList.push(obj)
    },
    /** 盘点单明细删除按钮操作 */
    handleDeleteErpStocktakeLine() {
      if (this.checkedErpStocktakeLine.length == 0) {
        this.$modal.msgError("请先选择要删除的盘点单明细数据")
      } else {
        const erpStocktakeLineList = this.erpStocktakeLineList
        const checkedErpStocktakeLine = this.checkedErpStocktakeLine
        this.erpStocktakeLineList = erpStocktakeLineList.filter(function(item) {
          return checkedErpStocktakeLine.indexOf(item.index) == -1
        })
      }
    },
    /** 复选框选中数据 */
    handleErpStocktakeLineSelectionChange(selection) {
      this.checkedErpStocktakeLine = selection.map(item => item.index)
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('inventory/stocktake/export', {
        ...this.queryParams
      }, `stocktake_${new Date().getTime()}.xlsx`)
    }
  }
}
</script>
