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
import com.ruoyi.sales.domain.ErpSalesReturn;
import com.ruoyi.sales.service.IErpSalesReturnService;

/**
 * 销售退货单Controller
 *
 * @author erp
 */
@RestController
@RequestMapping("/sales/return")
public class ErpSalesReturnController extends BaseController
{
    @Autowired
    private IErpSalesReturnService erpSalesReturnService;

    /**
     * 查询销售退货单列表
     */
    @PreAuthorize("@ss.hasPermi('sales:return:list')")
    @GetMapping("/list")
    public TableDataInfo list(ErpSalesReturn erpSalesReturn)
    {
        startPage();
        List<ErpSalesReturn> list = erpSalesReturnService.selectErpSalesReturnList(erpSalesReturn);
        return getDataTable(list);
    }

    /**
     * 导出销售退货单列表
     */
    @PreAuthorize("@ss.hasPermi('sales:return:export')")
    @Log(title = "销售退货单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ErpSalesReturn erpSalesReturn)
    {
        List<ErpSalesReturn> list = erpSalesReturnService.selectErpSalesReturnList(erpSalesReturn);
        ExcelUtil<ErpSalesReturn> util = new ExcelUtil<ErpSalesReturn>(ErpSalesReturn.class);
        util.exportExcel(response, list, "销售退货单数据");
    }

    /**
     * 获取销售退货单详细信息
     */
    @PreAuthorize("@ss.hasPermi('sales:return:query')")
    @GetMapping(value = "/{returnId}")
    public AjaxResult getInfo(@PathVariable("returnId") Long returnId)
    {
        return success(erpSalesReturnService.selectErpSalesReturnByReturnId(returnId));
    }

    /**
     * 新增销售退货单
     */
    @PreAuthorize("@ss.hasPermi('sales:return:add')")
    @Log(title = "销售退货单", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ErpSalesReturn erpSalesReturn)
    {
        erpSalesReturn.setCreateBy(getUsername());
        return toAjax(erpSalesReturnService.insertErpSalesReturn(erpSalesReturn));
    }

    /**
     * 修改销售退货单
     */
    @PreAuthorize("@ss.hasPermi('sales:return:edit')")
    @Log(title = "销售退货单", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ErpSalesReturn erpSalesReturn)
    {
        erpSalesReturn.setUpdateBy(getUsername());
        return toAjax(erpSalesReturnService.updateErpSalesReturn(erpSalesReturn));
    }

    /**
     * 删除销售退货单
     */
    @PreAuthorize("@ss.hasPermi('sales:return:remove')")
    @Log(title = "销售退货单", businessType = BusinessType.DELETE)
    @DeleteMapping("/{returnIds}")
    public AjaxResult remove(@PathVariable Long[] returnIds)
    {
        return toAjax(erpSalesReturnService.deleteErpSalesReturnByReturnIds(returnIds));
    }
}
