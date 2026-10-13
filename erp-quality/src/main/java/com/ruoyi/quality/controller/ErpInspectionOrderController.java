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
import com.ruoyi.quality.domain.ErpInspectionOrder;
import com.ruoyi.quality.service.IErpInspectionOrderService;

/**
 * 检验单Controller
 *
 * @author erp
 */
@RestController
@RequestMapping("/quality/inspect")
public class ErpInspectionOrderController extends BaseController
{
    @Autowired
    private IErpInspectionOrderService erpInspectionOrderService;

    /**
     * 查询检验单列表
     */
    @PreAuthorize("@ss.hasPermi('quality:inspect:list')")
    @GetMapping("/list")
    public TableDataInfo list(ErpInspectionOrder erpInspectionOrder)
    {
        startPage();
        List<ErpInspectionOrder> list = erpInspectionOrderService.selectErpInspectionOrderList(erpInspectionOrder);
        return getDataTable(list);
    }

    /**
     * 导出检验单列表
     */
    @PreAuthorize("@ss.hasPermi('quality:inspect:export')")
    @Log(title = "检验单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ErpInspectionOrder erpInspectionOrder)
    {
        List<ErpInspectionOrder> list = erpInspectionOrderService.selectErpInspectionOrderList(erpInspectionOrder);
        ExcelUtil<ErpInspectionOrder> util = new ExcelUtil<ErpInspectionOrder>(ErpInspectionOrder.class);
        util.exportExcel(response, list, "检验单数据");
    }

    /**
     * 获取检验单详细信息
     */
    @PreAuthorize("@ss.hasPermi('quality:inspect:query')")
    @GetMapping(value = "/{inspectId}")
    public AjaxResult getInfo(@PathVariable("inspectId") Long inspectId)
    {
        return success(erpInspectionOrderService.selectErpInspectionOrderByInspectId(inspectId));
    }

    /**
     * 新增检验单
     */
    @PreAuthorize("@ss.hasPermi('quality:inspect:add')")
    @Log(title = "检验单", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ErpInspectionOrder erpInspectionOrder)
    {
        erpInspectionOrder.setCreateBy(getUsername());
        return toAjax(erpInspectionOrderService.insertErpInspectionOrder(erpInspectionOrder));
    }

    /**
     * 修改检验单
     */
    @PreAuthorize("@ss.hasPermi('quality:inspect:edit')")
    @Log(title = "检验单", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ErpInspectionOrder erpInspectionOrder)
    {
        erpInspectionOrder.setUpdateBy(getUsername());
        return toAjax(erpInspectionOrderService.updateErpInspectionOrder(erpInspectionOrder));
    }

    /**
     * 删除检验单
     */
    @PreAuthorize("@ss.hasPermi('quality:inspect:remove')")
    @Log(title = "检验单", businessType = BusinessType.DELETE)
    @DeleteMapping("/{inspectIds}")
    public AjaxResult remove(@PathVariable Long[] inspectIds)
    {
        return toAjax(erpInspectionOrderService.deleteErpInspectionOrderByInspectIds(inspectIds));
    }
}
