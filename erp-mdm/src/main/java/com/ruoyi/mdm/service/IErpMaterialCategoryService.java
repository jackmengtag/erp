package com.ruoyi.mdm.service;

import java.util.List;
import com.ruoyi.mdm.domain.ErpMaterialCategory;

/**
 * 物料分类Service接口
 *
 * @author erp
 */
public interface IErpMaterialCategoryService
{
    /**
     * 查询物料分类
     */
    public ErpMaterialCategory selectErpMaterialCategoryByCategoryId(Long categoryId);

    /**
     * 查询物料分类列表
     */
    public List<ErpMaterialCategory> selectErpMaterialCategoryList(ErpMaterialCategory erpMaterialCategory);

    /**
     * 新增物料分类
     */
    public int insertErpMaterialCategory(ErpMaterialCategory erpMaterialCategory);

    /**
     * 修改物料分类
     */
    public int updateErpMaterialCategory(ErpMaterialCategory erpMaterialCategory);

    /**
     * 批量删除物料分类
     */
    public int deleteErpMaterialCategoryByCategoryIds(Long[] categoryIds);

    /**
     * 删除物料分类信息
     */
    public int deleteErpMaterialCategoryByCategoryId(Long categoryId);
}
