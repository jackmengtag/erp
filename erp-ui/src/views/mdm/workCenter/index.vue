<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="88px">
      <el-form-item label="工作中心编码" prop="wcCode">
        <el-input
          v-model="queryParams.wcCode"
          placeholder="请输入工作中心编码"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="工作中心名称" prop="wcName">
        <el-input
          v-model="queryParams.wcName"
          placeholder="请输入工作中心名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
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
          v-hasPermi="['mdm:workCenter:add']"
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
          v-hasPermi="['mdm:workCenter:edit']"
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
          v-hasPermi="['mdm:workCenter:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['mdm:workCenter:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="erpWorkCenterList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="工作中心ID" align="center" prop="wcId" />
      <el-table-column label="工作中心编码" align="center" prop="wcCode" />
      <el-table-column label="工作中心名称" align="center" prop="wcName" />
      <el-table-column label="所属部门" align="center" prop="deptId" />
      <el-table-column label="日产能(小时)" align="center" prop="capacityPerDay" />
      <el-table-column label="单位工时成本" align="center" prop="costRate" />
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
            v-hasPermi="['mdm:workCenter:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['mdm:workCenter:remove']"
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

    <!-- 添加或修改工作中心对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="600px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="110px">
        <el-row>
          <el-col :span="24">
            <el-form-item label="工作中心编码" prop="wcCode">
              <el-input v-model="form.wcCode" placeholder="请输入工作中心编码" />
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="工作中心名称" prop="wcName">
              <el-input v-model="form.wcName" placeholder="请输入工作中心名称" />
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="所属部门" prop="deptId">
              <el-input-number v-model="form.deptId" :min="0" placeholder="请输入所属部门" />
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="日产能(小时)" prop="capacityPerDay">
              <el-input-number v-model="form.capacityPerDay" :precision="2" :min="0" placeholder="请输入日产能(小时)" />
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="单位工时成本" prop="costRate">
              <el-input-number v-model="form.costRate" :precision="4" :min="0" placeholder="请输入单位工时成本" />
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
import { listErpWorkCenter, getErpWorkCenter, delErpWorkCenter, addErpWorkCenter, updateErpWorkCenter } from "@/api/mdm/workCenter"

export default {
  name: "ErpWorkCenter",
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
      // 工作中心表格数据
      erpWorkCenterList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        wcCode: null,
        wcName: null,
        status: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        wcCode: [
          { required: true, message: "工作中心编码不能为空", trigger: "blur" }
        ],
        wcName: [
          { required: true, message: "工作中心名称不能为空", trigger: "blur" }
        ]
      }
    }
  },
  created() {
    this.getList()
  },
  methods: {
    /** 查询工作中心列表 */
    getList() {
      this.loading = true
      listErpWorkCenter(this.queryParams).then(response => {
        this.erpWorkCenterList = response.rows
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
        wcId: null,
        wcCode: null,
        wcName: null,
        deptId: null,
        capacityPerDay: null,
        costRate: null,
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
      this.ids = selection.map(item => item.wcId)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset()
      this.open = true
      this.title = "添加工作中心"
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const wcId = row.wcId || this.ids
      getErpWorkCenter(wcId).then(response => {
        this.form = response.data
        this.open = true
        this.title = "修改工作中心"
      })
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.wcId != null) {
            updateErpWorkCenter(this.form).then(response => {
              this.$modal.msgSuccess("修改成功")
              this.open = false
              this.getList()
            })
          } else {
            addErpWorkCenter(this.form).then(response => {
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
      const wcIds = row.wcId || this.ids
      this.$modal.confirm('是否确认删除工作中心编号为"' + wcIds + '"的数据项？').then(function() {
        return delErpWorkCenter(wcIds)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess("删除成功")
      }).catch(() => {})
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('mdm/workCenter/export', {
        ...this.queryParams
      }, `workCenter_${new Date().getTime()}.xlsx`)
    }
  }
}
</script>
