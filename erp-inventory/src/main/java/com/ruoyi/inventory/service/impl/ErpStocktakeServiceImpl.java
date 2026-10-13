package com.ruoyi.inventory.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.inventory.domain.ErpStocktake;
import com.ruoyi.inventory.domain.ErpStocktakeLine;
import com.ruoyi.inventory.mapper.ErpStocktakeMapper;
import com.ruoyi.inventory.service.IErpStocktakeService;

/**
 * 盘点单Service业务层处理
 *
 * @author erp
 */
@Service
public class ErpStocktakeServiceImpl implements IErpStocktakeService
{
    @Autowired
    private ErpStocktakeMapper erpStocktakeMapper;

    /**
     * 查询盘点单
     */
    @Override
    public ErpStocktake selectErpStocktakeByStocktakeId(Long stocktakeId)
    {
        return erpStocktakeMapper.selectErpStocktakeByStocktakeId(stocktakeId);
    }

    /**
     * 查询盘点单列表
     */
    @Override
    public List<ErpStocktake> selectErpStocktakeList(ErpStocktake erpStocktake)
    {
        return erpStocktakeMapper.selectErpStocktakeList(erpStocktake);
    }

    /**
     * 新增盘点单
     */
    @Transactional
    @Override
    public int insertErpStocktake(ErpStocktake erpStocktake)
    {
        erpStocktake.setCreateTime(DateUtils.getNowDate());
        int rows = erpStocktakeMapper.insertErpStocktake(erpStocktake);
        insertErpStocktakeLine(erpStocktake);
        return rows;
    }

    /**
     * 修改盘点单
     */
    @Transactional
    @Override
    public int updateErpStocktake(ErpStocktake erpStocktake)
    {
        erpStocktake.setUpdateTime(DateUtils.getNowDate());
        erpStocktakeMapper.deleteErpStocktakeLineByStocktakeId(erpStocktake.getStocktakeId());
        insertErpStocktakeLine(erpStocktake);
        return erpStocktakeMapper.updateErpStocktake(erpStocktake);
    }

    /**
     * 批量删除盘点单
     */
    @Transactional
    @Override
    public int deleteErpStocktakeByStocktakeIds(Long[] stocktakeIds)
    {
        erpStocktakeMapper.deleteErpStocktakeLineByStocktakeIds(stocktakeIds);
        return erpStocktakeMapper.deleteErpStocktakeByStocktakeIds(stocktakeIds);
    }

    /**
     * 删除盘点单信息
     */
    @Transactional
    @Override
    public int deleteErpStocktakeByStocktakeId(Long stocktakeId)
    {
        erpStocktakeMapper.deleteErpStocktakeLineByStocktakeId(stocktakeId);
        return erpStocktakeMapper.deleteErpStocktakeByStocktakeId(stocktakeId);
    }

    /**
     * 新增盘点单明细信息
     */
    public void insertErpStocktakeLine(ErpStocktake erpStocktake)
    {
        List<ErpStocktakeLine> erpStocktakeLineList = erpStocktake.getErpStocktakeLineList();
        if (StringUtils.isNotNull(erpStocktakeLineList))
        {
            for (ErpStocktakeLine line : erpStocktakeLineList)
            {
                line.setStocktakeId(erpStocktake.getStocktakeId());
            }
            if (erpStocktakeLineList.size() > 0)
            {
                erpStocktakeMapper.batchErpStocktakeLine(erpStocktakeLineList);
            }
        }
    }
}
