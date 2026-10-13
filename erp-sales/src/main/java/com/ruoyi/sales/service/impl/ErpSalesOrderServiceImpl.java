package com.ruoyi.sales.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.sales.domain.ErpSalesOrder;
import com.ruoyi.sales.domain.ErpSalesOrderLine;
import com.ruoyi.sales.mapper.ErpSalesOrderMapper;
import com.ruoyi.sales.service.IErpSalesOrderService;

/**
 * 销售订单Service业务层处理
 *
 * @author erp
 */
@Service
public class ErpSalesOrderServiceImpl implements IErpSalesOrderService
{
    @Autowired
    private ErpSalesOrderMapper erpSalesOrderMapper;

    /**
     * 查询销售订单
     */
    @Override
    public ErpSalesOrder selectErpSalesOrderByOrderId(Long orderId)
    {
        return erpSalesOrderMapper.selectErpSalesOrderByOrderId(orderId);
    }

    /**
     * 查询销售订单列表
     */
    @Override
    public List<ErpSalesOrder> selectErpSalesOrderList(ErpSalesOrder erpSalesOrder)
    {
        return erpSalesOrderMapper.selectErpSalesOrderList(erpSalesOrder);
    }

    /**
     * 新增销售订单
     */
    @Transactional
    @Override
    public int insertErpSalesOrder(ErpSalesOrder erpSalesOrder)
    {
        erpSalesOrder.setCreateTime(DateUtils.getNowDate());
        int rows = erpSalesOrderMapper.insertErpSalesOrder(erpSalesOrder);
        insertErpSalesOrderLine(erpSalesOrder);
        return rows;
    }

    /**
     * 修改销售订单
     */
    @Transactional
    @Override
    public int updateErpSalesOrder(ErpSalesOrder erpSalesOrder)
    {
        erpSalesOrder.setUpdateTime(DateUtils.getNowDate());
        erpSalesOrderMapper.deleteErpSalesOrderLineByOrderId(erpSalesOrder.getOrderId());
        insertErpSalesOrderLine(erpSalesOrder);
        return erpSalesOrderMapper.updateErpSalesOrder(erpSalesOrder);
    }

    /**
     * 批量删除销售订单
     */
    @Transactional
    @Override
    public int deleteErpSalesOrderByOrderIds(Long[] orderIds)
    {
        erpSalesOrderMapper.deleteErpSalesOrderLineByOrderIds(orderIds);
        return erpSalesOrderMapper.deleteErpSalesOrderByOrderIds(orderIds);
    }

    /**
     * 删除销售订单信息
     */
    @Transactional
    @Override
    public int deleteErpSalesOrderByOrderId(Long orderId)
    {
        erpSalesOrderMapper.deleteErpSalesOrderLineByOrderId(orderId);
        return erpSalesOrderMapper.deleteErpSalesOrderByOrderId(orderId);
    }

    /**
     * 新增销售订单明细信息
     */
    public void insertErpSalesOrderLine(ErpSalesOrder erpSalesOrder)
    {
        List<ErpSalesOrderLine> erpSalesOrderLineList = erpSalesOrder.getErpSalesOrderLineList();
        if (StringUtils.isNotNull(erpSalesOrderLineList))
        {
            for (ErpSalesOrderLine line : erpSalesOrderLineList)
            {
                line.setOrderId(erpSalesOrder.getOrderId());
            }
            if (erpSalesOrderLineList.size() > 0)
            {
                erpSalesOrderMapper.batchErpSalesOrderLine(erpSalesOrderLineList);
            }
        }
    }
}
