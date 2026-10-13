<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="单位编码" prop="uomCode">
        <el-input
          v-model="queryParams.uomCode"
          placeholder="请输入单位编码"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="单位名称" prop="uomName">
        <el-input
          v-model="queryParams.uomName"
          placeholder="请输入单位名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="单位类型" prop="uomType">
        <el-select v-model="queryParams.uomType" placeholder="请选择单位类型" clearable>
          <el-option label="数量" value="qty" />
          <el-option label="时间" value="time" />
          <el-option label="重量" value="weight" />
          <el-option label="体积" value="volume" />
        </el-select>
      </el-form-item>
      <el-form-item label="状态" prop="status">
        <el-select v-model="queryParams.status" placeholder="请选择状态" clearable>
          <el-option label="正常" value="0" />
          <el-option label="停用" value="1" />
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
          v-hasPermi="['mdm:uom:add']"
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
          v-hasPermi="['mdm:uom:edit']"
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
          v-hasPermi="['mdm:uom:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['mdm:uom:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="erpUomList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="单位ID" align="center" prop="uomId" />
      <el-table-column label="单位编码" align="center" prop="uomCode" />
      <el-table-column label="单位名称" align="center" prop="uomName" />
      <el-table-column label="单位类型" align="center" prop="uomType">
        <template slot-scope="scope">
          <span v-if="scope.row.uomType == 'qty'">数量</span>
          <span v-else-if="scope.row.uomType == 'time'">时间</span>
          <span v-else-if="scope.row.uomType == 'weight'">重量</span>
          <span v-else-if="scope.row.uomType == 'volume'">体积</span>
        </template>
      </el-table-column>
      <el-table-column label="小数位" align="center" prop="precisionScale" />
      <el-table-column label="状态" align="center" prop="status">
        <template slot-scope="scope">
          <span v-if="scope.row.status == 0">正常</span>
          <span v-else-if="scope.row.status == 1">停用</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['mdm:uom:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['mdm:uom:remove']"
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

    <!-- 添加或修改计量单位对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="600px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="100px">
        <el-row>
          <el-col :span="24">
            <el-form-item label="单位编码" prop="uomCode">
              <el-input v-model="form.uomCode" placeholder="请输入单位编码" />
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="单位名称" prop="uomName">
              <el-input v-model="form.uomName" placeholder="请输入单位名称" />
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="单位类型" prop="uomType">
              <el-select v-model="form.uomType" placeholder="请选择单位类型">
                <el-option label="数量" value="qty" />
                <el-option label="时间" value="time" />
                <el-option label="重量" value="weight" />
                <el-option label="体积" value="volume" />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="小数位" prop="precisionScale">
              <el-input-number v-model="form.precisionScale" :min="0" placeholder="请输入小数位" />
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="状态" prop="status">
              <el-select v-model="form.status" placeholder="请选择状态">
                <el-option label="正常" value="0" />
                <el-option label="停用" value="1" />
              </el-select>
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
import { listErpUom, getErpUom, delErpUom, addErpUom, updateErpUom } from "@/api/mdm/uom"

export default {
  name: "ErpUom",
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
      // 计量单位表格数据
      erpUomList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        uomCode: null,
        uomName: null,
        uomType: null,
        status: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        uomCode: [
          { required: true, message: "单位编码不能为空", trigger: "blur" }
        ],
        uomName: [
          { required: true, message: "单位名称不能为空", trigger: "blur" }
        ]
      }
    }
  },
  created() {
    this.getList()
  },
  methods: {
    /** 查询计量单位列表 */
    getList() {
      this.loading = true
      listErpUom(this.queryParams).then(response => {
        this.erpUomList = response.rows
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
        uomId: null,
        uomCode: null,
        uomName: null,
        uomType: null,
        precisionScale: null,
        status: null
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
      this.ids = selection.map(item => item.uomId)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset()
      this.open = true
      this.title = "添加计量单位"
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const uomId = row.uomId || this.ids
      getErpUom(uomId).then(response => {
        this.form = response.data
        this.open = true
        this.title = "修改计量单位"
      })
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.uomId != null) {
            updateErpUom(this.form).then(response => {
              this.$modal.msgSuccess("修改成功")
              this.open = false
              this.getList()
            })
          } else {
            addErpUom(this.form).then(response => {
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
      const uomIds = row.uomId || this.ids
      this.$modal.confirm('是否确认删除计量单位编号为"' + uomIds + '"的数据项？').then(function() {
        return delErpUom(uomIds)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess("删除成功")
      }).catch(() => {})
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('mdm/uom/export', {
        ...this.queryParams
      }, `uom_${new Date().getTime()}.xlsx`)
    }
  }
}
</script>
