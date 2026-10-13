package com.ruoyi.sales.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 销售报价单对象 erp_sales_quote
 *
 * @author erp
 */
public class ErpSalesQuote extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 报价单ID */
    private Long quoteId;

    /** 报价单号 */
    @Excel(name = "报价单号")
    private String quoteCode;

    /** 客户ID */
    private Long customerId;

    /** 报价日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "报价日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date quoteDate;

    /** 有效期至 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "有效期至", width = 30, dateFormat = "yyyy-MM-dd")
    private Date validDate;

    /** 币种 */
    @Excel(name = "币种")
    private String currency;

    /** 价税合计 */
    @Excel(name = "价税合计")
    private BigDecimal amountTax;

    /** 未税金额 */
    @Excel(name = "未税金额")
    private BigDecimal amountNotax;

    /** 状态(0草稿 1待审核 2已审核 3已转订单 4已关闭) */
    @Excel(name = "状态", readConverterExp = "0=草稿,1=待审核,2=已审核,3=已转订单,4=已关闭")
    private String status;

    /** 销售报价单明细信息 */
    private List<ErpSalesQuoteLine> erpSalesQuoteLineList;

    public void setQuoteId(Long quoteId)
    {
        this.quoteId = quoteId;
    }

    public Long getQuoteId()
    {
        return quoteId;
    }

    public void setQuoteCode(String quoteCode)
    {
        this.quoteCode = quoteCode;
    }

    public String getQuoteCode()
    {
        return quoteCode;
    }

    public void setCustomerId(Long customerId)
    {
        this.customerId = customerId;
    }

    public Long getCustomerId()
    {
        return customerId;
    }

    public void setQuoteDate(Date quoteDate)
    {
        this.quoteDate = quoteDate;
    }

    public Date getQuoteDate()
    {
        return quoteDate;
    }

    public void setValidDate(Date validDate)
    {
        this.validDate = validDate;
    }

    public Date getValidDate()
    {
        return validDate;
    }

    public void setCurrency(String currency)
    {
        this.currency = currency;
    }

    public String getCurrency()
    {
        return currency;
    }

    public void setAmountTax(BigDecimal amountTax)
    {
        this.amountTax = amountTax;
    }

    public BigDecimal getAmountTax()
    {
        return amountTax;
    }

    public void setAmountNotax(BigDecimal amountNotax)
    {
        this.amountNotax = amountNotax;
    }

    public BigDecimal getAmountNotax()
    {
        return amountNotax;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getStatus()
    {
        return status;
    }

    public List<ErpSalesQuoteLine> getErpSalesQuoteLineList()
    {
        return erpSalesQuoteLineList;
    }

    public void setErpSalesQuoteLineList(List<ErpSalesQuoteLine> erpSalesQuoteLineList)
    {
        this.erpSalesQuoteLineList = erpSalesQuoteLineList;
    }

    @Override
    public String toString()
    {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("quoteId", getQuoteId())
            .append("quoteCode", getQuoteCode())
            .append("customerId", getCustomerId())
            .append("quoteDate", getQuoteDate())
            .append("validDate", getValidDate())
            .append("currency", getCurrency())
            .append("amountTax", getAmountTax())
            .append("amountNotax", getAmountNotax())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .append("erpSalesQuoteLineList", getErpSalesQuoteLineList())
            .toString();
    }
}
