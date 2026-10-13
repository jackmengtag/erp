package com.ruoyi.finance.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 生产成本归集对象 erp_production_cost
 *
 * @author erp
 */
public class ErpProductionCost extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 成本ID */
    private Long costId;

    /** 工单ID */
    @Excel(name = "工单ID")
    private Long moId;

    /** 会计期间 */
    @Excel(name = "会计期间")
    private String periods;

    /** 材料成本 */
    @Excel(name = "材料成本")
    private BigDecimal materialCost;

    /** 人工成本 */
    @Excel(name = "人工成本")
    private BigDecimal laborCost;

    /** 制造费用 */
    @Excel(name = "制造费用")
    private BigDecimal overheadCost;

    /** 委外加工费 */
    @Excel(name = "委外加工费")
    private BigDecimal outsourceCost;

    /** 成本合计 */
    @Excel(name = "成本合计")
    private BigDecimal totalCost;

    /** 单位成本 */
    @Excel(name = "单位成本")
    private BigDecimal unitCost;

    public void setCostId(Long costId)
    {
        this.costId = costId;
    }

    public Long getCostId()
    {
        return costId;
    }

    public void setMoId(Long moId)
    {
        this.moId = moId;
    }

    public Long getMoId()
    {
        return moId;
    }

    public void setPeriods(String periods)
    {
        this.periods = periods;
    }

    public String getPeriods()
    {
        return periods;
    }

    public void setMaterialCost(BigDecimal materialCost)
    {
        this.materialCost = materialCost;
    }

    public BigDecimal getMaterialCost()
    {
        return materialCost;
    }

    public void setLaborCost(BigDecimal laborCost)
    {
        this.laborCost = laborCost;
    }

    public BigDecimal getLaborCost()
    {
        return laborCost;
    }

    public void setOverheadCost(BigDecimal overheadCost)
    {
        this.overheadCost = overheadCost;
    }

    public BigDecimal getOverheadCost()
    {
        return overheadCost;
    }

    public void setOutsourceCost(BigDecimal outsourceCost)
    {
        this.outsourceCost = outsourceCost;
    }

    public BigDecimal getOutsourceCost()
    {
        return outsourceCost;
    }

    public void setTotalCost(BigDecimal totalCost)
    {
        this.totalCost = totalCost;
    }

    public BigDecimal getTotalCost()
    {
        return totalCost;
    }

    public void setUnitCost(BigDecimal unitCost)
    {
        this.unitCost = unitCost;
    }

    public BigDecimal getUnitCost()
    {
        return unitCost;
    }

    @Override
    public String toString()
    {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("costId", getCostId())
            .append("moId", getMoId())
            .append("periods", getPeriods())
            .append("materialCost", getMaterialCost())
            .append("laborCost", getLaborCost())
            .append("overheadCost", getOverheadCost())
            .append("outsourceCost", getOutsourceCost())
            .append("totalCost", getTotalCost())
            .append("unitCost", getUnitCost())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
