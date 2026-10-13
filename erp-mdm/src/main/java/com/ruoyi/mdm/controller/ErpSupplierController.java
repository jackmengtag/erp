package com.ruoyi.mdm.controller;

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
import com.ruoyi.mdm.domain.ErpSupplier;
import com.ruoyi.mdm.service.IErpSupplierService;

/**
 * 供应商Controller
 *
 * @author erp
 */
@RestController
@RequestMapping("/mdm/supplier")
public class ErpSupplierController extends BaseController
{
    @Autowired
    private IErpSupplierService erpSupplierService;

    /**
     * 查询供应商列表
     */
    @PreAuthorize("@ss.hasPermi('mdm:supplier:list')")
    @GetMapping("/list")
    public TableDataInfo list(ErpSupplier erpSupplier)
    {
        startPage();
        List<ErpSupplier> list = erpSupplierService.selectErpSupplierList(erpSupplier);
        return getDataTable(list);
    }

    /**
     * 导出供应商列表
     */
    @PreAuthorize("@ss.hasPermi('mdm:supplier:export')")
    @Log(title = "供应商", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ErpSupplier erpSupplier)
    {
        List<ErpSupplier> list = erpSupplierService.selectErpSupplierList(erpSupplier);
        ExcelUtil<ErpSupplier> util = new ExcelUtil<ErpSupplier>(ErpSupplier.class);
        util.exportExcel(response, list, "供应商数据");
    }

    /**
     * 获取供应商详细信息
     */
    @PreAuthorize("@ss.hasPermi('mdm:supplier:query')")
    @GetMapping(value = "/{supplierId}")
    public AjaxResult getInfo(@PathVariable("supplierId") Long supplierId)
    {
        return success(erpSupplierService.selectErpSupplierBySupplierId(supplierId));
    }

    /**
     * 新增供应商
     */
    @PreAuthorize("@ss.hasPermi('mdm:supplier:add')")
    @Log(title = "供应商", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ErpSupplier erpSupplier)
    {
        erpSupplier.setCreateBy(getUsername());
        return toAjax(erpSupplierService.insertErpSupplier(erpSupplier));
    }

    /**
     * 修改供应商
     */
    @PreAuthorize("@ss.hasPermi('mdm:supplier:edit')")
    @Log(title = "供应商", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ErpSupplier erpSupplier)
    {
        erpSupplier.setUpdateBy(getUsername());
        return toAjax(erpSupplierService.updateErpSupplier(erpSupplier));
    }

    /**
     * 删除供应商
     */
    @PreAuthorize("@ss.hasPermi('mdm:supplier:remove')")
    @Log(title = "供应商", businessType = BusinessType.DELETE)
    @DeleteMapping("/{supplierIds}")
    public AjaxResult remove(@PathVariable Long[] supplierIds)
    {
        return toAjax(erpSupplierService.deleteErpSupplierBySupplierIds(supplierIds));
    }
}
