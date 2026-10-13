<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="88px">
      <el-form-item label="工艺路线编码" prop="routingCode">
        <el-input
          v-model="queryParams.routingCode"
          placeholder="请输入工艺路线编码"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="工艺路线名称" prop="routingName">
        <el-input
          v-model="queryParams.routingName"
          placeholder="请输入工艺路线名称"
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
          v-hasPermi="['mdm:routing:add']"
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
          v-hasPermi="['mdm:routing:edit']"
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
          v-hasPermi="['mdm:routing:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['mdm:routing:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="erpRoutingList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="工艺路线ID" align="center" prop="routingId" />
      <el-table-column label="工艺路线编码" align="center" prop="routingCode" />
      <el-table-column label="工艺路线名称" align="center" prop="routingName" />
      <el-table-column label="产品物料ID" align="center" prop="materialId" />
      <el-table-column label="版本号" align="center" prop="version" />
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
            v-hasPermi="['mdm:routing:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['mdm:routing:remove']"
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

    <!-- 添加或修改工艺路线对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="1100px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="110px">
        <el-row>
          <el-col :span="8">
            <el-form-item label="工艺路线编码" prop="routingCode">
              <el-input v-model="form.routingCode" placeholder="请输入工艺路线编码" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="工艺路线名称" prop="routingName">
              <el-input v-model="form.routingName" placeholder="请输入工艺路线名称" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="产品物料ID" prop="materialId">
              <el-input-number v-model="form.materialId" :min="0" placeholder="请输入产品物料ID" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="版本号" prop="version">
              <el-input v-model="form.version" placeholder="请输入版本号" />
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
        <el-divider content-position="center">工序明细信息</el-divider>
        <el-row :gutter="10" class="mb8">
          <el-col :span="1.5">
            <el-button type="primary" icon="el-icon-plus" size="mini" @click="handleAddErpRoutingOperation">添加</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button type="danger" icon="el-icon-delete" size="mini" @click="handleDeleteErpRoutingOperation">删除</el-button>
          </el-col>
        </el-row>
        <el-table :data="erpRoutingOperationList" :row-class-name="rowErpRoutingOperationIndex" @selection-change="handleErpRoutingOperationSelectionChange" ref="erpRoutingOperation">
          <el-table-column type="selection" width="50" align="center" />
          <el-table-column label="序号" align="center" prop="index" width="50"/>
          <el-table-column label="工序号" prop="seqNo" width="120">
            <template slot-scope="scope">
              <el-input-number v-model="scope.row.seqNo" :min="0" placeholder="请输入工序号" />
            </template>
          </el-table-column>
          <el-table-column label="工序编码" prop="operationCode" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.operationCode" placeholder="请输入工序编码" />
            </template>
          </el-table-column>
          <el-table-column label="工序名称" prop="operationName" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.operationName" placeholder="请输入工序名称" />
            </template>
          </el-table-column>
          <el-table-column label="工作中心ID" prop="wcId" width="130">
            <template slot-scope="scope">
              <el-input-number v-model="scope.row.wcId" :min="0" placeholder="请输入工作中心ID" />
            </template>
          </el-table-column>
          <el-table-column label="标准工时(小时)" prop="standardHours" width="150">
            <template slot-scope="scope">
              <el-input-number v-model="scope.row.standardHours" :precision="4" :min="0" placeholder="请输入标准工时(小时)" />
            </template>
          </el-table-column>
          <el-table-column label="良率(%)" prop="yieldRate" width="130">
            <template slot-scope="scope">
              <el-input-number v-model="scope.row.yieldRate" :precision="4" :min="0" placeholder="请输入良率(%)" />
            </template>
          </el-table-column>
          <el-table-column label="是否质检点" prop="inspectFlag" width="130">
            <template slot-scope="scope">
              <el-select v-model="scope.row.inspectFlag" placeholder="请选择是否质检点">
                <el-option label="是" value="1" />
                <el-option label="否" value="0" />
              </el-select>
            </template>
          </el-table-column>
          <el-table-column label="是否委外" prop="outsourceFlag" width="130">
            <template slot-scope="scope">
              <el-select v-model="scope.row.outsourceFlag" placeholder="请选择是否委外">
                <el-option label="是" value="1" />
                <el-option label="否" value="0" />
              </el-select>
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
import { listErpRouting, getErpRouting, delErpRouting, addErpRouting, updateErpRouting } from "@/api/mdm/routing"

export default {
  name: "ErpRouting",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 子表选中数据
      checkedErpRoutingOperation: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 工艺路线表格数据
      erpRoutingList: [],
      // 工序明细表格数据
      erpRoutingOperationList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        routingCode: null,
        routingName: null,
        status: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        routingCode: [
          { required: true, message: "工艺路线编码不能为空", trigger: "blur" }
        ],
        routingName: [
          { required: true, message: "工艺路线名称不能为空", trigger: "blur" }
        ]
      }
    }
  },
  created() {
    this.getList()
  },
  methods: {
    /** 查询工艺路线列表 */
    getList() {
      this.loading = true
      listErpRouting(this.queryParams).then(response => {
        this.erpRoutingList = response.rows
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
        routingId: null,
        routingCode: null,
        routingName: null,
        materialId: null,
        version: null,
        status: null
      }
      this.erpRoutingOperationList = []
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
      this.ids = selection.map(item => item.routingId)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset()
      this.open = true
      this.title = "添加工艺路线"
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const routingId = row.routingId || this.ids
      getErpRouting(routingId).then(response => {
        this.form = response.data
        this.erpRoutingOperationList = response.data.erpRoutingOperationList
        this.open = true
        this.title = "修改工艺路线"
      })
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          this.form.erpRoutingOperationList = this.erpRoutingOperationList
          if (this.form.routingId != null) {
            updateErpRouting(this.form).then(response => {
              this.$modal.msgSuccess("修改成功")
              this.open = false
              this.getList()
            })
          } else {
            addErpRouting(this.form).then(response => {
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
      const routingIds = row.routingId || this.ids
      this.$modal.confirm('是否确认删除工艺路线编号为"' + routingIds + '"的数据项？').then(function() {
        return delErpRouting(routingIds)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess("删除成功")
      }).catch(() => {})
    },
    /** 工序明细序号 */
    rowErpRoutingOperationIndex({ row, rowIndex }) {
      row.index = rowIndex + 1
    },
    /** 工序明细添加按钮操作 */
    handleAddErpRoutingOperation() {
      let obj = {}
      obj.seqNo = ""
      obj.operationCode = ""
      obj.operationName = ""
      obj.wcId = ""
      obj.standardHours = ""
      obj.yieldRate = ""
      obj.inspectFlag = ""
      obj.outsourceFlag = ""
      this.erpRoutingOperationList.push(obj)
    },
    /** 工序明细删除按钮操作 */
    handleDeleteErpRoutingOperation() {
      if (this.checkedErpRoutingOperation.length == 0) {
        this.$modal.msgError("请先选择要删除的工序明细数据")
      } else {
        const erpRoutingOperationList = this.erpRoutingOperationList
        const checkedErpRoutingOperation = this.checkedErpRoutingOperation
        this.erpRoutingOperationList = erpRoutingOperationList.filter(function(item) {
          return checkedErpRoutingOperation.indexOf(item.index) == -1
        })
      }
    },
    /** 复选框选中数据 */
    handleErpRoutingOperationSelectionChange(selection) {
      this.checkedErpRoutingOperation = selection.map(item => item.index)
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('mdm/routing/export', {
        ...this.queryParams
      }, `routing_${new Date().getTime()}.xlsx`)
    }
  }
}
</script>
