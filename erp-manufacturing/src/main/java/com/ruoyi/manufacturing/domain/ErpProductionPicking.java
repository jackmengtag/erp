package com.ruoyi.manufacturing.domain;

import java.util.Date;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 生产领料单对象 erp_production_picking
 *
 * @author erp
 */
public class ErpProductionPicking extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 领料单ID */
    private Long pickingId;

    /** 领料单号 */
    @Excel(name = "领料单号")
    private String pickingCode;

    /** 工单ID */
    private Long moId;

    /** 类型(1领料 2退料 3补料) */
    @Excel(name = "类型", readConverterExp = "1=领料,2=退料,3=补料")
    private String pickingType;

    /** 仓库ID */
    private Long warehouseId;

    /** 领料日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "领料日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date pickingDate;

    /** 状态(0草稿 1待审核 2已审核 3已出库) */
    @Excel(name = "状态", readConverterExp = "0=草稿,1=待审核,2=已审核,3=已出库")
    private String status;

    /** 生产领料明细信息 */
    private List<ErpProductionPickingLine> erpProductionPickingLineList;

    public void setPickingId(Long pickingId)
    {
        this.pickingId = pickingId;
    }

    public Long getPickingId()
    {
        return pickingId;
    }

    public void setPickingCode(String pickingCode)
    {
        this.pickingCode = pickingCode;
    }

    public String getPickingCode()
    {
        return pickingCode;
    }

    public void setMoId(Long moId)
    {
        this.moId = moId;
    }

    public Long getMoId()
    {
        return moId;
    }

    public void setPickingType(String pickingType)
    {
        this.pickingType = pickingType;
    }

    public String getPickingType()
    {
        return pickingType;
    }

    public void setWarehouseId(Long warehouseId)
    {
        this.warehouseId = warehouseId;
    }

    public Long getWarehouseId()
    {
        return warehouseId;
    }

    public void setPickingDate(Date pickingDate)
    {
        this.pickingDate = pickingDate;
    }

    public Date getPickingDate()
    {
        return pickingDate;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getStatus()
    {
        return status;
    }

    public List<ErpProductionPickingLine> getErpProductionPickingLineList()
    {
        return erpProductionPickingLineList;
    }

    public void setErpProductionPickingLineList(List<ErpProductionPickingLine> erpProductionPickingLineList)
    {
        this.erpProductionPickingLineList = erpProductionPickingLineList;
    }

    @Override
    public String toString()
    {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("pickingId", getPickingId())
            .append("pickingCode", getPickingCode())
            .append("moId", getMoId())
            .append("pickingType", getPickingType())
            .append("warehouseId", getWarehouseId())
            .append("pickingDate", getPickingDate())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .append("erpProductionPickingLineList", getErpProductionPickingLineList())
            .toString();
    }
}
