package com.ruoyi.mdm.service;

import java.util.List;
import com.ruoyi.mdm.domain.ErpMaterial;

/**
 * 物料主数据Service接口
 *
 * @author erp
 */
public interface IErpMaterialService
{
    /**
     * 查询物料主数据
     */
    public ErpMaterial selectErpMaterialByMaterialId(Long materialId);

    /**
     * 查询物料主数据列表
     */
    public List<ErpMaterial> selectErpMaterialList(ErpMaterial erpMaterial);

    /**
     * 新增物料主数据
     */
    public int insertErpMaterial(ErpMaterial erpMaterial);

    /**
     * 修改物料主数据
     */
    public int updateErpMaterial(ErpMaterial erpMaterial);

    /**
     * 批量删除物料主数据
     */
    public int deleteErpMaterialByMaterialIds(Long[] materialIds);

    /**
     * 删除物料主数据信息
     */
    public int deleteErpMaterialByMaterialId(Long materialId);
}
