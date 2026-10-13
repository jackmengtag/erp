package com.ruoyi.manufacturing.controller;

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
import com.ruoyi.manufacturing.domain.ErpProductionOrder;
import com.ruoyi.manufacturing.service.IErpProductionOrderService;

/**
 * 生产工单Controller
 *
 * @author erp
 */
@RestController
@RequestMapping("/manufacturing/mo")
public class ErpProductionOrderController extends BaseController
{
    @Autowired
    private IErpProductionOrderService erpProductionOrderService;

    /**
     * 查询生产工单列表
     */
    @PreAuthorize("@ss.hasPermi('manufacturing:mo:list')")
    @GetMapping("/list")
    public TableDataInfo list(ErpProductionOrder erpProductionOrder)
    {
        startPage();
        List<ErpProductionOrder> list = erpProductionOrderService.selectErpProductionOrderList(erpProductionOrder);
        return getDataTable(list);
    }

    /**
     * 导出生产工单列表
     */
    @PreAuthorize("@ss.hasPermi('manufacturing:mo:export')")
    @Log(title = "生产工单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ErpProductionOrder erpProductionOrder)
    {
        List<ErpProductionOrder> list = erpProductionOrderService.selectErpProductionOrderList(erpProductionOrder);
        ExcelUtil<ErpProductionOrder> util = new ExcelUtil<ErpProductionOrder>(ErpProductionOrder.class);
        util.exportExcel(response, list, "生产工单数据");
    }

    /**
     * 获取生产工单详细信息
     */
    @PreAuthorize("@ss.hasPermi('manufacturing:mo:query')")
    @GetMapping(value = "/{moId}")
    public AjaxResult getInfo(@PathVariable("moId") Long moId)
    {
        return success(erpProductionOrderService.selectErpProductionOrderByMoId(moId));
    }

    /**
     * 新增生产工单
     */
    @PreAuthorize("@ss.hasPermi('manufacturing:mo:add')")
    @Log(title = "生产工单", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ErpProductionOrder erpProductionOrder)
    {
        erpProductionOrder.setCreateBy(getUsername());
        return toAjax(erpProductionOrderService.insertErpProductionOrder(erpProductionOrder));
    }

    /**
     * 修改生产工单
     */
    @PreAuthorize("@ss.hasPermi('manufacturing:mo:edit')")
    @Log(title = "生产工单", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ErpProductionOrder erpProductionOrder)
    {
        erpProductionOrder.setUpdateBy(getUsername());
        return toAjax(erpProductionOrderService.updateErpProductionOrder(erpProductionOrder));
    }

    /**
     * 删除生产工单
     */
    @PreAuthorize("@ss.hasPermi('manufacturing:mo:remove')")
    @Log(title = "生产工单", businessType = BusinessType.DELETE)
    @DeleteMapping("/{moIds}")
    public AjaxResult remove(@PathVariable Long[] moIds)
    {
        return toAjax(erpProductionOrderService.deleteErpProductionOrderByMoIds(moIds));
    }
}
