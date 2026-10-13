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
import com.ruoyi.purchase.domain.ErpPurchaseReq;
import com.ruoyi.purchase.service.IErpPurchaseReqService;

/**
 * 采购申请Controller
 *
 * @author erp
 */
@RestController
@RequestMapping("/purchase/req")
public class ErpPurchaseReqController extends BaseController
{
    @Autowired
    private IErpPurchaseReqService erpPurchaseReqService;

    /**
     * 查询采购申请列表
     */
    @PreAuthorize("@ss.hasPermi('purchase:req:list')")
    @GetMapping("/list")
    public TableDataInfo list(ErpPurchaseReq erpPurchaseReq)
    {
        startPage();
        List<ErpPurchaseReq> list = erpPurchaseReqService.selectErpPurchaseReqList(erpPurchaseReq);
        return getDataTable(list);
    }

    /**
     * 导出采购申请列表
     */
    @PreAuthorize("@ss.hasPermi('purchase:req:export')")
    @Log(title = "采购申请", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ErpPurchaseReq erpPurchaseReq)
    {
        List<ErpPurchaseReq> list = erpPurchaseReqService.selectErpPurchaseReqList(erpPurchaseReq);
        ExcelUtil<ErpPurchaseReq> util = new ExcelUtil<ErpPurchaseReq>(ErpPurchaseReq.class);
        util.exportExcel(response, list, "采购申请数据");
    }

    /**
     * 获取采购申请详细信息
     */
    @PreAuthorize("@ss.hasPermi('purchase:req:query')")
    @GetMapping(value = "/{reqId}")
    public AjaxResult getInfo(@PathVariable("reqId") Long reqId)
    {
        return success(erpPurchaseReqService.selectErpPurchaseReqByReqId(reqId));
    }

    /**
     * 新增采购申请
     */
    @PreAuthorize("@ss.hasPermi('purchase:req:add')")
    @Log(title = "采购申请", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ErpPurchaseReq erpPurchaseReq)
    {
        erpPurchaseReq.setCreateBy(getUsername());
        return toAjax(erpPurchaseReqService.insertErpPurchaseReq(erpPurchaseReq));
    }

    /**
     * 修改采购申请
     */
    @PreAuthorize("@ss.hasPermi('purchase:req:edit')")
    @Log(title = "采购申请", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ErpPurchaseReq erpPurchaseReq)
    {
        erpPurchaseReq.setUpdateBy(getUsername());
        return toAjax(erpPurchaseReqService.updateErpPurchaseReq(erpPurchaseReq));
    }

    /**
     * 删除采购申请
     */
    @PreAuthorize("@ss.hasPermi('purchase:req:remove')")
    @Log(title = "采购申请", businessType = BusinessType.DELETE)
    @DeleteMapping("/{reqIds}")
    public AjaxResult remove(@PathVariable Long[] reqIds)
    {
        return toAjax(erpPurchaseReqService.deleteErpPurchaseReqByReqIds(reqIds));
    }
}
