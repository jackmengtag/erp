package com.ruoyi.finance.service;

import java.util.List;
import com.ruoyi.finance.domain.ErpSettlement;

/**
 * 核销记录Service接口
 *
 * @author erp
 */
public interface IErpSettlementService
{
    /**
     * 查询核销记录
     */
    public ErpSettlement selectErpSettlementBySettleId(Long settleId);

    /**
     * 查询核销记录列表
     */
    public List<ErpSettlement> selectErpSettlementList(ErpSettlement erpSettlement);

    /**
     * 新增核销记录
     */
    public int insertErpSettlement(ErpSettlement erpSettlement);

    /**
     * 修改核销记录
     */
    public int updateErpSettlement(ErpSettlement erpSettlement);

    /**
     * 批量删除核销记录
     */
    public int deleteErpSettlementBySettleIds(Long[] settleIds);

    /**
     * 删除核销记录信息
     */
    public int deleteErpSettlementBySettleId(Long settleId);
}
