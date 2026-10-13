package com.ruoyi.finance.service;

import java.util.List;
import com.ruoyi.finance.domain.ErpInventoryCost;

/**
 * 存货核算Service接口
 *
 * @author erp
 */
public interface IErpInventoryCostService
{
    /**
     * 查询存货核算
     */
    public ErpInventoryCost selectErpInventoryCostByCostId(Long costId);

    /**
     * 查询存货核算列表
     */
    public List<ErpInventoryCost> selectErpInventoryCostList(ErpInventoryCost erpInventoryCost);

    /**
     * 新增存货核算
     */
    public int insertErpInventoryCost(ErpInventoryCost erpInventoryCost);

    /**
     * 修改存货核算
     */
    public int updateErpInventoryCost(ErpInventoryCost erpInventoryCost);

    /**
     * 批量删除存货核算
     */
    public int deleteErpInventoryCostByCostIds(Long[] costIds);

    /**
     * 删除存货核算信息
     */
    public int deleteErpInventoryCostByCostId(Long costId);
}
