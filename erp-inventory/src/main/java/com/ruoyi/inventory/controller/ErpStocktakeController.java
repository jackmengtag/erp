package com.ruoyi.inventory.controller;

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
import com.ruoyi.inventory.domain.ErpStocktake;
import com.ruoyi.inventory.service.IErpStocktakeService;

/**
 * 盘点单Controller
 *
 * @author erp
 */
@RestController
@RequestMapping("/inventory/stocktake")
public class ErpStocktakeController extends BaseController
{
    @Autowired
    private IErpStocktakeService erpStocktakeService;

    /**
     * 查询盘点单列表
     */
    @PreAuthorize("@ss.hasPermi('inventory:stocktake:list')")
    @GetMapping("/list")
    public TableDataInfo list(ErpStocktake erpStocktake)
    {
        startPage();
        List<ErpStocktake> list = erpStocktakeService.selectErpStocktakeList(erpStocktake);
        return getDataTable(list);
    }

    /**
     * 导出盘点单列表
     */
    @PreAuthorize("@ss.hasPermi('inventory:stocktake:export')")
    @Log(title = "盘点单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ErpStocktake erpStocktake)
    {
        List<ErpStocktake> list = erpStocktakeService.selectErpStocktakeList(erpStocktake);
        ExcelUtil<ErpStocktake> util = new ExcelUtil<ErpStocktake>(ErpStocktake.class);
        util.exportExcel(response, list, "盘点单数据");
    }

    /**
     * 获取盘点单详细信息
     */
    @PreAuthorize("@ss.hasPermi('inventory:stocktake:query')")
    @GetMapping(value = "/{stocktakeId}")
    public AjaxResult getInfo(@PathVariable("stocktakeId") Long stocktakeId)
    {
        return success(erpStocktakeService.selectErpStocktakeByStocktakeId(stocktakeId));
    }

    /**
     * 新增盘点单
     */
    @PreAuthorize("@ss.hasPermi('inventory:stocktake:add')")
    @Log(title = "盘点单", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ErpStocktake erpStocktake)
    {
        erpStocktake.setCreateBy(getUsername());
        return toAjax(erpStocktakeService.insertErpStocktake(erpStocktake));
    }

    /**
     * 修改盘点单
     */
    @PreAuthorize("@ss.hasPermi('inventory:stocktake:edit')")
    @Log(title = "盘点单", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ErpStocktake erpStocktake)
    {
        erpStocktake.setUpdateBy(getUsername());
        return toAjax(erpStocktakeService.updateErpStocktake(erpStocktake));
    }

    /**
     * 删除盘点单
     */
    @PreAuthorize("@ss.hasPermi('inventory:stocktake:remove')")
    @Log(title = "盘点单", businessType = BusinessType.DELETE)
    @DeleteMapping("/{stocktakeIds}")
    public AjaxResult remove(@PathVariable Long[] stocktakeIds)
    {
        return toAjax(erpStocktakeService.deleteErpStocktakeByStocktakeIds(stocktakeIds));
    }
}
