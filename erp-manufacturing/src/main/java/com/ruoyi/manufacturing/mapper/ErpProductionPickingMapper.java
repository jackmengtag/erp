package com.ruoyi.manufacturing.mapper;

import java.util.List;
import com.ruoyi.manufacturing.domain.ErpProductionPicking;
import com.ruoyi.manufacturing.domain.ErpProductionPickingLine;

/**
 * 生产领料单Mapper接口
 *
 * @author erp
 */
public interface ErpProductionPickingMapper
{
    /**
     * 查询生产领料单
     */
    public ErpProductionPicking selectErpProductionPickingByPickingId(Long pickingId);

    /**
     * 查询生产领料单列表
     */
    public List<ErpProductionPicking> selectErpProductionPickingList(ErpProductionPicking erpProductionPicking);

    /**
     * 新增生产领料单
     */
    public int insertErpProductionPicking(ErpProductionPicking erpProductionPicking);

    /**
     * 修改生产领料单
     */
    public int updateErpProductionPicking(ErpProductionPicking erpProductionPicking);

    /**
     * 删除生产领料单
     */
    public int deleteErpProductionPickingByPickingId(Long pickingId);

    /**
     * 批量删除生产领料单
     */
    public int deleteErpProductionPickingByPickingIds(Long[] pickingIds);

    /**
     * 批量删除生产领料明细
     */
    public int deleteErpProductionPickingLineByPickingIds(Long[] pickingIds);

    /**
     * 删除生产领料明细信息
     */
    public int deleteErpProductionPickingLineByPickingId(Long pickingId);

    /**
     * 批量新增生产领料明细
     */
    public int batchErpProductionPickingLine(List<ErpProductionPickingLine> erpProductionPickingLineList);
}
