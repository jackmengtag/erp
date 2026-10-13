package com.ruoyi.platform.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 工作流实例对象 erp_workflow_instance
 *
 * @author erp
 */
public class ErpWorkflowInstance extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 实例ID */
    private Long instanceId;

    /** 工作流ID */
    private Long wfId;

    /** 业务类型 */
    @Excel(name = "业务类型")
    private String bizType;

    /** 业务单据ID */
    @Excel(name = "业务单据ID")
    private String bizId;

    /** 单据标题 */
    @Excel(name = "单据标题")
    private String bizTitle;

    /** 单据金额 */
    @Excel(name = "单据金额")
    private BigDecimal amount;

    /** 当前节点 */
    @Excel(name = "当前节点")
    private String currentNode;

    /** 状态(0审批中 1通过 2驳回 3撤回) */
    @Excel(name = "状态", readConverterExp = "0=审批中,1=通过,2=驳回,3=撤回")
    private String status;

    /** 提交人 */
    @Excel(name = "提交人")
    private String submitBy;

    /** 提交时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "提交时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date submitTime;

    /** 完成时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "完成时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date finishTime;

    public void setInstanceId(Long instanceId)
    {
        this.instanceId = instanceId;
    }

    public Long getInstanceId()
    {
        return instanceId;
    }

    public void setWfId(Long wfId)
    {
        this.wfId = wfId;
    }

    public Long getWfId()
    {
        return wfId;
    }

    public void setBizType(String bizType)
    {
        this.bizType = bizType;
    }

    public String getBizType()
    {
        return bizType;
    }

    public void setBizId(String bizId)
    {
        this.bizId = bizId;
    }

    public String getBizId()
    {
        return bizId;
    }

    public void setBizTitle(String bizTitle)
    {
        this.bizTitle = bizTitle;
    }

    public String getBizTitle()
    {
        return bizTitle;
    }

    public void setAmount(BigDecimal amount)
    {
        this.amount = amount;
    }

    public BigDecimal getAmount()
    {
        return amount;
    }

    public void setCurrentNode(String currentNode)
    {
        this.currentNode = currentNode;
    }

    public String getCurrentNode()
    {
        return currentNode;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getStatus()
    {
        return status;
    }

    public void setSubmitBy(String submitBy)
    {
        this.submitBy = submitBy;
    }

    public String getSubmitBy()
    {
        return submitBy;
    }

    public void setSubmitTime(Date submitTime)
    {
        this.submitTime = submitTime;
    }

    public Date getSubmitTime()
    {
        return submitTime;
    }

    public void setFinishTime(Date finishTime)
    {
        this.finishTime = finishTime;
    }

    public Date getFinishTime()
    {
        return finishTime;
    }

    @Override
    public String toString()
    {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("instanceId", getInstanceId())
            .append("wfId", getWfId())
            .append("bizType", getBizType())
            .append("bizId", getBizId())
            .append("bizTitle", getBizTitle())
            .append("amount", getAmount())
            .append("currentNode", getCurrentNode())
            .append("status", getStatus())
            .append("submitBy", getSubmitBy())
            .append("submitTime", getSubmitTime())
            .append("finishTime", getFinishTime())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
