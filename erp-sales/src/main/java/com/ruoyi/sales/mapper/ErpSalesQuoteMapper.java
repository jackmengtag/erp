package com.ruoyi.sales.mapper;

import java.util.List;
import com.ruoyi.sales.domain.ErpSalesQuote;
import com.ruoyi.sales.domain.ErpSalesQuoteLine;

/**
 * 销售报价单Mapper接口
 *
 * @author erp
 */
public interface ErpSalesQuoteMapper
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
     * 删除销售报价单
     */
    public int deleteErpSalesQuoteByQuoteId(Long quoteId);

    /**
     * 批量删除销售报价单
     */
    public int deleteErpSalesQuoteByQuoteIds(Long[] quoteIds);

    /**
     * 批量删除销售报价单明细
     */
    public int deleteErpSalesQuoteLineByQuoteIds(Long[] quoteIds);

    /**
     * 删除销售报价单明细信息
     */
    public int deleteErpSalesQuoteLineByQuoteId(Long quoteId);

    /**
     * 批量新增销售报价单明细
     */
    public int batchErpSalesQuoteLine(List<ErpSalesQuoteLine> erpSalesQuoteLineList);
}
