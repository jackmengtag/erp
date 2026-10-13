package com.ruoyi.inventory.service;

import java.util.List;
import com.ruoyi.inventory.domain.ErpInventoryTxn;

/**
 * 库存流水Service接口
 *
 * @author erp
 */
public interface IErpInventoryTxnService
{
    /**
     * 查询库存流水
     */
    public ErpInventoryTxn selectErpInventoryTxnByTxnId(Long txnId);

    /**
     * 查询库存流水列表
     */
    public List<ErpInventoryTxn> selectErpInventoryTxnList(ErpInventoryTxn erpInventoryTxn);

    /**
     * 新增库存流水
     */
    public int insertErpInventoryTxn(ErpInventoryTxn erpInventoryTxn);

    /**
     * 修改库存流水
     */
    public int updateErpInventoryTxn(ErpInventoryTxn erpInventoryTxn);

    /**
     * 批量删除库存流水
     */
    public int deleteErpInventoryTxnByTxnIds(Long[] txnIds);

    /**
     * 删除库存流水信息
     */
    public int deleteErpInventoryTxnByTxnId(Long txnId);
}
