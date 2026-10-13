package com.ruoyi.manufacturing.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 生产领料明细对象 erp_production_picking_line
 *
 * @author erp
 */
public class ErpProductionPickingLine extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 明细ID */
    private Long lineId;

    /** 领料单ID */
    private Long pickingId;

    /** 物料ID */
    private Long materialId;

    /** 批次号 */
    @Excel(name = "批次号")
    private String batchNo;

    /** 数量 */
    @Excel(name = "数量")
    private BigDecimal qty;

    public void setLineId(Long lineId)
    {
        this.lineId = lineId;
    }

    public Long getLineId()
    {
        return lineId;
    }

    public void setPickingId(Long pickingId)
    {
        this.pickingId = pickingId;
    }

    public Long getPickingId()
    {
        return pickingId;
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

    public void setQty(BigDecimal qty)
    {
        this.qty = qty;
    }

    public BigDecimal getQty()
    {
        return qty;
    }

    @Override
    public String toString()
    {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("lineId", getLineId())
            .append("pickingId", getPickingId())
            .append("materialId", getMaterialId())
            .append("batchNo", getBatchNo())
            .append("qty", getQty())
            .append("createTime", getCreateTime())
            .toString();
    }
}
