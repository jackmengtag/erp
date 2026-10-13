package com.ruoyi.mdm.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 物料主数据对象 erp_material
 *
 * @author erp
 */
public class ErpMaterial extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 物料ID */
    private Long materialId;

    /** 物料编码 */
    @Excel(name = "物料编码")
    private String materialCode;

    /** 物料名称 */
    @Excel(name = "物料名称")
    private String materialName;

    /** 规格型号 */
    @Excel(name = "规格型号")
    private String specification;

    /** 物料分类ID */
    private Long categoryId;

    /** 基本单位ID */
    private Long uomId;

    /** 物料类型(1原材料 2半成品 3成品 4外购件 5虚拟件) */
    @Excel(name = "物料类型", readConverterExp = "1=原材料,2=半成品,3=成品,4=外购件,5=虚拟件")
    private String materialType;

    /** 批次管理(1是 0否) */
    @Excel(name = "批次管理", readConverterExp = "1=是,0=否")
    private String batchManage;

    /** 序列号管理(1是 0否) */
    @Excel(name = "序列号管理", readConverterExp = "1=是,0=否")
    private String serialManage;

    /** 保质期(天) */
    @Excel(name = "保质期(天)")
    private Integer shelfLifeDays;

    /** 采购属性(1是 0否) */
    private String purchaseFlag;

    /** 生产属性(1是 0否) */
    private String produceFlag;

    /** 销售属性(1是 0否) */
    private String saleFlag;

    /** 采购提前期(天) */
    @Excel(name = "采购提前期(天)")
    private Integer leadTimeDays;

    /** 生产提前期(天) */
    @Excel(name = "生产提前期(天)")
    private Integer produceLeadTime;

    /** 安全库存 */
    @Excel(name = "安全库存")
    private BigDecimal safeStock;

    /** 税率(%) */
    @Excel(name = "税率(%)")
    private BigDecimal taxRate;

    /** 标准成本 */
    @Excel(name = "标准成本")
    private BigDecimal costPrice;

    /** 标准售价 */
    @Excel(name = "标准售价")
    private BigDecimal salePrice;

    /** 状态(0正常 1停用) */
    @Excel(name = "状态", readConverterExp = "0=正常,1=停用")
    private String status;

    public void setMaterialId(Long materialId)
    {
        this.materialId = materialId;
    }

    public Long getMaterialId()
    {
        return materialId;
    }

    public void setMaterialCode(String materialCode)
    {
        this.materialCode = materialCode;
    }

    public String getMaterialCode()
    {
        return materialCode;
    }

    public void setMaterialName(String materialName)
    {
        this.materialName = materialName;
    }

    public String getMaterialName()
    {
        return materialName;
    }

    public void setSpecification(String specification)
    {
        this.specification = specification;
    }

    public String getSpecification()
    {
        return specification;
    }

    public void setCategoryId(Long categoryId)
    {
        this.categoryId = categoryId;
    }

    public Long getCategoryId()
    {
        return categoryId;
    }

    public void setUomId(Long uomId)
    {
        this.uomId = uomId;
    }

    public Long getUomId()
    {
        return uomId;
    }

    public void setMaterialType(String materialType)
    {
        this.materialType = materialType;
    }

    public String getMaterialType()
    {
        return materialType;
    }

    public void setBatchManage(String batchManage)
    {
        this.batchManage = batchManage;
    }

    public String getBatchManage()
    {
        return batchManage;
    }

    public void setSerialManage(String serialManage)
    {
        this.serialManage = serialManage;
    }

    public String getSerialManage()
    {
        return serialManage;
    }

    public void setShelfLifeDays(Integer shelfLifeDays)
    {
        this.shelfLifeDays = shelfLifeDays;
    }

    public Integer getShelfLifeDays()
    {
        return shelfLifeDays;
    }

    public void setPurchaseFlag(String purchaseFlag)
    {
        this.purchaseFlag = purchaseFlag;
    }

    public String getPurchaseFlag()
    {
        return purchaseFlag;
    }

    public void setProduceFlag(String produceFlag)
    {
        this.produceFlag = produceFlag;
    }

    public String getProduceFlag()
    {
        return produceFlag;
    }

    public void setSaleFlag(String saleFlag)
    {
        this.saleFlag = saleFlag;
    }

    public String getSaleFlag()
    {
        return saleFlag;
    }

    public void setLeadTimeDays(Integer leadTimeDays)
    {
        this.leadTimeDays = leadTimeDays;
    }

    public Integer getLeadTimeDays()
    {
        return leadTimeDays;
    }

    public void setProduceLeadTime(Integer produceLeadTime)
    {
        this.produceLeadTime = produceLeadTime;
    }

    public Integer getProduceLeadTime()
    {
        return produceLeadTime;
    }

    public void setSafeStock(BigDecimal safeStock)
    {
        this.safeStock = safeStock;
    }

    public BigDecimal getSafeStock()
    {
        return safeStock;
    }

    public void setTaxRate(BigDecimal taxRate)
    {
        this.taxRate = taxRate;
    }

    public BigDecimal getTaxRate()
    {
        return taxRate;
    }

    public void setCostPrice(BigDecimal costPrice)
    {
        this.costPrice = costPrice;
    }

    public BigDecimal getCostPrice()
    {
        return costPrice;
    }

    public void setSalePrice(BigDecimal salePrice)
    {
        this.salePrice = salePrice;
    }

    public BigDecimal getSalePrice()
    {
        return salePrice;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getStatus()
    {
        return status;
    }

    @Override
    public String toString()
    {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("materialId", getMaterialId())
            .append("materialCode", getMaterialCode())
            .append("materialName", getMaterialName())
            .append("specification", getSpecification())
            .append("categoryId", getCategoryId())
            .append("uomId", getUomId())
            .append("materialType", getMaterialType())
            .append("batchManage", getBatchManage())
            .append("serialManage", getSerialManage())
            .append("shelfLifeDays", getShelfLifeDays())
            .append("purchaseFlag", getPurchaseFlag())
            .append("produceFlag", getProduceFlag())
            .append("saleFlag", getSaleFlag())
            .append("leadTimeDays", getLeadTimeDays())
            .append("produceLeadTime", getProduceLeadTime())
            .append("safeStock", getSafeStock())
            .append("taxRate", getTaxRate())
            .append("costPrice", getCostPrice())
            .append("salePrice", getSalePrice())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
