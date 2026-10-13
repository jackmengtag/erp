package com.ruoyi.quality.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 不合格品处理对象 erp_defect_disposal
 *
 * @author erp
 */
public class ErpDefectDisposal extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 处理ID */
    private Long disposalId;

    /** 处理单号 */
    @Excel(name = "处理单号")
    private String disposalCode;

    /** 检验单ID */
    @Excel(name = "检验单ID")
    private Long inspectId;

    /** 物料ID */
    @Excel(name = "物料ID")
    private Long materialId;

    /** 批次号 */
    @Excel(name = "批次号")
    private String batchNo;

    /** 不合格数量 */
    @Excel(name = "不合格数量")
    private BigDecimal defectQty;

    /** 处理方式(1隔离 2返工 3返修 4让步接收 5报废 6退货) */
    @Excel(name = "处理方式", readConverterExp = "1=隔离,2=返工,3=返修,4=让步接收,5=报废,6=退货")
    private String disposalType;

    /** 原因分析 */
    @Excel(name = "原因分析")
    private String reason;

    /** 状态(0待处理 1处理中 2已关闭) */
    @Excel(name = "状态", readConverterExp = "0=待处理,1=处理中,2=已关闭")
    private String handleStatus;

    public void setDisposalId(Long disposalId)
    {
        this.disposalId = disposalId;
    }

    public Long getDisposalId()
    {
        return disposalId;
    }

    public void setDisposalCode(String disposalCode)
    {
        this.disposalCode = disposalCode;
    }

    public String getDisposalCode()
    {
        return disposalCode;
    }

    public void setInspectId(Long inspectId)
    {
        this.inspectId = inspectId;
    }

    public Long getInspectId()
    {
        return inspectId;
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

    public void setDefectQty(BigDecimal defectQty)
    {
        this.defectQty = defectQty;
    }

    public BigDecimal getDefectQty()
    {
        return defectQty;
    }

    public void setDisposalType(String disposalType)
    {
        this.disposalType = disposalType;
    }

    public String getDisposalType()
    {
        return disposalType;
    }

    public void setReason(String reason)
    {
        this.reason = reason;
    }

    public String getReason()
    {
        return reason;
    }

    public void setHandleStatus(String handleStatus)
    {
        this.handleStatus = handleStatus;
    }

    public String getHandleStatus()
    {
        return handleStatus;
    }

    @Override
    public String toString()
    {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("disposalId", getDisposalId())
            .append("disposalCode", getDisposalCode())
            .append("inspectId", getInspectId())
            .append("materialId", getMaterialId())
            .append("batchNo", getBatchNo())
            .append("defectQty", getDefectQty())
            .append("disposalType", getDisposalType())
            .append("reason", getReason())
            .append("handleStatus", getHandleStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
