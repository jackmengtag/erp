package com.ruoyi.purchase.mapper;

import java.util.List;
import com.ruoyi.purchase.domain.ErpPurchaseOrder;
import com.ruoyi.purchase.domain.ErpPurchaseOrderLine;

/**
 * 采购订单Mapper接口
 *
 * @author erp
 */
public interface ErpPurchaseOrderMapper
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
     * 删除采购订单
     */
    public int deleteErpPurchaseOrderByPoId(Long poId);

    /**
     * 批量删除采购订单
     */
    public int deleteErpPurchaseOrderByPoIds(Long[] poIds);

    /**
     * 批量删除采购订单明细
     */
    public int deleteErpPurchaseOrderLineByPoIds(Long[] poIds);

    /**
     * 删除采购订单明细信息
     */
    public int deleteErpPurchaseOrderLineByPoId(Long poId);

    /**
     * 批量新增采购订单明细
     */
    public int batchErpPurchaseOrderLine(List<ErpPurchaseOrderLine> erpPurchaseOrderLineList);
}
