package com.ruoyi.quality.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 检验方案明细对象 erp_inspection_plan_line
 *
 * @author erp
 */
public class ErpInspectionPlanLine extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 明细ID */
    private Long lineId;

    /** 方案ID */
    @Excel(name = "方案ID")
    private Long planId;

    /** 检验项目 */
    @Excel(name = "检验项目")
    private String itemName;

    /** 规格标准 */
    @Excel(name = "规格标准")
    private String standardValue;

    /** 判定规则 */
    @Excel(name = "判定规则")
    private String judgeRule;

    public void setLineId(Long lineId)
    {
        this.lineId = lineId;
    }

    public Long getLineId()
    {
        return lineId;
    }

    public void setPlanId(Long planId)
    {
        this.planId = planId;
    }

    public Long getPlanId()
    {
        return planId;
    }

    public void setItemName(String itemName)
    {
        this.itemName = itemName;
    }

    public String getItemName()
    {
        return itemName;
    }

    public void setStandardValue(String standardValue)
    {
        this.standardValue = standardValue;
    }

    public String getStandardValue()
    {
        return standardValue;
    }

    public void setJudgeRule(String judgeRule)
    {
        this.judgeRule = judgeRule;
    }

    public String getJudgeRule()
    {
        return judgeRule;
    }

    @Override
    public String toString()
    {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("lineId", getLineId())
            .append("planId", getPlanId())
            .append("itemName", getItemName())
            .append("standardValue", getStandardValue())
            .append("judgeRule", getJudgeRule())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
