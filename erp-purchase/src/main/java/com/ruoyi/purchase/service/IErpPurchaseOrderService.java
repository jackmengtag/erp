package com.ruoyi.purchase.service;

import java.util.List;
import com.ruoyi.purchase.domain.ErpPurchaseOrder;

/**
 * 采购订单Service接口
 *
 * @author erp
 */
public interface IErpPurchaseOrderService
{
    /**
     * 查询采购订单
     */
    public ErpPurchaseOrder selectErpPurchaseOrderByPoId(Long poId);

    /**
     * 查询采购订单列表
     */
    public List<ErpPurchaseOrder> selectErpPurchaseOrderList(ErpPurchaseOrder erpPurchaseOrder);

    /**
     * 新增采购订单
     */
    public int insertErpPurchaseOrder(ErpPurchaseOrder erpPurchaseOrder);

    /**
     * 修改采购订单
     */
    public int updateErpPurchaseOrder(ErpPurchaseOrder erpPurchaseOrder);

    /**
     * 批量删除采购订单
     */
    public int deleteErpPurchaseOrderByPoIds(Long[] poIds);

    /**
     * 删除采购订单信息
     */
    public int deleteErpPurchaseOrderByPoId(Long poId);
}
