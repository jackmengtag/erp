package com.ruoyi.manufacturing.mapper;

import java.util.List;
import com.ruoyi.manufacturing.domain.ErpProductionOrder;

/**
 * 生产工单Mapper接口
 *
 * @author erp
 */
public interface ErpProductionOrderMapper
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
     * 删除生产工单
     */
    public int deleteErpProductionOrderByMoId(Long moId);

    /**
     * 批量删除生产工单
     */
    public int deleteErpProductionOrderByMoIds(Long[] moIds);
}
