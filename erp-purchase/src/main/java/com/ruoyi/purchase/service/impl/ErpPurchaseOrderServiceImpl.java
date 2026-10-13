package com.ruoyi.purchase.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.purchase.domain.ErpPurchaseOrder;
import com.ruoyi.purchase.domain.ErpPurchaseOrderLine;
import com.ruoyi.purchase.mapper.ErpPurchaseOrderMapper;
import com.ruoyi.purchase.service.IErpPurchaseOrderService;

/**
 * 采购订单Service业务层处理
 *
 * @author erp
 */
@Service
public class ErpPurchaseOrderServiceImpl implements IErpPurchaseOrderService
{
    @Autowired
    private ErpPurchaseOrderMapper erpPurchaseOrderMapper;

    /**
     * 查询采购订单
     */
    @Override
    public ErpPurchaseOrder selectErpPurchaseOrderByPoId(Long poId)
    {
        return erpPurchaseOrderMapper.selectErpPurchaseOrderByPoId(poId);
    }

    /**
     * 查询采购订单列表
     */
    @Override
    public List<ErpPurchaseOrder> selectErpPurchaseOrderList(ErpPurchaseOrder erpPurchaseOrder)
    {
        return erpPurchaseOrderMapper.selectErpPurchaseOrderList(erpPurchaseOrder);
    }

    /**
     * 新增采购订单
     */
    @Transactional
    @Override
    public int insertErpPurchaseOrder(ErpPurchaseOrder erpPurchaseOrder)
    {
        erpPurchaseOrder.setCreateTime(DateUtils.getNowDate());
        int rows = erpPurchaseOrderMapper.insertErpPurchaseOrder(erpPurchaseOrder);
        insertErpPurchaseOrderLine(erpPurchaseOrder);
        return rows;
    }

    /**
     * 修改采购订单
     */
    @Transactional
    @Override
    public int updateErpPurchaseOrder(ErpPurchaseOrder erpPurchaseOrder)
    {
        erpPurchaseOrder.setUpdateTime(DateUtils.getNowDate());
        erpPurchaseOrderMapper.deleteErpPurchaseOrderLineByPoId(erpPurchaseOrder.getPoId());
        insertErpPurchaseOrderLine(erpPurchaseOrder);
        return erpPurchaseOrderMapper.updateErpPurchaseOrder(erpPurchaseOrder);
    }

    /**
     * 批量删除采购订单
     */
    @Transactional
    @Override
    public int deleteErpPurchaseOrderByPoIds(Long[] poIds)
    {
        erpPurchaseOrderMapper.deleteErpPurchaseOrderLineByPoIds(poIds);
        return erpPurchaseOrderMapper.deleteErpPurchaseOrderByPoIds(poIds);
    }

    /**
     * 删除采购订单信息
     */
    @Transactional
    @Override
    public int deleteErpPurchaseOrderByPoId(Long poId)
    {
        erpPurchaseOrderMapper.deleteErpPurchaseOrderLineByPoId(poId);
        return erpPurchaseOrderMapper.deleteErpPurchaseOrderByPoId(poId);
    }

    /**
     * 新增采购订单明细信息
     */
    public void insertErpPurchaseOrderLine(ErpPurchaseOrder erpPurchaseOrder)
    {
        List<ErpPurchaseOrderLine> erpPurchaseOrderLineList = erpPurchaseOrder.getErpPurchaseOrderLineList();
        if (StringUtils.isNotNull(erpPurchaseOrderLineList))
        {
            for (ErpPurchaseOrderLine line : erpPurchaseOrderLineList)
            {
                line.setPoId(erpPurchaseOrder.getPoId());
            }
            if (erpPurchaseOrderLineList.size() > 0)
            {
                erpPurchaseOrderMapper.batchErpPurchaseOrderLine(erpPurchaseOrderLineList);
            }
        }
    }
}
