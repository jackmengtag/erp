package com.ruoyi.mdm.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 工序明细对象 erp_routing_operation
 *
 * @author erp
 */
public class ErpRoutingOperation extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 工序ID */
    private Long operationId;

    /** 工艺路线ID */
    private Long routingId;

    /** 工序号 */
    @Excel(name = "工序号")
    private Integer seqNo;

    /** 工序编码 */
    @Excel(name = "工序编码")
    private String operationCode;

    /** 工序名称 */
    @Excel(name = "工序名称")
    private String operationName;

    /** 工作中心ID */
    private Long wcId;

    /** 标准工时(小时) */
    @Excel(name = "标准工时(小时)")
    private BigDecimal standardHours;

    /** 良率(%) */
    @Excel(name = "良率(%)")
    private BigDecimal yieldRate;

    /** 是否质检点(1是 0否) */
    @Excel(name = "是否质检点", readConverterExp = "1=是,0=否")
    private String inspectFlag;

    /** 是否委外(1是 0否) */
    @Excel(name = "是否委外", readConverterExp = "1=是,0=否")
    private String outsourceFlag;

    public void setOperationId(Long operationId)
    {
        this.operationId = operationId;
    }

    public Long getOperationId()
    {
        return operationId;
    }

    public void setRoutingId(Long routingId)
    {
        this.routingId = routingId;
    }

    public Long getRoutingId()
    {
        return routingId;
    }

    public void setSeqNo(Integer seqNo)
    {
        this.seqNo = seqNo;
    }

    public Integer getSeqNo()
    {
        return seqNo;
    }

    public void setOperationCode(String operationCode)
    {
        this.operationCode = operationCode;
    }

    public String getOperationCode()
    {
        return operationCode;
    }

    public void setOperationName(String operationName)
    {
        this.operationName = operationName;
    }

    public String getOperationName()
    {
        return operationName;
    }

    public void setWcId(Long wcId)
    {
        this.wcId = wcId;
    }

    public Long getWcId()
    {
        return wcId;
    }

    public void setStandardHours(BigDecimal standardHours)
    {
        this.standardHours = standardHours;
    }

    public BigDecimal getStandardHours()
    {
        return standardHours;
    }

    public void setYieldRate(BigDecimal yieldRate)
    {
        this.yieldRate = yieldRate;
    }

    public BigDecimal getYieldRate()
    {
        return yieldRate;
    }

    public void setInspectFlag(String inspectFlag)
    {
        this.inspectFlag = inspectFlag;
    }

    public String getInspectFlag()
    {
        return inspectFlag;
    }

    public void setOutsourceFlag(String outsourceFlag)
    {
        this.outsourceFlag = outsourceFlag;
    }

    public String getOutsourceFlag()
    {
        return outsourceFlag;
    }

    @Override
    public String toString()
    {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("operationId", getOperationId())
            .append("routingId", getRoutingId())
            .append("seqNo", getSeqNo())
            .append("operationCode", getOperationCode())
            .append("operationName", getOperationName())
            .append("wcId", getWcId())
            .append("standardHours", getStandardHours())
            .append("yieldRate", getYieldRate())
            .append("inspectFlag", getInspectFlag())
            .append("outsourceFlag", getOutsourceFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
