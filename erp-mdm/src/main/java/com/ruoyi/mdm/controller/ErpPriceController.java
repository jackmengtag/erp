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
import com.ruoyi.mdm.domain.ErpPrice;
import com.ruoyi.mdm.service.IErpPriceService;

/**
 * 价格条件Controller
 *
 * @author erp
 */
@RestController
@RequestMapping("/mdm/price")
public class ErpPriceController extends BaseController
{
    @Autowired
    private IErpPriceService erpPriceService;

    /**
     * 查询价格条件列表
     */
    @PreAuthorize("@ss.hasPermi('mdm:price:list')")
    @GetMapping("/list")
    public TableDataInfo list(ErpPrice erpPrice)
    {
        startPage();
        List<ErpPrice> list = erpPriceService.selectErpPriceList(erpPrice);
        return getDataTable(list);
    }

    /**
     * 导出价格条件列表
     */
    @PreAuthorize("@ss.hasPermi('mdm:price:export')")
    @Log(title = "价格条件", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ErpPrice erpPrice)
    {
        List<ErpPrice> list = erpPriceService.selectErpPriceList(erpPrice);
        ExcelUtil<ErpPrice> util = new ExcelUtil<ErpPrice>(ErpPrice.class);
        util.exportExcel(response, list, "价格条件数据");
    }

    /**
     * 获取价格条件详细信息
     */
    @PreAuthorize("@ss.hasPermi('mdm:price:query')")
    @GetMapping(value = "/{priceId}")
    public AjaxResult getInfo(@PathVariable("priceId") Long priceId)
    {
        return success(erpPriceService.selectErpPriceByPriceId(priceId));
    }

    /**
     * 新增价格条件
     */
    @PreAuthorize("@ss.hasPermi('mdm:price:add')")
    @Log(title = "价格条件", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ErpPrice erpPrice)
    {
        erpPrice.setCreateBy(getUsername());
        return toAjax(erpPriceService.insertErpPrice(erpPrice));
    }

    /**
     * 修改价格条件
     */
    @PreAuthorize("@ss.hasPermi('mdm:price:edit')")
    @Log(title = "价格条件", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ErpPrice erpPrice)
    {
        erpPrice.setUpdateBy(getUsername());
        return toAjax(erpPriceService.updateErpPrice(erpPrice));
    }

    /**
     * 删除价格条件
     */
    @PreAuthorize("@ss.hasPermi('mdm:price:remove')")
    @Log(title = "价格条件", businessType = BusinessType.DELETE)
    @DeleteMapping("/{priceIds}")
    public AjaxResult remove(@PathVariable Long[] priceIds)
    {
        return toAjax(erpPriceService.deleteErpPriceByPriceIds(priceIds));
    }
}
