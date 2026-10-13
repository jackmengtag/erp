<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="110px">
      <el-form-item label="凭证接口单号" prop="voucherCode">
        <el-input
          v-model="queryParams.voucherCode"
          placeholder="请输入凭证接口单号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="业务单据类型" prop="bizType">
        <el-input
          v-model="queryParams.bizType"
          placeholder="请输入业务单据类型"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="生成状态" prop="genStatus">
        <el-select v-model="queryParams.genStatus" placeholder="请选择生成状态" clearable>
          <el-option
            v-for="item in genStatusOptions"
            :key="item.value"
            :label="item.label"
            :value="item.value"
          />
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
          v-hasPermi="['finance:voucher:add']"
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
          v-hasPermi="['finance:voucher:edit']"
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
          v-hasPermi="['finance:voucher:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['finance:voucher:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="voucherList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="凭证ID" align="center" prop="voucherId" />
      <el-table-column label="凭证接口单号" align="center" prop="voucherCode" />
      <el-table-column label="业务单据类型" align="center" prop="bizType" />
      <el-table-column label="业务单据ID" align="center" prop="bizId" />
      <el-table-column label="事件类型" align="center" prop="eventType" />
      <el-table-column label="金额" align="center" prop="amount" />
      <el-table-column label="会计期间(YYYY-MM)" align="center" prop="periods" />
      <el-table-column label="来源单号" align="center" prop="sourceDoc" />
      <el-table-column label="生成状态" align="center" prop="genStatus" />
      <el-table-column label="重试次数" align="center" prop="retryTimes" />
      <el-table-column label="失败原因" align="center" prop="errorMsg" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['finance:voucher:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['finance:voucher:remove']"
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

    <!-- 添加或修改凭证接口对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="800px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="110px">
        <el-row>
          <el-col :span="12">
            <el-form-item label="凭证接口单号" prop="voucherCode">
              <el-input v-model="form.voucherCode" placeholder="请输入凭证接口单号" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="业务单据类型" prop="bizType">
              <el-input v-model="form.bizType" placeholder="请输入业务单据类型" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="业务单据ID" prop="bizId">
              <el-input-number v-model="form.bizId" :min="0" placeholder="请输入业务单据ID" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="事件类型" prop="eventType">
              <el-input v-model="form.eventType" placeholder="请输入事件类型" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="金额" prop="amount">
              <el-input-number v-model="form.amount" :precision="2" :min="0" placeholder="请输入金额" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="会计期间" prop="periods">
              <el-input v-model="form.periods" placeholder="请输入会计期间(YYYY-MM)" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="来源单号" prop="sourceDoc">
              <el-input v-model="form.sourceDoc" placeholder="请输入来源单号" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="生成状态" prop="genStatus">
              <el-select v-model="form.genStatus" placeholder="请选择生成状态">
                <el-option
                  v-for="item in genStatusOptions"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value"
                />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="重试次数" prop="retryTimes">
              <el-input-number v-model="form.retryTimes" :min="0" placeholder="请输入重试次数" />
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="失败原因" prop="errorMsg">
              <el-input v-model="form.errorMsg" type="textarea" placeholder="请输入内容" />
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
import { listErpVoucher, getErpVoucher, delErpVoucher, addErpVoucher, updateErpVoucher } from "@/api/finance/voucher"

export default {
  name: "ErpVoucher",
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
      // 凭证接口表格数据
      voucherList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 生成状态选项
      genStatusOptions: [
        { value: "0", label: "待生成" },
        { value: "1", label: "已生成" },
        { value: "2", label: "失败" },
        { value: "3", label: "已冲销" }
      ],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        voucherCode: null,
        bizType: null,
        genStatus: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        voucherCode: [
          { required: true, message: "凭证接口单号不能为空", trigger: "blur" }
        ]
      }
    }
  },
  created() {
    this.getList()
  },
  methods: {
    /** 查询凭证接口列表 */
    getList() {
      this.loading = true
      listErpVoucher(this.queryParams).then(response => {
        this.voucherList = response.rows
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
        voucherId: null,
        voucherCode: null,
        bizType: null,
        bizId: null,
        eventType: null,
        amount: null,
        periods: null,
        sourceDoc: null,
        genStatus: null,
        retryTimes: null,
        errorMsg: null,
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
      this.ids = selection.map(item => item.voucherId)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset()
      this.open = true
      this.title = "添加凭证接口"
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const voucherId = row.voucherId || this.ids
      getErpVoucher(voucherId).then(response => {
        this.form = response.data
        this.open = true
        this.title = "修改凭证接口"
      })
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.voucherId != null) {
            updateErpVoucher(this.form).then(response => {
              this.$modal.msgSuccess("修改成功")
              this.open = false
              this.getList()
            })
          } else {
            addErpVoucher(this.form).then(response => {
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
      const voucherIds = row.voucherId || this.ids
      this.$modal.confirm('是否确认删除凭证接口编号为"' + voucherIds + '"的数据项？').then(function() {
        return delErpVoucher(voucherIds)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess("删除成功")
      }).catch(() => {})
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('finance/voucher/export', {
        ...this.queryParams
      }, `voucher_${new Date().getTime()}.xlsx`)
    }
  }
}
</script>
