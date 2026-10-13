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
import com.ruoyi.finance.domain.ErpInvoice;
import com.ruoyi.finance.service.IErpInvoiceService;

/**
 * 发票Controller
 *
 * @author erp
 */
@RestController
@RequestMapping("/finance/invoice")
public class ErpInvoiceController extends BaseController
{
    @Autowired
    private IErpInvoiceService erpInvoiceService;

    /**
     * 查询发票列表
     */
    @PreAuthorize("@ss.hasPermi('finance:invoice:list')")
    @GetMapping("/list")
    public TableDataInfo list(ErpInvoice erpInvoice)
    {
        startPage();
        List<ErpInvoice> list = erpInvoiceService.selectErpInvoiceList(erpInvoice);
        return getDataTable(list);
    }

    /**
     * 导出发票列表
     */
    @PreAuthorize("@ss.hasPermi('finance:invoice:export')")
    @Log(title = "发票", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ErpInvoice erpInvoice)
    {
        List<ErpInvoice> list = erpInvoiceService.selectErpInvoiceList(erpInvoice);
        ExcelUtil<ErpInvoice> util = new ExcelUtil<ErpInvoice>(ErpInvoice.class);
        util.exportExcel(response, list, "发票数据");
    }

    /**
     * 获取发票详细信息
     */
    @PreAuthorize("@ss.hasPermi('finance:invoice:query')")
    @GetMapping(value = "/{invoiceId}")
    public AjaxResult getInfo(@PathVariable("invoiceId") Long invoiceId)
    {
        return success(erpInvoiceService.selectErpInvoiceByInvoiceId(invoiceId));
    }

    /**
     * 新增发票
     */
    @PreAuthorize("@ss.hasPermi('finance:invoice:add')")
    @Log(title = "发票", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ErpInvoice erpInvoice)
    {
        erpInvoice.setCreateBy(getUsername());
        return toAjax(erpInvoiceService.insertErpInvoice(erpInvoice));
    }

    /**
     * 修改发票
     */
    @PreAuthorize("@ss.hasPermi('finance:invoice:edit')")
    @Log(title = "发票", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ErpInvoice erpInvoice)
    {
        erpInvoice.setUpdateBy(getUsername());
        return toAjax(erpInvoiceService.updateErpInvoice(erpInvoice));
    }

    /**
     * 删除发票
     */
    @PreAuthorize("@ss.hasPermi('finance:invoice:remove')")
    @Log(title = "发票", businessType = BusinessType.DELETE)
    @DeleteMapping("/{invoiceIds}")
    public AjaxResult remove(@PathVariable Long[] invoiceIds)
    {
        return toAjax(erpInvoiceService.deleteErpInvoiceByInvoiceIds(invoiceIds));
    }
}
