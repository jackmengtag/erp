<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="物料编码" prop="materialCode">
        <el-input
          v-model="queryParams.materialCode"
          placeholder="请输入物料编码"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="物料名称" prop="materialName">
        <el-input
          v-model="queryParams.materialName"
          placeholder="请输入物料名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="物料类型" prop="materialType">
        <el-select v-model="queryParams.materialType" placeholder="请选择物料类型" clearable>
          <el-option label="原材料" value="1" />
          <el-option label="半成品" value="2" />
          <el-option label="成品" value="3" />
          <el-option label="外购件" value="4" />
          <el-option label="虚拟件" value="5" />
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
          v-hasPermi="['mdm:material:add']"
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
          v-hasPermi="['mdm:material:edit']"
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
          v-hasPermi="['mdm:material:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['mdm:material:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="erpMaterialList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="物料ID" align="center" prop="materialId" />
      <el-table-column label="物料编码" align="center" prop="materialCode" />
      <el-table-column label="物料名称" align="center" prop="materialName" />
      <el-table-column label="规格型号" align="center" prop="specification" />
      <el-table-column label="物料分类ID" align="center" prop="categoryId" />
      <el-table-column label="基本单位ID" align="center" prop="uomId" />
      <el-table-column label="物料类型" align="center" prop="materialType">
        <template slot-scope="scope">
          <span v-if="scope.row.materialType == 1">原材料</span>
          <span v-else-if="scope.row.materialType == 2">半成品</span>
          <span v-else-if="scope.row.materialType == 3">成品</span>
          <span v-else-if="scope.row.materialType == 4">外购件</span>
          <span v-else-if="scope.row.materialType == 5">虚拟件</span>
        </template>
      </el-table-column>
      <el-table-column label="批次管理" align="center" prop="batchManage">
        <template slot-scope="scope">
          <span v-if="scope.row.batchManage == 1">是</span>
          <span v-else-if="scope.row.batchManage == 0">否</span>
        </template>
      </el-table-column>
      <el-table-column label="序列号管理" align="center" prop="serialManage">
        <template slot-scope="scope">
          <span v-if="scope.row.serialManage == 1">是</span>
          <span v-else-if="scope.row.serialManage == 0">否</span>
        </template>
      </el-table-column>
      <el-table-column label="保质期(天)" align="center" prop="shelfLifeDays" />
      <el-table-column label="采购提前期(天)" align="center" prop="leadTimeDays" />
      <el-table-column label="生产提前期(天)" align="center" prop="produceLeadTime" />
      <el-table-column label="安全库存" align="center" prop="safeStock" />
      <el-table-column label="税率(%)" align="center" prop="taxRate" />
      <el-table-column label="标准成本" align="center" prop="costPrice" />
      <el-table-column label="标准售价" align="center" prop="salePrice" />
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
            v-hasPermi="['mdm:material:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['mdm:material:remove']"
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

    <!-- 添加或修改物料主数据对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="900px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="110px">
        <el-row>
          <el-col :span="12">
            <el-form-item label="物料编码" prop="materialCode">
              <el-input v-model="form.materialCode" placeholder="请输入物料编码" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="物料名称" prop="materialName">
              <el-input v-model="form.materialName" placeholder="请输入物料名称" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="规格型号" prop="specification">
              <el-input v-model="form.specification" placeholder="请输入规格型号" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="物料分类ID" prop="categoryId">
              <el-input-number v-model="form.categoryId" :min="0" placeholder="请输入物料分类ID" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="基本单位ID" prop="uomId">
              <el-input-number v-model="form.uomId" :min="0" placeholder="请输入基本单位ID" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="物料类型" prop="materialType">
              <el-select v-model="form.materialType" placeholder="请选择物料类型">
                <el-option label="原材料" value="1" />
                <el-option label="半成品" value="2" />
                <el-option label="成品" value="3" />
                <el-option label="外购件" value="4" />
                <el-option label="虚拟件" value="5" />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="批次管理" prop="batchManage">
              <el-select v-model="form.batchManage" placeholder="请选择批次管理">
                <el-option label="是" value="1" />
                <el-option label="否" value="0" />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="序列号管理" prop="serialManage">
              <el-select v-model="form.serialManage" placeholder="请选择序列号管理">
                <el-option label="是" value="1" />
                <el-option label="否" value="0" />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="保质期(天)" prop="shelfLifeDays">
              <el-input-number v-model="form.shelfLifeDays" :min="0" placeholder="请输入保质期(天)" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="采购属性" prop="purchaseFlag">
              <el-select v-model="form.purchaseFlag" placeholder="请选择采购属性">
                <el-option label="是" value="1" />
                <el-option label="否" value="0" />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="生产属性" prop="produceFlag">
              <el-select v-model="form.produceFlag" placeholder="请选择生产属性">
                <el-option label="是" value="1" />
                <el-option label="否" value="0" />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="销售属性" prop="saleFlag">
              <el-select v-model="form.saleFlag" placeholder="请选择销售属性">
                <el-option label="是" value="1" />
                <el-option label="否" value="0" />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="采购提前期(天)" prop="leadTimeDays">
              <el-input-number v-model="form.leadTimeDays" :min="0" placeholder="请输入采购提前期(天)" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="生产提前期(天)" prop="produceLeadTime">
              <el-input-number v-model="form.produceLeadTime" :min="0" placeholder="请输入生产提前期(天)" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="安全库存" prop="safeStock">
              <el-input-number v-model="form.safeStock" :precision="4" :min="0" placeholder="请输入安全库存" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="税率(%)" prop="taxRate">
              <el-input-number v-model="form.taxRate" :precision="4" :min="0" placeholder="请输入税率(%)" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="标准成本" prop="costPrice">
              <el-input-number v-model="form.costPrice" :precision="6" :min="0" placeholder="请输入标准成本" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="标准售价" prop="salePrice">
              <el-input-number v-model="form.salePrice" :precision="6" :min="0" placeholder="请输入标准售价" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
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
import { listErpMaterial, getErpMaterial, delErpMaterial, addErpMaterial, updateErpMaterial } from "@/api/mdm/material"

export default {
  name: "ErpMaterial",
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
      // 物料主数据表格数据
      erpMaterialList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        materialCode: null,
        materialName: null,
        materialType: null,
        status: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        materialCode: [
          { required: true, message: "物料编码不能为空", trigger: "blur" }
        ],
        materialName: [
          { required: true, message: "物料名称不能为空", trigger: "blur" }
        ]
      }
    }
  },
  created() {
    this.getList()
  },
  methods: {
    /** 查询物料主数据列表 */
    getList() {
      this.loading = true
      listErpMaterial(this.queryParams).then(response => {
        this.erpMaterialList = response.rows
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
        materialId: null,
        materialCode: null,
        materialName: null,
        specification: null,
        categoryId: null,
        uomId: null,
        materialType: null,
        batchManage: null,
        serialManage: null,
        shelfLifeDays: null,
        purchaseFlag: null,
        produceFlag: null,
        saleFlag: null,
        leadTimeDays: null,
        produceLeadTime: null,
        safeStock: null,
        taxRate: null,
        costPrice: null,
        salePrice: null,
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
      this.ids = selection.map(item => item.materialId)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset()
      this.open = true
      this.title = "添加物料主数据"
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const materialId = row.materialId || this.ids
      getErpMaterial(materialId).then(response => {
        this.form = response.data
        this.open = true
        this.title = "修改物料主数据"
      })
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.materialId != null) {
            updateErpMaterial(this.form).then(response => {
              this.$modal.msgSuccess("修改成功")
              this.open = false
              this.getList()
            })
          } else {
            addErpMaterial(this.form).then(response => {
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
      const materialIds = row.materialId || this.ids
      this.$modal.confirm('是否确认删除物料主数据编号为"' + materialIds + '"的数据项？').then(function() {
        return delErpMaterial(materialIds)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess("删除成功")
      }).catch(() => {})
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('mdm/material/export', {
        ...this.queryParams
      }, `material_${new Date().getTime()}.xlsx`)
    }
  }
}
</script>
