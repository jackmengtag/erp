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
import com.ruoyi.finance.domain.ErpPayment;
import com.ruoyi.finance.service.IErpPaymentService;

/**
 * 付款单Controller
 *
 * @author erp
 */
@RestController
@RequestMapping("/finance/payment")
public class ErpPaymentController extends BaseController
{
    @Autowired
    private IErpPaymentService erpPaymentService;

    /**
     * 查询付款单列表
     */
    @PreAuthorize("@ss.hasPermi('finance:payment:list')")
    @GetMapping("/list")
    public TableDataInfo list(ErpPayment erpPayment)
    {
        startPage();
        List<ErpPayment> list = erpPaymentService.selectErpPaymentList(erpPayment);
        return getDataTable(list);
    }

    /**
     * 导出付款单列表
     */
    @PreAuthorize("@ss.hasPermi('finance:payment:export')")
    @Log(title = "付款单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ErpPayment erpPayment)
    {
        List<ErpPayment> list = erpPaymentService.selectErpPaymentList(erpPayment);
        ExcelUtil<ErpPayment> util = new ExcelUtil<ErpPayment>(ErpPayment.class);
        util.exportExcel(response, list, "付款单数据");
    }

    /**
     * 获取付款单详细信息
     */
    @PreAuthorize("@ss.hasPermi('finance:payment:query')")
    @GetMapping(value = "/{paymentId}")
    public AjaxResult getInfo(@PathVariable("paymentId") Long paymentId)
    {
        return success(erpPaymentService.selectErpPaymentByPaymentId(paymentId));
    }

    /**
     * 新增付款单
     */
    @PreAuthorize("@ss.hasPermi('finance:payment:add')")
    @Log(title = "付款单", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ErpPayment erpPayment)
    {
        erpPayment.setCreateBy(getUsername());
        return toAjax(erpPaymentService.insertErpPayment(erpPayment));
    }

    /**
     * 修改付款单
     */
    @PreAuthorize("@ss.hasPermi('finance:payment:edit')")
    @Log(title = "付款单", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ErpPayment erpPayment)
    {
        erpPayment.setUpdateBy(getUsername());
        return toAjax(erpPaymentService.updateErpPayment(erpPayment));
    }

    /**
     * 删除付款单
     */
    @PreAuthorize("@ss.hasPermi('finance:payment:remove')")
    @Log(title = "付款单", businessType = BusinessType.DELETE)
    @DeleteMapping("/{paymentIds}")
    public AjaxResult remove(@PathVariable Long[] paymentIds)
    {
        return toAjax(erpPaymentService.deleteErpPaymentByPaymentIds(paymentIds));
    }
}
