package com.ruoyi.sales.service;

import java.util.List;
import com.ruoyi.sales.domain.ErpSalesReturn;

/**
 * 销售退货单Service接口
 *
 * @author erp
 */
public interface IErpSalesReturnService
{
    /**
     * 查询销售退货单
     */
    public ErpSalesReturn selectErpSalesReturnByReturnId(Long returnId);

    /**
     * 查询销售退货单列表
     */
    public List<ErpSalesReturn> selectErpSalesReturnList(ErpSalesReturn erpSalesReturn);

    /**
     * 新增销售退货单
     */
    public int insertErpSalesReturn(ErpSalesReturn erpSalesReturn);

    /**
     * 修改销售退货单
     */
    public int updateErpSalesReturn(ErpSalesReturn erpSalesReturn);

    /**
     * 批量删除销售退货单
     */
    public int deleteErpSalesReturnByReturnIds(Long[] returnIds);

    /**
     * 删除销售退货单信息
     */
    public int deleteErpSalesReturnByReturnId(Long returnId);
}
