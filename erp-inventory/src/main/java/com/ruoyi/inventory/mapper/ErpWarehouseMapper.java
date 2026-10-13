package com.ruoyi.inventory.mapper;

import java.util.List;
import com.ruoyi.inventory.domain.ErpWarehouse;

/**
 * 仓库Mapper接口
 *
 * @author erp
 */
public interface ErpWarehouseMapper
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
     * 删除仓库
     */
    public int deleteErpWarehouseByWarehouseId(Long warehouseId);

    /**
     * 批量删除仓库
     */
    public int deleteErpWarehouseByWarehouseIds(Long[] warehouseIds);
}
