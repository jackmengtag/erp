package com.ruoyi.purchase.mapper;

import java.util.List;
import com.ruoyi.purchase.domain.ErpPurchaseReq;
import com.ruoyi.purchase.domain.ErpPurchaseReqLine;

/**
 * 采购申请Mapper接口
 *
 * @author erp
 */
public interface ErpPurchaseReqMapper
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
     * 删除采购申请
     */
    public int deleteErpPurchaseReqByReqId(Long reqId);

    /**
     * 批量删除采购申请
     */
    public int deleteErpPurchaseReqByReqIds(Long[] reqIds);

    /**
     * 批量删除采购申请明细
     */
    public int deleteErpPurchaseReqLineByReqIds(Long[] reqIds);

    /**
     * 删除采购申请明细信息
     */
    public int deleteErpPurchaseReqLineByReqId(Long reqId);

    /**
     * 批量新增采购申请明细
     */
    public int batchErpPurchaseReqLine(List<ErpPurchaseReqLine> erpPurchaseReqLineList);
}
