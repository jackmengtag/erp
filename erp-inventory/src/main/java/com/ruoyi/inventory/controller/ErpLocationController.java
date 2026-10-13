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
import com.ruoyi.inventory.domain.ErpLocation;
import com.ruoyi.inventory.service.IErpLocationService;

/**
 * 库位Controller
 *
 * @author erp
 */
@RestController
@RequestMapping("/inventory/location")
public class ErpLocationController extends BaseController
{
    @Autowired
    private IErpLocationService erpLocationService;

    /**
     * 查询库位列表
     */
    @PreAuthorize("@ss.hasPermi('inventory:location:list')")
    @GetMapping("/list")
    public TableDataInfo list(ErpLocation erpLocation)
    {
        startPage();
        List<ErpLocation> list = erpLocationService.selectErpLocationList(erpLocation);
        return getDataTable(list);
    }

    /**
     * 导出库位列表
     */
    @PreAuthorize("@ss.hasPermi('inventory:location:export')")
    @Log(title = "库位", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ErpLocation erpLocation)
    {
        List<ErpLocation> list = erpLocationService.selectErpLocationList(erpLocation);
        ExcelUtil<ErpLocation> util = new ExcelUtil<ErpLocation>(ErpLocation.class);
        util.exportExcel(response, list, "库位数据");
    }

    /**
     * 获取库位详细信息
     */
    @PreAuthorize("@ss.hasPermi('inventory:location:query')")
    @GetMapping(value = "/{locationId}")
    public AjaxResult getInfo(@PathVariable("locationId") Long locationId)
    {
        return success(erpLocationService.selectErpLocationByLocationId(locationId));
    }

    /**
     * 新增库位
     */
    @PreAuthorize("@ss.hasPermi('inventory:location:add')")
    @Log(title = "库位", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ErpLocation erpLocation)
    {
        erpLocation.setCreateBy(getUsername());
        return toAjax(erpLocationService.insertErpLocation(erpLocation));
    }

    /**
     * 修改库位
     */
    @PreAuthorize("@ss.hasPermi('inventory:location:edit')")
    @Log(title = "库位", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ErpLocation erpLocation)
    {
        erpLocation.setUpdateBy(getUsername());
        return toAjax(erpLocationService.updateErpLocation(erpLocation));
    }

    /**
     * 删除库位
     */
    @PreAuthorize("@ss.hasPermi('inventory:location:remove')")
    @Log(title = "库位", businessType = BusinessType.DELETE)
    @DeleteMapping("/{locationIds}")
    public AjaxResult remove(@PathVariable Long[] locationIds)
    {
        return toAjax(erpLocationService.deleteErpLocationByLocationIds(locationIds));
    }
}
