package com.ruoyi.plan.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * MRP结果对象 erp_mrp_result
 *
 * @author erp
 */
public class ErpMrpResult extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 结果ID */
    private Long resultId;

    /** 运算ID */
    private Long runId;

    /** 物料ID */
    private Long materialId;

    /** 毛需求 */
    @Excel(name = "毛需求")
    private BigDecimal grossRequirement;

    /** 可用库存 */
    @Excel(name = "可用库存")
    private BigDecimal availableQty;

    /** 在途供应 */
    @Excel(name = "在途供应")
    private BigDecimal onHandSupply;

    /** 安全库存 */
    @Excel(name = "安全库存")
    private BigDecimal safeStock;

    /** 净需求 */
    @Excel(name = "净需求")
    private BigDecimal netRequirement;

    /** 建议类型(采购/生产) */
    @Excel(name = "建议类型")
    private String suggestType;

    /** 建议数量 */
    @Excel(name = "建议数量")
    private BigDecimal suggestQty;

    /** 需求日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "需求日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date needDate;

    public void setResultId(Long resultId)
    {
        this.resultId = resultId;
    }

    public Long getResultId()
    {
        return resultId;
    }

    public void setRunId(Long runId)
    {
        this.runId = runId;
    }

    public Long getRunId()
    {
        return runId;
    }

    public void setMaterialId(Long materialId)
    {
        this.materialId = materialId;
    }

    public Long getMaterialId()
    {
        return materialId;
    }

    public void setGrossRequirement(BigDecimal grossRequirement)
    {
        this.grossRequirement = grossRequirement;
    }

    public BigDecimal getGrossRequirement()
    {
        return grossRequirement;
    }

    public void setAvailableQty(BigDecimal availableQty)
    {
        this.availableQty = availableQty;
    }

    public BigDecimal getAvailableQty()
    {
        return availableQty;
    }

    public void setOnHandSupply(BigDecimal onHandSupply)
    {
        this.onHandSupply = onHandSupply;
    }

    public BigDecimal getOnHandSupply()
    {
        return onHandSupply;
    }

    public void setSafeStock(BigDecimal safeStock)
    {
        this.safeStock = safeStock;
    }

    public BigDecimal getSafeStock()
    {
        return safeStock;
    }

    public void setNetRequirement(BigDecimal netRequirement)
    {
        this.netRequirement = netRequirement;
    }

    public BigDecimal getNetRequirement()
    {
        return netRequirement;
    }

    public void setSuggestType(String suggestType)
    {
        this.suggestType = suggestType;
    }

    public String getSuggestType()
    {
        return suggestType;
    }

    public void setSuggestQty(BigDecimal suggestQty)
    {
        this.suggestQty = suggestQty;
    }

    public BigDecimal getSuggestQty()
    {
        return suggestQty;
    }

    public void setNeedDate(Date needDate)
    {
        this.needDate = needDate;
    }

    public Date getNeedDate()
    {
        return needDate;
    }

    @Override
    public String toString()
    {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("resultId", getResultId())
            .append("runId", getRunId())
            .append("materialId", getMaterialId())
            .append("grossRequirement", getGrossRequirement())
            .append("availableQty", getAvailableQty())
            .append("onHandSupply", getOnHandSupply())
            .append("safeStock", getSafeStock())
            .append("netRequirement", getNetRequirement())
            .append("suggestType", getSuggestType())
            .append("suggestQty", getSuggestQty())
            .append("needDate", getNeedDate())
            .append("createTime", getCreateTime())
            .toString();
    }
}
