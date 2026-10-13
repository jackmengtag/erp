package com.ruoyi.inventory.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 库存预留对象 erp_inventory_reserve
 *
 * @author erp
 */
public class ErpInventoryReserve extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 预留ID */
    private Long reserveId;

    /** 物料ID */
    private Long materialId;

    /** 仓库ID */
    private Long warehouseId;

    /** 批次号 */
    @Excel(name = "批次号")
    private String batchNo;

    /** 预留数量 */
    @Excel(name = "预留数量")
    private BigDecimal qty;

    /** 来源单据类型 */
    @Excel(name = "来源单据类型")
    private String sourceType;

    /** 来源单据ID */
    private Long sourceId;

    /** 来源单据号 */
    @Excel(name = "来源单据号")
    private String sourceCode;

    /** 状态(0预留中 1已释放 2已消耗) */
    @Excel(name = "状态", readConverterExp = "0=预留中,1=已释放,2=已消耗")
    private String status;

    public void setReserveId(Long reserveId)
    {
        this.reserveId = reserveId;
    }

    public Long getReserveId()
    {
        return reserveId;
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

    public void setBatchNo(String batchNo)
    {
        this.batchNo = batchNo;
    }

    public String getBatchNo()
    {
        return batchNo;
    }

    public void setQty(BigDecimal qty)
    {
        this.qty = qty;
    }

    public BigDecimal getQty()
    {
        return qty;
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
            .append("reserveId", getReserveId())
            .append("materialId", getMaterialId())
            .append("warehouseId", getWarehouseId())
            .append("batchNo", getBatchNo())
            .append("qty", getQty())
            .append("sourceType", getSourceType())
            .append("sourceId", getSourceId())
            .append("sourceCode", getSourceCode())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
