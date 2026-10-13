package com.ruoyi.inventory.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 盘点单明细对象 erp_stocktake_line
 *
 * @author erp
 */
public class ErpStocktakeLine extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 明细ID */
    private Long lineId;

    /** 盘点单ID */
    private Long stocktakeId;

    /** 物料ID */
    private Long materialId;

    /** 批次号 */
    @Excel(name = "批次号")
    private String batchNo;

    /** 账面数量 */
    @Excel(name = "账面数量")
    private BigDecimal bookQty;

    /** 实盘数量 */
    @Excel(name = "实盘数量")
    private BigDecimal actualQty;

    /** 差异数量 */
    @Excel(name = "差异数量")
    private BigDecimal diffQty;

    public void setLineId(Long lineId)
    {
        this.lineId = lineId;
    }

    public Long getLineId()
    {
        return lineId;
    }

    public void setStocktakeId(Long stocktakeId)
    {
        this.stocktakeId = stocktakeId;
    }

    public Long getStocktakeId()
    {
        return stocktakeId;
    }

    public void setMaterialId(Long materialId)
    {
        this.materialId = materialId;
    }

    public Long getMaterialId()
    {
        return materialId;
    }

    public void setBatchNo(String batchNo)
    {
        this.batchNo = batchNo;
    }

    public String getBatchNo()
    {
        return batchNo;
    }

    public void setBookQty(BigDecimal bookQty)
    {
        this.bookQty = bookQty;
    }

    public BigDecimal getBookQty()
    {
        return bookQty;
    }

    public void setActualQty(BigDecimal actualQty)
    {
        this.actualQty = actualQty;
    }

    public BigDecimal getActualQty()
    {
        return actualQty;
    }

    public void setDiffQty(BigDecimal diffQty)
    {
        this.diffQty = diffQty;
    }

    public BigDecimal getDiffQty()
    {
        return diffQty;
    }

    @Override
    public String toString()
    {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("lineId", getLineId())
            .append("stocktakeId", getStocktakeId())
            .append("materialId", getMaterialId())
            .append("batchNo", getBatchNo())
            .append("bookQty", getBookQty())
            .append("actualQty", getActualQty())
            .append("diffQty", getDiffQty())
            .append("createTime", getCreateTime())
            .toString();
    }
}
