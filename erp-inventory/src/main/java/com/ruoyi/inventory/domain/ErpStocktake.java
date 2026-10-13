package com.ruoyi.inventory.domain;

import java.util.Date;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 盘点单对象 erp_stocktake
 *
 * @author erp
 */
public class ErpStocktake extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 盘点单ID */
    private Long stocktakeId;

    /** 盘点单号 */
    @Excel(name = "盘点单号")
    private String stocktakeCode;

    /** 仓库ID */
    private Long warehouseId;

    /** 盘点类型(1全盘 2循环盘点 3盲盘) */
    @Excel(name = "盘点类型", readConverterExp = "1=全盘,2=循环盘点,3=盲盘")
    private String stocktakeType;

    /** 盘点日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "盘点日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date stocktakeDate;

    /** 状态(0草稿 1盘点中 2已审核 3已调整 4已关闭) */
    @Excel(name = "状态", readConverterExp = "0=草稿,1=盘点中,2=已审核,3=已调整,4=已关闭")
    private String status;

    /** 盘点单明细信息 */
    private List<ErpStocktakeLine> erpStocktakeLineList;

    public void setStocktakeId(Long stocktakeId)
    {
        this.stocktakeId = stocktakeId;
    }

    public Long getStocktakeId()
    {
        return stocktakeId;
    }

    public void setStocktakeCode(String stocktakeCode)
    {
        this.stocktakeCode = stocktakeCode;
    }

    public String getStocktakeCode()
    {
        return stocktakeCode;
    }

    public void setWarehouseId(Long warehouseId)
    {
        this.warehouseId = warehouseId;
    }

    public Long getWarehouseId()
    {
        return warehouseId;
    }

    public void setStocktakeType(String stocktakeType)
    {
        this.stocktakeType = stocktakeType;
    }

    public String getStocktakeType()
    {
        return stocktakeType;
    }

    public void setStocktakeDate(Date stocktakeDate)
    {
        this.stocktakeDate = stocktakeDate;
    }

    public Date getStocktakeDate()
    {
        return stocktakeDate;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getStatus()
    {
        return status;
    }

    public List<ErpStocktakeLine> getErpStocktakeLineList()
    {
        return erpStocktakeLineList;
    }

    public void setErpStocktakeLineList(List<ErpStocktakeLine> erpStocktakeLineList)
    {
        this.erpStocktakeLineList = erpStocktakeLineList;
    }

    @Override
    public String toString()
    {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("stocktakeId", getStocktakeId())
            .append("stocktakeCode", getStocktakeCode())
            .append("warehouseId", getWarehouseId())
            .append("stocktakeType", getStocktakeType())
            .append("stocktakeDate", getStocktakeDate())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .append("erpStocktakeLineList", getErpStocktakeLineList())
            .toString();
    }
}
