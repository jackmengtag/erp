package com.ruoyi.purchase.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 采购申请明细对象 erp_purchase_req_line
 *
 * @author erp
 */
public class ErpPurchaseReqLine extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 明细ID */
    private Long lineId;

    /** 申请ID */
    private Long reqId;

    /** 行号 */
    @Excel(name = "行号")
    private Integer lineNo;

    /** 物料ID */
    private Long materialId;

    /** 申请数量 */
    @Excel(name = "申请数量")
    private BigDecimal qty;

    /** 需求日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "需求日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date needDate;

    public void setLineId(Long lineId)
    {
        this.lineId = lineId;
    }

    public Long getLineId()
    {
        return lineId;
    }

    public void setReqId(Long reqId)
    {
        this.reqId = reqId;
    }

    public Long getReqId()
    {
        return reqId;
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

    public void setQty(BigDecimal qty)
    {
        this.qty = qty;
    }

    public BigDecimal getQty()
    {
        return qty;
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
            .append("lineId", getLineId())
            .append("reqId", getReqId())
            .append("lineNo", getLineNo())
            .append("materialId", getMaterialId())
            .append("qty", getQty())
            .append("needDate", getNeedDate())
            .append("createTime", getCreateTime())
            .toString();
    }
}
