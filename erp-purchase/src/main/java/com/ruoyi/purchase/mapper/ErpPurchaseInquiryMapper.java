package com.ruoyi.purchase.mapper;

import java.util.List;
import com.ruoyi.purchase.domain.ErpPurchaseInquiry;
import com.ruoyi.purchase.domain.ErpPurchaseInquiryLine;

/**
 * 采购询价Mapper接口
 *
 * @author erp
 */
public interface ErpPurchaseInquiryMapper
{
    /**
     * 查询采购询价
     */
    public ErpPurchaseInquiry selectErpPurchaseInquiryByInquiryId(Long inquiryId);

    /**
     * 查询采购询价列表
     */
    public List<ErpPurchaseInquiry> selectErpPurchaseInquiryList(ErpPurchaseInquiry erpPurchaseInquiry);

    /**
     * 新增采购询价
     */
    public int insertErpPurchaseInquiry(ErpPurchaseInquiry erpPurchaseInquiry);

    /**
     * 修改采购询价
     */
    public int updateErpPurchaseInquiry(ErpPurchaseInquiry erpPurchaseInquiry);

    /**
     * 删除采购询价
     */
    public int deleteErpPurchaseInquiryByInquiryId(Long inquiryId);

    /**
     * 批量删除采购询价
     */
    public int deleteErpPurchaseInquiryByInquiryIds(Long[] inquiryIds);

    /**
     * 批量删除采购询价明细
     */
    public int deleteErpPurchaseInquiryLineByInquiryIds(Long[] inquiryIds);

    /**
     * 删除采购询价明细信息
     */
    public int deleteErpPurchaseInquiryLineByInquiryId(Long inquiryId);

    /**
     * 批量新增采购询价明细
     */
    public int batchErpPurchaseInquiryLine(List<ErpPurchaseInquiryLine> erpPurchaseInquiryLineList);
}
