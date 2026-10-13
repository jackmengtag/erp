package com.ruoyi.inventory.service;

import java.util.List;
import com.ruoyi.inventory.domain.ErpWarehouse;

/**
 * 仓库Service接口
 *
 * @author erp
 */
public interface IErpWarehouseService
{
    /**
     * 查询仓库
     */
    public ErpWarehouse selectErpWarehouseByWarehouseId(Long warehouseId);

    /**
     * 查询仓库列表
     */
    public List<ErpWarehouse> selectErpWarehouseList(ErpWarehouse erpWarehouse);

    /**
     * 新增仓库
     */
    public int insertErpWarehouse(ErpWarehouse erpWarehouse);

    /**
     * 修改仓库
     */
    public int updateErpWarehouse(ErpWarehouse erpWarehouse);

    /**
     * 批量删除仓库
     */
    public int deleteErpWarehouseByWarehouseIds(Long[] warehouseIds);

    /**
     * 删除仓库信息
     */
    public int deleteErpWarehouseByWarehouseId(Long warehouseId);
}
