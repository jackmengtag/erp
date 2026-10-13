package com.ruoyi.manufacturing.controller;

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
import com.ruoyi.manufacturing.domain.ErpProductionPicking;
import com.ruoyi.manufacturing.service.IErpProductionPickingService;

/**
 * 生产领料单Controller
 *
 * @author erp
 */
@RestController
@RequestMapping("/manufacturing/picking")
public class ErpProductionPickingController extends BaseController
{
    @Autowired
    private IErpProductionPickingService erpProductionPickingService;

    /**
     * 查询生产领料单列表
     */
    @PreAuthorize("@ss.hasPermi('manufacturing:picking:list')")
    @GetMapping("/list")
    public TableDataInfo list(ErpProductionPicking erpProductionPicking)
    {
        startPage();
        List<ErpProductionPicking> list = erpProductionPickingService.selectErpProductionPickingList(erpProductionPicking);
        return getDataTable(list);
    }

    /**
     * 导出生产领料单列表
     */
    @PreAuthorize("@ss.hasPermi('manufacturing:picking:export')")
    @Log(title = "生产领料单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ErpProductionPicking erpProductionPicking)
    {
        List<ErpProductionPicking> list = erpProductionPickingService.selectErpProductionPickingList(erpProductionPicking);
        ExcelUtil<ErpProductionPicking> util = new ExcelUtil<ErpProductionPicking>(ErpProductionPicking.class);
        util.exportExcel(response, list, "生产领料单数据");
    }

    /**
     * 获取生产领料单详细信息
     */
    @PreAuthorize("@ss.hasPermi('manufacturing:picking:query')")
    @GetMapping(value = "/{pickingId}")
    public AjaxResult getInfo(@PathVariable("pickingId") Long pickingId)
    {
        return success(erpProductionPickingService.selectErpProductionPickingByPickingId(pickingId));
    }

    /**
     * 新增生产领料单
     */
    @PreAuthorize("@ss.hasPermi('manufacturing:picking:add')")
    @Log(title = "生产领料单", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ErpProductionPicking erpProductionPicking)
    {
        erpProductionPicking.setCreateBy(getUsername());
        return toAjax(erpProductionPickingService.insertErpProductionPicking(erpProductionPicking));
    }

    /**
     * 修改生产领料单
     */
    @PreAuthorize("@ss.hasPermi('manufacturing:picking:edit')")
    @Log(title = "生产领料单", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ErpProductionPicking erpProductionPicking)
    {
        erpProductionPicking.setUpdateBy(getUsername());
        return toAjax(erpProductionPickingService.updateErpProductionPicking(erpProductionPicking));
    }

    /**
     * 删除生产领料单
     */
    @PreAuthorize("@ss.hasPermi('manufacturing:picking:remove')")
    @Log(title = "生产领料单", businessType = BusinessType.DELETE)
    @DeleteMapping("/{pickingIds}")
    public AjaxResult remove(@PathVariable Long[] pickingIds)
    {
        return toAjax(erpProductionPickingService.deleteErpProductionPickingByPickingIds(pickingIds));
    }
}
