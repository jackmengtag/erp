package com.ruoyi.sales.service;

import java.util.List;
import com.ruoyi.sales.domain.ErpSalesOrder;

/**
 * 销售订单Service接口
 *
 * @author erp
 */
public interface IErpSalesOrderService
{
    /**
     * 查询销售订单
     */
    public ErpSalesOrder selectErpSalesOrderByOrderId(Long orderId);

    /**
     * 查询销售订单列表
     */
    public List<ErpSalesOrder> selectErpSalesOrderList(ErpSalesOrder erpSalesOrder);

    /**
     * 新增销售订单
     */
    public int insertErpSalesOrder(ErpSalesOrder erpSalesOrder);

    /**
     * 修改销售订单
     */
    public int updateErpSalesOrder(ErpSalesOrder erpSalesOrder);

    /**
     * 批量删除销售订单
     */
    public int deleteErpSalesOrderByOrderIds(Long[] orderIds);

    /**
     * 删除销售订单信息
     */
    public int deleteErpSalesOrderByOrderId(Long orderId);
}
