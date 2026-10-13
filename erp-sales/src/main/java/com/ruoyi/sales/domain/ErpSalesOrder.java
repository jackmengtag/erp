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
 * 销售订单对象 erp_sales_order
 *
 * @author erp
 */
public class ErpSalesOrder extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 订单ID */
    private Long orderId;

    /** 订单号 */
    @Excel(name = "订单号")
    private String orderCode;

    /** 客户ID */
    private Long customerId;

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

    /** 信用状态(0未检查 1通过 2超限放行) */
    @Excel(name = "信用状态", readConverterExp = "0=未检查,1=通过,2=超限放行")
    private String creditStatus;

    /** 订单类型(MTO/MTS/ATO) */
    @Excel(name = "订单类型")
    private String orderType;

    /** 状态(0草稿 1待审核 2已审核 3部分发货 4已完成 5已关闭 6已取消) */
    @Excel(name = "状态", readConverterExp = "0=草稿,1=待审核,2=已审核,3=部分发货,4=已完成,5=已关闭,6=已取消")
    private String status;

    /** 销售订单明细信息 */
    private List<ErpSalesOrderLine> erpSalesOrderLineList;

    public void setOrderId(Long orderId)
    {
        this.orderId = orderId;
    }

    public Long getOrderId()
    {
        return orderId;
    }

    public void setOrderCode(String orderCode)
    {
        this.orderCode = orderCode;
    }

    public String getOrderCode()
    {
        return orderCode;
    }

    public void setCustomerId(Long customerId)
    {
        this.customerId = customerId;
    }

    public Long getCustomerId()
    {
        return customerId;
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

    public void setCreditStatus(String creditStatus)
    {
        this.creditStatus = creditStatus;
    }

    public String getCreditStatus()
    {
        return creditStatus;
    }

    public void setOrderType(String orderType)
    {
        this.orderType = orderType;
    }

    public String getOrderType()
    {
        return orderType;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getStatus()
    {
        return status;
    }

    public List<ErpSalesOrderLine> getErpSalesOrderLineList()
    {
        return erpSalesOrderLineList;
    }

    public void setErpSalesOrderLineList(List<ErpSalesOrderLine> erpSalesOrderLineList)
    {
        this.erpSalesOrderLineList = erpSalesOrderLineList;
    }

    @Override
    public String toString()
    {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("orderId", getOrderId())
            .append("orderCode", getOrderCode())
            .append("customerId", getCustomerId())
            .append("orderDate", getOrderDate())
            .append("deliveryDate", getDeliveryDate())
            .append("currency", getCurrency())
            .append("amountTax", getAmountTax())
            .append("amountNotax", getAmountNotax())
            .append("creditStatus", getCreditStatus())
            .append("orderType", getOrderType())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .append("erpSalesOrderLineList", getErpSalesOrderLineList())
            .toString();
    }
}
