package com.ruoyi.mdm.service;

import java.util.List;
import com.ruoyi.mdm.domain.ErpBom;

/**
 * BOM主表Service接口
 *
 * @author erp
 */
public interface IErpBomService
{
    /**
     * 查询BOM主表
     */
    public ErpBom selectErpBomByBomId(Long bomId);

    /**
     * 查询BOM主表列表
     */
    public List<ErpBom> selectErpBomList(ErpBom erpBom);

    /**
     * 新增BOM主表
     */
    public int insertErpBom(ErpBom erpBom);

    /**
     * 修改BOM主表
     */
    public int updateErpBom(ErpBom erpBom);

    /**
     * 批量删除BOM主表
     */
    public int deleteErpBomByBomIds(Long[] bomIds);

    /**
     * 删除BOM主表信息
     */
    public int deleteErpBomByBomId(Long bomId);
}
