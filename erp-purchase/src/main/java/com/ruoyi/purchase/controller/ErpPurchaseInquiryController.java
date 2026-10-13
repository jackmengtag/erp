package com.ruoyi.purchase.controller;

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
import com.ruoyi.purchase.domain.ErpPurchaseInquiry;
import com.ruoyi.purchase.service.IErpPurchaseInquiryService;

/**
 * 采购询价Controller
 *
 * @author erp
 */
@RestController
@RequestMapping("/purchase/inquiry")
public class ErpPurchaseInquiryController extends BaseController
{
    @Autowired
    private IErpPurchaseInquiryService erpPurchaseInquiryService;

    /**
     * 查询采购询价列表
     */
    @PreAuthorize("@ss.hasPermi('purchase:inquiry:list')")
    @GetMapping("/list")
    public TableDataInfo list(ErpPurchaseInquiry erpPurchaseInquiry)
    {
        startPage();
        List<ErpPurchaseInquiry> list = erpPurchaseInquiryService.selectErpPurchaseInquiryList(erpPurchaseInquiry);
        return getDataTable(list);
    }

    /**
     * 导出采购询价列表
     */
    @PreAuthorize("@ss.hasPermi('purchase:inquiry:export')")
    @Log(title = "采购询价", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ErpPurchaseInquiry erpPurchaseInquiry)
    {
        List<ErpPurchaseInquiry> list = erpPurchaseInquiryService.selectErpPurchaseInquiryList(erpPurchaseInquiry);
        ExcelUtil<ErpPurchaseInquiry> util = new ExcelUtil<ErpPurchaseInquiry>(ErpPurchaseInquiry.class);
        util.exportExcel(response, list, "采购询价数据");
    }

    /**
     * 获取采购询价详细信息
     */
    @PreAuthorize("@ss.hasPermi('purchase:inquiry:query')")
    @GetMapping(value = "/{inquiryId}")
    public AjaxResult getInfo(@PathVariable("inquiryId") Long inquiryId)
    {
        return success(erpPurchaseInquiryService.selectErpPurchaseInquiryByInquiryId(inquiryId));
    }

    /**
     * 新增采购询价
     */
    @PreAuthorize("@ss.hasPermi('purchase:inquiry:add')")
    @Log(title = "采购询价", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ErpPurchaseInquiry erpPurchaseInquiry)
    {
        erpPurchaseInquiry.setCreateBy(getUsername());
        return toAjax(erpPurchaseInquiryService.insertErpPurchaseInquiry(erpPurchaseInquiry));
    }

    /**
     * 修改采购询价
     */
    @PreAuthorize("@ss.hasPermi('purchase:inquiry:edit')")
    @Log(title = "采购询价", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ErpPurchaseInquiry erpPurchaseInquiry)
    {
        erpPurchaseInquiry.setUpdateBy(getUsername());
        return toAjax(erpPurchaseInquiryService.updateErpPurchaseInquiry(erpPurchaseInquiry));
    }

    /**
     * 删除采购询价
     */
    @PreAuthorize("@ss.hasPermi('purchase:inquiry:remove')")
    @Log(title = "采购询价", businessType = BusinessType.DELETE)
    @DeleteMapping("/{inquiryIds}")
    public AjaxResult remove(@PathVariable Long[] inquiryIds)
    {
        return toAjax(erpPurchaseInquiryService.deleteErpPurchaseInquiryByInquiryIds(inquiryIds));
    }
}
