package com.ruoyi.mdm.mapper;

import java.util.List;
import com.ruoyi.mdm.domain.ErpPrice;

/**
 * 价格条件Mapper接口
 *
 * @author erp
 */
public interface ErpPriceMapper
{
    /**
     * 查询价格条件
     */
    public ErpPrice selectErpPriceByPriceId(Long priceId);

    /**
     * 查询价格条件列表
     */
    public List<ErpPrice> selectErpPriceList(ErpPrice erpPrice);

    /**
     * 新增价格条件
     */
    public int insertErpPrice(ErpPrice erpPrice);

    /**
     * 修改价格条件
     */
    public int updateErpPrice(ErpPrice erpPrice);

    /**
     * 删除价格条件
     */
    public int deleteErpPriceByPriceId(Long priceId);

    /**
     * 批量删除价格条件
     */
    public int deleteErpPriceByPriceIds(Long[] priceIds);
}
