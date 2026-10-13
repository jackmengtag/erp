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
import com.ruoyi.finance.domain.ErpSettlement;
import com.ruoyi.finance.service.IErpSettlementService;

/**
 * 核销记录Controller
 *
 * @author erp
 */
@RestController
@RequestMapping("/finance/settlement")
public class ErpSettlementController extends BaseController
{
    @Autowired
    private IErpSettlementService erpSettlementService;

    /**
     * 查询核销记录列表
     */
    @PreAuthorize("@ss.hasPermi('finance:settlement:list')")
    @GetMapping("/list")
    public TableDataInfo list(ErpSettlement erpSettlement)
    {
        startPage();
        List<ErpSettlement> list = erpSettlementService.selectErpSettlementList(erpSettlement);
        return getDataTable(list);
    }

    /**
     * 导出核销记录列表
     */
    @PreAuthorize("@ss.hasPermi('finance:settlement:export')")
    @Log(title = "核销记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ErpSettlement erpSettlement)
    {
        List<ErpSettlement> list = erpSettlementService.selectErpSettlementList(erpSettlement);
        ExcelUtil<ErpSettlement> util = new ExcelUtil<ErpSettlement>(ErpSettlement.class);
        util.exportExcel(response, list, "核销记录数据");
    }

    /**
     * 获取核销记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('finance:settlement:query')")
    @GetMapping(value = "/{settleId}")
    public AjaxResult getInfo(@PathVariable("settleId") Long settleId)
    {
        return success(erpSettlementService.selectErpSettlementBySettleId(settleId));
    }

    /**
     * 新增核销记录
     */
    @PreAuthorize("@ss.hasPermi('finance:settlement:add')")
    @Log(title = "核销记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ErpSettlement erpSettlement)
    {
        erpSettlement.setCreateBy(getUsername());
        return toAjax(erpSettlementService.insertErpSettlement(erpSettlement));
    }

    /**
     * 修改核销记录
     */
    @PreAuthorize("@ss.hasPermi('finance:settlement:edit')")
    @Log(title = "核销记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ErpSettlement erpSettlement)
    {
        erpSettlement.setUpdateBy(getUsername());
        return toAjax(erpSettlementService.updateErpSettlement(erpSettlement));
    }

    /**
     * 删除核销记录
     */
    @PreAuthorize("@ss.hasPermi('finance:settlement:remove')")
    @Log(title = "核销记录", businessType = BusinessType.DELETE)
    @DeleteMapping("/{settleIds}")
    public AjaxResult remove(@PathVariable Long[] settleIds)
    {
        return toAjax(erpSettlementService.deleteErpSettlementBySettleIds(settleIds));
    }
}
