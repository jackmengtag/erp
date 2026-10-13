package com.ruoyi.mdm.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.mdm.domain.ErpMaterialCategory;
import com.ruoyi.mdm.mapper.ErpMaterialCategoryMapper;
import com.ruoyi.mdm.service.IErpMaterialCategoryService;

/**
 * 物料分类Service业务层处理
 *
 * @author erp
 */
@Service
public class ErpMaterialCategoryServiceImpl implements IErpMaterialCategoryService
{
    @Autowired
    private ErpMaterialCategoryMapper erpMaterialCategoryMapper;

    /**
     * 查询物料分类
     */
    @Override
    public ErpMaterialCategory selectErpMaterialCategoryByCategoryId(Long categoryId)
    {
        return erpMaterialCategoryMapper.selectErpMaterialCategoryByCategoryId(categoryId);
    }

    /**
     * 查询物料分类列表
     */
    @Override
    public List<ErpMaterialCategory> selectErpMaterialCategoryList(ErpMaterialCategory erpMaterialCategory)
    {
        return erpMaterialCategoryMapper.selectErpMaterialCategoryList(erpMaterialCategory);
    }

    /**
     * 新增物料分类
     */
    @Override
    public int insertErpMaterialCategory(ErpMaterialCategory erpMaterialCategory)
    {
        erpMaterialCategory.setCreateTime(DateUtils.getNowDate());
        return erpMaterialCategoryMapper.insertErpMaterialCategory(erpMaterialCategory);
    }

    /**
     * 修改物料分类
     */
    @Override
    public int updateErpMaterialCategory(ErpMaterialCategory erpMaterialCategory)
    {
        erpMaterialCategory.setUpdateTime(DateUtils.getNowDate());
        return erpMaterialCategoryMapper.updateErpMaterialCategory(erpMaterialCategory);
    }

    /**
     * 批量删除物料分类
     */
    @Override
    public int deleteErpMaterialCategoryByCategoryIds(Long[] categoryIds)
    {
        return erpMaterialCategoryMapper.deleteErpMaterialCategoryByCategoryIds(categoryIds);
    }

    /**
     * 删除物料分类信息
     */
    @Override
    public int deleteErpMaterialCategoryByCategoryId(Long categoryId)
    {
        return erpMaterialCategoryMapper.deleteErpMaterialCategoryByCategoryId(categoryId);
    }
}
