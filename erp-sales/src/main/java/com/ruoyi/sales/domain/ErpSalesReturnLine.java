package com.ruoyi.sales.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 销售退货单明细对象 erp_sales_return_line
 *
 * @author erp
 */
public class ErpSalesReturnLine extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 明细ID */
    private Long lineId;

    /** 退货单ID */
    private Long returnId;

    /** 行号 */
    @Excel(name = "行号")
    private Integer lineNo;

    /** 物料ID */
    private Long materialId;

    /** 批次号 */
    @Excel(name = "批次号")
    private String batchNo;

    /** 退货数量 */
    @Excel(name = "退货数量")
    private BigDecimal qty;

    public void setLineId(Long lineId)
    {
        this.lineId = lineId;
    }

    public Long getLineId()
    {
        return lineId;
    }

    public void setReturnId(Long returnId)
    {
        this.returnId = returnId;
    }

    public Long getReturnId()
    {
        return returnId;
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
            .append("returnId", getReturnId())
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
