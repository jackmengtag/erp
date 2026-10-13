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
import com.ruoyi.purchase.domain.ErpPurchaseOrder;
import com.ruoyi.purchase.service.IErpPurchaseOrderService;

/**
 * 采购订单Controller
 *
 * @author erp
 */
@RestController
@RequestMapping("/purchase/order")
public class ErpPurchaseOrderController extends BaseController
{
    @Autowired
    private IErpPurchaseOrderService erpPurchaseOrderService;

    /**
     * 查询采购订单列表
     */
    @PreAuthorize("@ss.hasPermi('purchase:order:list')")
    @GetMapping("/list")
    public TableDataInfo list(ErpPurchaseOrder erpPurchaseOrder)
    {
        startPage();
        List<ErpPurchaseOrder> list = erpPurchaseOrderService.selectErpPurchaseOrderList(erpPurchaseOrder);
        return getDataTable(list);
    }

    /**
     * 导出采购订单列表
     */
    @PreAuthorize("@ss.hasPermi('purchase:order:export')")
    @Log(title = "采购订单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ErpPurchaseOrder erpPurchaseOrder)
    {
        List<ErpPurchaseOrder> list = erpPurchaseOrderService.selectErpPurchaseOrderList(erpPurchaseOrder);
        ExcelUtil<ErpPurchaseOrder> util = new ExcelUtil<ErpPurchaseOrder>(ErpPurchaseOrder.class);
        util.exportExcel(response, list, "采购订单数据");
    }

    /**
     * 获取采购订单详细信息
     */
    @PreAuthorize("@ss.hasPermi('purchase:order:query')")
    @GetMapping(value = "/{poId}")
    public AjaxResult getInfo(@PathVariable("poId") Long poId)
    {
        return success(erpPurchaseOrderService.selectErpPurchaseOrderByPoId(poId));
    }

    /**
     * 新增采购订单
     */
    @PreAuthorize("@ss.hasPermi('purchase:order:add')")
    @Log(title = "采购订单", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ErpPurchaseOrder erpPurchaseOrder)
    {
        erpPurchaseOrder.setCreateBy(getUsername());
        return toAjax(erpPurchaseOrderService.insertErpPurchaseOrder(erpPurchaseOrder));
    }

    /**
     * 修改采购订单
     */
    @PreAuthorize("@ss.hasPermi('purchase:order:edit')")
    @Log(title = "采购订单", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ErpPurchaseOrder erpPurchaseOrder)
    {
        erpPurchaseOrder.setUpdateBy(getUsername());
        return toAjax(erpPurchaseOrderService.updateErpPurchaseOrder(erpPurchaseOrder));
    }

    /**
     * 删除采购订单
     */
    @PreAuthorize("@ss.hasPermi('purchase:order:remove')")
    @Log(title = "采购订单", businessType = BusinessType.DELETE)
    @DeleteMapping("/{poIds}")
    public AjaxResult remove(@PathVariable Long[] poIds)
    {
        return toAjax(erpPurchaseOrderService.deleteErpPurchaseOrderByPoIds(poIds));
    }
}
