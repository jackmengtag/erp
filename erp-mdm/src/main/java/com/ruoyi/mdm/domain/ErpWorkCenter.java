package com.ruoyi.mdm.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 工作中心对象 erp_work_center
 *
 * @author erp
 */
public class ErpWorkCenter extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 工作中心ID */
    private Long wcId;

    /** 工作中心编码 */
    @Excel(name = "工作中心编码")
    private String wcCode;

    /** 工作中心名称 */
    @Excel(name = "工作中心名称")
    private String wcName;

    /** 所属部门 */
    private Long deptId;

    /** 日产能(小时) */
    @Excel(name = "日产能(小时)")
    private BigDecimal capacityPerDay;

    /** 单位工时成本 */
    @Excel(name = "单位工时成本")
    private BigDecimal costRate;

    /** 状态(0正常 1停用) */
    @Excel(name = "状态", readConverterExp = "0=正常,1=停用")
    private String status;

    public void setWcId(Long wcId)
    {
        this.wcId = wcId;
    }

    public Long getWcId()
    {
        return wcId;
    }

    public void setWcCode(String wcCode)
    {
        this.wcCode = wcCode;
    }

    public String getWcCode()
    {
        return wcCode;
    }

    public void setWcName(String wcName)
    {
        this.wcName = wcName;
    }

    public String getWcName()
    {
        return wcName;
    }

    public void setDeptId(Long deptId)
    {
        this.deptId = deptId;
    }

    public Long getDeptId()
    {
        return deptId;
    }

    public void setCapacityPerDay(BigDecimal capacityPerDay)
    {
        this.capacityPerDay = capacityPerDay;
    }

    public BigDecimal getCapacityPerDay()
    {
        return capacityPerDay;
    }

    public void setCostRate(BigDecimal costRate)
    {
        this.costRate = costRate;
    }

    public BigDecimal getCostRate()
    {
        return costRate;
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
            .append("wcId", getWcId())
            .append("wcCode", getWcCode())
            .append("wcName", getWcName())
            .append("deptId", getDeptId())
            .append("capacityPerDay", getCapacityPerDay())
            .append("costRate", getCostRate())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
