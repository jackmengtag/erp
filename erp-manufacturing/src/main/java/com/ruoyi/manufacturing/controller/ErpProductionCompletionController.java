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
import com.ruoyi.manufacturing.domain.ErpProductionCompletion;
import com.ruoyi.manufacturing.service.IErpProductionCompletionService;

/**
 * 完工入库Controller
 *
 * @author erp
 */
@RestController
@RequestMapping("/manufacturing/completion")
public class ErpProductionCompletionController extends BaseController
{
    @Autowired
    private IErpProductionCompletionService erpProductionCompletionService;

    /**
     * 查询完工入库列表
     */
    @PreAuthorize("@ss.hasPermi('manufacturing:completion:list')")
    @GetMapping("/list")
    public TableDataInfo list(ErpProductionCompletion erpProductionCompletion)
    {
        startPage();
        List<ErpProductionCompletion> list = erpProductionCompletionService.selectErpProductionCompletionList(erpProductionCompletion);
        return getDataTable(list);
    }

    /**
     * 导出完工入库列表
     */
    @PreAuthorize("@ss.hasPermi('manufacturing:completion:export')")
    @Log(title = "完工入库", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ErpProductionCompletion erpProductionCompletion)
    {
        List<ErpProductionCompletion> list = erpProductionCompletionService.selectErpProductionCompletionList(erpProductionCompletion);
        ExcelUtil<ErpProductionCompletion> util = new ExcelUtil<ErpProductionCompletion>(ErpProductionCompletion.class);
        util.exportExcel(response, list, "完工入库数据");
    }

    /**
     * 获取完工入库详细信息
     */
    @PreAuthorize("@ss.hasPermi('manufacturing:completion:query')")
    @GetMapping(value = "/{completionId}")
    public AjaxResult getInfo(@PathVariable("completionId") Long completionId)
    {
        return success(erpProductionCompletionService.selectErpProductionCompletionByCompletionId(completionId));
    }

    /**
     * 新增完工入库
     */
    @PreAuthorize("@ss.hasPermi('manufacturing:completion:add')")
    @Log(title = "完工入库", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ErpProductionCompletion erpProductionCompletion)
    {
        erpProductionCompletion.setCreateBy(getUsername());
        return toAjax(erpProductionCompletionService.insertErpProductionCompletion(erpProductionCompletion));
    }

    /**
     * 修改完工入库
     */
    @PreAuthorize("@ss.hasPermi('manufacturing:completion:edit')")
    @Log(title = "完工入库", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ErpProductionCompletion erpProductionCompletion)
    {
        erpProductionCompletion.setUpdateBy(getUsername());
        return toAjax(erpProductionCompletionService.updateErpProductionCompletion(erpProductionCompletion));
    }

    /**
     * 删除完工入库
     */
    @PreAuthorize("@ss.hasPermi('manufacturing:completion:remove')")
    @Log(title = "完工入库", businessType = BusinessType.DELETE)
    @DeleteMapping("/{completionIds}")
    public AjaxResult remove(@PathVariable Long[] completionIds)
    {
        return toAjax(erpProductionCompletionService.deleteErpProductionCompletionByCompletionIds(completionIds));
    }
}
