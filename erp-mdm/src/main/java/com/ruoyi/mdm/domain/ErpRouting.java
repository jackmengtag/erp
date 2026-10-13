package com.ruoyi.mdm.domain;

import java.util.List;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 工艺路线主表对象 erp_routing
 *
 * @author erp
 */
public class ErpRouting extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 工艺路线ID */
    private Long routingId;

    /** 工艺路线编码 */
    @Excel(name = "工艺路线编码")
    private String routingCode;

    /** 工艺路线名称 */
    @Excel(name = "工艺路线名称")
    private String routingName;

    /** 产品物料ID */
    private Long materialId;

    /** 版本号 */
    @Excel(name = "版本号")
    private String version;

    /** 状态(0草稿 1已审核 2停用) */
    @Excel(name = "状态", readConverterExp = "0=草稿,1=已审核,2=停用")
    private String status;

    /** 工序明细信息 */
    private List<ErpRoutingOperation> erpRoutingOperationList;

    public void setRoutingId(Long routingId)
    {
        this.routingId = routingId;
    }

    public Long getRoutingId()
    {
        return routingId;
    }

    public void setRoutingCode(String routingCode)
    {
        this.routingCode = routingCode;
    }

    public String getRoutingCode()
    {
        return routingCode;
    }

    public void setRoutingName(String routingName)
    {
        this.routingName = routingName;
    }

    public String getRoutingName()
    {
        return routingName;
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

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getStatus()
    {
        return status;
    }

    public List<ErpRoutingOperation> getErpRoutingOperationList()
    {
        return erpRoutingOperationList;
    }

    public void setErpRoutingOperationList(List<ErpRoutingOperation> erpRoutingOperationList)
    {
        this.erpRoutingOperationList = erpRoutingOperationList;
    }

    @Override
    public String toString()
    {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("routingId", getRoutingId())
            .append("routingCode", getRoutingCode())
            .append("routingName", getRoutingName())
            .append("materialId", getMaterialId())
            .append("version", getVersion())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .append("erpRoutingOperationList", getErpRoutingOperationList())
            .toString();
    }
}
