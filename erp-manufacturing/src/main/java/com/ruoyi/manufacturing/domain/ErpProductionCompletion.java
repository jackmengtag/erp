package com.ruoyi.manufacturing.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 完工入库对象 erp_production_completion
 *
 * @author erp
 */
public class ErpProductionCompletion extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 完工入库ID */
    private Long completionId;

    /** 完工单号 */
    @Excel(name = "完工单号")
    private String completionCode;

    /** 工单ID */
    private Long moId;

    /** 入库仓库ID */
    private Long warehouseId;

    /** 成品批次号 */
    @Excel(name = "成品批次号")
    private String batchNo;

    /** 入库数量 */
    @Excel(name = "入库数量")
    private BigDecimal qty;

    /** 完工日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "完工日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date completionDate;

    /** 状态(0草稿 1待检验 2已入库) */
    @Excel(name = "状态", readConverterExp = "0=草稿,1=待检验,2=已入库")
    private String status;

    public void setCompletionId(Long completionId)
    {
        this.completionId = completionId;
    }

    public Long getCompletionId()
    {
        return completionId;
    }

    public void setCompletionCode(String completionCode)
    {
        this.completionCode = completionCode;
    }

    public String getCompletionCode()
    {
        return completionCode;
    }

    public void setMoId(Long moId)
    {
        this.moId = moId;
    }

    public Long getMoId()
    {
        return moId;
    }

    public void setWarehouseId(Long warehouseId)
    {
        this.warehouseId = warehouseId;
    }

    public Long getWarehouseId()
    {
        return warehouseId;
    }

    public void setBatchNo(String batchNo)
    {
        this.batchNo = batchNo;
    }

    public String getBatchNo()
    {
        return batchNo;
    }

    public void setQty(BigDecimal qty)
    {
        this.qty = qty;
    }

    public BigDecimal getQty()
    {
        return qty;
    }

    public void setCompletionDate(Date completionDate)
    {
        this.completionDate = completionDate;
    }

    public Date getCompletionDate()
    {
        return completionDate;
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
            .append("completionId", getCompletionId())
            .append("completionCode", getCompletionCode())
            .append("moId", getMoId())
            .append("warehouseId", getWarehouseId())
            .append("batchNo", getBatchNo())
            .append("qty", getQty())
            .append("completionDate", getCompletionDate())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
