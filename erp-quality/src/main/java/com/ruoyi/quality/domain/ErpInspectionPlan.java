package com.ruoyi.quality.domain;

import java.util.List;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 检验方案对象 erp_inspection_plan
 *
 * @author erp
 */
public class ErpInspectionPlan extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 方案ID */
    private Long planId;

    /** 方案编码 */
    @Excel(name = "方案编码")
    private String planCode;

    /** 方案名称 */
    @Excel(name = "方案名称")
    private String planName;

    /** 检验类型(IQC/IPQC/FQC/OQC) */
    @Excel(name = "检验类型(IQC/IPQC/FQC/OQC)")
    private String inspectType;

    /** 物料ID */
    @Excel(name = "物料ID")
    private Long materialId;

    /** 抽样规则 */
    @Excel(name = "抽样规则")
    private String sampleRule;

    /** 状态(0正常 1停用) */
    @Excel(name = "状态", readConverterExp = "0=正常,1=停用")
    private String status;

    /** 检验方案明细信息 */
    private List<ErpInspectionPlanLine> erpInspectionPlanLineList;

    public void setPlanId(Long planId)
    {
        this.planId = planId;
    }

    public Long getPlanId()
    {
        return planId;
    }

    public void setPlanCode(String planCode)
    {
        this.planCode = planCode;
    }

    public String getPlanCode()
    {
        return planCode;
    }

    public void setPlanName(String planName)
    {
        this.planName = planName;
    }

    public String getPlanName()
    {
        return planName;
    }

    public void setInspectType(String inspectType)
    {
        this.inspectType = inspectType;
    }

    public String getInspectType()
    {
        return inspectType;
    }

    public void setMaterialId(Long materialId)
    {
        this.materialId = materialId;
    }

    public Long getMaterialId()
    {
        return materialId;
    }

    public void setSampleRule(String sampleRule)
    {
        this.sampleRule = sampleRule;
    }

    public String getSampleRule()
    {
        return sampleRule;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getStatus()
    {
        return status;
    }

    public List<ErpInspectionPlanLine> getErpInspectionPlanLineList()
    {
        return erpInspectionPlanLineList;
    }

    public void setErpInspectionPlanLineList(List<ErpInspectionPlanLine> erpInspectionPlanLineList)
    {
        this.erpInspectionPlanLineList = erpInspectionPlanLineList;
    }

    @Override
    public String toString()
    {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("planId", getPlanId())
            .append("planCode", getPlanCode())
            .append("planName", getPlanName())
            .append("inspectType", getInspectType())
            .append("materialId", getMaterialId())
            .append("sampleRule", getSampleRule())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .append("erpInspectionPlanLineList", getErpInspectionPlanLineList())
            .toString();
    }
}
