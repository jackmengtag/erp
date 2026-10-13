package com.ruoyi.finance.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 应收单对象 erp_receivable
 *
 * @author erp
 */
public class ErpReceivable extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 应收ID */
    private Long arId;

    /** 应收单号 */
    @Excel(name = "应收单号")
    private String arCode;

    /** 客户ID */
    @Excel(name = "客户ID")
    private Long customerId;

    /** 来源单据类型 */
    @Excel(name = "来源单据类型")
    private String sourceType;

    /** 来源单据ID */
    @Excel(name = "来源单据ID")
    private Long sourceId;

    /** 业务日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "业务日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date bizDate;

    /** 到期日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "到期日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date dueDate;

    /** 应收金额 */
    @Excel(name = "应收金额")
    private BigDecimal amount;

    /** 已核销金额 */
    @Excel(name = "已核销金额")
    private BigDecimal settledAmount;

    /** 状态(0未核销 1部分核销 2已核销 3已逾期) */
    @Excel(name = "状态", readConverterExp = "0=未核销,1=部分核销,2=已核销,3=已逾期")
    private String status;

    public void setArId(Long arId)
    {
        this.arId = arId;
    }

    public Long getArId()
    {
        return arId;
    }

    public void setArCode(String arCode)
    {
        this.arCode = arCode;
    }

    public String getArCode()
    {
        return arCode;
    }

    public void setCustomerId(Long customerId)
    {
        this.customerId = customerId;
    }

    public Long getCustomerId()
    {
        return customerId;
    }

    public void setSourceType(String sourceType)
    {
        this.sourceType = sourceType;
    }

    public String getSourceType()
    {
        return sourceType;
    }

    public void setSourceId(Long sourceId)
    {
        this.sourceId = sourceId;
    }

    public Long getSourceId()
    {
        return sourceId;
    }

    public void setBizDate(Date bizDate)
    {
        this.bizDate = bizDate;
    }

    public Date getBizDate()
    {
        return bizDate;
    }

    public void setDueDate(Date dueDate)
    {
        this.dueDate = dueDate;
    }

    public Date getDueDate()
    {
        return dueDate;
    }

    public void setAmount(BigDecimal amount)
    {
        this.amount = amount;
    }

    public BigDecimal getAmount()
    {
        return amount;
    }

    public void setSettledAmount(BigDecimal settledAmount)
    {
        this.settledAmount = settledAmount;
    }

    public BigDecimal getSettledAmount()
    {
        return settledAmount;
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
            .append("arId", getArId())
            .append("arCode", getArCode())
            .append("customerId", getCustomerId())
            .append("sourceType", getSourceType())
            .append("sourceId", getSourceId())
            .append("bizDate", getBizDate())
            .append("dueDate", getDueDate())
            .append("amount", getAmount())
            .append("settledAmount", getSettledAmount())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
