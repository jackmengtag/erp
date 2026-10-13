package com.ruoyi.mdm.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 供应商对象 erp_supplier
 *
 * @author erp
 */
public class ErpSupplier extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 供应商ID */
    private Long supplierId;

    /** 供应商编码 */
    @Excel(name = "供应商编码")
    private String supplierCode;

    /** 供应商名称 */
    @Excel(name = "供应商名称")
    private String supplierName;

    /** 供应商简称 */
    @Excel(name = "供应商简称")
    private String shortName;

    /** 供应商等级(A/B/C/D) */
    @Excel(name = "供应商等级")
    private String supplierLevel;

    /** 联系人 */
    @Excel(name = "联系人")
    private String contactPerson;

    /** 联系电话 */
    @Excel(name = "联系电话")
    private String contactPhone;

    /** 地址 */
    @Excel(name = "地址")
    private String address;

    /** 账期(天) */
    @Excel(name = "账期(天)")
    private Integer creditDays;

    /** 税号 */
    @Excel(name = "税号")
    private String taxNo;

    /** 银行账号 */
    @Excel(name = "银行账号")
    private String bankAccount;

    /** 评价得分 */
    @Excel(name = "评价得分")
    private BigDecimal evaluateScore;

    /** 状态(0正常 1停用) */
    @Excel(name = "状态", readConverterExp = "0=正常,1=停用")
    private String status;

    public void setSupplierId(Long supplierId)
    {
        this.supplierId = supplierId;
    }

    public Long getSupplierId()
    {
        return supplierId;
    }

    public void setSupplierCode(String supplierCode)
    {
        this.supplierCode = supplierCode;
    }

    public String getSupplierCode()
    {
        return supplierCode;
    }

    public void setSupplierName(String supplierName)
    {
        this.supplierName = supplierName;
    }

    public String getSupplierName()
    {
        return supplierName;
    }

    public void setShortName(String shortName)
    {
        this.shortName = shortName;
    }

    public String getShortName()
    {
        return shortName;
    }

    public void setSupplierLevel(String supplierLevel)
    {
        this.supplierLevel = supplierLevel;
    }

    public String getSupplierLevel()
    {
        return supplierLevel;
    }

    public void setContactPerson(String contactPerson)
    {
        this.contactPerson = contactPerson;
    }

    public String getContactPerson()
    {
        return contactPerson;
    }

    public void setContactPhone(String contactPhone)
    {
        this.contactPhone = contactPhone;
    }

    public String getContactPhone()
    {
        return contactPhone;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public String getAddress()
    {
        return address;
    }

    public void setCreditDays(Integer creditDays)
    {
        this.creditDays = creditDays;
    }

    public Integer getCreditDays()
    {
        return creditDays;
    }

    public void setTaxNo(String taxNo)
    {
        this.taxNo = taxNo;
    }

    public String getTaxNo()
    {
        return taxNo;
    }

    public void setBankAccount(String bankAccount)
    {
        this.bankAccount = bankAccount;
    }

    public String getBankAccount()
    {
        return bankAccount;
    }

    public void setEvaluateScore(BigDecimal evaluateScore)
    {
        this.evaluateScore = evaluateScore;
    }

    public BigDecimal getEvaluateScore()
    {
        return evaluateScore;
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
            .append("supplierId", getSupplierId())
            .append("supplierCode", getSupplierCode())
            .append("supplierName", getSupplierName())
            .append("shortName", getShortName())
            .append("supplierLevel", getSupplierLevel())
            .append("contactPerson", getContactPerson())
            .append("contactPhone", getContactPhone())
            .append("address", getAddress())
            .append("creditDays", getCreditDays())
            .append("taxNo", getTaxNo())
            .append("bankAccount", getBankAccount())
            .append("evaluateScore", getEvaluateScore())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
