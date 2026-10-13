package com.ruoyi.inventory.service;

import java.util.List;
import com.ruoyi.inventory.domain.ErpInventory;

/**
 * 库存余额Service接口
 *
 * @author erp
 */
public interface IErpInventoryService
{
    /**
     * 查询库存余额
     */
    public ErpInventory selectErpInventoryByInventoryId(Long inventoryId);

    /**
     * 查询库存余额列表
     */
    public List<ErpInventory> selectErpInventoryList(ErpInventory erpInventory);

    /**
     * 新增库存余额
     */
    public int insertErpInventory(ErpInventory erpInventory);

    /**
     * 修改库存余额
     */
    public int updateErpInventory(ErpInventory erpInventory);

    /**
     * 批量删除库存余额
     */
    public int deleteErpInventoryByInventoryIds(Long[] inventoryIds);

    /**
     * 删除库存余额信息
     */
    public int deleteErpInventoryByInventoryId(Long inventoryId);
}
