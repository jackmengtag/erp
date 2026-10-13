package com.ruoyi.sales.service;

import java.util.List;
import com.ruoyi.sales.domain.ErpSalesQuote;

/**
 * 销售报价单Service接口
 *
 * @author erp
 */
public interface IErpSalesQuoteService
{
    /**
     * 查询销售报价单
     */
    public ErpSalesQuote selectErpSalesQuoteByQuoteId(Long quoteId);

    /**
     * 查询销售报价单列表
     */
    public List<ErpSalesQuote> selectErpSalesQuoteList(ErpSalesQuote erpSalesQuote);

    /**
     * 新增销售报价单
     */
    public int insertErpSalesQuote(ErpSalesQuote erpSalesQuote);

    /**
     * 修改销售报价单
     */
    public int updateErpSalesQuote(ErpSalesQuote erpSalesQuote);

    /**
     * 批量删除销售报价单
     */
    public int deleteErpSalesQuoteByQuoteIds(Long[] quoteIds);

    /**
     * 删除销售报价单信息
     */
    public int deleteErpSalesQuoteByQuoteId(Long quoteId);
}
