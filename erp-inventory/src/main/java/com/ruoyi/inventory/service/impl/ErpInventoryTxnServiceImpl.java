package com.ruoyi.inventory.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.inventory.domain.ErpInventoryTxn;
import com.ruoyi.inventory.mapper.ErpInventoryTxnMapper;
import com.ruoyi.inventory.service.IErpInventoryTxnService;

/**
 * 库存流水Service业务层处理
 *
 * @author erp
 */
@Service
public class ErpInventoryTxnServiceImpl implements IErpInventoryTxnService
{
    @Autowired
    private ErpInventoryTxnMapper erpInventoryTxnMapper;

    /**
     * 查询库存流水
     */
    @Override
    public ErpInventoryTxn selectErpInventoryTxnByTxnId(Long txnId)
    {
        return erpInventoryTxnMapper.selectErpInventoryTxnByTxnId(txnId);
    }

    /**
     * 查询库存流水列表
     */
    @Override
    public List<ErpInventoryTxn> selectErpInventoryTxnList(ErpInventoryTxn erpInventoryTxn)
    {
        return erpInventoryTxnMapper.selectErpInventoryTxnList(erpInventoryTxn);
    }

    /**
     * 新增库存流水
     */
    @Override
    public int insertErpInventoryTxn(ErpInventoryTxn erpInventoryTxn)
    {
        erpInventoryTxn.setCreateTime(DateUtils.getNowDate());
        return erpInventoryTxnMapper.insertErpInventoryTxn(erpInventoryTxn);
    }

    /**
     * 修改库存流水
     */
    @Override
    public int updateErpInventoryTxn(ErpInventoryTxn erpInventoryTxn)
    {
        erpInventoryTxn.setUpdateTime(DateUtils.getNowDate());
        return erpInventoryTxnMapper.updateErpInventoryTxn(erpInventoryTxn);
    }

    /**
     * 批量删除库存流水
     */
    @Override
    public int deleteErpInventoryTxnByTxnIds(Long[] txnIds)
    {
        return erpInventoryTxnMapper.deleteErpInventoryTxnByTxnIds(txnIds);
    }

    /**
     * 删除库存流水信息
     */
    @Override
    public int deleteErpInventoryTxnByTxnId(Long txnId)
    {
        return erpInventoryTxnMapper.deleteErpInventoryTxnByTxnId(txnId);
    }
}
