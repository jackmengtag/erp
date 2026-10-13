package com.ruoyi.manufacturing.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 工单用料对象 erp_production_material
 *
 * @author erp
 */
public class ErpProductionMaterial extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 用料ID */
    private Long materialReqId;

    /** 工单ID */
    private Long moId;

    /** 物料ID */
    private Long materialId;

    /** 应领数量 */
    @Excel(name = "应领数量")
    private BigDecimal requireQty;

    /** 已领数量 */
    @Excel(name = "已领数量")
    private BigDecimal issuedQty;

    public void setMaterialReqId(Long materialReqId)
    {
        this.materialReqId = materialReqId;
    }

    public Long getMaterialReqId()
    {
        return materialReqId;
    }

    public void setMoId(Long moId)
    {
        this.moId = moId;
    }

    public Long getMoId()
    {
        return moId;
    }

    public void setMaterialId(Long materialId)
    {
        this.materialId = materialId;
    }

    public Long getMaterialId()
    {
        return materialId;
    }

    public void setRequireQty(BigDecimal requireQty)
    {
        this.requireQty = requireQty;
    }

    public BigDecimal getRequireQty()
    {
        return requireQty;
    }

    public void setIssuedQty(BigDecimal issuedQty)
    {
        this.issuedQty = issuedQty;
    }

    public BigDecimal getIssuedQty()
    {
        return issuedQty;
    }

    @Override
    public String toString()
    {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("materialReqId", getMaterialReqId())
            .append("moId", getMoId())
            .append("materialId", getMaterialId())
            .append("requireQty", getRequireQty())
            .append("issuedQty", getIssuedQty())
            .append("createTime", getCreateTime())
            .toString();
    }
}
