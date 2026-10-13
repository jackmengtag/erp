package com.ruoyi.inventory.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.inventory.domain.ErpWarehouse;
import com.ruoyi.inventory.mapper.ErpWarehouseMapper;
import com.ruoyi.inventory.service.IErpWarehouseService;

/**
 * 仓库Service业务层处理
 *
 * @author erp
 */
@Service
public class ErpWarehouseServiceImpl implements IErpWarehouseService
{
    @Autowired
    private ErpWarehouseMapper erpWarehouseMapper;

    /**
     * 查询仓库
     */
    @Override
    public ErpWarehouse selectErpWarehouseByWarehouseId(Long warehouseId)
    {
        return erpWarehouseMapper.selectErpWarehouseByWarehouseId(warehouseId);
    }

    /**
     * 查询仓库列表
     */
    @Override
    public List<ErpWarehouse> selectErpWarehouseList(ErpWarehouse erpWarehouse)
    {
        return erpWarehouseMapper.selectErpWarehouseList(erpWarehouse);
    }

    /**
     * 新增仓库
     */
    @Override
    public int insertErpWarehouse(ErpWarehouse erpWarehouse)
    {
        erpWarehouse.setCreateTime(DateUtils.getNowDate());
        return erpWarehouseMapper.insertErpWarehouse(erpWarehouse);
    }

    /**
     * 修改仓库
     */
    @Override
    public int updateErpWarehouse(ErpWarehouse erpWarehouse)
    {
        erpWarehouse.setUpdateTime(DateUtils.getNowDate());
        return erpWarehouseMapper.updateErpWarehouse(erpWarehouse);
    }

    /**
     * 批量删除仓库
     */
    @Override
    public int deleteErpWarehouseByWarehouseIds(Long[] warehouseIds)
    {
        return erpWarehouseMapper.deleteErpWarehouseByWarehouseIds(warehouseIds);
    }

    /**
     * 删除仓库信息
     */
    @Override
    public int deleteErpWarehouseByWarehouseId(Long warehouseId)
    {
        return erpWarehouseMapper.deleteErpWarehouseByWarehouseId(warehouseId);
    }
}
