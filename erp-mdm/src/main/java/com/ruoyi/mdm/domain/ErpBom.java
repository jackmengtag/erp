package com.ruoyi.mdm.domain;

import java.util.Date;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * BOM主表对象 erp_bom
 *
 * @author erp
 */
public class ErpBom extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** BOM ID */
    private Long bomId;

    /** BOM编码 */
    @Excel(name = "BOM编码")
    private String bomCode;

    /** 母件物料ID */
    private Long materialId;

    /** 版本号 */
    @Excel(name = "版本号")
    private String version;

    /** 生效日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "生效日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date effectDate;

    /** 失效日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "失效日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date expireDate;

    /** 状态(0草稿 1已审核 2停用) */
    @Excel(name = "状态", readConverterExp = "0=草稿,1=已审核,2=停用")
    private String status;

    /** BOM子件明细信息 */
    private List<ErpBomLine> erpBomLineList;

    public void setBomId(Long bomId)
    {
        this.bomId = bomId;
    }

    public Long getBomId()
    {
        return bomId;
    }

    public void setBomCode(String bomCode)
    {
        this.bomCode = bomCode;
    }

    public String getBomCode()
    {
        return bomCode;
    }

    public void setMaterialId(Long materialId)
    {
        this.materialId = materialId;
    }

    public Long getMaterialId()
    {
        return materialId;
    }

    public void setVersion(String version)
    {
        this.version = version;
    }

    public String getVersion()
    {
        return version;
    }

    public void setEffectDate(Date effectDate)
    {
        this.effectDate = effectDate;
    }

    public Date getEffectDate()
    {
        return effectDate;
    }

    public void setExpireDate(Date expireDate)
    {
        this.expireDate = expireDate;
    }

    public Date getExpireDate()
    {
        return expireDate;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getStatus()
    {
        return status;
    }

    public List<ErpBomLine> getErpBomLineList()
    {
        return erpBomLineList;
    }

    public void setErpBomLineList(List<ErpBomLine> erpBomLineList)
    {
        this.erpBomLineList = erpBomLineList;
    }

    @Override
    public String toString()
    {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("bomId", getBomId())
            .append("bomCode", getBomCode())
            .append("materialId", getMaterialId())
            .append("version", getVersion())
            .append("effectDate", getEffectDate())
            .append("expireDate", getExpireDate())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .append("erpBomLineList", getErpBomLineList())
            .toString();
    }
}
