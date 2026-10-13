package com.ruoyi.plan.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * MRP运算对象 erp_mrp_run
 *
 * @author erp
 */
public class ErpMrpRun extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 运算ID */
    private Long runId;

    /** 运算单号 */
    @Excel(name = "运算单号")
    private String runCode;

    /** 运算日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "运算日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date runDate;

    /** 运算类型(1全量 2净改变) */
    @Excel(name = "运算类型", readConverterExp = "1=全量,2=净改变")
    private String runType;

    /** 状态(0运算中 1完成 2失败) */
    @Excel(name = "状态", readConverterExp = "0=运算中,1=完成,2=失败")
    private String status;

    public void setRunId(Long runId)
    {
        this.runId = runId;
    }

    public Long getRunId()
    {
        return runId;
    }

    public void setRunCode(String runCode)
    {
        this.runCode = runCode;
    }

    public String getRunCode()
    {
        return runCode;
    }

    public void setRunDate(Date runDate)
    {
        this.runDate = runDate;
    }

    public Date getRunDate()
    {
        return runDate;
    }

    public void setRunType(String runType)
    {
        this.runType = runType;
    }

    public String getRunType()
    {
        return runType;
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
            .append("runId", getRunId())
            .append("runCode", getRunCode())
            .append("runDate", getRunDate())
            .append("runType", getRunType())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
