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
import com.ruoyi.manufacturing.domain.ErpProductionReport;
import com.ruoyi.manufacturing.service.IErpProductionReportService;

/**
 * 工序报工Controller
 *
 * @author erp
 */
@RestController
@RequestMapping("/manufacturing/report")
public class ErpProductionReportController extends BaseController
{
    @Autowired
    private IErpProductionReportService erpProductionReportService;

    /**
     * 查询工序报工列表
     */
    @PreAuthorize("@ss.hasPermi('manufacturing:report:list')")
    @GetMapping("/list")
    public TableDataInfo list(ErpProductionReport erpProductionReport)
    {
        startPage();
        List<ErpProductionReport> list = erpProductionReportService.selectErpProductionReportList(erpProductionReport);
        return getDataTable(list);
    }

    /**
     * 导出工序报工列表
     */
    @PreAuthorize("@ss.hasPermi('manufacturing:report:export')")
    @Log(title = "工序报工", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ErpProductionReport erpProductionReport)
    {
        List<ErpProductionReport> list = erpProductionReportService.selectErpProductionReportList(erpProductionReport);
        ExcelUtil<ErpProductionReport> util = new ExcelUtil<ErpProductionReport>(ErpProductionReport.class);
        util.exportExcel(response, list, "工序报工数据");
    }

    /**
     * 获取工序报工详细信息
     */
    @PreAuthorize("@ss.hasPermi('manufacturing:report:query')")
    @GetMapping(value = "/{reportId}")
    public AjaxResult getInfo(@PathVariable("reportId") Long reportId)
    {
        return success(erpProductionReportService.selectErpProductionReportByReportId(reportId));
    }

    /**
     * 新增工序报工
     */
    @PreAuthorize("@ss.hasPermi('manufacturing:report:add')")
    @Log(title = "工序报工", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ErpProductionReport erpProductionReport)
    {
        erpProductionReport.setCreateBy(getUsername());
        return toAjax(erpProductionReportService.insertErpProductionReport(erpProductionReport));
    }

    /**
     * 修改工序报工
     */
    @PreAuthorize("@ss.hasPermi('manufacturing:report:edit')")
    @Log(title = "工序报工", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ErpProductionReport erpProductionReport)
    {
        erpProductionReport.setUpdateBy(getUsername());
        return toAjax(erpProductionReportService.updateErpProductionReport(erpProductionReport));
    }

    /**
     * 删除工序报工
     */
    @PreAuthorize("@ss.hasPermi('manufacturing:report:remove')")
    @Log(title = "工序报工", businessType = BusinessType.DELETE)
    @DeleteMapping("/{reportIds}")
    public AjaxResult remove(@PathVariable Long[] reportIds)
    {
        return toAjax(erpProductionReportService.deleteErpProductionReportByReportIds(reportIds));
    }
}
