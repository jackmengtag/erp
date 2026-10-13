package com.ruoyi.finance.service;

import java.util.List;
import com.ruoyi.finance.domain.ErpProductionCost;

/**
 * 生产成本归集Service接口
 *
 * @author erp
 */
public interface IErpProductionCostService
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
     * 批量删除生产成本归集
     */
    public int deleteErpProductionCostByCostIds(Long[] costIds);

    /**
     * 删除生产成本归集信息
     */
    public int deleteErpProductionCostByCostId(Long costId);
}
