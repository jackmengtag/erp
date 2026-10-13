package com.ruoyi.finance.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 付款单对象 erp_payment
 *
 * @author erp
 */
public class ErpPayment extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 付款ID */
    private Long paymentId;

    /** 付款单号 */
    @Excel(name = "付款单号")
    private String paymentCode;

    /** 供应商ID */
    @Excel(name = "供应商ID")
    private Long supplierId;

    /** 付款日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "付款日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date paymentDate;

    /** 付款金额 */
    @Excel(name = "付款金额")
    private BigDecimal amount;

    /** 已核销金额 */
    @Excel(name = "已核销金额")
    private BigDecimal writeoffAmount;

    /** 付款方式 */
    @Excel(name = "付款方式")
    private String payMethod;

    /** 三单匹配(0未匹配 1已匹配 2授权豁免) */
    @Excel(name = "三单匹配", readConverterExp = "0=未匹配,1=已匹配,2=授权豁免")
    private String matchFlag;

    /** 状态(0待核销 1部分核销 2已核销) */
    @Excel(name = "状态", readConverterExp = "0=待核销,1=部分核销,2=已核销")
    private String status;

    public void setPaymentId(Long paymentId)
    {
        this.paymentId = paymentId;
    }

    public Long getPaymentId()
    {
        return paymentId;
    }

    public void setPaymentCode(String paymentCode)
    {
        this.paymentCode = paymentCode;
    }

    public String getPaymentCode()
    {
        return paymentCode;
    }

    public void setSupplierId(Long supplierId)
    {
        this.supplierId = supplierId;
    }

    public Long getSupplierId()
    {
        return supplierId;
    }

    public void setPaymentDate(Date paymentDate)
    {
        this.paymentDate = paymentDate;
    }

    public Date getPaymentDate()
    {
        return paymentDate;
    }

    public void setAmount(BigDecimal amount)
    {
        this.amount = amount;
    }

    public BigDecimal getAmount()
    {
        return amount;
    }

    public void setWriteoffAmount(BigDecimal writeoffAmount)
    {
        this.writeoffAmount = writeoffAmount;
    }

    public BigDecimal getWriteoffAmount()
    {
        return writeoffAmount;
    }

    public void setPayMethod(String payMethod)
    {
        this.payMethod = payMethod;
    }

    public String getPayMethod()
    {
        return payMethod;
    }

    public void setMatchFlag(String matchFlag)
    {
        this.matchFlag = matchFlag;
    }

    public String getMatchFlag()
    {
        return matchFlag;
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
            .append("paymentId", getPaymentId())
            .append("paymentCode", getPaymentCode())
            .append("supplierId", getSupplierId())
            .append("paymentDate", getPaymentDate())
            .append("amount", getAmount())
            .append("writeoffAmount", getWriteoffAmount())
            .append("payMethod", getPayMethod())
            .append("matchFlag", getMatchFlag())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
