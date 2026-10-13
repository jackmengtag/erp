package com.ruoyi.plan.domain;

import java.util.Date;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 需求预测对象 erp_demand_forecast
 *
 * @author erp
 */
public class ErpDemandForecast extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 预测ID */
    private Long forecastId;

    /** 预测单号 */
    @Excel(name = "预测单号")
    private String forecastCode;

    /** 预测名称 */
    @Excel(name = "预测名称")
    private String forecastName;

    /** 预测版本 */
    @Excel(name = "预测版本")
    private String forecastVersion;

    /** 期间开始 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "期间开始", width = 30, dateFormat = "yyyy-MM-dd")
    private Date periodStart;

    /** 期间结束 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "期间结束", width = 30, dateFormat = "yyyy-MM-dd")
    private Date periodEnd;

    /** 状态(0草稿 1已发布 2已冻结 3已关闭) */
    @Excel(name = "状态", readConverterExp = "0=草稿,1=已发布,2=已冻结,3=已关闭")
    private String status;

    /** 需求预测明细信息 */
    private List<ErpDemandForecastLine> erpDemandForecastLineList;

    public void setForecastId(Long forecastId)
    {
        this.forecastId = forecastId;
    }

    public Long getForecastId()
    {
        return forecastId;
    }

    public void setForecastCode(String forecastCode)
    {
        this.forecastCode = forecastCode;
    }

    public String getForecastCode()
    {
        return forecastCode;
    }

    public void setForecastName(String forecastName)
    {
        this.forecastName = forecastName;
    }

    public String getForecastName()
    {
        return forecastName;
    }

    public void setForecastVersion(String forecastVersion)
    {
        this.forecastVersion = forecastVersion;
    }

    public String getForecastVersion()
    {
        return forecastVersion;
    }

    public void setPeriodStart(Date periodStart)
    {
        this.periodStart = periodStart;
    }

    public Date getPeriodStart()
    {
        return periodStart;
    }

    public void setPeriodEnd(Date periodEnd)
    {
        this.periodEnd = periodEnd;
    }

    public Date getPeriodEnd()
    {
        return periodEnd;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getStatus()
    {
        return status;
    }

    public List<ErpDemandForecastLine> getErpDemandForecastLineList()
    {
        return erpDemandForecastLineList;
    }

    public void setErpDemandForecastLineList(List<ErpDemandForecastLine> erpDemandForecastLineList)
    {
        this.erpDemandForecastLineList = erpDemandForecastLineList;
    }

    @Override
    public String toString()
    {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("forecastId", getForecastId())
            .append("forecastCode", getForecastCode())
            .append("forecastName", getForecastName())
            .append("forecastVersion", getForecastVersion())
            .append("periodStart", getPeriodStart())
            .append("periodEnd", getPeriodEnd())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .append("erpDemandForecastLineList", getErpDemandForecastLineList())
            .toString();
    }
}
