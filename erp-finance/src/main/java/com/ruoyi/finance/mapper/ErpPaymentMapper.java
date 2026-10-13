package com.ruoyi.finance.mapper;

import java.util.List;
import com.ruoyi.finance.domain.ErpPayment;

/**
 * 付款单Mapper接口
 *
 * @author erp
 */
public interface ErpPaymentMapper
{
    /**
     * 查询付款单
     */
    public ErpPayment selectErpPaymentByPaymentId(Long paymentId);

    /**
     * 查询付款单列表
     */
    public List<ErpPayment> selectErpPaymentList(ErpPayment erpPayment);

    /**
     * 新增付款单
     */
    public int insertErpPayment(ErpPayment erpPayment);

    /**
     * 修改付款单
     */
    public int updateErpPayment(ErpPayment erpPayment);

    /**
     * 删除付款单
     */
    public int deleteErpPaymentByPaymentId(Long paymentId);

    /**
     * 批量删除付款单
     */
    public int deleteErpPaymentByPaymentIds(Long[] paymentIds);
}
