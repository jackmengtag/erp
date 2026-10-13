package com.ruoyi.manufacturing.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 工序报工对象 erp_production_report
 *
 * @author erp
 */
public class ErpProductionReport extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 报工ID */
    private Long reportId;

    /** 报工单号 */
    @Excel(name = "报工单号")
    private String reportCode;

    /** 工单ID */
    private Long moId;

    /** 工序ID */
    private Long operationId;

    /** 报工日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "报工日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date reportDate;

    /** 合格数量 */
    @Excel(name = "合格数量")
    private BigDecimal okQty;

    /** 不良数量 */
    @Excel(name = "不良数量")
    private BigDecimal ngQty;

    /** 返工数量 */
    @Excel(name = "返工数量")
    private BigDecimal reworkQty;

    /** 工时 */
    @Excel(name = "工时")
    private BigDecimal workHours;

    /** 报工人 */
    @Excel(name = "报工人")
    private String worker;

    /** 状态(0草稿 1已提交 2已审核) */
    @Excel(name = "状态", readConverterExp = "0=草稿,1=已提交,2=已审核")
    private String status;

    public void setReportId(Long reportId)
    {
        this.reportId = reportId;
    }

    public Long getReportId()
    {
        return reportId;
    }

    public void setReportCode(String reportCode)
    {
        this.reportCode = reportCode;
    }

    public String getReportCode()
    {
        return reportCode;
    }

    public void setMoId(Long moId)
    {
        this.moId = moId;
    }

    public Long getMoId()
    {
        return moId;
    }

    public void setOperationId(Long operationId)
    {
        this.operationId = operationId;
    }

    public Long getOperationId()
    {
        return operationId;
    }

    public void setReportDate(Date reportDate)
    {
        this.reportDate = reportDate;
    }

    public Date getReportDate()
    {
        return reportDate;
    }

    public void setOkQty(BigDecimal okQty)
    {
        this.okQty = okQty;
    }

    public BigDecimal getOkQty()
    {
        return okQty;
    }

    public void setNgQty(BigDecimal ngQty)
    {
        this.ngQty = ngQty;
    }

    public BigDecimal getNgQty()
    {
        return ngQty;
    }

    public void setReworkQty(BigDecimal reworkQty)
    {
        this.reworkQty = reworkQty;
    }

    public BigDecimal getReworkQty()
    {
        return reworkQty;
    }

    public void setWorkHours(BigDecimal workHours)
    {
        this.workHours = workHours;
    }

    public BigDecimal getWorkHours()
    {
        return workHours;
    }

    public void setWorker(String worker)
    {
        this.worker = worker;
    }

    public String getWorker()
    {
        return worker;
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
            .append("reportId", getReportId())
            .append("reportCode", getReportCode())
            .append("moId", getMoId())
            .append("operationId", getOperationId())
            .append("reportDate", getReportDate())
            .append("okQty", getOkQty())
            .append("ngQty", getNgQty())
            .append("reworkQty", getReworkQty())
            .append("workHours", getWorkHours())
            .append("worker", getWorker())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
