package com.ruoyi.plan.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 需求预测明细对象 erp_demand_forecast_line
 *
 * @author erp
 */
public class ErpDemandForecastLine extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 明细ID */
    private Long lineId;

    /** 预测ID */
    private Long forecastId;

    /** 物料ID */
    private Long materialId;

    /** 期间(YYYY-MM) */
    @Excel(name = "期间")
    private String periodMonth;

    /** 预测数量 */
    @Excel(name = "预测数量")
    private BigDecimal forecastQty;

    public void setLineId(Long lineId)
    {
        this.lineId = lineId;
    }

    public Long getLineId()
    {
        return lineId;
    }

    public void setForecastId(Long forecastId)
    {
        this.forecastId = forecastId;
    }

    public Long getForecastId()
    {
        return forecastId;
    }

    public void setMaterialId(Long materialId)
    {
        this.materialId = materialId;
    }

    public Long getMaterialId()
    {
        return materialId;
    }

    public void setPeriodMonth(String periodMonth)
    {
        this.periodMonth = periodMonth;
    }

    public String getPeriodMonth()
    {
        return periodMonth;
    }

    public void setForecastQty(BigDecimal forecastQty)
    {
        this.forecastQty = forecastQty;
    }

    public BigDecimal getForecastQty()
    {
        return forecastQty;
    }

    @Override
    public String toString()
    {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("lineId", getLineId())
            .append("forecastId", getForecastId())
            .append("materialId", getMaterialId())
            .append("periodMonth", getPeriodMonth())
            .append("forecastQty", getForecastQty())
            .append("createTime", getCreateTime())
            .toString();
    }
}
