package com.ruoyi.purchase.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 采购询价明细对象 erp_purchase_inquiry_line
 *
 * @author erp
 */
public class ErpPurchaseInquiryLine extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 明细ID */
    private Long lineId;

    /** 询价ID */
    private Long inquiryId;

    /** 行号 */
    @Excel(name = "行号")
    private Integer lineNo;

    /** 物料ID */
    private Long materialId;

    /** 询价数量 */
    @Excel(name = "询价数量")
    private BigDecimal qty;

    /** 报价供应商ID */
    private Long supplierId;

    /** 报价单价 */
    @Excel(name = "报价单价")
    private BigDecimal quotePrice;

    /** 税率(%) */
    @Excel(name = "税率(%)")
    private BigDecimal taxRate;

    /** 交货天数 */
    @Excel(name = "交货天数")
    private Integer deliveryDays;

    /** 是否中选(1是 0否) */
    @Excel(name = "是否中选", readConverterExp = "1=是,0=否")
    private String selectedFlag;

    public void setLineId(Long lineId)
    {
        this.lineId = lineId;
    }

    public Long getLineId()
    {
        return lineId;
    }

    public void setInquiryId(Long inquiryId)
    {
        this.inquiryId = inquiryId;
    }

    public Long getInquiryId()
    {
        return inquiryId;
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

    public void setSupplierId(Long supplierId)
    {
        this.supplierId = supplierId;
    }

    public Long getSupplierId()
    {
        return supplierId;
    }

    public void setQuotePrice(BigDecimal quotePrice)
    {
        this.quotePrice = quotePrice;
    }

    public BigDecimal getQuotePrice()
    {
        return quotePrice;
    }

    public void setTaxRate(BigDecimal taxRate)
    {
        this.taxRate = taxRate;
    }

    public BigDecimal getTaxRate()
    {
        return taxRate;
    }

    public void setDeliveryDays(Integer deliveryDays)
    {
        this.deliveryDays = deliveryDays;
    }

    public Integer getDeliveryDays()
    {
        return deliveryDays;
    }

    public void setSelectedFlag(String selectedFlag)
    {
        this.selectedFlag = selectedFlag;
    }

    public String getSelectedFlag()
    {
        return selectedFlag;
    }

    @Override
    public String toString()
    {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("lineId", getLineId())
            .append("inquiryId", getInquiryId())
            .append("lineNo", getLineNo())
            .append("materialId", getMaterialId())
            .append("qty", getQty())
            .append("supplierId", getSupplierId())
            .append("quotePrice", getQuotePrice())
            .append("taxRate", getTaxRate())
            .append("deliveryDays", getDeliveryDays())
            .append("selectedFlag", getSelectedFlag())
            .append("createTime", getCreateTime())
            .toString();
    }
}
