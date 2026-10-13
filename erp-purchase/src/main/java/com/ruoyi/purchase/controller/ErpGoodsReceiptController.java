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
import com.ruoyi.purchase.domain.ErpGoodsReceipt;
import com.ruoyi.purchase.service.IErpGoodsReceiptService;

/**
 * 收货单Controller
 *
 * @author erp
 */
@RestController
@RequestMapping("/purchase/receipt")
public class ErpGoodsReceiptController extends BaseController
{
    @Autowired
    private IErpGoodsReceiptService erpGoodsReceiptService;

    /**
     * 查询收货单列表
     */
    @PreAuthorize("@ss.hasPermi('purchase:receipt:list')")
    @GetMapping("/list")
    public TableDataInfo list(ErpGoodsReceipt erpGoodsReceipt)
    {
        startPage();
        List<ErpGoodsReceipt> list = erpGoodsReceiptService.selectErpGoodsReceiptList(erpGoodsReceipt);
        return getDataTable(list);
    }

    /**
     * 导出收货单列表
     */
    @PreAuthorize("@ss.hasPermi('purchase:receipt:export')")
    @Log(title = "收货单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ErpGoodsReceipt erpGoodsReceipt)
    {
        List<ErpGoodsReceipt> list = erpGoodsReceiptService.selectErpGoodsReceiptList(erpGoodsReceipt);
        ExcelUtil<ErpGoodsReceipt> util = new ExcelUtil<ErpGoodsReceipt>(ErpGoodsReceipt.class);
        util.exportExcel(response, list, "收货单数据");
    }

    /**
     * 获取收货单详细信息
     */
    @PreAuthorize("@ss.hasPermi('purchase:receipt:query')")
    @GetMapping(value = "/{receiptId}")
    public AjaxResult getInfo(@PathVariable("receiptId") Long receiptId)
    {
        return success(erpGoodsReceiptService.selectErpGoodsReceiptByReceiptId(receiptId));
    }

    /**
     * 新增收货单
     */
    @PreAuthorize("@ss.hasPermi('purchase:receipt:add')")
    @Log(title = "收货单", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ErpGoodsReceipt erpGoodsReceipt)
    {
        erpGoodsReceipt.setCreateBy(getUsername());
        return toAjax(erpGoodsReceiptService.insertErpGoodsReceipt(erpGoodsReceipt));
    }

    /**
     * 修改收货单
     */
    @PreAuthorize("@ss.hasPermi('purchase:receipt:edit')")
    @Log(title = "收货单", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ErpGoodsReceipt erpGoodsReceipt)
    {
        erpGoodsReceipt.setUpdateBy(getUsername());
        return toAjax(erpGoodsReceiptService.updateErpGoodsReceipt(erpGoodsReceipt));
    }

    /**
     * 删除收货单
     */
    @PreAuthorize("@ss.hasPermi('purchase:receipt:remove')")
    @Log(title = "收货单", businessType = BusinessType.DELETE)
    @DeleteMapping("/{receiptIds}")
    public AjaxResult remove(@PathVariable Long[] receiptIds)
    {
        return toAjax(erpGoodsReceiptService.deleteErpGoodsReceiptByReceiptIds(receiptIds));
    }
}
