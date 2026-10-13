package com.ruoyi.manufacturing.controller;

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
import com.ruoyi.manufacturing.domain.ErpProductionMaterial;
import com.ruoyi.manufacturing.service.IErpProductionMaterialService;

/**
 * 工单用料Controller
 *
 * @author erp
 */
@RestController
@RequestMapping("/manufacturing/material")
public class ErpProductionMaterialController extends BaseController
{
    @Autowired
    private IErpProductionMaterialService erpProductionMaterialService;

    /**
     * 查询工单用料列表
     */
    @PreAuthorize("@ss.hasPermi('manufacturing:material:list')")
    @GetMapping("/list")
    public TableDataInfo list(ErpProductionMaterial erpProductionMaterial)
    {
        startPage();
        List<ErpProductionMaterial> list = erpProductionMaterialService.selectErpProductionMaterialList(erpProductionMaterial);
        return getDataTable(list);
    }

    /**
     * 导出工单用料列表
     */
    @PreAuthorize("@ss.hasPermi('manufacturing:material:export')")
    @Log(title = "工单用料", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ErpProductionMaterial erpProductionMaterial)
    {
        List<ErpProductionMaterial> list = erpProductionMaterialService.selectErpProductionMaterialList(erpProductionMaterial);
        ExcelUtil<ErpProductionMaterial> util = new ExcelUtil<ErpProductionMaterial>(ErpProductionMaterial.class);
        util.exportExcel(response, list, "工单用料数据");
    }

    /**
     * 获取工单用料详细信息
     */
    @PreAuthorize("@ss.hasPermi('manufacturing:material:query')")
    @GetMapping(value = "/{materialReqId}")
    public AjaxResult getInfo(@PathVariable("materialReqId") Long materialReqId)
    {
        return success(erpProductionMaterialService.selectErpProductionMaterialByMaterialReqId(materialReqId));
    }

    /**
     * 新增工单用料
     */
    @PreAuthorize("@ss.hasPermi('manufacturing:material:add')")
    @Log(title = "工单用料", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ErpProductionMaterial erpProductionMaterial)
    {
        erpProductionMaterial.setCreateBy(getUsername());
        return toAjax(erpProductionMaterialService.insertErpProductionMaterial(erpProductionMaterial));
    }

    /**
     * 修改工单用料
     */
    @PreAuthorize("@ss.hasPermi('manufacturing:material:edit')")
    @Log(title = "工单用料", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ErpProductionMaterial erpProductionMaterial)
    {
        erpProductionMaterial.setUpdateBy(getUsername());
        return toAjax(erpProductionMaterialService.updateErpProductionMaterial(erpProductionMaterial));
    }

    /**
     * 删除工单用料
     */
    @PreAuthorize("@ss.hasPermi('manufacturing:material:remove')")
    @Log(title = "工单用料", businessType = BusinessType.DELETE)
    @DeleteMapping("/{materialReqIds}")
    public AjaxResult remove(@PathVariable Long[] materialReqIds)
    {
        return toAjax(erpProductionMaterialService.deleteErpProductionMaterialByMaterialReqIds(materialReqIds));
    }
}
