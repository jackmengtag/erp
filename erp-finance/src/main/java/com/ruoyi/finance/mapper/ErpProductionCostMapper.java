package com.ruoyi.finance.mapper;

import java.util.List;
import com.ruoyi.finance.domain.ErpProductionCost;

/**
 * 生产成本归集Mapper接口
 *
 * @author erp
 */
public interface ErpProductionCostMapper
{
    /**
     * 查询生产成本归集
     */
    public ErpProductionCost selectErpProductionCostByCostId(Long costId);

    /**
     * 查询生产成本归集列表
     */
    public List<ErpProductionCost> selectErpProductionCostList(ErpProductionCost erpProductionCost);

    /**
     * 新增生产成本归集
     */
    public int insertErpProductionCost(ErpProductionCost erpProductionCost);

    /**
     * 修改生产成本归集
     */
    public int updateErpProductionCost(ErpProductionCost erpProductionCost);

    /**
     * 删除生产成本归集
     */
    public int deleteErpProductionCostByCostId(Long costId);

    /**
     * 批量删除生产成本归集
     */
    public int deleteErpProductionCostByCostIds(Long[] costIds);
}
