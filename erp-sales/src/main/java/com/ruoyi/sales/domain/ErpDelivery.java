package com.ruoyi.sales.domain;

import java.util.Date;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 销售发货单对象 erp_delivery
 *
 * @author erp
 */
public class ErpDelivery extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 发货单ID */
    private Long deliveryId;

    /** 发货单号 */
    @Excel(name = "发货单号")
    private String deliveryCode;

    /** 销售订单ID */
    private Long orderId;

    /** 客户ID */
    private Long customerId;

    /** 发货仓库ID */
    private Long warehouseId;

    /** 发货日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "发货日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date deliveryDate;

    /** 物流单号 */
    @Excel(name = "物流单号")
    private String logisticsNo;

    /** 状态(0草稿 1待审核 2已审核 3已出库 4已签收) */
    @Excel(name = "状态", readConverterExp = "0=草稿,1=待审核,2=已审核,3=已出库,4=已签收")
    private String status;

    /** 销售发货单明细信息 */
    private List<ErpDeliveryLine> erpDeliveryLineList;

    public void setDeliveryId(Long deliveryId)
    {
        this.deliveryId = deliveryId;
    }

    public Long getDeliveryId()
    {
        return deliveryId;
    }

    public void setDeliveryCode(String deliveryCode)
    {
        this.deliveryCode = deliveryCode;
    }

    public String getDeliveryCode()
    {
        return deliveryCode;
    }

    public void setOrderId(Long orderId)
    {
        this.orderId = orderId;
    }

    public Long getOrderId()
    {
        return orderId;
    }

    public void setCustomerId(Long customerId)
    {
        this.customerId = customerId;
    }

    public Long getCustomerId()
    {
        return customerId;
    }

    public void setWarehouseId(Long warehouseId)
    {
        this.warehouseId = warehouseId;
    }

    public Long getWarehouseId()
    {
        return warehouseId;
    }

    public void setDeliveryDate(Date deliveryDate)
    {
        this.deliveryDate = deliveryDate;
    }

    public Date getDeliveryDate()
    {
        return deliveryDate;
    }

    public void setLogisticsNo(String logisticsNo)
    {
        this.logisticsNo = logisticsNo;
    }

    public String getLogisticsNo()
    {
        return logisticsNo;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getStatus()
    {
        return status;
    }

    public List<ErpDeliveryLine> getErpDeliveryLineList()
    {
        return erpDeliveryLineList;
    }

    public void setErpDeliveryLineList(List<ErpDeliveryLine> erpDeliveryLineList)
    {
        this.erpDeliveryLineList = erpDeliveryLineList;
    }

    @Override
    public String toString()
    {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("deliveryId", getDeliveryId())
            .append("deliveryCode", getDeliveryCode())
            .append("orderId", getOrderId())
            .append("customerId", getCustomerId())
            .append("warehouseId", getWarehouseId())
            .append("deliveryDate", getDeliveryDate())
            .append("logisticsNo", getLogisticsNo())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .append("erpDeliveryLineList", getErpDeliveryLineList())
            .toString();
    }
}
