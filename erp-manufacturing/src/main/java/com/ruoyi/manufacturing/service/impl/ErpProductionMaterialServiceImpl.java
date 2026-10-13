package com.ruoyi.manufacturing.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.manufacturing.domain.ErpProductionMaterial;
import com.ruoyi.manufacturing.mapper.ErpProductionMaterialMapper;
import com.ruoyi.manufacturing.service.IErpProductionMaterialService;

/**
 * 工单用料Service业务层处理
 *
 * @author erp
 */
@Service
public class ErpProductionMaterialServiceImpl implements IErpProductionMaterialService
{
    @Autowired
    private ErpProductionMaterialMapper erpProductionMaterialMapper;

    /**
     * 查询工单用料
     */
    @Override
    public ErpProductionMaterial selectErpProductionMaterialByMaterialReqId(Long materialReqId)
    {
        return erpProductionMaterialMapper.selectErpProductionMaterialByMaterialReqId(materialReqId);
    }

    /**
     * 查询工单用料列表
     */
    @Override
    public List<ErpProductionMaterial> selectErpProductionMaterialList(ErpProductionMaterial erpProductionMaterial)
    {
        return erpProductionMaterialMapper.selectErpProductionMaterialList(erpProductionMaterial);
    }

    /**
     * 新增工单用料
     */
    @Override
    public int insertErpProductionMaterial(ErpProductionMaterial erpProductionMaterial)
    {
        erpProductionMaterial.setCreateTime(DateUtils.getNowDate());
        return erpProductionMaterialMapper.insertErpProductionMaterial(erpProductionMaterial);
    }

    /**
     * 修改工单用料
     */
    @Override
    public int updateErpProductionMaterial(ErpProductionMaterial erpProductionMaterial)
    {
        erpProductionMaterial.setUpdateTime(DateUtils.getNowDate());
        return erpProductionMaterialMapper.updateErpProductionMaterial(erpProductionMaterial);
    }

    /**
     * 批量删除工单用料
     */
    @Override
    public int deleteErpProductionMaterialByMaterialReqIds(Long[] materialReqIds)
    {
        return erpProductionMaterialMapper.deleteErpProductionMaterialByMaterialReqIds(materialReqIds);
    }

    /**
     * 删除工单用料信息
     */
    @Override
    public int deleteErpProductionMaterialByMaterialReqId(Long materialReqId)
    {
        return erpProductionMaterialMapper.deleteErpProductionMaterialByMaterialReqId(materialReqId);
    }
}
