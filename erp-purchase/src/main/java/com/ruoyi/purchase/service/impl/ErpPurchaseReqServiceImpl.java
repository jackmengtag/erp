package com.ruoyi.purchase.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.purchase.domain.ErpPurchaseReq;
import com.ruoyi.purchase.domain.ErpPurchaseReqLine;
import com.ruoyi.purchase.mapper.ErpPurchaseReqMapper;
import com.ruoyi.purchase.service.IErpPurchaseReqService;

/**
 * 采购申请Service业务层处理
 *
 * @author erp
 */
@Service
public class ErpPurchaseReqServiceImpl implements IErpPurchaseReqService
{
    @Autowired
    private ErpPurchaseReqMapper erpPurchaseReqMapper;

    /**
     * 查询采购申请
     */
    @Override
    public ErpPurchaseReq selectErpPurchaseReqByReqId(Long reqId)
    {
        return erpPurchaseReqMapper.selectErpPurchaseReqByReqId(reqId);
    }

    /**
     * 查询采购申请列表
     */
    @Override
    public List<ErpPurchaseReq> selectErpPurchaseReqList(ErpPurchaseReq erpPurchaseReq)
    {
        return erpPurchaseReqMapper.selectErpPurchaseReqList(erpPurchaseReq);
    }

    /**
     * 新增采购申请
     */
    @Transactional
    @Override
    public int insertErpPurchaseReq(ErpPurchaseReq erpPurchaseReq)
    {
        erpPurchaseReq.setCreateTime(DateUtils.getNowDate());
        int rows = erpPurchaseReqMapper.insertErpPurchaseReq(erpPurchaseReq);
        insertErpPurchaseReqLine(erpPurchaseReq);
        return rows;
    }

    /**
     * 修改采购申请
     */
    @Transactional
    @Override
    public int updateErpPurchaseReq(ErpPurchaseReq erpPurchaseReq)
    {
        erpPurchaseReq.setUpdateTime(DateUtils.getNowDate());
        erpPurchaseReqMapper.deleteErpPurchaseReqLineByReqId(erpPurchaseReq.getReqId());
        insertErpPurchaseReqLine(erpPurchaseReq);
        return erpPurchaseReqMapper.updateErpPurchaseReq(erpPurchaseReq);
    }

    /**
     * 批量删除采购申请
     */
    @Transactional
    @Override
    public int deleteErpPurchaseReqByReqIds(Long[] reqIds)
    {
        erpPurchaseReqMapper.deleteErpPurchaseReqLineByReqIds(reqIds);
        return erpPurchaseReqMapper.deleteErpPurchaseReqByReqIds(reqIds);
    }

    /**
     * 删除采购申请信息
     */
    @Transactional
    @Override
    public int deleteErpPurchaseReqByReqId(Long reqId)
    {
        erpPurchaseReqMapper.deleteErpPurchaseReqLineByReqId(reqId);
        return erpPurchaseReqMapper.deleteErpPurchaseReqByReqId(reqId);
    }

    /**
     * 新增采购申请明细信息
     */
    public void insertErpPurchaseReqLine(ErpPurchaseReq erpPurchaseReq)
    {
        List<ErpPurchaseReqLine> erpPurchaseReqLineList = erpPurchaseReq.getErpPurchaseReqLineList();
        if (StringUtils.isNotNull(erpPurchaseReqLineList))
        {
            for (ErpPurchaseReqLine line : erpPurchaseReqLineList)
            {
                line.setReqId(erpPurchaseReq.getReqId());
            }
            if (erpPurchaseReqLineList.size() > 0)
            {
                erpPurchaseReqMapper.batchErpPurchaseReqLine(erpPurchaseReqLineList);
            }
        }
    }
}
