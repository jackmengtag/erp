package com.ruoyi.mdm.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.mdm.domain.ErpMaterial;
import com.ruoyi.mdm.mapper.ErpMaterialMapper;
import com.ruoyi.mdm.service.IErpMaterialService;

/**
 * 物料主数据Service业务层处理
 *
 * @author erp
 */
@Service
public class ErpMaterialServiceImpl implements IErpMaterialService
{
    @Autowired
    private ErpMaterialMapper erpMaterialMapper;

    /**
     * 查询物料主数据
     */
    @Override
    public ErpMaterial selectErpMaterialByMaterialId(Long materialId)
    {
        return erpMaterialMapper.selectErpMaterialByMaterialId(materialId);
    }

    /**
     * 查询物料主数据列表
     */
    @Override
    public List<ErpMaterial> selectErpMaterialList(ErpMaterial erpMaterial)
    {
        return erpMaterialMapper.selectErpMaterialList(erpMaterial);
    }

    /**
     * 新增物料主数据
     */
    @Override
    public int insertErpMaterial(ErpMaterial erpMaterial)
    {
        erpMaterial.setCreateTime(DateUtils.getNowDate());
        return erpMaterialMapper.insertErpMaterial(erpMaterial);
    }

    /**
     * 修改物料主数据
     */
    @Override
    public int updateErpMaterial(ErpMaterial erpMaterial)
    {
        erpMaterial.setUpdateTime(DateUtils.getNowDate());
        return erpMaterialMapper.updateErpMaterial(erpMaterial);
    }

    /**
     * 批量删除物料主数据
     */
    @Override
    public int deleteErpMaterialByMaterialIds(Long[] materialIds)
    {
        return erpMaterialMapper.deleteErpMaterialByMaterialIds(materialIds);
    }

    /**
     * 删除物料主数据信息
     */
    @Override
    public int deleteErpMaterialByMaterialId(Long materialId)
    {
        return erpMaterialMapper.deleteErpMaterialByMaterialId(materialId);
    }
}
