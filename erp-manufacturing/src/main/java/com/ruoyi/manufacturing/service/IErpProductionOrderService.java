package com.ruoyi.manufacturing.service;

import java.util.List;
import com.ruoyi.manufacturing.domain.ErpProductionOrder;

/**
 * 生产工单Service接口
 *
 * @author erp
 */
public interface IErpProductionOrderService
{
    /**
     * 查询生产工单
     */
    public ErpProductionOrder selectErpProductionOrderByMoId(Long moId);

    /**
     * 查询生产工单列表
     */
    public List<ErpProductionOrder> selectErpProductionOrderList(ErpProductionOrder erpProductionOrder);

    /**
     * 新增生产工单
     */
    public int insertErpProductionOrder(ErpProductionOrder erpProductionOrder);

    /**
     * 修改生产工单
     */
    public int updateErpProductionOrder(ErpProductionOrder erpProductionOrder);

    /**
     * 批量删除生产工单
     */
    public int deleteErpProductionOrderByMoIds(Long[] moIds);

    /**
     * 删除生产工单信息
     */
    public int deleteErpProductionOrderByMoId(Long moId);
}
