package com.ruoyi.inventory.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 库存流水对象 erp_inventory_txn
 *
 * @author erp
 */
public class ErpInventoryTxn extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 流水ID */
    private Long txnId;

    /** 业务类型(采购入库/销售出库/生产领料/生产入库/调拨/其他) */
    @Excel(name = "业务类型")
    private String txnType;

    /** 物料ID */
    private Long materialId;

    /** 仓库ID */
    private Long warehouseId;

    /** 库位ID */
    private Long locationId;

    /** 批次号 */
    @Excel(name = "批次号")
    private String batchNo;

    /** 方向(1入库 -1出库) */
    @Excel(name = "方向", readConverterExp = "1=入库,-1=出库")
    private String direction;

    /** 数量 */
    @Excel(name = "数量")
    private BigDecimal qty;

    /** 单价 */
    @Excel(name = "单价")
    private BigDecimal costPrice;

    /** 金额 */
    @Excel(name = "金额")
    private BigDecimal amount;

    /** 来源单据类型 */
    @Excel(name = "来源单据类型")
    private String sourceType;

    /** 来源单据ID */
    private Long sourceId;

    /** 来源单据号 */
    @Excel(name = "来源单据号")
    private String sourceCode;

    /** 操作人 */
    @Excel(name = "操作人")
    private String operBy;

    /** 业务时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "业务时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date txnTime;

    public void setTxnId(Long txnId)
    {
        this.txnId = txnId;
    }

    public Long getTxnId()
    {
        return txnId;
    }

    public void setTxnType(String txnType)
    {
        this.txnType = txnType;
    }

    public String getTxnType()
    {
        return txnType;
    }

    public void setMaterialId(Long materialId)
    {
        this.materialId = materialId;
    }

    public Long getMaterialId()
    {
        return materialId;
    }

    public void setWarehouseId(Long warehouseId)
    {
        this.warehouseId = warehouseId;
    }

    public Long getWarehouseId()
    {
        return warehouseId;
    }

    public void setLocationId(Long locationId)
    {
        this.locationId = locationId;
    }

    public Long getLocationId()
    {
        return locationId;
    }

    public void setBatchNo(String batchNo)
    {
        this.batchNo = batchNo;
    }

    public String getBatchNo()
    {
        return batchNo;
    }

    public void setDirection(String direction)
    {
        this.direction = direction;
    }

    public String getDirection()
    {
        return direction;
    }

    public void setQty(BigDecimal qty)
    {
        this.qty = qty;
    }

    public BigDecimal getQty()
    {
        return qty;
    }

    public void setCostPrice(BigDecimal costPrice)
    {
        this.costPrice = costPrice;
    }

    public BigDecimal getCostPrice()
    {
        return costPrice;
    }

    public void setAmount(BigDecimal amount)
    {
        this.amount = amount;
    }

    public BigDecimal getAmount()
    {
        return amount;
    }

    public void setSourceType(String sourceType)
    {
        this.sourceType = sourceType;
    }

    public String getSourceType()
    {
        return sourceType;
    }

    public void setSourceId(Long sourceId)
    {
        this.sourceId = sourceId;
    }

    public Long getSourceId()
    {
        return sourceId;
    }

    public void setSourceCode(String sourceCode)
    {
        this.sourceCode = sourceCode;
    }

    public String getSourceCode()
    {
        return sourceCode;
    }

    public void setOperBy(String operBy)
    {
        this.operBy = operBy;
    }

    public String getOperBy()
    {
        return operBy;
    }

    public void setTxnTime(Date txnTime)
    {
        this.txnTime = txnTime;
    }

    public Date getTxnTime()
    {
        return txnTime;
    }

    @Override
    public String toString()
    {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("txnId", getTxnId())
            .append("txnType", getTxnType())
            .append("materialId", getMaterialId())
            .append("warehouseId", getWarehouseId())
            .append("locationId", getLocationId())
            .append("batchNo", getBatchNo())
            .append("direction", getDirection())
            .append("qty", getQty())
            .append("costPrice", getCostPrice())
            .append("amount", getAmount())
            .append("sourceType", getSourceType())
            .append("sourceId", getSourceId())
            .append("sourceCode", getSourceCode())
            .append("operBy", getOperBy())
            .append("txnTime", getTxnTime())
            .append("createTime", getCreateTime())
            .toString();
    }
}
