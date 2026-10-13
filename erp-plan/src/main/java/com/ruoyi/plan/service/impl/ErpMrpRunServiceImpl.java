package com.ruoyi.plan.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.plan.domain.ErpMrpRun;
import com.ruoyi.plan.mapper.ErpMrpRunMapper;
import com.ruoyi.plan.service.IErpMrpRunService;

/**
 * MRP运算Service业务层处理
 *
 * @author erp
 */
@Service
public class ErpMrpRunServiceImpl implements IErpMrpRunService
{
    @Autowired
    private ErpMrpRunMapper erpMrpRunMapper;

    /**
     * 查询MRP运算
     */
    @Override
    public ErpMrpRun selectErpMrpRunByRunId(Long runId)
    {
        return erpMrpRunMapper.selectErpMrpRunByRunId(runId);
    }

    /**
     * 查询MRP运算列表
     */
    @Override
    public List<ErpMrpRun> selectErpMrpRunList(ErpMrpRun erpMrpRun)
    {
        return erpMrpRunMapper.selectErpMrpRunList(erpMrpRun);
    }

    /**
     * 新增MRP运算
     */
    @Override
    public int insertErpMrpRun(ErpMrpRun erpMrpRun)
    {
        erpMrpRun.setCreateTime(DateUtils.getNowDate());
        return erpMrpRunMapper.insertErpMrpRun(erpMrpRun);
    }

    /**
     * 修改MRP运算
     */
    @Override
    public int updateErpMrpRun(ErpMrpRun erpMrpRun)
    {
        erpMrpRun.setUpdateTime(DateUtils.getNowDate());
        return erpMrpRunMapper.updateErpMrpRun(erpMrpRun);
    }

    /**
     * 批量删除MRP运算
     */
    @Override
    public int deleteErpMrpRunByRunIds(Long[] runIds)
    {
        return erpMrpRunMapper.deleteErpMrpRunByRunIds(runIds);
    }

    /**
     * 删除MRP运算信息
     */
    @Override
    public int deleteErpMrpRunByRunId(Long runId)
    {
        return erpMrpRunMapper.deleteErpMrpRunByRunId(runId);
    }
}
