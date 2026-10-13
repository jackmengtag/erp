package com.ruoyi.finance.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.finance.domain.ErpInventoryCost;
import com.ruoyi.finance.mapper.ErpInventoryCostMapper;
import com.ruoyi.finance.service.IErpInventoryCostService;

/**
 * 存货核算Service业务层处理
 *
 * @author erp
 */
@Service
public class ErpInventoryCostServiceImpl implements IErpInventoryCostService
{
    @Autowired
    private ErpInventoryCostMapper erpInventoryCostMapper;

    /**
     * 查询存货核算
     */
    @Override
    public ErpInventoryCost selectErpInventoryCostByCostId(Long costId)
    {
        return erpInventoryCostMapper.selectErpInventoryCostByCostId(costId);
    }

    /**
     * 查询存货核算列表
     */
    @Override
    public List<ErpInventoryCost> selectErpInventoryCostList(ErpInventoryCost erpInventoryCost)
    {
        return erpInventoryCostMapper.selectErpInventoryCostList(erpInventoryCost);
    }

    /**
     * 新增存货核算
     */
    @Override
    public int insertErpInventoryCost(ErpInventoryCost erpInventoryCost)
    {
        erpInventoryCost.setCreateTime(DateUtils.getNowDate());
        return erpInventoryCostMapper.insertErpInventoryCost(erpInventoryCost);
    }

    /**
     * 修改存货核算
     */
    @Override
    public int updateErpInventoryCost(ErpInventoryCost erpInventoryCost)
    {
        erpInventoryCost.setUpdateTime(DateUtils.getNowDate());
        return erpInventoryCostMapper.updateErpInventoryCost(erpInventoryCost);
    }

    /**
     * 批量删除存货核算
     */
    @Override
    public int deleteErpInventoryCostByCostIds(Long[] costIds)
    {
        return erpInventoryCostMapper.deleteErpInventoryCostByCostIds(costIds);
    }

    /**
     * 删除存货核算信息
     */
    @Override
    public int deleteErpInventoryCostByCostId(Long costId)
    {
        return erpInventoryCostMapper.deleteErpInventoryCostByCostId(costId);
    }
}
