package com.ruoyi.finance.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.finance.domain.ErpVoucher;
import com.ruoyi.finance.mapper.ErpVoucherMapper;
import com.ruoyi.finance.service.IErpVoucherService;

/**
 * 财务凭证接口Service业务层处理
 *
 * @author erp
 */
@Service
public class ErpVoucherServiceImpl implements IErpVoucherService
{
    @Autowired
    private ErpVoucherMapper erpVoucherMapper;

    /**
     * 查询财务凭证接口
     */
    @Override
    public ErpVoucher selectErpVoucherByVoucherId(Long voucherId)
    {
        return erpVoucherMapper.selectErpVoucherByVoucherId(voucherId);
    }

    /**
     * 查询财务凭证接口列表
     */
    @Override
    public List<ErpVoucher> selectErpVoucherList(ErpVoucher erpVoucher)
    {
        return erpVoucherMapper.selectErpVoucherList(erpVoucher);
    }

    /**
     * 新增财务凭证接口
     */
    @Override
    public int insertErpVoucher(ErpVoucher erpVoucher)
    {
        erpVoucher.setCreateTime(DateUtils.getNowDate());
        return erpVoucherMapper.insertErpVoucher(erpVoucher);
    }

    /**
     * 修改财务凭证接口
     */
    @Override
    public int updateErpVoucher(ErpVoucher erpVoucher)
    {
        erpVoucher.setUpdateTime(DateUtils.getNowDate());
        return erpVoucherMapper.updateErpVoucher(erpVoucher);
    }

    /**
     * 批量删除财务凭证接口
     */
    @Override
    public int deleteErpVoucherByVoucherIds(Long[] voucherIds)
    {
        return erpVoucherMapper.deleteErpVoucherByVoucherIds(voucherIds);
    }

    /**
     * 删除财务凭证接口信息
     */
    @Override
    public int deleteErpVoucherByVoucherId(Long voucherId)
    {
        return erpVoucherMapper.deleteErpVoucherByVoucherId(voucherId);
    }
}
