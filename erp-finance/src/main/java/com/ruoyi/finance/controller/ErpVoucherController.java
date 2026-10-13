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
import com.ruoyi.finance.domain.ErpVoucher;
import com.ruoyi.finance.service.IErpVoucherService;

/**
 * 财务凭证接口Controller
 *
 * @author erp
 */
@RestController
@RequestMapping("/finance/voucher")
public class ErpVoucherController extends BaseController
{
    @Autowired
    private IErpVoucherService erpVoucherService;

    /**
     * 查询财务凭证接口列表
     */
    @PreAuthorize("@ss.hasPermi('finance:voucher:list')")
    @GetMapping("/list")
    public TableDataInfo list(ErpVoucher erpVoucher)
    {
        startPage();
        List<ErpVoucher> list = erpVoucherService.selectErpVoucherList(erpVoucher);
        return getDataTable(list);
    }

    /**
     * 导出财务凭证接口列表
     */
    @PreAuthorize("@ss.hasPermi('finance:voucher:export')")
    @Log(title = "财务凭证接口", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ErpVoucher erpVoucher)
    {
        List<ErpVoucher> list = erpVoucherService.selectErpVoucherList(erpVoucher);
        ExcelUtil<ErpVoucher> util = new ExcelUtil<ErpVoucher>(ErpVoucher.class);
        util.exportExcel(response, list, "财务凭证接口数据");
    }

    /**
     * 获取财务凭证接口详细信息
     */
    @PreAuthorize("@ss.hasPermi('finance:voucher:query')")
    @GetMapping(value = "/{voucherId}")
    public AjaxResult getInfo(@PathVariable("voucherId") Long voucherId)
    {
        return success(erpVoucherService.selectErpVoucherByVoucherId(voucherId));
    }

    /**
     * 新增财务凭证接口
     */
    @PreAuthorize("@ss.hasPermi('finance:voucher:add')")
    @Log(title = "财务凭证接口", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ErpVoucher erpVoucher)
    {
        erpVoucher.setCreateBy(getUsername());
        return toAjax(erpVoucherService.insertErpVoucher(erpVoucher));
    }

    /**
     * 修改财务凭证接口
     */
    @PreAuthorize("@ss.hasPermi('finance:voucher:edit')")
    @Log(title = "财务凭证接口", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ErpVoucher erpVoucher)
    {
        erpVoucher.setUpdateBy(getUsername());
        return toAjax(erpVoucherService.updateErpVoucher(erpVoucher));
    }

    /**
     * 删除财务凭证接口
     */
    @PreAuthorize("@ss.hasPermi('finance:voucher:remove')")
    @Log(title = "财务凭证接口", businessType = BusinessType.DELETE)
    @DeleteMapping("/{voucherIds}")
    public AjaxResult remove(@PathVariable Long[] voucherIds)
    {
        return toAjax(erpVoucherService.deleteErpVoucherByVoucherIds(voucherIds));
    }
}
