package com.ruoyi.purchase.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.purchase.domain.ErpPurchaseInquiry;
import com.ruoyi.purchase.domain.ErpPurchaseInquiryLine;
import com.ruoyi.purchase.mapper.ErpPurchaseInquiryMapper;
import com.ruoyi.purchase.service.IErpPurchaseInquiryService;

/**
 * 采购询价Service业务层处理
 *
 * @author erp
 */
@Service
public class ErpPurchaseInquiryServiceImpl implements IErpPurchaseInquiryService
{
    @Autowired
    private ErpPurchaseInquiryMapper erpPurchaseInquiryMapper;

    /**
     * 查询采购询价
     */
    @Override
    public ErpPurchaseInquiry selectErpPurchaseInquiryByInquiryId(Long inquiryId)
    {
        return erpPurchaseInquiryMapper.selectErpPurchaseInquiryByInquiryId(inquiryId);
    }

    /**
     * 查询采购询价列表
     */
    @Override
    public List<ErpPurchaseInquiry> selectErpPurchaseInquiryList(ErpPurchaseInquiry erpPurchaseInquiry)
    {
        return erpPurchaseInquiryMapper.selectErpPurchaseInquiryList(erpPurchaseInquiry);
    }

    /**
     * 新增采购询价
     */
    @Transactional
    @Override
    public int insertErpPurchaseInquiry(ErpPurchaseInquiry erpPurchaseInquiry)
    {
        erpPurchaseInquiry.setCreateTime(DateUtils.getNowDate());
        int rows = erpPurchaseInquiryMapper.insertErpPurchaseInquiry(erpPurchaseInquiry);
        insertErpPurchaseInquiryLine(erpPurchaseInquiry);
        return rows;
    }

    /**
     * 修改采购询价
     */
    @Transactional
    @Override
    public int updateErpPurchaseInquiry(ErpPurchaseInquiry erpPurchaseInquiry)
    {
        erpPurchaseInquiry.setUpdateTime(DateUtils.getNowDate());
        erpPurchaseInquiryMapper.deleteErpPurchaseInquiryLineByInquiryId(erpPurchaseInquiry.getInquiryId());
        insertErpPurchaseInquiryLine(erpPurchaseInquiry);
        return erpPurchaseInquiryMapper.updateErpPurchaseInquiry(erpPurchaseInquiry);
    }

    /**
     * 批量删除采购询价
     */
    @Transactional
    @Override
    public int deleteErpPurchaseInquiryByInquiryIds(Long[] inquiryIds)
    {
        erpPurchaseInquiryMapper.deleteErpPurchaseInquiryLineByInquiryIds(inquiryIds);
        return erpPurchaseInquiryMapper.deleteErpPurchaseInquiryByInquiryIds(inquiryIds);
    }

    /**
     * 删除采购询价信息
     */
    @Transactional
    @Override
    public int deleteErpPurchaseInquiryByInquiryId(Long inquiryId)
    {
        erpPurchaseInquiryMapper.deleteErpPurchaseInquiryLineByInquiryId(inquiryId);
        return erpPurchaseInquiryMapper.deleteErpPurchaseInquiryByInquiryId(inquiryId);
    }

    /**
     * 新增采购询价明细信息
     */
    public void insertErpPurchaseInquiryLine(ErpPurchaseInquiry erpPurchaseInquiry)
    {
        List<ErpPurchaseInquiryLine> erpPurchaseInquiryLineList = erpPurchaseInquiry.getErpPurchaseInquiryLineList();
        if (StringUtils.isNotNull(erpPurchaseInquiryLineList))
        {
            for (ErpPurchaseInquiryLine line : erpPurchaseInquiryLineList)
            {
                line.setInquiryId(erpPurchaseInquiry.getInquiryId());
            }
            if (erpPurchaseInquiryLineList.size() > 0)
            {
                erpPurchaseInquiryMapper.batchErpPurchaseInquiryLine(erpPurchaseInquiryLineList);
            }
        }
    }
}
