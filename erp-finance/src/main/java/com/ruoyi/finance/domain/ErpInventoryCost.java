package com.ruoyi.finance.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 存货核算对象 erp_inventory_cost
 *
 * @author erp
 */
public class ErpInventoryCost extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 核算ID */
    private Long costId;

    /** 物料ID */
    @Excel(name = "物料ID")
    private Long materialId;

    /** 仓库ID */
    @Excel(name = "仓库ID")
    private Long warehouseId;

    /** 会计期间(YYYY-MM) */
    @Excel(name = "会计期间(YYYY-MM)")
    private String periods;

    /** 期初数量 */
    @Excel(name = "期初数量")
    private BigDecimal beginQty;

    /** 期初金额 */
    @Excel(name = "期初金额")
    private BigDecimal beginAmount;

    /** 入库数量 */
    @Excel(name = "入库数量")
    private BigDecimal inQty;

    /** 入库金额 */
    @Excel(name = "入库金额")
    private BigDecimal inAmount;

    /** 出库数量 */
    @Excel(name = "出库数量")
    private BigDecimal outQty;

    /** 出库金额 */
    @Excel(name = "出库金额")
    private BigDecimal outAmount;

    /** 期末数量 */
    @Excel(name = "期末数量")
    private BigDecimal endQty;

    /** 期末金额 */
    @Excel(name = "期末金额")
    private BigDecimal endAmount;

    /** 结存单价 */
    @Excel(name = "结存单价")
    private BigDecimal unitCost;

    public void setCostId(Long costId)
    {
        this.costId = costId;
    }

    public Long getCostId()
    {
        return costId;
    }

    public void setMaterialId(Long materialId)
    {
        this.materialId = materialId;
    }

    public Long getMaterialId()
    {
        return materialId;
    }

    public void setWarehouseId(Long warehouseId)
    {
        this.warehouseId = warehouseId;
    }

    public Long getWarehouseId()
    {
        return warehouseId;
    }

    public void setPeriods(String periods)
    {
        this.periods = periods;
    }

    public String getPeriods()
    {
        return periods;
    }

    public void setBeginQty(BigDecimal beginQty)
    {
        this.beginQty = beginQty;
    }

    public BigDecimal getBeginQty()
    {
        return beginQty;
    }

    public void setBeginAmount(BigDecimal beginAmount)
    {
        this.beginAmount = beginAmount;
    }

    public BigDecimal getBeginAmount()
    {
        return beginAmount;
    }

    public void setInQty(BigDecimal inQty)
    {
        this.inQty = inQty;
    }

    public BigDecimal getInQty()
    {
        return inQty;
    }

    public void setInAmount(BigDecimal inAmount)
    {
        this.inAmount = inAmount;
    }

    public BigDecimal getInAmount()
    {
        return inAmount;
    }

    public void setOutQty(BigDecimal outQty)
    {
        this.outQty = outQty;
    }

    public BigDecimal getOutQty()
    {
        return outQty;
    }

    public void setOutAmount(BigDecimal outAmount)
    {
        this.outAmount = outAmount;
    }

    public BigDecimal getOutAmount()
    {
        return outAmount;
    }

    public void setEndQty(BigDecimal endQty)
    {
        this.endQty = endQty;
    }

    public BigDecimal getEndQty()
    {
        return endQty;
    }

    public void setEndAmount(BigDecimal endAmount)
    {
        this.endAmount = endAmount;
    }

    public BigDecimal getEndAmount()
    {
        return endAmount;
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
            .append("materialId", getMaterialId())
            .append("warehouseId", getWarehouseId())
            .append("periods", getPeriods())
            .append("beginQty", getBeginQty())
            .append("beginAmount", getBeginAmount())
            .append("inQty", getInQty())
            .append("inAmount", getInAmount())
            .append("outQty", getOutQty())
            .append("outAmount", getOutAmount())
            .append("endQty", getEndQty())
            .append("endAmount", getEndAmount())
            .append("unitCost", getUnitCost())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
