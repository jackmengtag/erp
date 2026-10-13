package com.ruoyi.sales.domain;

import java.util.Date;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 销售退货单对象 erp_sales_return
 *
 * @author erp
 */
public class ErpSalesReturn extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 退货单ID */
    private Long returnId;

    /** 退货单号 */
    @Excel(name = "退货单号")
    private String returnCode;

    /** 客户ID */
    private Long customerId;

    /** 原订单ID */
    private Long orderId;

    /** 退货仓库ID */
    private Long warehouseId;

    /** 退货日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "退货日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date returnDate;

    /** 退货原因 */
    @Excel(name = "退货原因")
    private String returnReason;

    /** 状态(0草稿 1待审核 2已审核 3已入库 4已关闭) */
    @Excel(name = "状态", readConverterExp = "0=草稿,1=待审核,2=已审核,3=已入库,4=已关闭")
    private String status;

    /** 销售退货单明细信息 */
    private List<ErpSalesReturnLine> erpSalesReturnLineList;

    public void setReturnId(Long returnId)
    {
        this.returnId = returnId;
    }

    public Long getReturnId()
    {
        return returnId;
    }

    public void setReturnCode(String returnCode)
    {
        this.returnCode = returnCode;
    }

    public String getReturnCode()
    {
        return returnCode;
    }

    public void setCustomerId(Long customerId)
    {
        this.customerId = customerId;
    }

    public Long getCustomerId()
    {
        return customerId;
    }

    public void setOrderId(Long orderId)
    {
        this.orderId = orderId;
    }

    public Long getOrderId()
    {
        return orderId;
    }

    public void setWarehouseId(Long warehouseId)
    {
        this.warehouseId = warehouseId;
    }

    public Long getWarehouseId()
    {
        return warehouseId;
    }

    public void setReturnDate(Date returnDate)
    {
        this.returnDate = returnDate;
    }

    public Date getReturnDate()
    {
        return returnDate;
    }

    public void setReturnReason(String returnReason)
    {
        this.returnReason = returnReason;
    }

    public String getReturnReason()
    {
        return returnReason;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getStatus()
    {
        return status;
    }

    public List<ErpSalesReturnLine> getErpSalesReturnLineList()
    {
        return erpSalesReturnLineList;
    }

    public void setErpSalesReturnLineList(List<ErpSalesReturnLine> erpSalesReturnLineList)
    {
        this.erpSalesReturnLineList = erpSalesReturnLineList;
    }

    @Override
    public String toString()
    {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("returnId", getReturnId())
            .append("returnCode", getReturnCode())
            .append("customerId", getCustomerId())
            .append("orderId", getOrderId())
            .append("warehouseId", getWarehouseId())
            .append("returnDate", getReturnDate())
            .append("returnReason", getReturnReason())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .append("erpSalesReturnLineList", getErpSalesReturnLineList())
            .toString();
    }
}
