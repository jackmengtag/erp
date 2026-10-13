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
import com.ruoyi.platform.domain.ErpWorkflowDef;
import com.ruoyi.platform.service.IErpWorkflowDefService;

/**
 * 工作流定义Controller
 *
 * @author erp
 */
@RestController
@RequestMapping("/platform/workflowDef")
public class ErpWorkflowDefController extends BaseController
{
    @Autowired
    private IErpWorkflowDefService erpWorkflowDefService;

    /**
     * 查询工作流定义列表
     */
    @PreAuthorize("@ss.hasPermi('platform:workflowDef:list')")
    @GetMapping("/list")
    public TableDataInfo list(ErpWorkflowDef erpWorkflowDef)
    {
        startPage();
        List<ErpWorkflowDef> list = erpWorkflowDefService.selectErpWorkflowDefList(erpWorkflowDef);
        return getDataTable(list);
    }

    /**
     * 导出工作流定义列表
     */
    @PreAuthorize("@ss.hasPermi('platform:workflowDef:export')")
    @Log(title = "工作流定义", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ErpWorkflowDef erpWorkflowDef)
    {
        List<ErpWorkflowDef> list = erpWorkflowDefService.selectErpWorkflowDefList(erpWorkflowDef);
        ExcelUtil<ErpWorkflowDef> util = new ExcelUtil<ErpWorkflowDef>(ErpWorkflowDef.class);
        util.exportExcel(response, list, "工作流定义数据");
    }

    /**
     * 获取工作流定义详细信息
     */
    @PreAuthorize("@ss.hasPermi('platform:workflowDef:query')")
    @GetMapping(value = "/{wfId}")
    public AjaxResult getInfo(@PathVariable("wfId") Long wfId)
    {
        return success(erpWorkflowDefService.selectErpWorkflowDefByWfId(wfId));
    }

    /**
     * 新增工作流定义
     */
    @PreAuthorize("@ss.hasPermi('platform:workflowDef:add')")
    @Log(title = "工作流定义", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ErpWorkflowDef erpWorkflowDef)
    {
        erpWorkflowDef.setCreateBy(getUsername());
        return toAjax(erpWorkflowDefService.insertErpWorkflowDef(erpWorkflowDef));
    }

    /**
     * 修改工作流定义
     */
    @PreAuthorize("@ss.hasPermi('platform:workflowDef:edit')")
    @Log(title = "工作流定义", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ErpWorkflowDef erpWorkflowDef)
    {
        erpWorkflowDef.setUpdateBy(getUsername());
        return toAjax(erpWorkflowDefService.updateErpWorkflowDef(erpWorkflowDef));
    }

    /**
     * 删除工作流定义
     */
    @PreAuthorize("@ss.hasPermi('platform:workflowDef:remove')")
    @Log(title = "工作流定义", businessType = BusinessType.DELETE)
    @DeleteMapping("/{wfIds}")
    public AjaxResult remove(@PathVariable Long[] wfIds)
    {
        return toAjax(erpWorkflowDefService.deleteErpWorkflowDefByWfIds(wfIds));
    }
}
