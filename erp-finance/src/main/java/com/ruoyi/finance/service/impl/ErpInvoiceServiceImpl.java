package com.ruoyi.finance.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.finance.domain.ErpInvoice;
import com.ruoyi.finance.mapper.ErpInvoiceMapper;
import com.ruoyi.finance.service.IErpInvoiceService;

/**
 * 发票Service业务层处理
 *
 * @author erp
 */
@Service
public class ErpInvoiceServiceImpl implements IErpInvoiceService
{
    @Autowired
    private ErpInvoiceMapper erpInvoiceMapper;

    /**
     * 查询发票
     */
    @Override
    public ErpInvoice selectErpInvoiceByInvoiceId(Long invoiceId)
    {
        return erpInvoiceMapper.selectErpInvoiceByInvoiceId(invoiceId);
    }

    /**
     * 查询发票列表
     */
    @Override
    public List<ErpInvoice> selectErpInvoiceList(ErpInvoice erpInvoice)
    {
        return erpInvoiceMapper.selectErpInvoiceList(erpInvoice);
    }

    /**
     * 新增发票
     */
    @Override
    public int insertErpInvoice(ErpInvoice erpInvoice)
    {
        erpInvoice.setCreateTime(DateUtils.getNowDate());
        return erpInvoiceMapper.insertErpInvoice(erpInvoice);
    }

    /**
     * 修改发票
     */
    @Override
    public int updateErpInvoice(ErpInvoice erpInvoice)
    {
        erpInvoice.setUpdateTime(DateUtils.getNowDate());
        return erpInvoiceMapper.updateErpInvoice(erpInvoice);
    }

    /**
     * 批量删除发票
     */
    @Override
    public int deleteErpInvoiceByInvoiceIds(Long[] invoiceIds)
    {
        return erpInvoiceMapper.deleteErpInvoiceByInvoiceIds(invoiceIds);
    }

    /**
     * 删除发票信息
     */
    @Override
    public int deleteErpInvoiceByInvoiceId(Long invoiceId)
    {
        return erpInvoiceMapper.deleteErpInvoiceByInvoiceId(invoiceId);
    }
}
