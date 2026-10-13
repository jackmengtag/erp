package com.ruoyi.mdm.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 计量单位对象 erp_uom
 *
 * @author erp
 */
public class ErpUom extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 单位ID */
    private Long uomId;

    /** 单位编码 */
    @Excel(name = "单位编码")
    private String uomCode;

    /** 单位名称 */
    @Excel(name = "单位名称")
    private String uomName;

    /** 单位类型(qty数量/time时间/weight重量/volume体积) */
    @Excel(name = "单位类型", readConverterExp = "qty=数量,time=时间,weight=重量,volume=体积")
    private String uomType;

    /** 小数位 */
    @Excel(name = "小数位")
    private Integer precisionScale;

    /** 状态(0正常 1停用) */
    @Excel(name = "状态", readConverterExp = "0=正常,1=停用")
    private String status;

    public void setUomId(Long uomId)
    {
        this.uomId = uomId;
    }

    public Long getUomId()
    {
        return uomId;
    }

    public void setUomCode(String uomCode)
    {
        this.uomCode = uomCode;
    }

    public String getUomCode()
    {
        return uomCode;
    }

    public void setUomName(String uomName)
    {
        this.uomName = uomName;
    }

    public String getUomName()
    {
        return uomName;
    }

    public void setUomType(String uomType)
    {
        this.uomType = uomType;
    }

    public String getUomType()
    {
        return uomType;
    }

    public void setPrecisionScale(Integer precisionScale)
    {
        this.precisionScale = precisionScale;
    }

    public Integer getPrecisionScale()
    {
        return precisionScale;
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
            .append("uomId", getUomId())
            .append("uomCode", getUomCode())
            .append("uomName", getUomName())
            .append("uomType", getUomType())
            .append("precisionScale", getPrecisionScale())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
