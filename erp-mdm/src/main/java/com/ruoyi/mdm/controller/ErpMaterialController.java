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
import com.ruoyi.mdm.domain.ErpMaterial;
import com.ruoyi.mdm.service.IErpMaterialService;

/**
 * 物料主数据Controller
 *
 * @author erp
 */
@RestController
@RequestMapping("/mdm/material")
public class ErpMaterialController extends BaseController
{
    @Autowired
    private IErpMaterialService erpMaterialService;

    /**
     * 查询物料主数据列表
     */
    @PreAuthorize("@ss.hasPermi('mdm:material:list')")
    @GetMapping("/list")
    public TableDataInfo list(ErpMaterial erpMaterial)
    {
        startPage();
        List<ErpMaterial> list = erpMaterialService.selectErpMaterialList(erpMaterial);
        return getDataTable(list);
    }

    /**
     * 导出物料主数据列表
     */
    @PreAuthorize("@ss.hasPermi('mdm:material:export')")
    @Log(title = "物料主数据", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ErpMaterial erpMaterial)
    {
        List<ErpMaterial> list = erpMaterialService.selectErpMaterialList(erpMaterial);
        ExcelUtil<ErpMaterial> util = new ExcelUtil<ErpMaterial>(ErpMaterial.class);
        util.exportExcel(response, list, "物料主数据数据");
    }

    /**
     * 获取物料主数据详细信息
     */
    @PreAuthorize("@ss.hasPermi('mdm:material:query')")
    @GetMapping(value = "/{materialId}")
    public AjaxResult getInfo(@PathVariable("materialId") Long materialId)
    {
        return success(erpMaterialService.selectErpMaterialByMaterialId(materialId));
    }

    /**
     * 新增物料主数据
     */
    @PreAuthorize("@ss.hasPermi('mdm:material:add')")
    @Log(title = "物料主数据", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ErpMaterial erpMaterial)
    {
        erpMaterial.setCreateBy(getUsername());
        return toAjax(erpMaterialService.insertErpMaterial(erpMaterial));
    }

    /**
     * 修改物料主数据
     */
    @PreAuthorize("@ss.hasPermi('mdm:material:edit')")
    @Log(title = "物料主数据", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ErpMaterial erpMaterial)
    {
        erpMaterial.setUpdateBy(getUsername());
        return toAjax(erpMaterialService.updateErpMaterial(erpMaterial));
    }

    /**
     * 删除物料主数据
     */
    @PreAuthorize("@ss.hasPermi('mdm:material:remove')")
    @Log(title = "物料主数据", businessType = BusinessType.DELETE)
    @DeleteMapping("/{materialIds}")
    public AjaxResult remove(@PathVariable Long[] materialIds)
    {
        return toAjax(erpMaterialService.deleteErpMaterialByMaterialIds(materialIds));
    }
}
