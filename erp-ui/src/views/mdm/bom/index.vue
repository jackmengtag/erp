<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="BOM编码" prop="bomCode">
        <el-input
          v-model="queryParams.bomCode"
          placeholder="请输入BOM编码"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="母件物料ID" prop="materialId">
        <el-input
          v-model="queryParams.materialId"
          placeholder="请输入母件物料ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="状态" prop="status">
        <el-select v-model="queryParams.status" placeholder="请选择状态" clearable>
          <el-option label="草稿" value="0" />
          <el-option label="已审核" value="1" />
          <el-option label="停用" value="2" />
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
          v-hasPermi="['mdm:bom:add']"
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
          v-hasPermi="['mdm:bom:edit']"
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
          v-hasPermi="['mdm:bom:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['mdm:bom:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="erpBomList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="BOM ID" align="center" prop="bomId" />
      <el-table-column label="BOM编码" align="center" prop="bomCode" />
      <el-table-column label="母件物料ID" align="center" prop="materialId" />
      <el-table-column label="版本号" align="center" prop="version" />
      <el-table-column label="生效日期" align="center" prop="effectDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.effectDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="失效日期" align="center" prop="expireDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.expireDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="状态" align="center" prop="status">
        <template slot-scope="scope">
          <span v-if="scope.row.status == 0">草稿</span>
          <span v-else-if="scope.row.status == 1">已审核</span>
          <span v-else-if="scope.row.status == 2">停用</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['mdm:bom:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['mdm:bom:remove']"
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

    <!-- 添加或修改BOM对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="1100px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="100px">
        <el-row>
          <el-col :span="8">
            <el-form-item label="BOM编码" prop="bomCode">
              <el-input v-model="form.bomCode" placeholder="请输入BOM编码" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="母件物料ID" prop="materialId">
              <el-input-number v-model="form.materialId" :min="0" placeholder="请输入母件物料ID" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="版本号" prop="version">
              <el-input v-model="form.version" placeholder="请输入版本号" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="生效日期" prop="effectDate">
              <el-date-picker clearable
                v-model="form.effectDate"
                type="date"
                value-format="yyyy-MM-dd"
                placeholder="请选择生效日期">
              </el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="失效日期" prop="expireDate">
              <el-date-picker clearable
                v-model="form.expireDate"
                type="date"
                value-format="yyyy-MM-dd"
                placeholder="请选择失效日期">
              </el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="状态" prop="status">
              <el-select v-model="form.status" placeholder="请选择状态">
                <el-option label="草稿" value="0" />
                <el-option label="已审核" value="1" />
                <el-option label="停用" value="2" />
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-divider content-position="center">BOM子件明细信息</el-divider>
        <el-row :gutter="10" class="mb8">
          <el-col :span="1.5">
            <el-button type="primary" icon="el-icon-plus" size="mini" @click="handleAddErpBomLine">添加</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button type="danger" icon="el-icon-delete" size="mini" @click="handleDeleteErpBomLine">删除</el-button>
          </el-col>
        </el-row>
        <el-table :data="erpBomLineList" :row-class-name="rowErpBomLineIndex" @selection-change="handleErpBomLineSelectionChange" ref="erpBomLine">
          <el-table-column type="selection" width="50" align="center" />
          <el-table-column label="序号" align="center" prop="index" width="50"/>
          <el-table-column label="子件物料ID" prop="materialId" width="150">
            <template slot-scope="scope">
              <el-input-number v-model="scope.row.materialId" :min="0" placeholder="请输入子件物料ID" />
            </template>
          </el-table-column>
          <el-table-column label="单位用量" prop="qtyPer" width="150">
            <template slot-scope="scope">
              <el-input-number v-model="scope.row.qtyPer" :precision="6" :min="0" placeholder="请输入单位用量" />
            </template>
          </el-table-column>
          <el-table-column label="损耗率(%)" prop="lossRate" width="150">
            <template slot-scope="scope">
              <el-input-number v-model="scope.row.lossRate" :precision="4" :min="0" placeholder="请输入损耗率(%)" />
            </template>
          </el-table-column>
          <el-table-column label="是否替代料" prop="substituteFlag" width="150">
            <template slot-scope="scope">
              <el-select v-model="scope.row.substituteFlag" placeholder="请选择是否替代料">
                <el-option label="是" value="1" />
                <el-option label="否" value="0" />
              </el-select>
            </template>
          </el-table-column>
          <el-table-column label="行号" prop="lineNo" width="150">
            <template slot-scope="scope">
              <el-input-number v-model="scope.row.lineNo" :min="0" placeholder="请输入行号" />
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
import { listErpBom, getErpBom, delErpBom, addErpBom, updateErpBom } from "@/api/mdm/bom"

export default {
  name: "ErpBom",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 子表选中数据
      checkedErpBomLine: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // BOM表格数据
      erpBomList: [],
      // BOM子件明细表格数据
      erpBomLineList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        bomCode: null,
        materialId: null,
        status: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        bomCode: [
          { required: true, message: "BOM编码不能为空", trigger: "blur" }
        ],
        materialId: [
          { required: true, message: "母件物料ID不能为空", trigger: "blur" }
        ]
      }
    }
  },
  created() {
    this.getList()
  },
  methods: {
    /** 查询BOM列表 */
    getList() {
      this.loading = true
      listErpBom(this.queryParams).then(response => {
        this.erpBomList = response.rows
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
        bomId: null,
        bomCode: null,
        materialId: null,
        version: null,
        effectDate: null,
        expireDate: null,
        status: null
      }
      this.erpBomLineList = []
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
      this.ids = selection.map(item => item.bomId)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset()
      this.open = true
      this.title = "添加BOM"
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const bomId = row.bomId || this.ids
      getErpBom(bomId).then(response => {
        this.form = response.data
        this.erpBomLineList = response.data.erpBomLineList
        this.open = true
        this.title = "修改BOM"
      })
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          this.form.erpBomLineList = this.erpBomLineList
          if (this.form.bomId != null) {
            updateErpBom(this.form).then(response => {
              this.$modal.msgSuccess("修改成功")
              this.open = false
              this.getList()
            })
          } else {
            addErpBom(this.form).then(response => {
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
      const bomIds = row.bomId || this.ids
      this.$modal.confirm('是否确认删除BOM编号为"' + bomIds + '"的数据项？').then(function() {
        return delErpBom(bomIds)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess("删除成功")
      }).catch(() => {})
    },
    /** BOM子件明细序号 */
    rowErpBomLineIndex({ row, rowIndex }) {
      row.index = rowIndex + 1
    },
    /** BOM子件明细添加按钮操作 */
    handleAddErpBomLine() {
      let obj = {}
      obj.materialId = ""
      obj.qtyPer = ""
      obj.lossRate = ""
      obj.substituteFlag = ""
      obj.lineNo = ""
      this.erpBomLineList.push(obj)
    },
    /** BOM子件明细删除按钮操作 */
    handleDeleteErpBomLine() {
      if (this.checkedErpBomLine.length == 0) {
        this.$modal.msgError("请先选择要删除的BOM子件明细数据")
      } else {
        const erpBomLineList = this.erpBomLineList
        const checkedErpBomLine = this.checkedErpBomLine
        this.erpBomLineList = erpBomLineList.filter(function(item) {
          return checkedErpBomLine.indexOf(item.index) == -1
        })
      }
    },
    /** 复选框选中数据 */
    handleErpBomLineSelectionChange(selection) {
      this.checkedErpBomLine = selection.map(item => item.index)
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('mdm/bom/export', {
        ...this.queryParams
      }, `bom_${new Date().getTime()}.xlsx`)
    }
  }
}
</script>
