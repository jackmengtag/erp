package com.ruoyi.purchase.domain;

import java.util.Date;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 采购申请对象 erp_purchase_req
 *
 * @author erp
 */
public class ErpPurchaseReq extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 申请ID */
    private Long reqId;

    /** 申请单号 */
    @Excel(name = "申请单号")
    private String reqCode;

    /** 申请日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "申请日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date reqDate;

    /** 来源(1手工 2MRP 3库存预警 4生产工单) */
    @Excel(name = "来源", readConverterExp = "1=手工,2=MRP,3=库存预警,4=生产工单")
    private String reqType;

    /** 申请部门 */
    private Long deptId;

    /** 状态(0草稿 1待审核 2已审核 3已转订单 4已关闭) */
    @Excel(name = "状态", readConverterExp = "0=草稿,1=待审核,2=已审核,3=已转订单,4=已关闭")
    private String status;

    /** 采购申请明细信息 */
    private List<ErpPurchaseReqLine> erpPurchaseReqLineList;

    public void setReqId(Long reqId)
    {
        this.reqId = reqId;
    }

    public Long getReqId()
    {
        return reqId;
    }

    public void setReqCode(String reqCode)
    {
        this.reqCode = reqCode;
    }

    public String getReqCode()
    {
        return reqCode;
    }

    public void setReqDate(Date reqDate)
    {
        this.reqDate = reqDate;
    }

    public Date getReqDate()
    {
        return reqDate;
    }

    public void setReqType(String reqType)
    {
        this.reqType = reqType;
    }

    public String getReqType()
    {
        return reqType;
    }

    public void setDeptId(Long deptId)
    {
        this.deptId = deptId;
    }

    public Long getDeptId()
    {
        return deptId;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getStatus()
    {
        return status;
    }

    public List<ErpPurchaseReqLine> getErpPurchaseReqLineList()
    {
        return erpPurchaseReqLineList;
    }

    public void setErpPurchaseReqLineList(List<ErpPurchaseReqLine> erpPurchaseReqLineList)
    {
        this.erpPurchaseReqLineList = erpPurchaseReqLineList;
    }

    @Override
    public String toString()
    {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("reqId", getReqId())
            .append("reqCode", getReqCode())
            .append("reqDate", getReqDate())
            .append("reqType", getReqType())
            .append("deptId", getDeptId())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .append("erpPurchaseReqLineList", getErpPurchaseReqLineList())
            .toString();
    }
}
