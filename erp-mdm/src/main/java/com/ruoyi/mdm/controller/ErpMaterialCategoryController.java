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
import com.ruoyi.mdm.domain.ErpMaterialCategory;
import com.ruoyi.mdm.service.IErpMaterialCategoryService;

/**
 * 物料分类Controller
 *
 * @author erp
 */
@RestController
@RequestMapping("/mdm/category")
public class ErpMaterialCategoryController extends BaseController
{
    @Autowired
    private IErpMaterialCategoryService erpMaterialCategoryService;

    /**
     * 查询物料分类列表
     */
    @PreAuthorize("@ss.hasPermi('mdm:category:list')")
    @GetMapping("/list")
    public TableDataInfo list(ErpMaterialCategory erpMaterialCategory)
    {
        startPage();
        List<ErpMaterialCategory> list = erpMaterialCategoryService.selectErpMaterialCategoryList(erpMaterialCategory);
        return getDataTable(list);
    }

    /**
     * 导出物料分类列表
     */
    @PreAuthorize("@ss.hasPermi('mdm:category:export')")
    @Log(title = "物料分类", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ErpMaterialCategory erpMaterialCategory)
    {
        List<ErpMaterialCategory> list = erpMaterialCategoryService.selectErpMaterialCategoryList(erpMaterialCategory);
        ExcelUtil<ErpMaterialCategory> util = new ExcelUtil<ErpMaterialCategory>(ErpMaterialCategory.class);
        util.exportExcel(response, list, "物料分类数据");
    }

    /**
     * 获取物料分类详细信息
     */
    @PreAuthorize("@ss.hasPermi('mdm:category:query')")
    @GetMapping(value = "/{categoryId}")
    public AjaxResult getInfo(@PathVariable("categoryId") Long categoryId)
    {
        return success(erpMaterialCategoryService.selectErpMaterialCategoryByCategoryId(categoryId));
    }

    /**
     * 新增物料分类
     */
    @PreAuthorize("@ss.hasPermi('mdm:category:add')")
    @Log(title = "物料分类", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ErpMaterialCategory erpMaterialCategory)
    {
        erpMaterialCategory.setCreateBy(getUsername());
        return toAjax(erpMaterialCategoryService.insertErpMaterialCategory(erpMaterialCategory));
    }

    /**
     * 修改物料分类
     */
    @PreAuthorize("@ss.hasPermi('mdm:category:edit')")
    @Log(title = "物料分类", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ErpMaterialCategory erpMaterialCategory)
    {
        erpMaterialCategory.setUpdateBy(getUsername());
        return toAjax(erpMaterialCategoryService.updateErpMaterialCategory(erpMaterialCategory));
    }

    /**
     * 删除物料分类
     */
    @PreAuthorize("@ss.hasPermi('mdm:category:remove')")
    @Log(title = "物料分类", businessType = BusinessType.DELETE)
    @DeleteMapping("/{categoryIds}")
    public AjaxResult remove(@PathVariable Long[] categoryIds)
    {
        return toAjax(erpMaterialCategoryService.deleteErpMaterialCategoryByCategoryIds(categoryIds));
    }
}
