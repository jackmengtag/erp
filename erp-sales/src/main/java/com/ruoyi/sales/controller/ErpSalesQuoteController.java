package com.ruoyi.sales.controller;

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
import com.ruoyi.sales.domain.ErpSalesQuote;
import com.ruoyi.sales.service.IErpSalesQuoteService;

/**
 * 销售报价单Controller
 *
 * @author erp
 */
@RestController
@RequestMapping("/sales/quote")
public class ErpSalesQuoteController extends BaseController
{
    @Autowired
    private IErpSalesQuoteService erpSalesQuoteService;

    /**
     * 查询销售报价单列表
     */
    @PreAuthorize("@ss.hasPermi('sales:quote:list')")
    @GetMapping("/list")
    public TableDataInfo list(ErpSalesQuote erpSalesQuote)
    {
        startPage();
        List<ErpSalesQuote> list = erpSalesQuoteService.selectErpSalesQuoteList(erpSalesQuote);
        return getDataTable(list);
    }

    /**
     * 导出销售报价单列表
     */
    @PreAuthorize("@ss.hasPermi('sales:quote:export')")
    @Log(title = "销售报价单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ErpSalesQuote erpSalesQuote)
    {
        List<ErpSalesQuote> list = erpSalesQuoteService.selectErpSalesQuoteList(erpSalesQuote);
        ExcelUtil<ErpSalesQuote> util = new ExcelUtil<ErpSalesQuote>(ErpSalesQuote.class);
        util.exportExcel(response, list, "销售报价单数据");
    }

    /**
     * 获取销售报价单详细信息
     */
    @PreAuthorize("@ss.hasPermi('sales:quote:query')")
    @GetMapping(value = "/{quoteId}")
    public AjaxResult getInfo(@PathVariable("quoteId") Long quoteId)
    {
        return success(erpSalesQuoteService.selectErpSalesQuoteByQuoteId(quoteId));
    }

    /**
     * 新增销售报价单
     */
    @PreAuthorize("@ss.hasPermi('sales:quote:add')")
    @Log(title = "销售报价单", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ErpSalesQuote erpSalesQuote)
    {
        erpSalesQuote.setCreateBy(getUsername());
        return toAjax(erpSalesQuoteService.insertErpSalesQuote(erpSalesQuote));
    }

    /**
     * 修改销售报价单
     */
    @PreAuthorize("@ss.hasPermi('sales:quote:edit')")
    @Log(title = "销售报价单", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ErpSalesQuote erpSalesQuote)
    {
        erpSalesQuote.setUpdateBy(getUsername());
        return toAjax(erpSalesQuoteService.updateErpSalesQuote(erpSalesQuote));
    }

    /**
     * 删除销售报价单
     */
    @PreAuthorize("@ss.hasPermi('sales:quote:remove')")
    @Log(title = "销售报价单", businessType = BusinessType.DELETE)
    @DeleteMapping("/{quoteIds}")
    public AjaxResult remove(@PathVariable Long[] quoteIds)
    {
        return toAjax(erpSalesQuoteService.deleteErpSalesQuoteByQuoteIds(quoteIds));
    }
}
