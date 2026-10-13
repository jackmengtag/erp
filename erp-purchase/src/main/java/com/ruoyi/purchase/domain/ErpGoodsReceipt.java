package com.ruoyi.purchase.domain;

import java.util.Date;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 收货单对象 erp_goods_receipt
 *
 * @author erp
 */
public class ErpGoodsReceipt extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 收货单ID */
    private Long receiptId;

    /** 收货单号 */
    @Excel(name = "收货单号")
    private String receiptCode;

    /** 采购订单ID */
    private Long poId;

    /** 供应商ID */
    private Long supplierId;

    /** 收货仓库ID */
    private Long warehouseId;

    /** 收货日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "收货日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date receiptDate;

    /** 检验状态(0待检 1合格 2不合格 3让步接收) */
    @Excel(name = "检验状态", readConverterExp = "0=待检,1=合格,2=不合格,3=让步接收")
    private String inspectStatus;

    /** 状态(0草稿 1待审核 2已审核 3已入库 4已关闭) */
    @Excel(name = "状态", readConverterExp = "0=草稿,1=待审核,2=已审核,3=已入库,4=已关闭")
    private String status;

    /** 收货单明细信息 */
    private List<ErpGoodsReceiptLine> erpGoodsReceiptLineList;

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

    public void setPoId(Long poId)
    {
        this.poId = poId;
    }

    public Long getPoId()
    {
        return poId;
    }

    public void setSupplierId(Long supplierId)
    {
        this.supplierId = supplierId;
    }

    public Long getSupplierId()
    {
        return supplierId;
    }

    public void setWarehouseId(Long warehouseId)
    {
        this.warehouseId = warehouseId;
    }

    public Long getWarehouseId()
    {
        return warehouseId;
    }

    public void setReceiptDate(Date receiptDate)
    {
        this.receiptDate = receiptDate;
    }

    public Date getReceiptDate()
    {
        return receiptDate;
    }

    public void setInspectStatus(String inspectStatus)
    {
        this.inspectStatus = inspectStatus;
    }

    public String getInspectStatus()
    {
        return inspectStatus;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getStatus()
    {
        return status;
    }

    public List<ErpGoodsReceiptLine> getErpGoodsReceiptLineList()
    {
        return erpGoodsReceiptLineList;
    }

    public void setErpGoodsReceiptLineList(List<ErpGoodsReceiptLine> erpGoodsReceiptLineList)
    {
        this.erpGoodsReceiptLineList = erpGoodsReceiptLineList;
    }

    @Override
    public String toString()
    {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("receiptId", getReceiptId())
            .append("receiptCode", getReceiptCode())
            .append("poId", getPoId())
            .append("supplierId", getSupplierId())
            .append("warehouseId", getWarehouseId())
            .append("receiptDate", getReceiptDate())
            .append("inspectStatus", getInspectStatus())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .append("erpGoodsReceiptLineList", getErpGoodsReceiptLineList())
            .toString();
    }
}
