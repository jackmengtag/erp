package com.ruoyi.platform.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 编码流水对象 erp_code_serial
 *
 * @author erp
 */
public class ErpCodeSerial extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 流水ID */
    private Long serialId;

    /** 规则编码 */
    @Excel(name = "规则编码")
    private String ruleCode;

    /** 周期键(如 20261013) */
    @Excel(name = "周期键")
    private String periodKey;

    /** 当前流水值 */
    @Excel(name = "当前流水值")
    private Long currentSeq;

    public void setSerialId(Long serialId)
    {
        this.serialId = serialId;
    }

    public Long getSerialId()
    {
        return serialId;
    }

    public void setRuleCode(String ruleCode)
    {
        this.ruleCode = ruleCode;
    }

    public String getRuleCode()
    {
        return ruleCode;
    }

    public void setPeriodKey(String periodKey)
    {
        this.periodKey = periodKey;
    }

    public String getPeriodKey()
    {
        return periodKey;
    }

    public void setCurrentSeq(Long currentSeq)
    {
        this.currentSeq = currentSeq;
    }

    public Long getCurrentSeq()
    {
        return currentSeq;
    }

    @Override
    public String toString()
    {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("serialId", getSerialId())
            .append("ruleCode", getRuleCode())
            .append("periodKey", getPeriodKey())
            .append("currentSeq", getCurrentSeq())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
