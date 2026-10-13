package com.ruoyi.purchase.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 采购订单对象 erp_purchase_order
 *
 * @author erp
 */
public class ErpPurchaseOrder extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 采购订单ID */
    private Long poId;

    /** 采购订单号 */
    @Excel(name = "采购订单号")
    private String poCode;

    /** 供应商ID */
    private Long supplierId;

    /** 订单日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "订单日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date orderDate;

    /** 交货日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "交货日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date deliveryDate;

    /** 币种 */
    @Excel(name = "币种")
    private String currency;

    /** 价税合计 */
    @Excel(name = "价税合计")
    private BigDecimal amountTax;

    /** 未税金额 */
    @Excel(name = "未税金额")
    private BigDecimal amountNotax;

    /** 三单匹配状态(0未匹配 1已匹配 2差异) */
    @Excel(name = "三单匹配状态", readConverterExp = "0=未匹配,1=已匹配,2=差异")
    private String matchStatus;

    /** 状态(0草稿 1待审核 2已审核 3部分收货 4完全收货 5已关闭) */
    @Excel(name = "状态", readConverterExp = "0=草稿,1=待审核,2=已审核,3=部分收货,4=完全收货,5=已关闭")
    private String status;

    /** 采购订单明细信息 */
    private List<ErpPurchaseOrderLine> erpPurchaseOrderLineList;

    public void setPoId(Long poId)
    {
        this.poId = poId;
    }

    public Long getPoId()
    {
        return poId;
    }

    public void setPoCode(String poCode)
    {
        this.poCode = poCode;
    }

    public String getPoCode()
    {
        return poCode;
    }

    public void setSupplierId(Long supplierId)
    {
        this.supplierId = supplierId;
    }

    public Long getSupplierId()
    {
        return supplierId;
    }

    public void setOrderDate(Date orderDate)
    {
        this.orderDate = orderDate;
    }

    public Date getOrderDate()
    {
        return orderDate;
    }

    public void setDeliveryDate(Date deliveryDate)
    {
        this.deliveryDate = deliveryDate;
    }

    public Date getDeliveryDate()
    {
        return deliveryDate;
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

    public void setMatchStatus(String matchStatus)
    {
        this.matchStatus = matchStatus;
    }

    public String getMatchStatus()
    {
        return matchStatus;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getStatus()
    {
        return status;
    }

    public List<ErpPurchaseOrderLine> getErpPurchaseOrderLineList()
    {
        return erpPurchaseOrderLineList;
    }

    public void setErpPurchaseOrderLineList(List<ErpPurchaseOrderLine> erpPurchaseOrderLineList)
    {
        this.erpPurchaseOrderLineList = erpPurchaseOrderLineList;
    }

    @Override
    public String toString()
    {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("poId", getPoId())
            .append("poCode", getPoCode())
            .append("supplierId", getSupplierId())
            .append("orderDate", getOrderDate())
            .append("deliveryDate", getDeliveryDate())
            .append("currency", getCurrency())
            .append("amountTax", getAmountTax())
            .append("amountNotax", getAmountNotax())
            .append("matchStatus", getMatchStatus())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .append("erpPurchaseOrderLineList", getErpPurchaseOrderLineList())
            .toString();
    }
}
