package com.ruoyi.plan.controller;

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
import com.ruoyi.plan.domain.ErpMrpResult;
import com.ruoyi.plan.service.IErpMrpResultService;

/**
 * MRP结果Controller
 *
 * @author erp
 */
@RestController
@RequestMapping("/plan/mrpResult")
public class ErpMrpResultController extends BaseController
{
    @Autowired
    private IErpMrpResultService erpMrpResultService;

    /**
     * 查询MRP结果列表
     */
    @PreAuthorize("@ss.hasPermi('plan:mrpResult:list')")
    @GetMapping("/list")
    public TableDataInfo list(ErpMrpResult erpMrpResult)
    {
        startPage();
        List<ErpMrpResult> list = erpMrpResultService.selectErpMrpResultList(erpMrpResult);
        return getDataTable(list);
    }

    /**
     * 导出MRP结果列表
     */
    @PreAuthorize("@ss.hasPermi('plan:mrpResult:export')")
    @Log(title = "MRP结果", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ErpMrpResult erpMrpResult)
    {
        List<ErpMrpResult> list = erpMrpResultService.selectErpMrpResultList(erpMrpResult);
        ExcelUtil<ErpMrpResult> util = new ExcelUtil<ErpMrpResult>(ErpMrpResult.class);
        util.exportExcel(response, list, "MRP结果数据");
    }

    /**
     * 获取MRP结果详细信息
     */
    @PreAuthorize("@ss.hasPermi('plan:mrpResult:query')")
    @GetMapping(value = "/{resultId}")
    public AjaxResult getInfo(@PathVariable("resultId") Long resultId)
    {
        return success(erpMrpResultService.selectErpMrpResultByResultId(resultId));
    }

    /**
     * 新增MRP结果
     */
    @PreAuthorize("@ss.hasPermi('plan:mrpResult:add')")
    @Log(title = "MRP结果", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ErpMrpResult erpMrpResult)
    {
        erpMrpResult.setCreateBy(getUsername());
        return toAjax(erpMrpResultService.insertErpMrpResult(erpMrpResult));
    }

    /**
     * 修改MRP结果
     */
    @PreAuthorize("@ss.hasPermi('plan:mrpResult:edit')")
    @Log(title = "MRP结果", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ErpMrpResult erpMrpResult)
    {
        erpMrpResult.setUpdateBy(getUsername());
        return toAjax(erpMrpResultService.updateErpMrpResult(erpMrpResult));
    }

    /**
     * 删除MRP结果
     */
    @PreAuthorize("@ss.hasPermi('plan:mrpResult:remove')")
    @Log(title = "MRP结果", businessType = BusinessType.DELETE)
    @DeleteMapping("/{resultIds}")
    public AjaxResult remove(@PathVariable Long[] resultIds)
    {
        return toAjax(erpMrpResultService.deleteErpMrpResultByResultIds(resultIds));
    }
}
