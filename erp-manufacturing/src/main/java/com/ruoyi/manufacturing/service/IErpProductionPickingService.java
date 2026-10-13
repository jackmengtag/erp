package com.ruoyi.manufacturing.service;

import java.util.List;
import com.ruoyi.manufacturing.domain.ErpProductionPicking;

/**
 * 生产领料单Service接口
 *
 * @author erp
 */
public interface IErpProductionPickingService
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
     * 批量删除生产领料单
     */
    public int deleteErpProductionPickingByPickingIds(Long[] pickingIds);

    /**
     * 删除生产领料单信息
     */
    public int deleteErpProductionPickingByPickingId(Long pickingId);
}
