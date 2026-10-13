package com.ruoyi.sales.mapper;

import java.util.List;
import com.ruoyi.sales.domain.ErpSalesReturn;
import com.ruoyi.sales.domain.ErpSalesReturnLine;

/**
 * 销售退货单Mapper接口
 *
 * @author erp
 */
public interface ErpSalesReturnMapper
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
     * 删除销售退货单
     */
    public int deleteErpSalesReturnByReturnId(Long returnId);

    /**
     * 批量删除销售退货单
     */
    public int deleteErpSalesReturnByReturnIds(Long[] returnIds);

    /**
     * 批量删除销售退货单明细
     */
    public int deleteErpSalesReturnLineByReturnIds(Long[] returnIds);

    /**
     * 删除销售退货单明细信息
     */
    public int deleteErpSalesReturnLineByReturnId(Long returnId);

    /**
     * 批量新增销售退货单明细
     */
    public int batchErpSalesReturnLine(List<ErpSalesReturnLine> erpSalesReturnLineList);
}
