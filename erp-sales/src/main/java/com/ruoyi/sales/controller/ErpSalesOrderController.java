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
import com.ruoyi.sales.domain.ErpSalesOrder;
import com.ruoyi.sales.service.IErpSalesOrderService;

/**
 * 销售订单Controller
 *
 * @author erp
 */
@RestController
@RequestMapping("/sales/order")
public class ErpSalesOrderController extends BaseController
{
    @Autowired
    private IErpSalesOrderService erpSalesOrderService;

    /**
     * 查询销售订单列表
     */
    @PreAuthorize("@ss.hasPermi('sales:order:list')")
    @GetMapping("/list")
    public TableDataInfo list(ErpSalesOrder erpSalesOrder)
    {
        startPage();
        List<ErpSalesOrder> list = erpSalesOrderService.selectErpSalesOrderList(erpSalesOrder);
        return getDataTable(list);
    }

    /**
     * 导出销售订单列表
     */
    @PreAuthorize("@ss.hasPermi('sales:order:export')")
    @Log(title = "销售订单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ErpSalesOrder erpSalesOrder)
    {
        List<ErpSalesOrder> list = erpSalesOrderService.selectErpSalesOrderList(erpSalesOrder);
        ExcelUtil<ErpSalesOrder> util = new ExcelUtil<ErpSalesOrder>(ErpSalesOrder.class);
        util.exportExcel(response, list, "销售订单数据");
    }

    /**
     * 获取销售订单详细信息
     */
    @PreAuthorize("@ss.hasPermi('sales:order:query')")
    @GetMapping(value = "/{orderId}")
    public AjaxResult getInfo(@PathVariable("orderId") Long orderId)
    {
        return success(erpSalesOrderService.selectErpSalesOrderByOrderId(orderId));
    }

    /**
     * 新增销售订单
     */
    @PreAuthorize("@ss.hasPermi('sales:order:add')")
    @Log(title = "销售订单", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ErpSalesOrder erpSalesOrder)
    {
        erpSalesOrder.setCreateBy(getUsername());
        return toAjax(erpSalesOrderService.insertErpSalesOrder(erpSalesOrder));
    }

    /**
     * 修改销售订单
     */
    @PreAuthorize("@ss.hasPermi('sales:order:edit')")
    @Log(title = "销售订单", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ErpSalesOrder erpSalesOrder)
    {
        erpSalesOrder.setUpdateBy(getUsername());
        return toAjax(erpSalesOrderService.updateErpSalesOrder(erpSalesOrder));
    }

    /**
     * 删除销售订单
     */
    @PreAuthorize("@ss.hasPermi('sales:order:remove')")
    @Log(title = "销售订单", businessType = BusinessType.DELETE)
    @DeleteMapping("/{orderIds}")
    public AjaxResult remove(@PathVariable Long[] orderIds)
    {
        return toAjax(erpSalesOrderService.deleteErpSalesOrderByOrderIds(orderIds));
    }
}
