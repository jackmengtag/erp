package com.ruoyi.sales.service;

import java.util.List;
import com.ruoyi.sales.domain.ErpDelivery;

/**
 * 销售发货单Service接口
 *
 * @author erp
 */
public interface IErpDeliveryService
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
     * 批量删除销售发货单
     */
    public int deleteErpDeliveryByDeliveryIds(Long[] deliveryIds);

    /**
     * 删除销售发货单信息
     */
    public int deleteErpDeliveryByDeliveryId(Long deliveryId);
}
