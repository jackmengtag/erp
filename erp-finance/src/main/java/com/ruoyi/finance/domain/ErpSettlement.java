package com.ruoyi.finance.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 核销记录对象 erp_settlement
 *
 * @author erp
 */
public class ErpSettlement extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 核销ID */
    private Long settleId;

    /** 核销类型(1应收 2应付) */
    @Excel(name = "核销类型", readConverterExp = "1=应收,2=应付")
    private String settleType;

    /** 应收/应付单ID */
    @Excel(name = "应收/应付单ID")
    private Long sourceId;

    /** 收款/付款单ID */
    @Excel(name = "收款/付款单ID")
    private Long targetId;

    /** 核销金额 */
    @Excel(name = "核销金额")
    private BigDecimal amount;

    /** 核销日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "核销日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date settleDate;

    public void setSettleId(Long settleId)
    {
        this.settleId = settleId;
    }

    public Long getSettleId()
    {
        return settleId;
    }

    public void setSettleType(String settleType)
    {
        this.settleType = settleType;
    }

    public String getSettleType()
    {
        return settleType;
    }

    public void setSourceId(Long sourceId)
    {
        this.sourceId = sourceId;
    }

    public Long getSourceId()
    {
        return sourceId;
    }

    public void setTargetId(Long targetId)
    {
        this.targetId = targetId;
    }

    public Long getTargetId()
    {
        return targetId;
    }

    public void setAmount(BigDecimal amount)
    {
        this.amount = amount;
    }

    public BigDecimal getAmount()
    {
        return amount;
    }

    public void setSettleDate(Date settleDate)
    {
        this.settleDate = settleDate;
    }

    public Date getSettleDate()
    {
        return settleDate;
    }

    @Override
    public String toString()
    {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("settleId", getSettleId())
            .append("settleType", getSettleType())
            .append("sourceId", getSourceId())
            .append("targetId", getTargetId())
            .append("amount", getAmount())
            .append("settleDate", getSettleDate())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
