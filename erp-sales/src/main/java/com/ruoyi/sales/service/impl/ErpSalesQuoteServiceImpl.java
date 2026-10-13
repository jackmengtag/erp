package com.ruoyi.sales.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.sales.domain.ErpSalesQuote;
import com.ruoyi.sales.domain.ErpSalesQuoteLine;
import com.ruoyi.sales.mapper.ErpSalesQuoteMapper;
import com.ruoyi.sales.service.IErpSalesQuoteService;

/**
 * 销售报价单Service业务层处理
 *
 * @author erp
 */
@Service
public class ErpSalesQuoteServiceImpl implements IErpSalesQuoteService
{
    @Autowired
    private ErpSalesQuoteMapper erpSalesQuoteMapper;

    /**
     * 查询销售报价单
     */
    @Override
    public ErpSalesQuote selectErpSalesQuoteByQuoteId(Long quoteId)
    {
        return erpSalesQuoteMapper.selectErpSalesQuoteByQuoteId(quoteId);
    }

    /**
     * 查询销售报价单列表
     */
    @Override
    public List<ErpSalesQuote> selectErpSalesQuoteList(ErpSalesQuote erpSalesQuote)
    {
        return erpSalesQuoteMapper.selectErpSalesQuoteList(erpSalesQuote);
    }

    /**
     * 新增销售报价单
     */
    @Transactional
    @Override
    public int insertErpSalesQuote(ErpSalesQuote erpSalesQuote)
    {
        erpSalesQuote.setCreateTime(DateUtils.getNowDate());
        int rows = erpSalesQuoteMapper.insertErpSalesQuote(erpSalesQuote);
        insertErpSalesQuoteLine(erpSalesQuote);
        return rows;
    }

    /**
     * 修改销售报价单
     */
    @Transactional
    @Override
    public int updateErpSalesQuote(ErpSalesQuote erpSalesQuote)
    {
        erpSalesQuote.setUpdateTime(DateUtils.getNowDate());
        erpSalesQuoteMapper.deleteErpSalesQuoteLineByQuoteId(erpSalesQuote.getQuoteId());
        insertErpSalesQuoteLine(erpSalesQuote);
        return erpSalesQuoteMapper.updateErpSalesQuote(erpSalesQuote);
    }

    /**
     * 批量删除销售报价单
     */
    @Transactional
    @Override
    public int deleteErpSalesQuoteByQuoteIds(Long[] quoteIds)
    {
        erpSalesQuoteMapper.deleteErpSalesQuoteLineByQuoteIds(quoteIds);
        return erpSalesQuoteMapper.deleteErpSalesQuoteByQuoteIds(quoteIds);
    }

    /**
     * 删除销售报价单信息
     */
    @Transactional
    @Override
    public int deleteErpSalesQuoteByQuoteId(Long quoteId)
    {
        erpSalesQuoteMapper.deleteErpSalesQuoteLineByQuoteId(quoteId);
        return erpSalesQuoteMapper.deleteErpSalesQuoteByQuoteId(quoteId);
    }

    /**
     * 新增销售报价单明细信息
     */
    public void insertErpSalesQuoteLine(ErpSalesQuote erpSalesQuote)
    {
        List<ErpSalesQuoteLine> erpSalesQuoteLineList = erpSalesQuote.getErpSalesQuoteLineList();
        if (StringUtils.isNotNull(erpSalesQuoteLineList))
        {
            for (ErpSalesQuoteLine line : erpSalesQuoteLineList)
            {
                line.setQuoteId(erpSalesQuote.getQuoteId());
            }
            if (erpSalesQuoteLineList.size() > 0)
            {
                erpSalesQuoteMapper.batchErpSalesQuoteLine(erpSalesQuoteLineList);
            }
        }
    }
}
