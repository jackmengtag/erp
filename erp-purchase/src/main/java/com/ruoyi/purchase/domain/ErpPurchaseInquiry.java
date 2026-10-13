package com.ruoyi.purchase.domain;

import java.util.Date;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 采购询价对象 erp_purchase_inquiry
 *
 * @author erp
 */
public class ErpPurchaseInquiry extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 询价ID */
    private Long inquiryId;

    /** 询价单号 */
    @Excel(name = "询价单号")
    private String inquiryCode;

    /** 询价日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "询价日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date inquiryDate;

    /** 状态(0草稿 1询价中 2已定标 3已关闭) */
    @Excel(name = "状态", readConverterExp = "0=草稿,1=询价中,2=已定标,3=已关闭")
    private String status;

    /** 采购询价明细信息 */
    private List<ErpPurchaseInquiryLine> erpPurchaseInquiryLineList;

    public void setInquiryId(Long inquiryId)
    {
        this.inquiryId = inquiryId;
    }

    public Long getInquiryId()
    {
        return inquiryId;
    }

    public void setInquiryCode(String inquiryCode)
    {
        this.inquiryCode = inquiryCode;
    }

    public String getInquiryCode()
    {
        return inquiryCode;
    }

    public void setInquiryDate(Date inquiryDate)
    {
        this.inquiryDate = inquiryDate;
    }

    public Date getInquiryDate()
    {
        return inquiryDate;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getStatus()
    {
        return status;
    }

    public List<ErpPurchaseInquiryLine> getErpPurchaseInquiryLineList()
    {
        return erpPurchaseInquiryLineList;
    }

    public void setErpPurchaseInquiryLineList(List<ErpPurchaseInquiryLine> erpPurchaseInquiryLineList)
    {
        this.erpPurchaseInquiryLineList = erpPurchaseInquiryLineList;
    }

    @Override
    public String toString()
    {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("inquiryId", getInquiryId())
            .append("inquiryCode", getInquiryCode())
            .append("inquiryDate", getInquiryDate())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .append("erpPurchaseInquiryLineList", getErpPurchaseInquiryLineList())
            .toString();
    }
}
