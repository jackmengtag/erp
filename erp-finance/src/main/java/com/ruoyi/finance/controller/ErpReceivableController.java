package com.ruoyi.finance.controller;

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
import com.ruoyi.finance.domain.ErpReceivable;
import com.ruoyi.finance.service.IErpReceivableService;

/**
 * 应收单Controller
 *
 * @author erp
 */
@RestController
@RequestMapping("/finance/receivable")
public class ErpReceivableController extends BaseController
{
    @Autowired
    private IErpReceivableService erpReceivableService;

    /**
     * 查询应收单列表
     */
    @PreAuthorize("@ss.hasPermi('finance:receivable:list')")
    @GetMapping("/list")
    public TableDataInfo list(ErpReceivable erpReceivable)
    {
        startPage();
        List<ErpReceivable> list = erpReceivableService.selectErpReceivableList(erpReceivable);
        return getDataTable(list);
    }

    /**
     * 导出应收单列表
     */
    @PreAuthorize("@ss.hasPermi('finance:receivable:export')")
    @Log(title = "应收单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ErpReceivable erpReceivable)
    {
        List<ErpReceivable> list = erpReceivableService.selectErpReceivableList(erpReceivable);
        ExcelUtil<ErpReceivable> util = new ExcelUtil<ErpReceivable>(ErpReceivable.class);
        util.exportExcel(response, list, "应收单数据");
    }

    /**
     * 获取应收单详细信息
     */
    @PreAuthorize("@ss.hasPermi('finance:receivable:query')")
    @GetMapping(value = "/{arId}")
    public AjaxResult getInfo(@PathVariable("arId") Long arId)
    {
        return success(erpReceivableService.selectErpReceivableByArId(arId));
    }

    /**
     * 新增应收单
     */
    @PreAuthorize("@ss.hasPermi('finance:receivable:add')")
    @Log(title = "应收单", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ErpReceivable erpReceivable)
    {
        erpReceivable.setCreateBy(getUsername());
        return toAjax(erpReceivableService.insertErpReceivable(erpReceivable));
    }

    /**
     * 修改应收单
     */
    @PreAuthorize("@ss.hasPermi('finance:receivable:edit')")
    @Log(title = "应收单", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ErpReceivable erpReceivable)
    {
        erpReceivable.setUpdateBy(getUsername());
        return toAjax(erpReceivableService.updateErpReceivable(erpReceivable));
    }

    /**
     * 删除应收单
     */
    @PreAuthorize("@ss.hasPermi('finance:receivable:remove')")
    @Log(title = "应收单", businessType = BusinessType.DELETE)
    @DeleteMapping("/{arIds}")
    public AjaxResult remove(@PathVariable Long[] arIds)
    {
        return toAjax(erpReceivableService.deleteErpReceivableByArIds(arIds));
    }
}
