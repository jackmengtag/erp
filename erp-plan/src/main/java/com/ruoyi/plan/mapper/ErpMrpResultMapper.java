package com.ruoyi.plan.mapper;

import java.util.List;
import com.ruoyi.plan.domain.ErpMrpResult;

/**
 * MRP结果Mapper接口
 *
 * @author erp
 */
public interface ErpMrpResultMapper
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
     * 删除MRP结果
     */
    public int deleteErpMrpResultByResultId(Long resultId);

    /**
     * 批量删除MRP结果
     */
    public int deleteErpMrpResultByResultIds(Long[] resultIds);
}
