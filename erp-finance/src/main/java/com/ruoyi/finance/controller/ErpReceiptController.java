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
import com.ruoyi.finance.domain.ErpReceipt;
import com.ruoyi.finance.service.IErpReceiptService;

/**
 * 收款单Controller
 *
 * @author erp
 */
@RestController
@RequestMapping("/finance/receipt")
public class ErpReceiptController extends BaseController
{
    @Autowired
    private IErpReceiptService erpReceiptService;

    /**
     * 查询收款单列表
     */
    @PreAuthorize("@ss.hasPermi('finance:receipt:list')")
    @GetMapping("/list")
    public TableDataInfo list(ErpReceipt erpReceipt)
    {
        startPage();
        List<ErpReceipt> list = erpReceiptService.selectErpReceiptList(erpReceipt);
        return getDataTable(list);
    }

    /**
     * 导出收款单列表
     */
    @PreAuthorize("@ss.hasPermi('finance:receipt:export')")
    @Log(title = "收款单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ErpReceipt erpReceipt)
    {
        List<ErpReceipt> list = erpReceiptService.selectErpReceiptList(erpReceipt);
        ExcelUtil<ErpReceipt> util = new ExcelUtil<ErpReceipt>(ErpReceipt.class);
        util.exportExcel(response, list, "收款单数据");
    }

    /**
     * 获取收款单详细信息
     */
    @PreAuthorize("@ss.hasPermi('finance:receipt:query')")
    @GetMapping(value = "/{receiptId}")
    public AjaxResult getInfo(@PathVariable("receiptId") Long receiptId)
    {
        return success(erpReceiptService.selectErpReceiptByReceiptId(receiptId));
    }

    /**
     * 新增收款单
     */
    @PreAuthorize("@ss.hasPermi('finance:receipt:add')")
    @Log(title = "收款单", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ErpReceipt erpReceipt)
    {
        erpReceipt.setCreateBy(getUsername());
        return toAjax(erpReceiptService.insertErpReceipt(erpReceipt));
    }

    /**
     * 修改收款单
     */
    @PreAuthorize("@ss.hasPermi('finance:receipt:edit')")
    @Log(title = "收款单", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ErpReceipt erpReceipt)
    {
        erpReceipt.setUpdateBy(getUsername());
        return toAjax(erpReceiptService.updateErpReceipt(erpReceipt));
    }

    /**
     * 删除收款单
     */
    @PreAuthorize("@ss.hasPermi('finance:receipt:remove')")
    @Log(title = "收款单", businessType = BusinessType.DELETE)
    @DeleteMapping("/{receiptIds}")
    public AjaxResult remove(@PathVariable Long[] receiptIds)
    {
        return toAjax(erpReceiptService.deleteErpReceiptByReceiptIds(receiptIds));
    }
}
