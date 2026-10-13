package com.ruoyi.mdm.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.mdm.domain.ErpPrice;
import com.ruoyi.mdm.mapper.ErpPriceMapper;
import com.ruoyi.mdm.service.IErpPriceService;

/**
 * 价格条件Service业务层处理
 *
 * @author erp
 */
@Service
public class ErpPriceServiceImpl implements IErpPriceService
{
    @Autowired
    private ErpPriceMapper erpPriceMapper;

    /**
     * 查询价格条件
     */
    @Override
    public ErpPrice selectErpPriceByPriceId(Long priceId)
    {
        return erpPriceMapper.selectErpPriceByPriceId(priceId);
    }

    /**
     * 查询价格条件列表
     */
    @Override
    public List<ErpPrice> selectErpPriceList(ErpPrice erpPrice)
    {
        return erpPriceMapper.selectErpPriceList(erpPrice);
    }

    /**
     * 新增价格条件
     */
    @Override
    public int insertErpPrice(ErpPrice erpPrice)
    {
        erpPrice.setCreateTime(DateUtils.getNowDate());
        return erpPriceMapper.insertErpPrice(erpPrice);
    }

    /**
     * 修改价格条件
     */
    @Override
    public int updateErpPrice(ErpPrice erpPrice)
    {
        erpPrice.setUpdateTime(DateUtils.getNowDate());
        return erpPriceMapper.updateErpPrice(erpPrice);
    }

    /**
     * 批量删除价格条件
     */
    @Override
    public int deleteErpPriceByPriceIds(Long[] priceIds)
    {
        return erpPriceMapper.deleteErpPriceByPriceIds(priceIds);
    }

    /**
     * 删除价格条件信息
     */
    @Override
    public int deleteErpPriceByPriceId(Long priceId)
    {
        return erpPriceMapper.deleteErpPriceByPriceId(priceId);
    }
}
