package com.ruoyi.finance.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 财务凭证接口对象 erp_voucher
 *
 * @author erp
 */
public class ErpVoucher extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 凭证ID */
    private Long voucherId;

    /** 凭证接口单号 */
    @Excel(name = "凭证接口单号")
    private String voucherCode;

    /** 业务单据类型 */
    @Excel(name = "业务单据类型")
    private String bizType;

    /** 业务单据ID */
    @Excel(name = "业务单据ID")
    private Long bizId;

    /** 事件类型 */
    @Excel(name = "事件类型")
    private String eventType;

    /** 金额 */
    @Excel(name = "金额")
    private BigDecimal amount;

    /** 会计期间(YYYY-MM) */
    @Excel(name = "会计期间(YYYY-MM)")
    private String periods;

    /** 来源单号 */
    @Excel(name = "来源单号")
    private String sourceDoc;

    /** 生成状态(0待生成 1已生成 2失败 3已冲销) */
    @Excel(name = "生成状态", readConverterExp = "0=待生成,1=已生成,2=失败,3=已冲销")
    private String genStatus;

    /** 重试次数 */
    @Excel(name = "重试次数")
    private Integer retryTimes;

    /** 失败原因 */
    @Excel(name = "失败原因")
    private String errorMsg;

    public void setVoucherId(Long voucherId)
    {
        this.voucherId = voucherId;
    }

    public Long getVoucherId()
    {
        return voucherId;
    }

    public void setVoucherCode(String voucherCode)
    {
        this.voucherCode = voucherCode;
    }

    public String getVoucherCode()
    {
        return voucherCode;
    }

    public void setBizType(String bizType)
    {
        this.bizType = bizType;
    }

    public String getBizType()
    {
        return bizType;
    }

    public void setBizId(Long bizId)
    {
        this.bizId = bizId;
    }

    public Long getBizId()
    {
        return bizId;
    }

    public void setEventType(String eventType)
    {
        this.eventType = eventType;
    }

    public String getEventType()
    {
        return eventType;
    }

    public void setAmount(BigDecimal amount)
    {
        this.amount = amount;
    }

    public BigDecimal getAmount()
    {
        return amount;
    }

    public void setPeriods(String periods)
    {
        this.periods = periods;
    }

    public String getPeriods()
    {
        return periods;
    }

    public void setSourceDoc(String sourceDoc)
    {
        this.sourceDoc = sourceDoc;
    }

    public String getSourceDoc()
    {
        return sourceDoc;
    }

    public void setGenStatus(String genStatus)
    {
        this.genStatus = genStatus;
    }

    public String getGenStatus()
    {
        return genStatus;
    }

    public void setRetryTimes(Integer retryTimes)
    {
        this.retryTimes = retryTimes;
    }

    public Integer getRetryTimes()
    {
        return retryTimes;
    }

    public void setErrorMsg(String errorMsg)
    {
        this.errorMsg = errorMsg;
    }

    public String getErrorMsg()
    {
        return errorMsg;
    }

    @Override
    public String toString()
    {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("voucherId", getVoucherId())
            .append("voucherCode", getVoucherCode())
            .append("bizType", getBizType())
            .append("bizId", getBizId())
            .append("eventType", getEventType())
            .append("amount", getAmount())
            .append("periods", getPeriods())
            .append("sourceDoc", getSourceDoc())
            .append("genStatus", getGenStatus())
            .append("retryTimes", getRetryTimes())
            .append("errorMsg", getErrorMsg())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
