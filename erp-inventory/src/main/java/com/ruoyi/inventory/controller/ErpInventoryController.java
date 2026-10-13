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
import com.ruoyi.inventory.domain.ErpInventory;
import com.ruoyi.inventory.service.IErpInventoryService;

/**
 * 库存余额Controller
 *
 * @author erp
 */
@RestController
@RequestMapping("/inventory/inventory")
public class ErpInventoryController extends BaseController
{
    @Autowired
    private IErpInventoryService erpInventoryService;

    /**
     * 查询库存余额列表
     */
    @PreAuthorize("@ss.hasPermi('inventory:inventory:list')")
    @GetMapping("/list")
    public TableDataInfo list(ErpInventory erpInventory)
    {
        startPage();
        List<ErpInventory> list = erpInventoryService.selectErpInventoryList(erpInventory);
        return getDataTable(list);
    }

    /**
     * 导出库存余额列表
     */
    @PreAuthorize("@ss.hasPermi('inventory:inventory:export')")
    @Log(title = "库存余额", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ErpInventory erpInventory)
    {
        List<ErpInventory> list = erpInventoryService.selectErpInventoryList(erpInventory);
        ExcelUtil<ErpInventory> util = new ExcelUtil<ErpInventory>(ErpInventory.class);
        util.exportExcel(response, list, "库存余额数据");
    }

    /**
     * 获取库存余额详细信息
     */
    @PreAuthorize("@ss.hasPermi('inventory:inventory:query')")
    @GetMapping(value = "/{inventoryId}")
    public AjaxResult getInfo(@PathVariable("inventoryId") Long inventoryId)
    {
        return success(erpInventoryService.selectErpInventoryByInventoryId(inventoryId));
    }

    /**
     * 新增库存余额
     */
    @PreAuthorize("@ss.hasPermi('inventory:inventory:add')")
    @Log(title = "库存余额", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ErpInventory erpInventory)
    {
        erpInventory.setCreateBy(getUsername());
        return toAjax(erpInventoryService.insertErpInventory(erpInventory));
    }

    /**
     * 修改库存余额
     */
    @PreAuthorize("@ss.hasPermi('inventory:inventory:edit')")
    @Log(title = "库存余额", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ErpInventory erpInventory)
    {
        erpInventory.setUpdateBy(getUsername());
        return toAjax(erpInventoryService.updateErpInventory(erpInventory));
    }

    /**
     * 删除库存余额
     */
    @PreAuthorize("@ss.hasPermi('inventory:inventory:remove')")
    @Log(title = "库存余额", businessType = BusinessType.DELETE)
    @DeleteMapping("/{inventoryIds}")
    public AjaxResult remove(@PathVariable Long[] inventoryIds)
    {
        return toAjax(erpInventoryService.deleteErpInventoryByInventoryIds(inventoryIds));
    }
}
