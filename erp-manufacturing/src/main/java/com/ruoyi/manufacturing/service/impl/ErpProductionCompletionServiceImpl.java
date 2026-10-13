package com.ruoyi.manufacturing.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.manufacturing.domain.ErpProductionCompletion;
import com.ruoyi.manufacturing.mapper.ErpProductionCompletionMapper;
import com.ruoyi.manufacturing.service.IErpProductionCompletionService;

/**
 * 完工入库Service业务层处理
 *
 * @author erp
 */
@Service
public class ErpProductionCompletionServiceImpl implements IErpProductionCompletionService
{
    @Autowired
    private ErpProductionCompletionMapper erpProductionCompletionMapper;

    /**
     * 查询完工入库
     */
    @Override
    public ErpProductionCompletion selectErpProductionCompletionByCompletionId(Long completionId)
    {
        return erpProductionCompletionMapper.selectErpProductionCompletionByCompletionId(completionId);
    }

    /**
     * 查询完工入库列表
     */
    @Override
    public List<ErpProductionCompletion> selectErpProductionCompletionList(ErpProductionCompletion erpProductionCompletion)
    {
        return erpProductionCompletionMapper.selectErpProductionCompletionList(erpProductionCompletion);
    }

    /**
     * 新增完工入库
     */
    @Override
    public int insertErpProductionCompletion(ErpProductionCompletion erpProductionCompletion)
    {
        erpProductionCompletion.setCreateTime(DateUtils.getNowDate());
        return erpProductionCompletionMapper.insertErpProductionCompletion(erpProductionCompletion);
    }

    /**
     * 修改完工入库
     */
    @Override
    public int updateErpProductionCompletion(ErpProductionCompletion erpProductionCompletion)
    {
        erpProductionCompletion.setUpdateTime(DateUtils.getNowDate());
        return erpProductionCompletionMapper.updateErpProductionCompletion(erpProductionCompletion);
    }

    /**
     * 批量删除完工入库
     */
    @Override
    public int deleteErpProductionCompletionByCompletionIds(Long[] completionIds)
    {
        return erpProductionCompletionMapper.deleteErpProductionCompletionByCompletionIds(completionIds);
    }

    /**
     * 删除完工入库信息
     */
    @Override
    public int deleteErpProductionCompletionByCompletionId(Long completionId)
    {
        return erpProductionCompletionMapper.deleteErpProductionCompletionByCompletionId(completionId);
    }
}
