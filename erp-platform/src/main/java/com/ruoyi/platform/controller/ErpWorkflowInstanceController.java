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
import com.ruoyi.platform.domain.ErpWorkflowInstance;
import com.ruoyi.platform.service.IErpWorkflowInstanceService;

/**
 * 工作流实例Controller
 *
 * @author erp
 */
@RestController
@RequestMapping("/platform/workflowInstance")
public class ErpWorkflowInstanceController extends BaseController
{
    @Autowired
    private IErpWorkflowInstanceService erpWorkflowInstanceService;

    /**
     * 查询工作流实例列表
     */
    @PreAuthorize("@ss.hasPermi('platform:workflowInstance:list')")
    @GetMapping("/list")
    public TableDataInfo list(ErpWorkflowInstance erpWorkflowInstance)
    {
        startPage();
        List<ErpWorkflowInstance> list = erpWorkflowInstanceService.selectErpWorkflowInstanceList(erpWorkflowInstance);
        return getDataTable(list);
    }

    /**
     * 导出工作流实例列表
     */
    @PreAuthorize("@ss.hasPermi('platform:workflowInstance:export')")
    @Log(title = "工作流实例", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ErpWorkflowInstance erpWorkflowInstance)
    {
        List<ErpWorkflowInstance> list = erpWorkflowInstanceService.selectErpWorkflowInstanceList(erpWorkflowInstance);
        ExcelUtil<ErpWorkflowInstance> util = new ExcelUtil<ErpWorkflowInstance>(ErpWorkflowInstance.class);
        util.exportExcel(response, list, "工作流实例数据");
    }

    /**
     * 获取工作流实例详细信息
     */
    @PreAuthorize("@ss.hasPermi('platform:workflowInstance:query')")
    @GetMapping(value = "/{instanceId}")
    public AjaxResult getInfo(@PathVariable("instanceId") Long instanceId)
    {
        return success(erpWorkflowInstanceService.selectErpWorkflowInstanceByInstanceId(instanceId));
    }

    /**
     * 新增工作流实例
     */
    @PreAuthorize("@ss.hasPermi('platform:workflowInstance:add')")
    @Log(title = "工作流实例", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ErpWorkflowInstance erpWorkflowInstance)
    {
        erpWorkflowInstance.setCreateBy(getUsername());
        return toAjax(erpWorkflowInstanceService.insertErpWorkflowInstance(erpWorkflowInstance));
    }

    /**
     * 修改工作流实例
     */
    @PreAuthorize("@ss.hasPermi('platform:workflowInstance:edit')")
    @Log(title = "工作流实例", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ErpWorkflowInstance erpWorkflowInstance)
    {
        erpWorkflowInstance.setUpdateBy(getUsername());
        return toAjax(erpWorkflowInstanceService.updateErpWorkflowInstance(erpWorkflowInstance));
    }

    /**
     * 删除工作流实例
     */
    @PreAuthorize("@ss.hasPermi('platform:workflowInstance:remove')")
    @Log(title = "工作流实例", businessType = BusinessType.DELETE)
    @DeleteMapping("/{instanceIds}")
    public AjaxResult remove(@PathVariable Long[] instanceIds)
    {
        return toAjax(erpWorkflowInstanceService.deleteErpWorkflowInstanceByInstanceIds(instanceIds));
    }
}
