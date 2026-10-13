package com.ruoyi.finance.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.finance.domain.ErpPayment;
import com.ruoyi.finance.mapper.ErpPaymentMapper;
import com.ruoyi.finance.service.IErpPaymentService;

/**
 * 付款单Service业务层处理
 *
 * @author erp
 */
@Service
public class ErpPaymentServiceImpl implements IErpPaymentService
{
    @Autowired
    private ErpPaymentMapper erpPaymentMapper;

    /**
     * 查询付款单
     */
    @Override
    public ErpPayment selectErpPaymentByPaymentId(Long paymentId)
    {
        return erpPaymentMapper.selectErpPaymentByPaymentId(paymentId);
    }

    /**
     * 查询付款单列表
     */
    @Override
    public List<ErpPayment> selectErpPaymentList(ErpPayment erpPayment)
    {
        return erpPaymentMapper.selectErpPaymentList(erpPayment);
    }

    /**
     * 新增付款单
     */
    @Override
    public int insertErpPayment(ErpPayment erpPayment)
    {
        erpPayment.setCreateTime(DateUtils.getNowDate());
        return erpPaymentMapper.insertErpPayment(erpPayment);
    }

    /**
     * 修改付款单
     */
    @Override
    public int updateErpPayment(ErpPayment erpPayment)
    {
        erpPayment.setUpdateTime(DateUtils.getNowDate());
        return erpPaymentMapper.updateErpPayment(erpPayment);
    }

    /**
     * 批量删除付款单
     */
    @Override
    public int deleteErpPaymentByPaymentIds(Long[] paymentIds)
    {
        return erpPaymentMapper.deleteErpPaymentByPaymentIds(paymentIds);
    }

    /**
     * 删除付款单信息
     */
    @Override
    public int deleteErpPaymentByPaymentId(Long paymentId)
    {
        return erpPaymentMapper.deleteErpPaymentByPaymentId(paymentId);
    }
}
