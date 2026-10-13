package com.ruoyi.quality.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 检验结果明细对象 erp_inspection_result
 *
 * @author erp
 */
public class ErpInspectionResult extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 结果ID */
    private Long resultId;

    /** 检验单ID */
    @Excel(name = "检验单ID")
    private Long inspectId;

    /** 检验项目 */
    @Excel(name = "检验项目")
    private String itemName;

    /** 规格标准 */
    @Excel(name = "规格标准")
    private String standardValue;

    /** 检验值 */
    @Excel(name = "检验值")
    private String inspectValue;

    /** 判定(0待定 1合格 2不合格) */
    @Excel(name = "判定", readConverterExp = "0=待定,1=合格,2=不合格")
    private String judgeResult;

    public void setResultId(Long resultId)
    {
        this.resultId = resultId;
    }

    public Long getResultId()
    {
        return resultId;
    }

    public void setInspectId(Long inspectId)
    {
        this.inspectId = inspectId;
    }

    public Long getInspectId()
    {
        return inspectId;
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

    public void setInspectValue(String inspectValue)
    {
        this.inspectValue = inspectValue;
    }

    public String getInspectValue()
    {
        return inspectValue;
    }

    public void setJudgeResult(String judgeResult)
    {
        this.judgeResult = judgeResult;
    }

    public String getJudgeResult()
    {
        return judgeResult;
    }

    @Override
    public String toString()
    {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("resultId", getResultId())
            .append("inspectId", getInspectId())
            .append("itemName", getItemName())
            .append("standardValue", getStandardValue())
            .append("inspectValue", getInspectValue())
            .append("judgeResult", getJudgeResult())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
