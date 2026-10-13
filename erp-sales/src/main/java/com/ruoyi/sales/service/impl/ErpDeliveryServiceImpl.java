package com.ruoyi.sales.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.sales.domain.ErpDelivery;
import com.ruoyi.sales.domain.ErpDeliveryLine;
import com.ruoyi.sales.mapper.ErpDeliveryMapper;
import com.ruoyi.sales.service.IErpDeliveryService;

/**
 * 销售发货单Service业务层处理
 *
 * @author erp
 */
@Service
public class ErpDeliveryServiceImpl implements IErpDeliveryService
{
    @Autowired
    private ErpDeliveryMapper erpDeliveryMapper;

    /**
     * 查询销售发货单
     */
    @Override
    public ErpDelivery selectErpDeliveryByDeliveryId(Long deliveryId)
    {
        return erpDeliveryMapper.selectErpDeliveryByDeliveryId(deliveryId);
    }

    /**
     * 查询销售发货单列表
     */
    @Override
    public List<ErpDelivery> selectErpDeliveryList(ErpDelivery erpDelivery)
    {
        return erpDeliveryMapper.selectErpDeliveryList(erpDelivery);
    }

    /**
     * 新增销售发货单
     */
    @Transactional
    @Override
    public int insertErpDelivery(ErpDelivery erpDelivery)
    {
        erpDelivery.setCreateTime(DateUtils.getNowDate());
        int rows = erpDeliveryMapper.insertErpDelivery(erpDelivery);
        insertErpDeliveryLine(erpDelivery);
        return rows;
    }

    /**
     * 修改销售发货单
     */
    @Transactional
    @Override
    public int updateErpDelivery(ErpDelivery erpDelivery)
    {
        erpDelivery.setUpdateTime(DateUtils.getNowDate());
        erpDeliveryMapper.deleteErpDeliveryLineByDeliveryId(erpDelivery.getDeliveryId());
        insertErpDeliveryLine(erpDelivery);
        return erpDeliveryMapper.updateErpDelivery(erpDelivery);
    }

    /**
     * 批量删除销售发货单
     */
    @Transactional
    @Override
    public int deleteErpDeliveryByDeliveryIds(Long[] deliveryIds)
    {
        erpDeliveryMapper.deleteErpDeliveryLineByDeliveryIds(deliveryIds);
        return erpDeliveryMapper.deleteErpDeliveryByDeliveryIds(deliveryIds);
    }

    /**
     * 删除销售发货单信息
     */
    @Transactional
    @Override
    public int deleteErpDeliveryByDeliveryId(Long deliveryId)
    {
        erpDeliveryMapper.deleteErpDeliveryLineByDeliveryId(deliveryId);
        return erpDeliveryMapper.deleteErpDeliveryByDeliveryId(deliveryId);
    }

    /**
     * 新增销售发货单明细信息
     */
    public void insertErpDeliveryLine(ErpDelivery erpDelivery)
    {
        List<ErpDeliveryLine> erpDeliveryLineList = erpDelivery.getErpDeliveryLineList();
        if (StringUtils.isNotNull(erpDeliveryLineList))
        {
            for (ErpDeliveryLine line : erpDeliveryLineList)
            {
                line.setDeliveryId(erpDelivery.getDeliveryId());
            }
            if (erpDeliveryLineList.size() > 0)
            {
                erpDeliveryMapper.batchErpDeliveryLine(erpDeliveryLineList);
            }
        }
    }
}
