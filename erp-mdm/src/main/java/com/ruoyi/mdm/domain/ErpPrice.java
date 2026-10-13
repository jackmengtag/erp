package com.ruoyi.mdm.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 价格条件对象 erp_price
 *
 * @author erp
 */
public class ErpPrice extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 价格ID */
    private Long priceId;

    /** 价格类型(1销售价 2采购价) */
    @Excel(name = "价格类型", readConverterExp = "1=销售价,2=采购价")
    private String priceType;

    /** 物料ID */
    private Long materialId;

    /** 客户/供应商ID(0为通用) */
    private Long partnerId;

    /** 币种 */
    @Excel(name = "币种")
    private String currency;

    /** 数量下限 */
    @Excel(name = "数量下限")
    private BigDecimal qtyMin;

    /** 价格 */
    @Excel(name = "价格")
    private BigDecimal price;

    /** 税率(%) */
    @Excel(name = "税率(%)")
    private BigDecimal taxRate;

    /** 生效日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "生效日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date effectDate;

    /** 失效日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "失效日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date expireDate;

    /** 状态(0正常 1停用) */
    @Excel(name = "状态", readConverterExp = "0=正常,1=停用")
    private String status;

    public void setPriceId(Long priceId)
    {
        this.priceId = priceId;
    }

    public Long getPriceId()
    {
        return priceId;
    }

    public void setPriceType(String priceType)
    {
        this.priceType = priceType;
    }

    public String getPriceType()
    {
        return priceType;
    }

    public void setMaterialId(Long materialId)
    {
        this.materialId = materialId;
    }

    public Long getMaterialId()
    {
        return materialId;
    }

    public void setPartnerId(Long partnerId)
    {
        this.partnerId = partnerId;
    }

    public Long getPartnerId()
    {
        return partnerId;
    }

    public void setCurrency(String currency)
    {
        this.currency = currency;
    }

    public String getCurrency()
    {
        return currency;
    }

    public void setQtyMin(BigDecimal qtyMin)
    {
        this.qtyMin = qtyMin;
    }

    public BigDecimal getQtyMin()
    {
        return qtyMin;
    }

    public void setPrice(BigDecimal price)
    {
        this.price = price;
    }

    public BigDecimal getPrice()
    {
        return price;
    }

    public void setTaxRate(BigDecimal taxRate)
    {
        this.taxRate = taxRate;
    }

    public BigDecimal getTaxRate()
    {
        return taxRate;
    }

    public void setEffectDate(Date effectDate)
    {
        this.effectDate = effectDate;
    }

    public Date getEffectDate()
    {
        return effectDate;
    }

    public void setExpireDate(Date expireDate)
    {
        this.expireDate = expireDate;
    }

    public Date getExpireDate()
    {
        return expireDate;
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
            .append("priceId", getPriceId())
            .append("priceType", getPriceType())
            .append("materialId", getMaterialId())
            .append("partnerId", getPartnerId())
            .append("currency", getCurrency())
            .append("qtyMin", getQtyMin())
            .append("price", getPrice())
            .append("taxRate", getTaxRate())
            .append("effectDate", getEffectDate())
            .append("expireDate", getExpireDate())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
