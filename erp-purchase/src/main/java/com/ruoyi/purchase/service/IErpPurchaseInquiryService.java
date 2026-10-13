package com.ruoyi.purchase.service;

import java.util.List;
import com.ruoyi.purchase.domain.ErpPurchaseInquiry;

/**
 * 采购询价Service接口
 *
 * @author erp
 */
public interface IErpPurchaseInquiryService
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
     * 批量删除采购询价
     */
    public int deleteErpPurchaseInquiryByInquiryIds(Long[] inquiryIds);

    /**
     * 删除采购询价信息
     */
    public int deleteErpPurchaseInquiryByInquiryId(Long inquiryId);
}
