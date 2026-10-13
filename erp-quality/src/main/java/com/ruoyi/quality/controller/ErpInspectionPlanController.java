package com.ruoyi.quality.controller;

import java.util.List;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.quality.domain.ErpInspectionPlan;
import com.ruoyi.quality.service.IErpInspectionPlanService;

/**
 * 检验方案Controller
 *
 * @author erp
 */
@RestController
@RequestMapping("/quality/plan")
public class ErpInspectionPlanController extends BaseController
{
    @Autowired
    private IErpInspectionPlanService erpInspectionPlanService;

    /**
     * 查询检验方案列表
     */
    @PreAuthorize("@ss.hasPermi('quality:plan:list')")
    @GetMapping("/list")
    public TableDataInfo list(ErpInspectionPlan erpInspectionPlan)
    {
        startPage();
        List<ErpInspectionPlan> list = erpInspectionPlanService.selectErpInspectionPlanList(erpInspectionPlan);
        return getDataTable(list);
    }

    /**
     * 导出检验方案列表
     */
    @PreAuthorize("@ss.hasPermi('quality:plan:export')")
    @Log(title = "检验方案", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ErpInspectionPlan erpInspectionPlan)
    {
        List<ErpInspectionPlan> list = erpInspectionPlanService.selectErpInspectionPlanList(erpInspectionPlan);
        ExcelUtil<ErpInspectionPlan> util = new ExcelUtil<ErpInspectionPlan>(ErpInspectionPlan.class);
        util.exportExcel(response, list, "检验方案数据");
    }

    /**
     * 获取检验方案详细信息
     */
    @PreAuthorize("@ss.hasPermi('quality:plan:query')")
    @GetMapping(value = "/{planId}")
    public AjaxResult getInfo(@PathVariable("planId") Long planId)
    {
        return success(erpInspectionPlanService.selectErpInspectionPlanByPlanId(planId));
    }

    /**
     * 新增检验方案
     */
    @PreAuthorize("@ss.hasPermi('quality:plan:add')")
    @Log(title = "检验方案", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ErpInspectionPlan erpInspectionPlan)
    {
        erpInspectionPlan.setCreateBy(getUsername());
        return toAjax(erpInspectionPlanService.insertErpInspectionPlan(erpInspectionPlan));
    }

    /**
     * 修改检验方案
     */
    @PreAuthorize("@ss.hasPermi('quality:plan:edit')")
    @Log(title = "检验方案", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ErpInspectionPlan erpInspectionPlan)
    {
        erpInspectionPlan.setUpdateBy(getUsername());
        return toAjax(erpInspectionPlanService.updateErpInspectionPlan(erpInspectionPlan));
    }

    /**
     * 删除检验方案
     */
    @PreAuthorize("@ss.hasPermi('quality:plan:remove')")
    @Log(title = "检验方案", businessType = BusinessType.DELETE)
    @DeleteMapping("/{planIds}")
    public AjaxResult remove(@PathVariable Long[] planIds)
    {
        return toAjax(erpInspectionPlanService.deleteErpInspectionPlanByPlanIds(planIds));
    }
}
