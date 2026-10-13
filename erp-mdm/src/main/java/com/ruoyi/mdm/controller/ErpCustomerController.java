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
import com.ruoyi.mdm.domain.ErpCustomer;
import com.ruoyi.mdm.service.IErpCustomerService;

/**
 * 客户Controller
 *
 * @author erp
 */
@RestController
@RequestMapping("/mdm/customer")
public class ErpCustomerController extends BaseController
{
    @Autowired
    private IErpCustomerService erpCustomerService;

    /**
     * 查询客户列表
     */
    @PreAuthorize("@ss.hasPermi('mdm:customer:list')")
    @GetMapping("/list")
    public TableDataInfo list(ErpCustomer erpCustomer)
    {
        startPage();
        List<ErpCustomer> list = erpCustomerService.selectErpCustomerList(erpCustomer);
        return getDataTable(list);
    }

    /**
     * 导出客户列表
     */
    @PreAuthorize("@ss.hasPermi('mdm:customer:export')")
    @Log(title = "客户", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ErpCustomer erpCustomer)
    {
        List<ErpCustomer> list = erpCustomerService.selectErpCustomerList(erpCustomer);
        ExcelUtil<ErpCustomer> util = new ExcelUtil<ErpCustomer>(ErpCustomer.class);
        util.exportExcel(response, list, "客户数据");
    }

    /**
     * 获取客户详细信息
     */
    @PreAuthorize("@ss.hasPermi('mdm:customer:query')")
    @GetMapping(value = "/{customerId}")
    public AjaxResult getInfo(@PathVariable("customerId") Long customerId)
    {
        return success(erpCustomerService.selectErpCustomerByCustomerId(customerId));
    }

    /**
     * 新增客户
     */
    @PreAuthorize("@ss.hasPermi('mdm:customer:add')")
    @Log(title = "客户", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ErpCustomer erpCustomer)
    {
        erpCustomer.setCreateBy(getUsername());
        return toAjax(erpCustomerService.insertErpCustomer(erpCustomer));
    }

    /**
     * 修改客户
     */
    @PreAuthorize("@ss.hasPermi('mdm:customer:edit')")
    @Log(title = "客户", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ErpCustomer erpCustomer)
    {
        erpCustomer.setUpdateBy(getUsername());
        return toAjax(erpCustomerService.updateErpCustomer(erpCustomer));
    }

    /**
     * 删除客户
     */
    @PreAuthorize("@ss.hasPermi('mdm:customer:remove')")
    @Log(title = "客户", businessType = BusinessType.DELETE)
    @DeleteMapping("/{customerIds}")
    public AjaxResult remove(@PathVariable Long[] customerIds)
    {
        return toAjax(erpCustomerService.deleteErpCustomerByCustomerIds(customerIds));
    }
}
