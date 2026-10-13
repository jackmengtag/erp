package com.ruoyi.purchase.service;

import java.util.List;
import com.ruoyi.purchase.domain.ErpPurchaseReq;

/**
 * 采购申请Service接口
 *
 * @author erp
 */
public interface IErpPurchaseReqService
{
    /**
     * 查询采购申请
     */
    public ErpPurchaseReq selectErpPurchaseReqByReqId(Long reqId);

    /**
     * 查询采购申请列表
     */
    public List<ErpPurchaseReq> selectErpPurchaseReqList(ErpPurchaseReq erpPurchaseReq);

    /**
     * 新增采购申请
     */
    public int insertErpPurchaseReq(ErpPurchaseReq erpPurchaseReq);

    /**
     * 修改采购申请
     */
    public int updateErpPurchaseReq(ErpPurchaseReq erpPurchaseReq);

    /**
     * 批量删除采购申请
     */
    public int deleteErpPurchaseReqByReqIds(Long[] reqIds);

    /**
     * 删除采购申请信息
     */
    public int deleteErpPurchaseReqByReqId(Long reqId);
}
