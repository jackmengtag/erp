package com.ruoyi.mdm.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * BOM子件明细对象 erp_bom_line
 *
 * @author erp
 */
public class ErpBomLine extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 明细ID */
    private Long lineId;

    /** BOM ID */
    private Long bomId;

    /** 子件物料ID */
    private Long materialId;

    /** 单位用量 */
    @Excel(name = "单位用量")
    private BigDecimal qtyPer;

    /** 损耗率(%) */
    @Excel(name = "损耗率(%)")
    private BigDecimal lossRate;

    /** 是否替代料(1是 0否) */
    @Excel(name = "是否替代料", readConverterExp = "1=是,0=否")
    private String substituteFlag;

    /** 行号 */
    @Excel(name = "行号")
    private Integer lineNo;

    public void setLineId(Long lineId)
    {
        this.lineId = lineId;
    }

    public Long getLineId()
    {
        return lineId;
    }

    public void setBomId(Long bomId)
    {
        this.bomId = bomId;
    }

    public Long getBomId()
    {
        return bomId;
    }

    public void setMaterialId(Long materialId)
    {
        this.materialId = materialId;
    }

    public Long getMaterialId()
    {
        return materialId;
    }

    public void setQtyPer(BigDecimal qtyPer)
    {
        this.qtyPer = qtyPer;
    }

    public BigDecimal getQtyPer()
    {
        return qtyPer;
    }

    public void setLossRate(BigDecimal lossRate)
    {
        this.lossRate = lossRate;
    }

    public BigDecimal getLossRate()
    {
        return lossRate;
    }

    public void setSubstituteFlag(String substituteFlag)
    {
        this.substituteFlag = substituteFlag;
    }

    public String getSubstituteFlag()
    {
        return substituteFlag;
    }

    public void setLineNo(Integer lineNo)
    {
        this.lineNo = lineNo;
    }

    public Integer getLineNo()
    {
        return lineNo;
    }

    @Override
    public String toString()
    {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("lineId", getLineId())
            .append("bomId", getBomId())
            .append("materialId", getMaterialId())
            .append("qtyPer", getQtyPer())
            .append("lossRate", getLossRate())
            .append("substituteFlag", getSubstituteFlag())
            .append("lineNo", getLineNo())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
