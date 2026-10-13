package com.ruoyi.inventory.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 库存余额对象 erp_inventory
 *
 * @author erp
 */
public class ErpInventory extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 库存ID */
    private Long inventoryId;

    /** 物料ID */
    private Long materialId;

    /** 仓库ID */
    private Long warehouseId;

    /** 库位ID */
    private Long locationId;

    /** 批次号 */
    @Excel(name = "批次号")
    private String batchNo;

    /** 现存数量 */
    @Excel(name = "现存数量")
    private BigDecimal stockQty;

    /** 可用数量 */
    @Excel(name = "可用数量")
    private BigDecimal availableQty;

    /** 预留数量 */
    @Excel(name = "预留数量")
    private BigDecimal reservedQty;

    /** 质检数量 */
    @Excel(name = "质检数量")
    private BigDecimal inspectQty;

    /** 冻结数量 */
    @Excel(name = "冻结数量")
    private BigDecimal frozenQty;

    /** 结存单价 */
    @Excel(name = "结存单价")
    private BigDecimal costPrice;

    public void setInventoryId(Long inventoryId)
    {
        this.inventoryId = inventoryId;
    }

    public Long getInventoryId()
    {
        return inventoryId;
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

    public void setStockQty(BigDecimal stockQty)
    {
        this.stockQty = stockQty;
    }

    public BigDecimal getStockQty()
    {
        return stockQty;
    }

    public void setAvailableQty(BigDecimal availableQty)
    {
        this.availableQty = availableQty;
    }

    public BigDecimal getAvailableQty()
    {
        return availableQty;
    }

    public void setReservedQty(BigDecimal reservedQty)
    {
        this.reservedQty = reservedQty;
    }

    public BigDecimal getReservedQty()
    {
        return reservedQty;
    }

    public void setInspectQty(BigDecimal inspectQty)
    {
        this.inspectQty = inspectQty;
    }

    public BigDecimal getInspectQty()
    {
        return inspectQty;
    }

    public void setFrozenQty(BigDecimal frozenQty)
    {
        this.frozenQty = frozenQty;
    }

    public BigDecimal getFrozenQty()
    {
        return frozenQty;
    }

    public void setCostPrice(BigDecimal costPrice)
    {
        this.costPrice = costPrice;
    }

    public BigDecimal getCostPrice()
    {
        return costPrice;
    }

    @Override
    public String toString()
    {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("inventoryId", getInventoryId())
            .append("materialId", getMaterialId())
            .append("warehouseId", getWarehouseId())
            .append("locationId", getLocationId())
            .append("batchNo", getBatchNo())
            .append("stockQty", getStockQty())
            .append("availableQty", getAvailableQty())
            .append("reservedQty", getReservedQty())
            .append("inspectQty", getInspectQty())
            .append("frozenQty", getFrozenQty())
            .append("costPrice", getCostPrice())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
