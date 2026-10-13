package com.ruoyi.purchase.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 采购订单明细对象 erp_purchase_order_line
 *
 * @author erp
 */
public class ErpPurchaseOrderLine extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 明细ID */
    private Long lineId;

    /** 采购订单ID */
    private Long poId;

    /** 行号 */
    @Excel(name = "行号")
    private Integer lineNo;

    /** 物料ID */
    private Long materialId;

    /** 订购数量 */
    @Excel(name = "订购数量")
    private BigDecimal qty;

    /** 已收货数量 */
    @Excel(name = "已收货数量")
    private BigDecimal receivedQty;

    /** 单价 */
    @Excel(name = "单价")
    private BigDecimal price;

    /** 税率(%) */
    @Excel(name = "税率(%)")
    private BigDecimal taxRate;

    /** 金额 */
    @Excel(name = "金额")
    private BigDecimal amount;

    /** 交期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "交期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date deliveryDate;

    public void setLineId(Long lineId)
    {
        this.lineId = lineId;
    }

    public Long getLineId()
    {
        return lineId;
    }

    public void setPoId(Long poId)
    {
        this.poId = poId;
    }

    public Long getPoId()
    {
        return poId;
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

    public void setReceivedQty(BigDecimal receivedQty)
    {
        this.receivedQty = receivedQty;
    }

    public BigDecimal getReceivedQty()
    {
        return receivedQty;
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

    public void setAmount(BigDecimal amount)
    {
        this.amount = amount;
    }

    public BigDecimal getAmount()
    {
        return amount;
    }

    public void setDeliveryDate(Date deliveryDate)
    {
        this.deliveryDate = deliveryDate;
    }

    public Date getDeliveryDate()
    {
        return deliveryDate;
    }

    @Override
    public String toString()
    {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("lineId", getLineId())
            .append("poId", getPoId())
            .append("lineNo", getLineNo())
            .append("materialId", getMaterialId())
            .append("qty", getQty())
            .append("receivedQty", getReceivedQty())
            .append("price", getPrice())
            .append("taxRate", getTaxRate())
            .append("amount", getAmount())
            .append("deliveryDate", getDeliveryDate())
            .append("createTime", getCreateTime())
            .toString();
    }
}
