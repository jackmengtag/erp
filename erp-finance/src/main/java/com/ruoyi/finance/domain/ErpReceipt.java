package com.ruoyi.finance.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 收款单对象 erp_receipt
 *
 * @author erp
 */
public class ErpReceipt extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 收款ID */
    private Long receiptId;

    /** 收款单号 */
    @Excel(name = "收款单号")
    private String receiptCode;

    /** 客户ID */
    @Excel(name = "客户ID")
    private Long customerId;

    /** 收款日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "收款日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date receiptDate;

    /** 收款金额 */
    @Excel(name = "收款金额")
    private BigDecimal amount;

    /** 已核销金额 */
    @Excel(name = "已核销金额")
    private BigDecimal writeoffAmount;

    /** 收款方式 */
    @Excel(name = "收款方式")
    private String payMethod;

    /** 状态(0待核销 1部分核销 2已核销) */
    @Excel(name = "状态", readConverterExp = "0=待核销,1=部分核销,2=已核销")
    private String status;

    public void setReceiptId(Long receiptId)
    {
        this.receiptId = receiptId;
    }

    public Long getReceiptId()
    {
        return receiptId;
    }

    public void setReceiptCode(String receiptCode)
    {
        this.receiptCode = receiptCode;
    }

    public String getReceiptCode()
    {
        return receiptCode;
    }

    public void setCustomerId(Long customerId)
    {
        this.customerId = customerId;
    }

    public Long getCustomerId()
    {
        return customerId;
    }

    public void setReceiptDate(Date receiptDate)
    {
        this.receiptDate = receiptDate;
    }

    public Date getReceiptDate()
    {
        return receiptDate;
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
            .append("receiptId", getReceiptId())
            .append("receiptCode", getReceiptCode())
            .append("customerId", getCustomerId())
            .append("receiptDate", getReceiptDate())
            .append("amount", getAmount())
            .append("writeoffAmount", getWriteoffAmount())
            .append("payMethod", getPayMethod())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
