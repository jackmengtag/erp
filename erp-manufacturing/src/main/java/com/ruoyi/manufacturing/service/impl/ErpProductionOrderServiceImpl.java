package com.ruoyi.manufacturing.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.manufacturing.domain.ErpProductionOrder;
import com.ruoyi.manufacturing.mapper.ErpProductionOrderMapper;
import com.ruoyi.manufacturing.service.IErpProductionOrderService;

/**
 * 生产工单Service业务层处理
 *
 * @author erp
 */
@Service
public class ErpProductionOrderServiceImpl implements IErpProductionOrderService
{
    @Autowired
    private ErpProductionOrderMapper erpProductionOrderMapper;

    /**
     * 查询生产工单
     */
    @Override
    public ErpProductionOrder selectErpProductionOrderByMoId(Long moId)
    {
        return erpProductionOrderMapper.selectErpProductionOrderByMoId(moId);
    }

    /**
     * 查询生产工单列表
     */
    @Override
    public List<ErpProductionOrder> selectErpProductionOrderList(ErpProductionOrder erpProductionOrder)
    {
        return erpProductionOrderMapper.selectErpProductionOrderList(erpProductionOrder);
    }

    /**
     * 新增生产工单
     */
    @Override
    public int insertErpProductionOrder(ErpProductionOrder erpProductionOrder)
    {
        erpProductionOrder.setCreateTime(DateUtils.getNowDate());
        return erpProductionOrderMapper.insertErpProductionOrder(erpProductionOrder);
    }

    /**
     * 修改生产工单
     */
    @Override
    public int updateErpProductionOrder(ErpProductionOrder erpProductionOrder)
    {
        erpProductionOrder.setUpdateTime(DateUtils.getNowDate());
        return erpProductionOrderMapper.updateErpProductionOrder(erpProductionOrder);
    }

    /**
     * 批量删除生产工单
     */
    @Override
    public int deleteErpProductionOrderByMoIds(Long[] moIds)
    {
        return erpProductionOrderMapper.deleteErpProductionOrderByMoIds(moIds);
    }

    /**
     * 删除生产工单信息
     */
    @Override
    public int deleteErpProductionOrderByMoId(Long moId)
    {
        return erpProductionOrderMapper.deleteErpProductionOrderByMoId(moId);
    }
}
