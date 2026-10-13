package com.ruoyi.manufacturing.service;

import java.util.List;
import com.ruoyi.manufacturing.domain.ErpProductionCompletion;

/**
 * 完工入库Service接口
 *
 * @author erp
 */
public interface IErpProductionCompletionService
{
    /**
     * 查询完工入库
     */
    public ErpProductionCompletion selectErpProductionCompletionByCompletionId(Long completionId);

    /**
     * 查询完工入库列表
     */
    public List<ErpProductionCompletion> selectErpProductionCompletionList(ErpProductionCompletion erpProductionCompletion);

    /**
     * 新增完工入库
     */
    public int insertErpProductionCompletion(ErpProductionCompletion erpProductionCompletion);

    /**
     * 修改完工入库
     */
    public int updateErpProductionCompletion(ErpProductionCompletion erpProductionCompletion);

    /**
     * 批量删除完工入库
     */
    public int deleteErpProductionCompletionByCompletionIds(Long[] completionIds);

    /**
     * 删除完工入库信息
     */
    public int deleteErpProductionCompletionByCompletionId(Long completionId);
}
