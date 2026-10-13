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
import com.ruoyi.finance.domain.ErpInventoryCost;
import com.ruoyi.finance.service.IErpInventoryCostService;

/**
 * 存货核算Controller
 *
 * @author erp
 */
@RestController
@RequestMapping("/finance/inventoryCost")
public class ErpInventoryCostController extends BaseController
{
    @Autowired
    private IErpInventoryCostService erpInventoryCostService;

    /**
     * 查询存货核算列表
     */
    @PreAuthorize("@ss.hasPermi('finance:inventoryCost:list')")
    @GetMapping("/list")
    public TableDataInfo list(ErpInventoryCost erpInventoryCost)
    {
        startPage();
        List<ErpInventoryCost> list = erpInventoryCostService.selectErpInventoryCostList(erpInventoryCost);
        return getDataTable(list);
    }

    /**
     * 导出存货核算列表
     */
    @PreAuthorize("@ss.hasPermi('finance:inventoryCost:export')")
    @Log(title = "存货核算", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ErpInventoryCost erpInventoryCost)
    {
        List<ErpInventoryCost> list = erpInventoryCostService.selectErpInventoryCostList(erpInventoryCost);
        ExcelUtil<ErpInventoryCost> util = new ExcelUtil<ErpInventoryCost>(ErpInventoryCost.class);
        util.exportExcel(response, list, "存货核算数据");
    }

    /**
     * 获取存货核算详细信息
     */
    @PreAuthorize("@ss.hasPermi('finance:inventoryCost:query')")
    @GetMapping(value = "/{costId}")
    public AjaxResult getInfo(@PathVariable("costId") Long costId)
    {
        return success(erpInventoryCostService.selectErpInventoryCostByCostId(costId));
    }

    /**
     * 新增存货核算
     */
    @PreAuthorize("@ss.hasPermi('finance:inventoryCost:add')")
    @Log(title = "存货核算", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ErpInventoryCost erpInventoryCost)
    {
        erpInventoryCost.setCreateBy(getUsername());
        return toAjax(erpInventoryCostService.insertErpInventoryCost(erpInventoryCost));
    }

    /**
     * 修改存货核算
     */
    @PreAuthorize("@ss.hasPermi('finance:inventoryCost:edit')")
    @Log(title = "存货核算", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ErpInventoryCost erpInventoryCost)
    {
        erpInventoryCost.setUpdateBy(getUsername());
        return toAjax(erpInventoryCostService.updateErpInventoryCost(erpInventoryCost));
    }

    /**
     * 删除存货核算
     */
    @PreAuthorize("@ss.hasPermi('finance:inventoryCost:remove')")
    @Log(title = "存货核算", businessType = BusinessType.DELETE)
    @DeleteMapping("/{costIds}")
    public AjaxResult remove(@PathVariable Long[] costIds)
    {
        return toAjax(erpInventoryCostService.deleteErpInventoryCostByCostIds(costIds));
    }
}
