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
import com.ruoyi.mdm.domain.ErpBom;
import com.ruoyi.mdm.service.IErpBomService;

/**
 * BOM主表Controller
 *
 * @author erp
 */
@RestController
@RequestMapping("/mdm/bom")
public class ErpBomController extends BaseController
{
    @Autowired
    private IErpBomService erpBomService;

    /**
     * 查询BOM主表列表
     */
    @PreAuthorize("@ss.hasPermi('mdm:bom:list')")
    @GetMapping("/list")
    public TableDataInfo list(ErpBom erpBom)
    {
        startPage();
        List<ErpBom> list = erpBomService.selectErpBomList(erpBom);
        return getDataTable(list);
    }

    /**
     * 导出BOM主表列表
     */
    @PreAuthorize("@ss.hasPermi('mdm:bom:export')")
    @Log(title = "BOM主表", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ErpBom erpBom)
    {
        List<ErpBom> list = erpBomService.selectErpBomList(erpBom);
        ExcelUtil<ErpBom> util = new ExcelUtil<ErpBom>(ErpBom.class);
        util.exportExcel(response, list, "BOM主表数据");
    }

    /**
     * 获取BOM主表详细信息
     */
    @PreAuthorize("@ss.hasPermi('mdm:bom:query')")
    @GetMapping(value = "/{bomId}")
    public AjaxResult getInfo(@PathVariable("bomId") Long bomId)
    {
        return success(erpBomService.selectErpBomByBomId(bomId));
    }

    /**
     * 新增BOM主表
     */
    @PreAuthorize("@ss.hasPermi('mdm:bom:add')")
    @Log(title = "BOM主表", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ErpBom erpBom)
    {
        erpBom.setCreateBy(getUsername());
        return toAjax(erpBomService.insertErpBom(erpBom));
    }

    /**
     * 修改BOM主表
     */
    @PreAuthorize("@ss.hasPermi('mdm:bom:edit')")
    @Log(title = "BOM主表", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ErpBom erpBom)
    {
        erpBom.setUpdateBy(getUsername());
        return toAjax(erpBomService.updateErpBom(erpBom));
    }

    /**
     * 删除BOM主表
     */
    @PreAuthorize("@ss.hasPermi('mdm:bom:remove')")
    @Log(title = "BOM主表", businessType = BusinessType.DELETE)
    @DeleteMapping("/{bomIds}")
    public AjaxResult remove(@PathVariable Long[] bomIds)
    {
        return toAjax(erpBomService.deleteErpBomByBomIds(bomIds));
    }
}
