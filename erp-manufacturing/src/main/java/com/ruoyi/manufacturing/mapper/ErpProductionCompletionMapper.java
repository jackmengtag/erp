package com.ruoyi.manufacturing.mapper;

import java.util.List;
import com.ruoyi.manufacturing.domain.ErpProductionCompletion;

/**
 * 完工入库Mapper接口
 *
 * @author erp
 */
public interface ErpProductionCompletionMapper
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
     * 删除完工入库
     */
    public int deleteErpProductionCompletionByCompletionId(Long completionId);

    /**
     * 批量删除完工入库
     */
    public int deleteErpProductionCompletionByCompletionIds(Long[] completionIds);
}
