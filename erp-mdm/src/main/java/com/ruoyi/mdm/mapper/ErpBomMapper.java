package com.ruoyi.mdm.mapper;

import java.util.List;
import com.ruoyi.mdm.domain.ErpBom;
import com.ruoyi.mdm.domain.ErpBomLine;

/**
 * BOM主表Mapper接口
 *
 * @author erp
 */
public interface ErpBomMapper
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
     * 删除BOM主表
     */
    public int deleteErpBomByBomId(Long bomId);

    /**
     * 批量删除BOM主表
     */
    public int deleteErpBomByBomIds(Long[] bomIds);

    /**
     * 批量删除BOM子件明细
     */
    public int deleteErpBomLineByBomIds(Long[] bomIds);

    /**
     * 删除BOM子件明细信息
     */
    public int deleteErpBomLineByBomId(Long bomId);

    /**
     * 批量新增BOM子件明细
     */
    public int batchErpBomLine(List<ErpBomLine> erpBomLineList);
}
