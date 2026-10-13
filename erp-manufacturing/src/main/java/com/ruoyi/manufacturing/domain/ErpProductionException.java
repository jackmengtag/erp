package com.ruoyi.manufacturing.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 生产异常对象 erp_production_exception
 *
 * @author erp
 */
public class ErpProductionException extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 异常ID */
    private Long exceptionId;

    /** 异常单号 */
    @Excel(name = "异常单号")
    private String exceptionCode;

    /** 工单ID */
    private Long moId;

    /** 类型(1缺料 2设备停机 3质量异常 4工艺变更 5返工 6报废 7计划延期) */
    @Excel(name = "类型", readConverterExp = "1=缺料,2=设备停机,3=质量异常,4=工艺变更,5=返工,6=报废,7=计划延期")
    private String exceptionType;

    /** 异常描述 */
    @Excel(name = "异常描述")
    private String exceptionDesc;

    /** 处理措施 */
    @Excel(name = "处理措施")
    private String handleMeasure;

    /** 处理状态(0待处理 1处理中 2已关闭) */
    @Excel(name = "处理状态", readConverterExp = "0=待处理,1=处理中,2=已关闭")
    private String handleStatus;

    public void setExceptionId(Long exceptionId)
    {
        this.exceptionId = exceptionId;
    }

    public Long getExceptionId()
    {
        return exceptionId;
    }

    public void setExceptionCode(String exceptionCode)
    {
        this.exceptionCode = exceptionCode;
    }

    public String getExceptionCode()
    {
        return exceptionCode;
    }

    public void setMoId(Long moId)
    {
        this.moId = moId;
    }

    public Long getMoId()
    {
        return moId;
    }

    public void setExceptionType(String exceptionType)
    {
        this.exceptionType = exceptionType;
    }

    public String getExceptionType()
    {
        return exceptionType;
    }

    public void setExceptionDesc(String exceptionDesc)
    {
        this.exceptionDesc = exceptionDesc;
    }

    public String getExceptionDesc()
    {
        return exceptionDesc;
    }

    public void setHandleMeasure(String handleMeasure)
    {
        this.handleMeasure = handleMeasure;
    }

    public String getHandleMeasure()
    {
        return handleMeasure;
    }

    public void setHandleStatus(String handleStatus)
    {
        this.handleStatus = handleStatus;
    }

    public String getHandleStatus()
    {
        return handleStatus;
    }

    @Override
    public String toString()
    {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("exceptionId", getExceptionId())
            .append("exceptionCode", getExceptionCode())
            .append("moId", getMoId())
            .append("exceptionType", getExceptionType())
            .append("exceptionDesc", getExceptionDesc())
            .append("handleMeasure", getHandleMeasure())
            .append("handleStatus", getHandleStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
