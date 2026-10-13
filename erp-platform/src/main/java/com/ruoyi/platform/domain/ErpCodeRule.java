package com.ruoyi.platform.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 编码规则对象 erp_code_rule
 *
 * @author erp
 */
public class ErpCodeRule extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 规则ID */
    private Long ruleId;

    /** 规则编码 */
    @Excel(name = "规则编码")
    private String ruleCode;

    /** 规则名称 */
    @Excel(name = "规则名称")
    private String ruleName;

    /** 业务对象(如 sales_order) */
    @Excel(name = "业务对象")
    private String bizObject;

    /** 前缀 */
    @Excel(name = "前缀")
    private String prefix;

    /** 日期格式(yyyyMMdd/yyyyMM/none) */
    @Excel(name = "日期格式")
    private String datePattern;

    /** 流水号位数 */
    @Excel(name = "流水号位数")
    private Integer seqLength;

    /** 重置周期(1按日 2按月 3按年 4不重置) */
    @Excel(name = "重置周期", readConverterExp = "1=按日,2=按月,3=按年,4=不重置")
    private String seqReset;

    /** 分隔符 */
    @Excel(name = "分隔符")
    private String separator;

    /** 示例 */
    @Excel(name = "示例")
    private String sample;

    /** 状态(0正常 1停用) */
    @Excel(name = "状态", readConverterExp = "0=正常,1=停用")
    private String status;

    public void setRuleId(Long ruleId)
    {
        this.ruleId = ruleId;
    }

    public Long getRuleId()
    {
        return ruleId;
    }

    public void setRuleCode(String ruleCode)
    {
        this.ruleCode = ruleCode;
    }

    public String getRuleCode()
    {
        return ruleCode;
    }

    public void setRuleName(String ruleName)
    {
        this.ruleName = ruleName;
    }

    public String getRuleName()
    {
        return ruleName;
    }

    public void setBizObject(String bizObject)
    {
        this.bizObject = bizObject;
    }

    public String getBizObject()
    {
        return bizObject;
    }

    public void setPrefix(String prefix)
    {
        this.prefix = prefix;
    }

    public String getPrefix()
    {
        return prefix;
    }

    public void setDatePattern(String datePattern)
    {
        this.datePattern = datePattern;
    }

    public String getDatePattern()
    {
        return datePattern;
    }

    public void setSeqLength(Integer seqLength)
    {
        this.seqLength = seqLength;
    }

    public Integer getSeqLength()
    {
        return seqLength;
    }

    public void setSeqReset(String seqReset)
    {
        this.seqReset = seqReset;
    }

    public String getSeqReset()
    {
        return seqReset;
    }

    public void setSeparator(String separator)
    {
        this.separator = separator;
    }

    public String getSeparator()
    {
        return separator;
    }

    public void setSample(String sample)
    {
        this.sample = sample;
    }

    public String getSample()
    {
        return sample;
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
            .append("ruleId", getRuleId())
            .append("ruleCode", getRuleCode())
            .append("ruleName", getRuleName())
            .append("bizObject", getBizObject())
            .append("prefix", getPrefix())
            .append("datePattern", getDatePattern())
            .append("seqLength", getSeqLength())
            .append("seqReset", getSeqReset())
            .append("separator", getSeparator())
            .append("sample", getSample())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
