package com.ruoyi.inventory.mapper;

import java.util.List;
import com.ruoyi.inventory.domain.ErpInventory;

/**
 * 库存余额Mapper接口
 *
 * @author erp
 */
public interface ErpInventoryMapper
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
     * 删除库存余额
     */
    public int deleteErpInventoryByInventoryId(Long inventoryId);

    /**
     * 批量删除库存余额
     */
    public int deleteErpInventoryByInventoryIds(Long[] inventoryIds);
}
