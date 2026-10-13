package com.ruoyi.manufacturing.service;

import java.util.List;
import com.ruoyi.manufacturing.domain.ErpProductionMaterial;

/**
 * 工单用料Service接口
 *
 * @author erp
 */
public interface IErpProductionMaterialService
{
    /**
     * 查询工单用料
     */
    public ErpProductionMaterial selectErpProductionMaterialByMaterialReqId(Long materialReqId);

    /**
     * 查询工单用料列表
     */
    public List<ErpProductionMaterial> selectErpProductionMaterialList(ErpProductionMaterial erpProductionMaterial);

    /**
     * 新增工单用料
     */
    public int insertErpProductionMaterial(ErpProductionMaterial erpProductionMaterial);

    /**
     * 修改工单用料
     */
    public int updateErpProductionMaterial(ErpProductionMaterial erpProductionMaterial);

    /**
     * 批量删除工单用料
     */
    public int deleteErpProductionMaterialByMaterialReqIds(Long[] materialReqIds);

    /**
     * 删除工单用料信息
     */
    public int deleteErpProductionMaterialByMaterialReqId(Long materialReqId);
}
