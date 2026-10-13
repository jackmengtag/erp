package com.ruoyi.quality.domain;

import java.math.BigDecimal;
import java.util.List;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 检验单对象 erp_inspection_order
 *
 * @author erp
 */
public class ErpInspectionOrder extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 检验单ID */
    private Long inspectId;

    /** 检验单号 */
    @Excel(name = "检验单号")
    private String inspectCode;

    /** 检验类型(IQC/IPQC/FQC/OQC) */
    @Excel(name = "检验类型(IQC/IPQC/FQC/OQC)")
    private String inspectType;

    /** 检验方案ID */
    @Excel(name = "检验方案ID")
    private Long planId;

    /** 物料ID */
    @Excel(name = "物料ID")
    private Long materialId;

    /** 批次号 */
    @Excel(name = "批次号")
    private String batchNo;

    /** 来源单据类型 */
    @Excel(name = "来源单据类型")
    private String sourceType;

    /** 来源单据ID */
    @Excel(name = "来源单据ID")
    private Long sourceId;

    /** 检验数量 */
    @Excel(name = "检验数量")
    private BigDecimal inspectQty;

    /** 合格数量 */
    @Excel(name = "合格数量")
    private BigDecimal qualifiedQty;

    /** 不合格数量 */
    @Excel(name = "不合格数量")
    private BigDecimal unqualifiedQty;

    /** 检验结果(0待检验 1合格 2不合格) */
    @Excel(name = "检验结果", readConverterExp = "0=待检验,1=合格,2=不合格")
    private String inspectResult;

    /** 检验员 */
    @Excel(name = "检验员")
    private String inspector;

    /** 状态(0待检验 1检验中 2已完成 3已关闭) */
    @Excel(name = "状态", readConverterExp = "0=待检验,1=检验中,2=已完成,3=已关闭")
    private String status;

    /** 检验结果明细信息 */
    private List<ErpInspectionResult> erpInspectionResultList;

    public void setInspectId(Long inspectId)
    {
        this.inspectId = inspectId;
    }

    public Long getInspectId()
    {
        return inspectId;
    }

    public void setInspectCode(String inspectCode)
    {
        this.inspectCode = inspectCode;
    }

    public String getInspectCode()
    {
        return inspectCode;
    }

    public void setInspectType(String inspectType)
    {
        this.inspectType = inspectType;
    }

    public String getInspectType()
    {
        return inspectType;
    }

    public void setPlanId(Long planId)
    {
        this.planId = planId;
    }

    public Long getPlanId()
    {
        return planId;
    }

    public void setMaterialId(Long materialId)
    {
        this.materialId = materialId;
    }

    public Long getMaterialId()
    {
        return materialId;
    }

    public void setBatchNo(String batchNo)
    {
        this.batchNo = batchNo;
    }

    public String getBatchNo()
    {
        return batchNo;
    }

    public void setSourceType(String sourceType)
    {
        this.sourceType = sourceType;
    }

    public String getSourceType()
    {
        return sourceType;
    }

    public void setSourceId(Long sourceId)
    {
        this.sourceId = sourceId;
    }

    public Long getSourceId()
    {
        return sourceId;
    }

    public void setInspectQty(BigDecimal inspectQty)
    {
        this.inspectQty = inspectQty;
    }

    public BigDecimal getInspectQty()
    {
        return inspectQty;
    }

    public void setQualifiedQty(BigDecimal qualifiedQty)
    {
        this.qualifiedQty = qualifiedQty;
    }

    public BigDecimal getQualifiedQty()
    {
        return qualifiedQty;
    }

    public void setUnqualifiedQty(BigDecimal unqualifiedQty)
    {
        this.unqualifiedQty = unqualifiedQty;
    }

    public BigDecimal getUnqualifiedQty()
    {
        return unqualifiedQty;
    }

    public void setInspectResult(String inspectResult)
    {
        this.inspectResult = inspectResult;
    }

    public String getInspectResult()
    {
        return inspectResult;
    }

    public void setInspector(String inspector)
    {
        this.inspector = inspector;
    }

    public String getInspector()
    {
        return inspector;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getStatus()
    {
        return status;
    }

    public List<ErpInspectionResult> getErpInspectionResultList()
    {
        return erpInspectionResultList;
    }

    public void setErpInspectionResultList(List<ErpInspectionResult> erpInspectionResultList)
    {
        this.erpInspectionResultList = erpInspectionResultList;
    }

    @Override
    public String toString()
    {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("inspectId", getInspectId())
            .append("inspectCode", getInspectCode())
            .append("inspectType", getInspectType())
            .append("planId", getPlanId())
            .append("materialId", getMaterialId())
            .append("batchNo", getBatchNo())
            .append("sourceType", getSourceType())
            .append("sourceId", getSourceId())
            .append("inspectQty", getInspectQty())
            .append("qualifiedQty", getQualifiedQty())
            .append("unqualifiedQty", getUnqualifiedQty())
            .append("inspectResult", getInspectResult())
            .append("inspector", getInspector())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .append("erpInspectionResultList", getErpInspectionResultList())
            .toString();
    }
}
