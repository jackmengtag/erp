package com.ruoyi.sales.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 销售发货单明细对象 erp_delivery_line
 *
 * @author erp
 */
public class ErpDeliveryLine extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 明细ID */
    private Long lineId;

    /** 发货单ID */
    private Long deliveryId;

    /** 行号 */
    @Excel(name = "行号")
    private Integer lineNo;

    /** 物料ID */
    private Long materialId;

    /** 批次号 */
    @Excel(name = "批次号")
    private String batchNo;

    /** 发货数量 */
    @Excel(name = "发货数量")
    private BigDecimal qty;

    public void setLineId(Long lineId)
    {
        this.lineId = lineId;
    }

    public Long getLineId()
    {
        return lineId;
    }

    public void setDeliveryId(Long deliveryId)
    {
        this.deliveryId = deliveryId;
    }

    public Long getDeliveryId()
    {
        return deliveryId;
    }

    public void setLineNo(Integer lineNo)
    {
        this.lineNo = lineNo;
    }

    public Integer getLineNo()
    {
        return lineNo;
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
            .append("deliveryId", getDeliveryId())
            .append("lineNo", getLineNo())
            .append("materialId", getMaterialId())
            .append("batchNo", getBatchNo())
            .append("qty", getQty())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
