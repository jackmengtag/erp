package com.ruoyi.sales.mapper;

import java.util.List;
import com.ruoyi.sales.domain.ErpDelivery;
import com.ruoyi.sales.domain.ErpDeliveryLine;

/**
 * 销售发货单Mapper接口
 *
 * @author erp
 */
public interface ErpDeliveryMapper
{
    /**
     * 查询销售发货单
     */
    public ErpDelivery selectErpDeliveryByDeliveryId(Long deliveryId);

    /**
     * 查询销售发货单列表
     */
    public List<ErpDelivery> selectErpDeliveryList(ErpDelivery erpDelivery);

    /**
     * 新增销售发货单
     */
    public int insertErpDelivery(ErpDelivery erpDelivery);

    /**
     * 修改销售发货单
     */
    public int updateErpDelivery(ErpDelivery erpDelivery);

    /**
     * 删除销售发货单
     */
    public int deleteErpDeliveryByDeliveryId(Long deliveryId);

    /**
     * 批量删除销售发货单
     */
    public int deleteErpDeliveryByDeliveryIds(Long[] deliveryIds);

    /**
     * 批量删除销售发货单明细
     */
    public int deleteErpDeliveryLineByDeliveryIds(Long[] deliveryIds);

    /**
     * 删除销售发货单明细信息
     */
    public int deleteErpDeliveryLineByDeliveryId(Long deliveryId);

    /**
     * 批量新增销售发货单明细
     */
    public int batchErpDeliveryLine(List<ErpDeliveryLine> erpDeliveryLineList);
}
