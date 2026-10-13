package com.ruoyi.finance.controller;

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
import com.ruoyi.finance.domain.ErpProductionCost;
import com.ruoyi.finance.service.IErpProductionCostService;

/**
 * 生产成本归集Controller
 *
 * @author erp
 */
@RestController
@RequestMapping("/finance/productionCost")
public class ErpProductionCostController extends BaseController
{
    @Autowired
    private IErpProductionCostService erpProductionCostService;

    /**
     * 查询生产成本归集列表
     */
    @PreAuthorize("@ss.hasPermi('finance:productionCost:list')")
    @GetMapping("/list")
    public TableDataInfo list(ErpProductionCost erpProductionCost)
    {
        startPage();
        List<ErpProductionCost> list = erpProductionCostService.selectErpProductionCostList(erpProductionCost);
        return getDataTable(list);
    }

    /**
     * 导出生产成本归集列表
     */
    @PreAuthorize("@ss.hasPermi('finance:productionCost:export')")
    @Log(title = "生产成本归集", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ErpProductionCost erpProductionCost)
    {
        List<ErpProductionCost> list = erpProductionCostService.selectErpProductionCostList(erpProductionCost);
        ExcelUtil<ErpProductionCost> util = new ExcelUtil<ErpProductionCost>(ErpProductionCost.class);
        util.exportExcel(response, list, "生产成本归集数据");
    }

    /**
     * 获取生产成本归集详细信息
     */
    @PreAuthorize("@ss.hasPermi('finance:productionCost:query')")
    @GetMapping(value = "/{costId}")
    public AjaxResult getInfo(@PathVariable("costId") Long costId)
    {
        return success(erpProductionCostService.selectErpProductionCostByCostId(costId));
    }

    /**
     * 新增生产成本归集
     */
    @PreAuthorize("@ss.hasPermi('finance:productionCost:add')")
    @Log(title = "生产成本归集", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ErpProductionCost erpProductionCost)
    {
        erpProductionCost.setCreateBy(getUsername());
        return toAjax(erpProductionCostService.insertErpProductionCost(erpProductionCost));
    }

    /**
     * 修改生产成本归集
     */
    @PreAuthorize("@ss.hasPermi('finance:productionCost:edit')")
    @Log(title = "生产成本归集", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ErpProductionCost erpProductionCost)
    {
        erpProductionCost.setUpdateBy(getUsername());
        return toAjax(erpProductionCostService.updateErpProductionCost(erpProductionCost));
    }

    /**
     * 删除生产成本归集
     */
    @PreAuthorize("@ss.hasPermi('finance:productionCost:remove')")
    @Log(title = "生产成本归集", businessType = BusinessType.DELETE)
    @DeleteMapping("/{costIds}")
    public AjaxResult remove(@PathVariable Long[] costIds)
    {
        return toAjax(erpProductionCostService.deleteErpProductionCostByCostIds(costIds));
    }
}
