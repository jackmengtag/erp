package com.ruoyi.finance.mapper;

import java.util.List;
import com.ruoyi.finance.domain.ErpVoucher;

/**
 * 财务凭证接口Mapper接口
 *
 * @author erp
 */
public interface ErpVoucherMapper
{
    /**
     * 查询财务凭证接口
     */
    public ErpVoucher selectErpVoucherByVoucherId(Long voucherId);

    /**
     * 查询财务凭证接口列表
     */
    public List<ErpVoucher> selectErpVoucherList(ErpVoucher erpVoucher);

    /**
     * 新增财务凭证接口
     */
    public int insertErpVoucher(ErpVoucher erpVoucher);

    /**
     * 修改财务凭证接口
     */
    public int updateErpVoucher(ErpVoucher erpVoucher);

    /**
     * 删除财务凭证接口
     */
    public int deleteErpVoucherByVoucherId(Long voucherId);

    /**
     * 批量删除财务凭证接口
     */
    public int deleteErpVoucherByVoucherIds(Long[] voucherIds);
}
