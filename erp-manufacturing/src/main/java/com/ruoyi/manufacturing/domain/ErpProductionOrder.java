package com.ruoyi.manufacturing.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 生产工单对象 erp_production_order
 *
 * @author erp
 */
public class ErpProductionOrder extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 工单ID */
    private Long moId;

    /** 工单号 */
    @Excel(name = "工单号")
    private String moCode;

    /** 产品物料ID */
    private Long materialId;

    /** BOM ID */
    private Long bomId;

    /** 工艺路线ID */
    private Long routingId;

    /** 计划数量 */
    @Excel(name = "计划数量")
    private BigDecimal planQty;

    /** 完工数量 */
    @Excel(name = "完工数量")
    private BigDecimal finishQty;

    /** 报废数量 */
    @Excel(name = "报废数量")
    private BigDecimal scrapQty;

    /** 计划开工日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "计划开工日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date planStartDate;

    /** 计划完工日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "计划完工日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date planEndDate;

    /** 关联销售订单ID */
    private Long orderId;

    /** 完工入库仓库 */
    private Long warehouseId;

    /** 状态(0计划 1已下达 2领料中 3生产中 4待检验 5完工入库 6已关闭) */
    @Excel(name = "状态", readConverterExp = "0=计划,1=已下达,2=领料中,3=生产中,4=待检验,5=完工入库,6=已关闭")
    private String status;

    public void setMoId(Long moId)
    {
        this.moId = moId;
    }

    public Long getMoId()
    {
        return moId;
    }

    public void setMoCode(String moCode)
    {
        this.moCode = moCode;
    }

    public String getMoCode()
    {
        return moCode;
    }

    public void setMaterialId(Long materialId)
    {
        this.materialId = materialId;
    }

    public Long getMaterialId()
    {
        return materialId;
    }

    public void setBomId(Long bomId)
    {
        this.bomId = bomId;
    }

    public Long getBomId()
    {
        return bomId;
    }

    public void setRoutingId(Long routingId)
    {
        this.routingId = routingId;
    }

    public Long getRoutingId()
    {
        return routingId;
    }

    public void setPlanQty(BigDecimal planQty)
    {
        this.planQty = planQty;
    }

    public BigDecimal getPlanQty()
    {
        return planQty;
    }

    public void setFinishQty(BigDecimal finishQty)
    {
        this.finishQty = finishQty;
    }

    public BigDecimal getFinishQty()
    {
        return finishQty;
    }

    public void setScrapQty(BigDecimal scrapQty)
    {
        this.scrapQty = scrapQty;
    }

    public BigDecimal getScrapQty()
    {
        return scrapQty;
    }

    public void setPlanStartDate(Date planStartDate)
    {
        this.planStartDate = planStartDate;
    }

    public Date getPlanStartDate()
    {
        return planStartDate;
    }

    public void setPlanEndDate(Date planEndDate)
    {
        this.planEndDate = planEndDate;
    }

    public Date getPlanEndDate()
    {
        return planEndDate;
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
            .append("moId", getMoId())
            .append("moCode", getMoCode())
            .append("materialId", getMaterialId())
            .append("bomId", getBomId())
            .append("routingId", getRoutingId())
            .append("planQty", getPlanQty())
            .append("finishQty", getFinishQty())
            .append("scrapQty", getScrapQty())
            .append("planStartDate", getPlanStartDate())
            .append("planEndDate", getPlanEndDate())
            .append("orderId", getOrderId())
            .append("warehouseId", getWarehouseId())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
