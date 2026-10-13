package com.ruoyi.finance.mapper;

import java.util.List;
import com.ruoyi.finance.domain.ErpInventoryCost;

/**
 * 存货核算Mapper接口
 *
 * @author erp
 */
public interface ErpInventoryCostMapper
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
     * 删除存货核算
     */
    public int deleteErpInventoryCostByCostId(Long costId);

    /**
     * 批量删除存货核算
     */
    public int deleteErpInventoryCostByCostIds(Long[] costIds);
}
