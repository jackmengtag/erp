package com.ruoyi.inventory.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.inventory.domain.ErpInventoryReserve;
import com.ruoyi.inventory.mapper.ErpInventoryReserveMapper;
import com.ruoyi.inventory.service.IErpInventoryReserveService;

/**
 * 库存预留Service业务层处理
 *
 * @author erp
 */
@Service
public class ErpInventoryReserveServiceImpl implements IErpInventoryReserveService
{
    @Autowired
    private ErpInventoryReserveMapper erpInventoryReserveMapper;

    /**
     * 查询库存预留
     */
    @Override
    public ErpInventoryReserve selectErpInventoryReserveByReserveId(Long reserveId)
    {
        return erpInventoryReserveMapper.selectErpInventoryReserveByReserveId(reserveId);
    }

    /**
     * 查询库存预留列表
     */
    @Override
    public List<ErpInventoryReserve> selectErpInventoryReserveList(ErpInventoryReserve erpInventoryReserve)
    {
        return erpInventoryReserveMapper.selectErpInventoryReserveList(erpInventoryReserve);
    }

    /**
     * 新增库存预留
     */
    @Override
    public int insertErpInventoryReserve(ErpInventoryReserve erpInventoryReserve)
    {
        erpInventoryReserve.setCreateTime(DateUtils.getNowDate());
        return erpInventoryReserveMapper.insertErpInventoryReserve(erpInventoryReserve);
    }

    /**
     * 修改库存预留
     */
    @Override
    public int updateErpInventoryReserve(ErpInventoryReserve erpInventoryReserve)
    {
        erpInventoryReserve.setUpdateTime(DateUtils.getNowDate());
        return erpInventoryReserveMapper.updateErpInventoryReserve(erpInventoryReserve);
    }

    /**
     * 批量删除库存预留
     */
    @Override
    public int deleteErpInventoryReserveByReserveIds(Long[] reserveIds)
    {
        return erpInventoryReserveMapper.deleteErpInventoryReserveByReserveIds(reserveIds);
    }

    /**
     * 删除库存预留信息
     */
    @Override
    public int deleteErpInventoryReserveByReserveId(Long reserveId)
    {
        return erpInventoryReserveMapper.deleteErpInventoryReserveByReserveId(reserveId);
    }
}
