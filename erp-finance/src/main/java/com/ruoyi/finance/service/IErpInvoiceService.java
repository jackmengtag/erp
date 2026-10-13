package com.ruoyi.finance.service;

import java.util.List;
import com.ruoyi.finance.domain.ErpInvoice;

/**
 * 发票Service接口
 *
 * @author erp
 */
public interface IErpInvoiceService
{
    /**
     * 查询发票
     */
    public ErpInvoice selectErpInvoiceByInvoiceId(Long invoiceId);

    /**
     * 查询发票列表
     */
    public List<ErpInvoice> selectErpInvoiceList(ErpInvoice erpInvoice);

    /**
     * 新增发票
     */
    public int insertErpInvoice(ErpInvoice erpInvoice);

    /**
     * 修改发票
     */
    public int updateErpInvoice(ErpInvoice erpInvoice);

    /**
     * 批量删除发票
     */
    public int deleteErpInvoiceByInvoiceIds(Long[] invoiceIds);

    /**
     * 删除发票信息
     */
    public int deleteErpInvoiceByInvoiceId(Long invoiceId);
}
