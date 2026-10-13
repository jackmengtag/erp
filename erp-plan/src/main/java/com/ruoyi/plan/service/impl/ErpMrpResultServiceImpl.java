package com.ruoyi.plan.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.plan.domain.ErpMrpResult;
import com.ruoyi.plan.mapper.ErpMrpResultMapper;
import com.ruoyi.plan.service.IErpMrpResultService;

/**
 * MRP结果Service业务层处理
 *
 * @author erp
 */
@Service
public class ErpMrpResultServiceImpl implements IErpMrpResultService
{
    @Autowired
    private ErpMrpResultMapper erpMrpResultMapper;

    /**
     * 查询MRP结果
     */
    @Override
    public ErpMrpResult selectErpMrpResultByResultId(Long resultId)
    {
        return erpMrpResultMapper.selectErpMrpResultByResultId(resultId);
    }

    /**
     * 查询MRP结果列表
     */
    @Override
    public List<ErpMrpResult> selectErpMrpResultList(ErpMrpResult erpMrpResult)
    {
        return erpMrpResultMapper.selectErpMrpResultList(erpMrpResult);
    }

    /**
     * 新增MRP结果
     */
    @Override
    public int insertErpMrpResult(ErpMrpResult erpMrpResult)
    {
        erpMrpResult.setCreateTime(DateUtils.getNowDate());
        return erpMrpResultMapper.insertErpMrpResult(erpMrpResult);
    }

    /**
     * 修改MRP结果
     */
    @Override
    public int updateErpMrpResult(ErpMrpResult erpMrpResult)
    {
        erpMrpResult.setUpdateTime(DateUtils.getNowDate());
        return erpMrpResultMapper.updateErpMrpResult(erpMrpResult);
    }

    /**
     * 批量删除MRP结果
     */
    @Override
    public int deleteErpMrpResultByResultIds(Long[] resultIds)
    {
        return erpMrpResultMapper.deleteErpMrpResultByResultIds(resultIds);
    }

    /**
     * 删除MRP结果信息
     */
    @Override
    public int deleteErpMrpResultByResultId(Long resultId)
    {
        return erpMrpResultMapper.deleteErpMrpResultByResultId(resultId);
    }
}
