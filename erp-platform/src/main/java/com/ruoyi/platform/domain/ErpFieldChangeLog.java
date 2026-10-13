package com.ruoyi.platform.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 字段变更审计日志对象 erp_field_change_log
 *
 * @author erp
 */
public class ErpFieldChangeLog extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 日志ID */
    private Long logId;

    /** 业务类型 */
    @Excel(name = "业务类型")
    private String bizType;

    /** 业务单据ID */
    @Excel(name = "业务单据ID")
    private String bizId;

    /** 字段名 */
    @Excel(name = "字段名")
    private String fieldName;

    /** 字段说明 */
    @Excel(name = "字段说明")
    private String fieldLabel;

    /** 修改前值 */
    @Excel(name = "修改前值")
    private String oldValue;

    /** 修改后值 */
    @Excel(name = "修改后值")
    private String newValue;

    /** 操作类型(insert/update/delete/audit) */
    @Excel(name = "操作类型")
    private String operType;

    /** 操作人 */
    @Excel(name = "操作人")
    private String operName;

    /** 操作IP */
    @Excel(name = "操作IP")
    private String operIp;

    /** 操作时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "操作时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date operTime;

    /** 变更原因 */
    @Excel(name = "变更原因")
    private String reason;

    public void setLogId(Long logId)
    {
        this.logId = logId;
    }

    public Long getLogId()
    {
        return logId;
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

    public void setFieldName(String fieldName)
    {
        this.fieldName = fieldName;
    }

    public String getFieldName()
    {
        return fieldName;
    }

    public void setFieldLabel(String fieldLabel)
    {
        this.fieldLabel = fieldLabel;
    }

    public String getFieldLabel()
    {
        return fieldLabel;
    }

    public void setOldValue(String oldValue)
    {
        this.oldValue = oldValue;
    }

    public String getOldValue()
    {
        return oldValue;
    }

    public void setNewValue(String newValue)
    {
        this.newValue = newValue;
    }

    public String getNewValue()
    {
        return newValue;
    }

    public void setOperType(String operType)
    {
        this.operType = operType;
    }

    public String getOperType()
    {
        return operType;
    }

    public void setOperName(String operName)
    {
        this.operName = operName;
    }

    public String getOperName()
    {
        return operName;
    }

    public void setOperIp(String operIp)
    {
        this.operIp = operIp;
    }

    public String getOperIp()
    {
        return operIp;
    }

    public void setOperTime(Date operTime)
    {
        this.operTime = operTime;
    }

    public Date getOperTime()
    {
        return operTime;
    }

    public void setReason(String reason)
    {
        this.reason = reason;
    }

    public String getReason()
    {
        return reason;
    }

    @Override
    public String toString()
    {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("logId", getLogId())
            .append("bizType", getBizType())
            .append("bizId", getBizId())
            .append("fieldName", getFieldName())
            .append("fieldLabel", getFieldLabel())
            .append("oldValue", getOldValue())
            .append("newValue", getNewValue())
            .append("operType", getOperType())
            .append("operName", getOperName())
            .append("operIp", getOperIp())
            .append("operTime", getOperTime())
            .append("reason", getReason())
            .toString();
    }
}
