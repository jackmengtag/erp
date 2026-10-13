package com.ruoyi.plan.mapper;

import java.util.List;
import com.ruoyi.plan.domain.ErpMrpRun;

/**
 * MRP运算Mapper接口
 *
 * @author erp
 */
public interface ErpMrpRunMapper
{
    /**
     * 查询MRP运算
     */
    public ErpMrpRun selectErpMrpRunByRunId(Long runId);

    /**
     * 查询MRP运算列表
     */
    public List<ErpMrpRun> selectErpMrpRunList(ErpMrpRun erpMrpRun);

    /**
     * 新增MRP运算
     */
    public int insertErpMrpRun(ErpMrpRun erpMrpRun);

    /**
     * 修改MRP运算
     */
    public int updateErpMrpRun(ErpMrpRun erpMrpRun);

    /**
     * 删除MRP运算
     */
    public int deleteErpMrpRunByRunId(Long runId);

    /**
     * 批量删除MRP运算
     */
    public int deleteErpMrpRunByRunIds(Long[] runIds);
}
