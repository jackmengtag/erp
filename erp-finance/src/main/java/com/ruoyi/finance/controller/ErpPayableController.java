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
import com.ruoyi.finance.domain.ErpPayable;
import com.ruoyi.finance.service.IErpPayableService;

/**
 * 应付单Controller
 *
 * @author erp
 */
@RestController
@RequestMapping("/finance/payable")
public class ErpPayableController extends BaseController
{
    @Autowired
    private IErpPayableService erpPayableService;

    /**
     * 查询应付单列表
     */
    @PreAuthorize("@ss.hasPermi('finance:payable:list')")
    @GetMapping("/list")
    public TableDataInfo list(ErpPayable erpPayable)
    {
        startPage();
        List<ErpPayable> list = erpPayableService.selectErpPayableList(erpPayable);
        return getDataTable(list);
    }

    /**
     * 导出应付单列表
     */
    @PreAuthorize("@ss.hasPermi('finance:payable:export')")
    @Log(title = "应付单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ErpPayable erpPayable)
    {
        List<ErpPayable> list = erpPayableService.selectErpPayableList(erpPayable);
        ExcelUtil<ErpPayable> util = new ExcelUtil<ErpPayable>(ErpPayable.class);
        util.exportExcel(response, list, "应付单数据");
    }

    /**
     * 获取应付单详细信息
     */
    @PreAuthorize("@ss.hasPermi('finance:payable:query')")
    @GetMapping(value = "/{apId}")
    public AjaxResult getInfo(@PathVariable("apId") Long apId)
    {
        return success(erpPayableService.selectErpPayableByApId(apId));
    }

    /**
     * 新增应付单
     */
    @PreAuthorize("@ss.hasPermi('finance:payable:add')")
    @Log(title = "应付单", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ErpPayable erpPayable)
    {
        erpPayable.setCreateBy(getUsername());
        return toAjax(erpPayableService.insertErpPayable(erpPayable));
    }

    /**
     * 修改应付单
     */
    @PreAuthorize("@ss.hasPermi('finance:payable:edit')")
    @Log(title = "应付单", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ErpPayable erpPayable)
    {
        erpPayable.setUpdateBy(getUsername());
        return toAjax(erpPayableService.updateErpPayable(erpPayable));
    }

    /**
     * 删除应付单
     */
    @PreAuthorize("@ss.hasPermi('finance:payable:remove')")
    @Log(title = "应付单", businessType = BusinessType.DELETE)
    @DeleteMapping("/{apIds}")
    public AjaxResult remove(@PathVariable Long[] apIds)
    {
        return toAjax(erpPayableService.deleteErpPayableByApIds(apIds));
    }
}
