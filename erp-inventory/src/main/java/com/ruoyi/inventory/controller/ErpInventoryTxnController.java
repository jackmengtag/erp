package com.ruoyi.inventory.controller;

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
import com.ruoyi.inventory.domain.ErpInventoryTxn;
import com.ruoyi.inventory.service.IErpInventoryTxnService;

/**
 * 库存流水Controller
 *
 * @author erp
 */
@RestController
@RequestMapping("/inventory/txn")
public class ErpInventoryTxnController extends BaseController
{
    @Autowired
    private IErpInventoryTxnService erpInventoryTxnService;

    /**
     * 查询库存流水列表
     */
    @PreAuthorize("@ss.hasPermi('inventory:txn:list')")
    @GetMapping("/list")
    public TableDataInfo list(ErpInventoryTxn erpInventoryTxn)
    {
        startPage();
        List<ErpInventoryTxn> list = erpInventoryTxnService.selectErpInventoryTxnList(erpInventoryTxn);
        return getDataTable(list);
    }

    /**
     * 导出库存流水列表
     */
    @PreAuthorize("@ss.hasPermi('inventory:txn:export')")
    @Log(title = "库存流水", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ErpInventoryTxn erpInventoryTxn)
    {
        List<ErpInventoryTxn> list = erpInventoryTxnService.selectErpInventoryTxnList(erpInventoryTxn);
        ExcelUtil<ErpInventoryTxn> util = new ExcelUtil<ErpInventoryTxn>(ErpInventoryTxn.class);
        util.exportExcel(response, list, "库存流水数据");
    }

    /**
     * 获取库存流水详细信息
     */
    @PreAuthorize("@ss.hasPermi('inventory:txn:query')")
    @GetMapping(value = "/{txnId}")
    public AjaxResult getInfo(@PathVariable("txnId") Long txnId)
    {
        return success(erpInventoryTxnService.selectErpInventoryTxnByTxnId(txnId));
    }

    /**
     * 新增库存流水
     */
    @PreAuthorize("@ss.hasPermi('inventory:txn:add')")
    @Log(title = "库存流水", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ErpInventoryTxn erpInventoryTxn)
    {
        erpInventoryTxn.setCreateBy(getUsername());
        return toAjax(erpInventoryTxnService.insertErpInventoryTxn(erpInventoryTxn));
    }

    /**
     * 修改库存流水
     */
    @PreAuthorize("@ss.hasPermi('inventory:txn:edit')")
    @Log(title = "库存流水", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ErpInventoryTxn erpInventoryTxn)
    {
        erpInventoryTxn.setUpdateBy(getUsername());
        return toAjax(erpInventoryTxnService.updateErpInventoryTxn(erpInventoryTxn));
    }

    /**
     * 删除库存流水
     */
    @PreAuthorize("@ss.hasPermi('inventory:txn:remove')")
    @Log(title = "库存流水", businessType = BusinessType.DELETE)
    @DeleteMapping("/{txnIds}")
    public AjaxResult remove(@PathVariable Long[] txnIds)
    {
        return toAjax(erpInventoryTxnService.deleteErpInventoryTxnByTxnIds(txnIds));
    }
}
