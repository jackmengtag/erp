package com.ruoyi.plan.service;

import java.util.List;
import com.ruoyi.plan.domain.ErpMrpResult;

/**
 * MRP结果Service接口
 *
 * @author erp
 */
public interface IErpMrpResultService
{
    /**
     * 查询MRP结果
     */
    public ErpMrpResult selectErpMrpResultByResultId(Long resultId);

    /**
     * 查询MRP结果列表
     */
    public List<ErpMrpResult> selectErpMrpResultList(ErpMrpResult erpMrpResult);

    /**
     * 新增MRP结果
     */
    public int insertErpMrpResult(ErpMrpResult erpMrpResult);

    /**
     * 修改MRP结果
     */
    public int updateErpMrpResult(ErpMrpResult erpMrpResult);

    /**
     * 批量删除MRP结果
     */
    public int deleteErpMrpResultByResultIds(Long[] resultIds);

    /**
     * 删除MRP结果信息
     */
    public int deleteErpMrpResultByResultId(Long resultId);
}
