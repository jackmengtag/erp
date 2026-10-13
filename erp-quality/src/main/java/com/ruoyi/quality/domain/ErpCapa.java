package com.ruoyi.quality.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * CAPA与客诉对象 erp_capa
 *
 * @author erp
 */
public class ErpCapa extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** CAPA ID */
    private Long capaId;

    /** CAPA单号 */
    @Excel(name = "CAPA单号")
    private String capaCode;

    /** 类型(1质量问题 2客户投诉) */
    @Excel(name = "类型", readConverterExp = "1=质量问题,2=客户投诉")
    private String capaType;

    /** 问题描述 */
    @Excel(name = "问题描述")
    private String sourceDesc;

    /** 责任部门 */
    @Excel(name = "责任部门")
    private Long deptId;

    /** 纠正预防措施 */
    @Excel(name = "纠正预防措施")
    private String measure;

    /** 截止日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "截止日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date dueDate;

    /** 验证结果 */
    @Excel(name = "验证结果")
    private String verifyResult;

    /** 状态(0待处理 1处理中 2已验证 3已关闭) */
    @Excel(name = "状态", readConverterExp = "0=待处理,1=处理中,2=已验证,3=已关闭")
    private String status;

    public void setCapaId(Long capaId)
    {
        this.capaId = capaId;
    }

    public Long getCapaId()
    {
        return capaId;
    }

    public void setCapaCode(String capaCode)
    {
        this.capaCode = capaCode;
    }

    public String getCapaCode()
    {
        return capaCode;
    }

    public void setCapaType(String capaType)
    {
        this.capaType = capaType;
    }

    public String getCapaType()
    {
        return capaType;
    }

    public void setSourceDesc(String sourceDesc)
    {
        this.sourceDesc = sourceDesc;
    }

    public String getSourceDesc()
    {
        return sourceDesc;
    }

    public void setDeptId(Long deptId)
    {
        this.deptId = deptId;
    }

    public Long getDeptId()
    {
        return deptId;
    }

    public void setMeasure(String measure)
    {
        this.measure = measure;
    }

    public String getMeasure()
    {
        return measure;
    }

    public void setDueDate(Date dueDate)
    {
        this.dueDate = dueDate;
    }

    public Date getDueDate()
    {
        return dueDate;
    }

    public void setVerifyResult(String verifyResult)
    {
        this.verifyResult = verifyResult;
    }

    public String getVerifyResult()
    {
        return verifyResult;
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
            .append("capaId", getCapaId())
            .append("capaCode", getCapaCode())
            .append("capaType", getCapaType())
            .append("sourceDesc", getSourceDesc())
            .append("deptId", getDeptId())
            .append("measure", getMeasure())
            .append("dueDate", getDueDate())
            .append("verifyResult", getVerifyResult())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
