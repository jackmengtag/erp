package com.ruoyi.purchase.service;

import java.util.List;
import com.ruoyi.purchase.domain.ErpGoodsReceipt;

/**
 * 收货单Service接口
 *
 * @author erp
 */
public interface IErpGoodsReceiptService
{
    /**
     * 查询收货单
     */
    public ErpGoodsReceipt selectErpGoodsReceiptByReceiptId(Long receiptId);

    /**
     * 查询收货单列表
     */
    public List<ErpGoodsReceipt> selectErpGoodsReceiptList(ErpGoodsReceipt erpGoodsReceipt);

    /**
     * 新增收货单
     */
    public int insertErpGoodsReceipt(ErpGoodsReceipt erpGoodsReceipt);

    /**
     * 修改收货单
     */
    public int updateErpGoodsReceipt(ErpGoodsReceipt erpGoodsReceipt);

    /**
     * 批量删除收货单
     */
    public int deleteErpGoodsReceiptByReceiptIds(Long[] receiptIds);

    /**
     * 删除收货单信息
     */
    public int deleteErpGoodsReceiptByReceiptId(Long receiptId);
}
