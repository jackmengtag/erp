package com.ruoyi.finance.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.finance.domain.ErpSettlement;
import com.ruoyi.finance.mapper.ErpSettlementMapper;
import com.ruoyi.finance.service.IErpSettlementService;

/**
 * 核销记录Service业务层处理
 *
 * @author erp
 */
@Service
public class ErpSettlementServiceImpl implements IErpSettlementService
{
    @Autowired
    private ErpSettlementMapper erpSettlementMapper;

    /**
     * 查询核销记录
     */
    @Override
    public ErpSettlement selectErpSettlementBySettleId(Long settleId)
    {
        return erpSettlementMapper.selectErpSettlementBySettleId(settleId);
    }

    /**
     * 查询核销记录列表
     */
    @Override
    public List<ErpSettlement> selectErpSettlementList(ErpSettlement erpSettlement)
    {
        return erpSettlementMapper.selectErpSettlementList(erpSettlement);
    }

    /**
     * 新增核销记录
     */
    @Override
    public int insertErpSettlement(ErpSettlement erpSettlement)
    {
        erpSettlement.setCreateTime(DateUtils.getNowDate());
        return erpSettlementMapper.insertErpSettlement(erpSettlement);
    }

    /**
     * 修改核销记录
     */
    @Override
    public int updateErpSettlement(ErpSettlement erpSettlement)
    {
        erpSettlement.setUpdateTime(DateUtils.getNowDate());
        return erpSettlementMapper.updateErpSettlement(erpSettlement);
    }

    /**
     * 批量删除核销记录
     */
    @Override
    public int deleteErpSettlementBySettleIds(Long[] settleIds)
    {
        return erpSettlementMapper.deleteErpSettlementBySettleIds(settleIds);
    }

    /**
     * 删除核销记录信息
     */
    @Override
    public int deleteErpSettlementBySettleId(Long settleId)
    {
        return erpSettlementMapper.deleteErpSettlementBySettleId(settleId);
    }
}
