package com.ruoyi.quality.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.quality.domain.ErpInspectionPlan;
import com.ruoyi.quality.domain.ErpInspectionPlanLine;
import com.ruoyi.quality.mapper.ErpInspectionPlanMapper;
import com.ruoyi.quality.service.IErpInspectionPlanService;

/**
 * 检验方案Service业务层处理
 *
 * @author erp
 */
@Service
public class ErpInspectionPlanServiceImpl implements IErpInspectionPlanService
{
    @Autowired
    private ErpInspectionPlanMapper erpInspectionPlanMapper;

    /**
     * 查询检验方案
     */
    @Override
    public ErpInspectionPlan selectErpInspectionPlanByPlanId(Long planId)
    {
        return erpInspectionPlanMapper.selectErpInspectionPlanByPlanId(planId);
    }

    /**
     * 查询检验方案列表
     */
    @Override
    public List<ErpInspectionPlan> selectErpInspectionPlanList(ErpInspectionPlan erpInspectionPlan)
    {
        return erpInspectionPlanMapper.selectErpInspectionPlanList(erpInspectionPlan);
    }

    /**
     * 新增检验方案
     */
    @Transactional
    @Override
    public int insertErpInspectionPlan(ErpInspectionPlan erpInspectionPlan)
    {
        erpInspectionPlan.setCreateTime(DateUtils.getNowDate());
        int rows = erpInspectionPlanMapper.insertErpInspectionPlan(erpInspectionPlan);
        insertErpInspectionPlanLine(erpInspectionPlan);
        return rows;
    }

    /**
     * 修改检验方案
     */
    @Transactional
    @Override
    public int updateErpInspectionPlan(ErpInspectionPlan erpInspectionPlan)
    {
        erpInspectionPlan.setUpdateTime(DateUtils.getNowDate());
        erpInspectionPlanMapper.deleteErpInspectionPlanLineByPlanId(erpInspectionPlan.getPlanId());
        insertErpInspectionPlanLine(erpInspectionPlan);
        return erpInspectionPlanMapper.updateErpInspectionPlan(erpInspectionPlan);
    }

    /**
     * 批量删除检验方案
     */
    @Transactional
    @Override
    public int deleteErpInspectionPlanByPlanIds(Long[] planIds)
    {
        erpInspectionPlanMapper.deleteErpInspectionPlanLineByPlanIds(planIds);
        return erpInspectionPlanMapper.deleteErpInspectionPlanByPlanIds(planIds);
    }

    /**
     * 删除检验方案信息
     */
    @Transactional
    @Override
    public int deleteErpInspectionPlanByPlanId(Long planId)
    {
        erpInspectionPlanMapper.deleteErpInspectionPlanLineByPlanId(planId);
        return erpInspectionPlanMapper.deleteErpInspectionPlanByPlanId(planId);
    }

    /**
     * 新增检验方案明细信息
     */
    public void insertErpInspectionPlanLine(ErpInspectionPlan erpInspectionPlan)
    {
        List<ErpInspectionPlanLine> erpInspectionPlanLineList = erpInspectionPlan.getErpInspectionPlanLineList();
        if (StringUtils.isNotNull(erpInspectionPlanLineList))
        {
            for (ErpInspectionPlanLine line : erpInspectionPlanLineList)
            {
                line.setPlanId(erpInspectionPlan.getPlanId());
            }
            if (erpInspectionPlanLineList.size() > 0)
            {
                erpInspectionPlanMapper.batchErpInspectionPlanLine(erpInspectionPlanLineList);
            }
        }
    }
}
