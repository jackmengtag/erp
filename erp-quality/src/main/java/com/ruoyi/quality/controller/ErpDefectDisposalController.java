package com.ruoyi.quality.controller;

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
import com.ruoyi.quality.domain.ErpDefectDisposal;
import com.ruoyi.quality.service.IErpDefectDisposalService;

/**
 * 不合格品处理Controller
 *
 * @author erp
 */
@RestController
@RequestMapping("/quality/defect")
public class ErpDefectDisposalController extends BaseController
{
    @Autowired
    private IErpDefectDisposalService erpDefectDisposalService;

    /**
     * 查询不合格品处理列表
     */
    @PreAuthorize("@ss.hasPermi('quality:defect:list')")
    @GetMapping("/list")
    public TableDataInfo list(ErpDefectDisposal erpDefectDisposal)
    {
        startPage();
        List<ErpDefectDisposal> list = erpDefectDisposalService.selectErpDefectDisposalList(erpDefectDisposal);
        return getDataTable(list);
    }

    /**
     * 导出不合格品处理列表
     */
    @PreAuthorize("@ss.hasPermi('quality:defect:export')")
    @Log(title = "不合格品处理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ErpDefectDisposal erpDefectDisposal)
    {
        List<ErpDefectDisposal> list = erpDefectDisposalService.selectErpDefectDisposalList(erpDefectDisposal);
        ExcelUtil<ErpDefectDisposal> util = new ExcelUtil<ErpDefectDisposal>(ErpDefectDisposal.class);
        util.exportExcel(response, list, "不合格品处理数据");
    }

    /**
     * 获取不合格品处理详细信息
     */
    @PreAuthorize("@ss.hasPermi('quality:defect:query')")
    @GetMapping(value = "/{disposalId}")
    public AjaxResult getInfo(@PathVariable("disposalId") Long disposalId)
    {
        return success(erpDefectDisposalService.selectErpDefectDisposalByDisposalId(disposalId));
    }

    /**
     * 新增不合格品处理
     */
    @PreAuthorize("@ss.hasPermi('quality:defect:add')")
    @Log(title = "不合格品处理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ErpDefectDisposal erpDefectDisposal)
    {
        erpDefectDisposal.setCreateBy(getUsername());
        return toAjax(erpDefectDisposalService.insertErpDefectDisposal(erpDefectDisposal));
    }

    /**
     * 修改不合格品处理
     */
    @PreAuthorize("@ss.hasPermi('quality:defect:edit')")
    @Log(title = "不合格品处理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ErpDefectDisposal erpDefectDisposal)
    {
        erpDefectDisposal.setUpdateBy(getUsername());
        return toAjax(erpDefectDisposalService.updateErpDefectDisposal(erpDefectDisposal));
    }

    /**
     * 删除不合格品处理
     */
    @PreAuthorize("@ss.hasPermi('quality:defect:remove')")
    @Log(title = "不合格品处理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{disposalIds}")
    public AjaxResult remove(@PathVariable Long[] disposalIds)
    {
        return toAjax(erpDefectDisposalService.deleteErpDefectDisposalByDisposalIds(disposalIds));
    }
}
