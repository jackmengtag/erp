package com.ruoyi.platform.controller;

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
import com.ruoyi.platform.domain.ErpFieldChangeLog;
import com.ruoyi.platform.service.IErpFieldChangeLogService;

/**
 * 字段变更审计日志Controller
 *
 * @author erp
 */
@RestController
@RequestMapping("/platform/fieldLog")
public class ErpFieldChangeLogController extends BaseController
{
    @Autowired
    private IErpFieldChangeLogService erpFieldChangeLogService;

    /**
     * 查询字段变更审计日志列表
     */
    @PreAuthorize("@ss.hasPermi('platform:fieldLog:list')")
    @GetMapping("/list")
    public TableDataInfo list(ErpFieldChangeLog erpFieldChangeLog)
    {
        startPage();
        List<ErpFieldChangeLog> list = erpFieldChangeLogService.selectErpFieldChangeLogList(erpFieldChangeLog);
        return getDataTable(list);
    }

    /**
     * 导出字段变更审计日志列表
     */
    @PreAuthorize("@ss.hasPermi('platform:fieldLog:export')")
    @Log(title = "字段变更审计日志", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ErpFieldChangeLog erpFieldChangeLog)
    {
        List<ErpFieldChangeLog> list = erpFieldChangeLogService.selectErpFieldChangeLogList(erpFieldChangeLog);
        ExcelUtil<ErpFieldChangeLog> util = new ExcelUtil<ErpFieldChangeLog>(ErpFieldChangeLog.class);
        util.exportExcel(response, list, "字段变更审计日志数据");
    }

    /**
     * 获取字段变更审计日志详细信息
     */
    @PreAuthorize("@ss.hasPermi('platform:fieldLog:query')")
    @GetMapping(value = "/{logId}")
    public AjaxResult getInfo(@PathVariable("logId") Long logId)
    {
        return success(erpFieldChangeLogService.selectErpFieldChangeLogByLogId(logId));
    }

    /**
     * 新增字段变更审计日志
     */
    @PreAuthorize("@ss.hasPermi('platform:fieldLog:add')")
    @Log(title = "字段变更审计日志", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ErpFieldChangeLog erpFieldChangeLog)
    {
        erpFieldChangeLog.setCreateBy(getUsername());
        return toAjax(erpFieldChangeLogService.insertErpFieldChangeLog(erpFieldChangeLog));
    }

    /**
     * 修改字段变更审计日志
     */
    @PreAuthorize("@ss.hasPermi('platform:fieldLog:edit')")
    @Log(title = "字段变更审计日志", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ErpFieldChangeLog erpFieldChangeLog)
    {
        erpFieldChangeLog.setUpdateBy(getUsername());
        return toAjax(erpFieldChangeLogService.updateErpFieldChangeLog(erpFieldChangeLog));
    }

    /**
     * 删除字段变更审计日志
     */
    @PreAuthorize("@ss.hasPermi('platform:fieldLog:remove')")
    @Log(title = "字段变更审计日志", businessType = BusinessType.DELETE)
    @DeleteMapping("/{logIds}")
    public AjaxResult remove(@PathVariable Long[] logIds)
    {
        return toAjax(erpFieldChangeLogService.deleteErpFieldChangeLogByLogIds(logIds));
    }
}
