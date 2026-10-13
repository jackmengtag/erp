package com.ruoyi.inventory.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.inventory.domain.ErpInventory;
import com.ruoyi.inventory.mapper.ErpInventoryMapper;
import com.ruoyi.inventory.service.IErpInventoryService;

/**
 * 库存余额Service业务层处理
 *
 * @author erp
 */
@Service
public class ErpInventoryServiceImpl implements IErpInventoryService
{
    @Autowired
    private ErpInventoryMapper erpInventoryMapper;

    /**
     * 查询库存余额
     */
    @Override
    public ErpInventory selectErpInventoryByInventoryId(Long inventoryId)
    {
        return erpInventoryMapper.selectErpInventoryByInventoryId(inventoryId);
    }

    /**
     * 查询库存余额列表
     */
    @Override
    public List<ErpInventory> selectErpInventoryList(ErpInventory erpInventory)
    {
        return erpInventoryMapper.selectErpInventoryList(erpInventory);
    }

    /**
     * 新增库存余额
     */
    @Override
    public int insertErpInventory(ErpInventory erpInventory)
    {
        erpInventory.setCreateTime(DateUtils.getNowDate());
        return erpInventoryMapper.insertErpInventory(erpInventory);
    }

    /**
     * 修改库存余额
     */
    @Override
    public int updateErpInventory(ErpInventory erpInventory)
    {
        erpInventory.setUpdateTime(DateUtils.getNowDate());
        return erpInventoryMapper.updateErpInventory(erpInventory);
    }

    /**
     * 批量删除库存余额
     */
    @Override
    public int deleteErpInventoryByInventoryIds(Long[] inventoryIds)
    {
        return erpInventoryMapper.deleteErpInventoryByInventoryIds(inventoryIds);
    }

    /**
     * 删除库存余额信息
     */
    @Override
    public int deleteErpInventoryByInventoryId(Long inventoryId)
    {
        return erpInventoryMapper.deleteErpInventoryByInventoryId(inventoryId);
    }
}
