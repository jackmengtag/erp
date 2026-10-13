package com.ruoyi.quality.mapper;

import java.util.List;
import com.ruoyi.quality.domain.ErpInspectionPlan;
import com.ruoyi.quality.domain.ErpInspectionPlanLine;

/**
 * 检验方案Mapper接口
 *
 * @author erp
 */
public interface ErpInspectionPlanMapper
{
    /**
     * 查询检验方案
     */
    public ErpInspectionPlan selectErpInspectionPlanByPlanId(Long planId);

    /**
     * 查询检验方案列表
     */
    public List<ErpInspectionPlan> selectErpInspectionPlanList(ErpInspectionPlan erpInspectionPlan);

    /**
     * 新增检验方案
     */
    public int insertErpInspectionPlan(ErpInspectionPlan erpInspectionPlan);

    /**
     * 修改检验方案
     */
    public int updateErpInspectionPlan(ErpInspectionPlan erpInspectionPlan);

    /**
     * 删除检验方案
     */
    public int deleteErpInspectionPlanByPlanId(Long planId);

    /**
     * 批量删除检验方案
     */
    public int deleteErpInspectionPlanByPlanIds(Long[] planIds);

    /**
     * 批量删除检验方案明细
     */
    public int deleteErpInspectionPlanLineByPlanIds(Long[] planIds);

    /**
     * 删除检验方案明细信息
     */
    public int deleteErpInspectionPlanLineByPlanId(Long planId);

    /**
     * 批量新增检验方案明细
     */
    public int batchErpInspectionPlanLine(List<ErpInspectionPlanLine> erpInspectionPlanLineList);
}
