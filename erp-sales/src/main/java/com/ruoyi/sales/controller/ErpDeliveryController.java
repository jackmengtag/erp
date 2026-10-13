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
import com.ruoyi.sales.domain.ErpDelivery;
import com.ruoyi.sales.service.IErpDeliveryService;

/**
 * 销售发货单Controller
 *
 * @author erp
 */
@RestController
@RequestMapping("/sales/delivery")
public class ErpDeliveryController extends BaseController
{
    @Autowired
    private IErpDeliveryService erpDeliveryService;

    /**
     * 查询销售发货单列表
     */
    @PreAuthorize("@ss.hasPermi('sales:delivery:list')")
    @GetMapping("/list")
    public TableDataInfo list(ErpDelivery erpDelivery)
    {
        startPage();
        List<ErpDelivery> list = erpDeliveryService.selectErpDeliveryList(erpDelivery);
        return getDataTable(list);
    }

    /**
     * 导出销售发货单列表
     */
    @PreAuthorize("@ss.hasPermi('sales:delivery:export')")
    @Log(title = "销售发货单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ErpDelivery erpDelivery)
    {
        List<ErpDelivery> list = erpDeliveryService.selectErpDeliveryList(erpDelivery);
        ExcelUtil<ErpDelivery> util = new ExcelUtil<ErpDelivery>(ErpDelivery.class);
        util.exportExcel(response, list, "销售发货单数据");
    }

    /**
     * 获取销售发货单详细信息
     */
    @PreAuthorize("@ss.hasPermi('sales:delivery:query')")
    @GetMapping(value = "/{deliveryId}")
    public AjaxResult getInfo(@PathVariable("deliveryId") Long deliveryId)
    {
        return success(erpDeliveryService.selectErpDeliveryByDeliveryId(deliveryId));
    }

    /**
     * 新增销售发货单
     */
    @PreAuthorize("@ss.hasPermi('sales:delivery:add')")
    @Log(title = "销售发货单", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ErpDelivery erpDelivery)
    {
        erpDelivery.setCreateBy(getUsername());
        return toAjax(erpDeliveryService.insertErpDelivery(erpDelivery));
    }

    /**
     * 修改销售发货单
     */
    @PreAuthorize("@ss.hasPermi('sales:delivery:edit')")
    @Log(title = "销售发货单", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ErpDelivery erpDelivery)
    {
        erpDelivery.setUpdateBy(getUsername());
        return toAjax(erpDeliveryService.updateErpDelivery(erpDelivery));
    }

    /**
     * 删除销售发货单
     */
    @PreAuthorize("@ss.hasPermi('sales:delivery:remove')")
    @Log(title = "销售发货单", businessType = BusinessType.DELETE)
    @DeleteMapping("/{deliveryIds}")
    public AjaxResult remove(@PathVariable Long[] deliveryIds)
    {
        return toAjax(erpDeliveryService.deleteErpDeliveryByDeliveryIds(deliveryIds));
    }
}
