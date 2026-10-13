package com.ruoyi.finance.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 应付单对象 erp_payable
 *
 * @author erp
 */
public class ErpPayable extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 应付ID */
    private Long apId;

    /** 应付单号 */
    @Excel(name = "应付单号")
    private String apCode;

    /** 供应商ID */
    @Excel(name = "供应商ID")
    private Long supplierId;

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

    /** 应付金额 */
    @Excel(name = "应付金额")
    private BigDecimal amount;

    /** 已核销金额 */
    @Excel(name = "已核销金额")
    private BigDecimal settledAmount;

    /** 状态(0未核销 1部分核销 2已核销 3已逾期) */
    @Excel(name = "状态", readConverterExp = "0=未核销,1=部分核销,2=已核销,3=已逾期")
    private String status;

    public void setApId(Long apId)
    {
        this.apId = apId;
    }

    public Long getApId()
    {
        return apId;
    }

    public void setApCode(String apCode)
    {
        this.apCode = apCode;
    }

    public String getApCode()
    {
        return apCode;
    }

    public void setSupplierId(Long supplierId)
    {
        this.supplierId = supplierId;
    }

    public Long getSupplierId()
    {
        return supplierId;
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
            .append("apId", getApId())
            .append("apCode", getApCode())
            .append("supplierId", getSupplierId())
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
