package com.ruoyi.platform.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 工作流定义对象 erp_workflow_def
 *
 * @author erp
 */
public class ErpWorkflowDef extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 工作流ID */
    private Long wfId;

    /** 工作流编码 */
    @Excel(name = "工作流编码")
    private String wfCode;

    /** 工作流名称 */
    @Excel(name = "工作流名称")
    private String wfName;

    /** 业务对象 */
    @Excel(name = "业务对象")
    private String bizObject;

    /** 金额分级下限 */
    @Excel(name = "金额分级下限")
    private BigDecimal amountLimit;

    /** 审批人类型(role/user) */
    @Excel(name = "审批人类型", readConverterExp = "role=角色,user=用户")
    private String assigneeType;

    /** 审批人(角色key或用户) */
    @Excel(name = "审批人")
    private String assigneeValue;

    /** 会签模式(single或sign) */
    @Excel(name = "会签模式", readConverterExp = "single=单人,sign=会签")
    private String approveMode;

    /** 超时提醒(小时) */
    @Excel(name = "超时提醒(小时)")
    private Integer timeoutHours;

    /** 状态(0正常 1停用) */
    @Excel(name = "状态", readConverterExp = "0=正常,1=停用")
    private String status;

    public void setWfId(Long wfId)
    {
        this.wfId = wfId;
    }

    public Long getWfId()
    {
        return wfId;
    }

    public void setWfCode(String wfCode)
    {
        this.wfCode = wfCode;
    }

    public String getWfCode()
    {
        return wfCode;
    }

    public void setWfName(String wfName)
    {
        this.wfName = wfName;
    }

    public String getWfName()
    {
        return wfName;
    }

    public void setBizObject(String bizObject)
    {
        this.bizObject = bizObject;
    }

    public String getBizObject()
    {
        return bizObject;
    }

    public void setAmountLimit(BigDecimal amountLimit)
    {
        this.amountLimit = amountLimit;
    }

    public BigDecimal getAmountLimit()
    {
        return amountLimit;
    }

    public void setAssigneeType(String assigneeType)
    {
        this.assigneeType = assigneeType;
    }

    public String getAssigneeType()
    {
        return assigneeType;
    }

    public void setAssigneeValue(String assigneeValue)
    {
        this.assigneeValue = assigneeValue;
    }

    public String getAssigneeValue()
    {
        return assigneeValue;
    }

    public void setApproveMode(String approveMode)
    {
        this.approveMode = approveMode;
    }

    public String getApproveMode()
    {
        return approveMode;
    }

    public void setTimeoutHours(Integer timeoutHours)
    {
        this.timeoutHours = timeoutHours;
    }

    public Integer getTimeoutHours()
    {
        return timeoutHours;
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
            .append("wfId", getWfId())
            .append("wfCode", getWfCode())
            .append("wfName", getWfName())
            .append("bizObject", getBizObject())
            .append("amountLimit", getAmountLimit())
            .append("assigneeType", getAssigneeType())
            .append("assigneeValue", getAssigneeValue())
            .append("approveMode", getApproveMode())
            .append("timeoutHours", getTimeoutHours())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
