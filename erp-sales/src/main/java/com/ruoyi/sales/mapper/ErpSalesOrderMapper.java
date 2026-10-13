package com.ruoyi.sales.mapper;

import java.util.List;
import com.ruoyi.sales.domain.ErpSalesOrder;
import com.ruoyi.sales.domain.ErpSalesOrderLine;

/**
 * 销售订单Mapper接口
 *
 * @author erp
 */
public interface ErpSalesOrderMapper
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
     * 删除销售订单
     */
    public int deleteErpSalesOrderByOrderId(Long orderId);

    /**
     * 批量删除销售订单
     */
    public int deleteErpSalesOrderByOrderIds(Long[] orderIds);

    /**
     * 批量删除销售订单明细
     */
    public int deleteErpSalesOrderLineByOrderIds(Long[] orderIds);

    /**
     * 删除销售订单明细信息
     */
    public int deleteErpSalesOrderLineByOrderId(Long orderId);

    /**
     * 批量新增销售订单明细
     */
    public int batchErpSalesOrderLine(List<ErpSalesOrderLine> erpSalesOrderLineList);
}
