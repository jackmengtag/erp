package com.ruoyi.quality.service;

import java.util.List;
import com.ruoyi.quality.domain.ErpInspectionPlan;

/**
 * 检验方案Service接口
 *
 * @author erp
 */
public interface IErpInspectionPlanService
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
     * 批量删除检验方案
     */
    public int deleteErpInspectionPlanByPlanIds(Long[] planIds);

    /**
     * 删除检验方案信息
     */
    public int deleteErpInspectionPlanByPlanId(Long planId);
}
