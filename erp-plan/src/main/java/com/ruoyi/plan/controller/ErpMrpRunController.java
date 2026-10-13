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
import com.ruoyi.plan.domain.ErpMrpRun;
import com.ruoyi.plan.service.IErpMrpRunService;

/**
 * MRP运算Controller
 *
 * @author erp
 */
@RestController
@RequestMapping("/plan/mrpRun")
public class ErpMrpRunController extends BaseController
{
    @Autowired
    private IErpMrpRunService erpMrpRunService;

    /**
     * 查询MRP运算列表
     */
    @PreAuthorize("@ss.hasPermi('plan:mrpRun:list')")
    @GetMapping("/list")
    public TableDataInfo list(ErpMrpRun erpMrpRun)
    {
        startPage();
        List<ErpMrpRun> list = erpMrpRunService.selectErpMrpRunList(erpMrpRun);
        return getDataTable(list);
    }

    /**
     * 导出MRP运算列表
     */
    @PreAuthorize("@ss.hasPermi('plan:mrpRun:export')")
    @Log(title = "MRP运算", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ErpMrpRun erpMrpRun)
    {
        List<ErpMrpRun> list = erpMrpRunService.selectErpMrpRunList(erpMrpRun);
        ExcelUtil<ErpMrpRun> util = new ExcelUtil<ErpMrpRun>(ErpMrpRun.class);
        util.exportExcel(response, list, "MRP运算数据");
    }

    /**
     * 获取MRP运算详细信息
     */
    @PreAuthorize("@ss.hasPermi('plan:mrpRun:query')")
    @GetMapping(value = "/{runId}")
    public AjaxResult getInfo(@PathVariable("runId") Long runId)
    {
        return success(erpMrpRunService.selectErpMrpRunByRunId(runId));
    }

    /**
     * 新增MRP运算
     */
    @PreAuthorize("@ss.hasPermi('plan:mrpRun:add')")
    @Log(title = "MRP运算", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ErpMrpRun erpMrpRun)
    {
        erpMrpRun.setCreateBy(getUsername());
        return toAjax(erpMrpRunService.insertErpMrpRun(erpMrpRun));
    }

    /**
     * 修改MRP运算
     */
    @PreAuthorize("@ss.hasPermi('plan:mrpRun:edit')")
    @Log(title = "MRP运算", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ErpMrpRun erpMrpRun)
    {
        erpMrpRun.setUpdateBy(getUsername());
        return toAjax(erpMrpRunService.updateErpMrpRun(erpMrpRun));
    }

    /**
     * 删除MRP运算
     */
    @PreAuthorize("@ss.hasPermi('plan:mrpRun:remove')")
    @Log(title = "MRP运算", businessType = BusinessType.DELETE)
    @DeleteMapping("/{runIds}")
    public AjaxResult remove(@PathVariable Long[] runIds)
    {
        return toAjax(erpMrpRunService.deleteErpMrpRunByRunIds(runIds));
    }
}
