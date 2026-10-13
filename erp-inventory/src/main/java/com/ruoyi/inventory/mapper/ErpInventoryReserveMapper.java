package com.ruoyi.inventory.mapper;

import java.util.List;
import com.ruoyi.inventory.domain.ErpInventoryReserve;

/**
 * 库存预留Mapper接口
 *
 * @author erp
 */
public interface ErpInventoryReserveMapper
{
    /**
     * 查询库存预留
     */
    public ErpInventoryReserve selectErpInventoryReserveByReserveId(Long reserveId);

    /**
     * 查询库存预留列表
     */
    public List<ErpInventoryReserve> selectErpInventoryReserveList(ErpInventoryReserve erpInventoryReserve);

    /**
     * 新增库存预留
     */
    public int insertErpInventoryReserve(ErpInventoryReserve erpInventoryReserve);

    /**
     * 修改库存预留
     */
    public int updateErpInventoryReserve(ErpInventoryReserve erpInventoryReserve);

    /**
     * 删除库存预留
     */
    public int deleteErpInventoryReserveByReserveId(Long reserveId);

    /**
     * 批量删除库存预留
     */
    public int deleteErpInventoryReserveByReserveIds(Long[] reserveIds);
}
