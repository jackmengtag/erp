package com.ruoyi.finance.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.finance.domain.ErpProductionCost;
import com.ruoyi.finance.mapper.ErpProductionCostMapper;
import com.ruoyi.finance.service.IErpProductionCostService;

/**
 * 生产成本归集Service业务层处理
 *
 * @author erp
 */
@Service
public class ErpProductionCostServiceImpl implements IErpProductionCostService
{
    @Autowired
    private ErpProductionCostMapper erpProductionCostMapper;

    /**
     * 查询生产成本归集
     */
    @Override
    public ErpProductionCost selectErpProductionCostByCostId(Long costId)
    {
        return erpProductionCostMapper.selectErpProductionCostByCostId(costId);
    }

    /**
     * 查询生产成本归集列表
     */
    @Override
    public List<ErpProductionCost> selectErpProductionCostList(ErpProductionCost erpProductionCost)
    {
        return erpProductionCostMapper.selectErpProductionCostList(erpProductionCost);
    }

    /**
     * 新增生产成本归集
     */
    @Override
    public int insertErpProductionCost(ErpProductionCost erpProductionCost)
    {
        erpProductionCost.setCreateTime(DateUtils.getNowDate());
        return erpProductionCostMapper.insertErpProductionCost(erpProductionCost);
    }

    /**
     * 修改生产成本归集
     */
    @Override
    public int updateErpProductionCost(ErpProductionCost erpProductionCost)
    {
        erpProductionCost.setUpdateTime(DateUtils.getNowDate());
        return erpProductionCostMapper.updateErpProductionCost(erpProductionCost);
    }

    /**
     * 批量删除生产成本归集
     */
    @Override
    public int deleteErpProductionCostByCostIds(Long[] costIds)
    {
        return erpProductionCostMapper.deleteErpProductionCostByCostIds(costIds);
    }

    /**
     * 删除生产成本归集信息
     */
    @Override
    public int deleteErpProductionCostByCostId(Long costId)
    {
        return erpProductionCostMapper.deleteErpProductionCostByCostId(costId);
    }
}
