package com.ruoyi.purchase.mapper;

import java.util.List;
import com.ruoyi.purchase.domain.ErpGoodsReceipt;
import com.ruoyi.purchase.domain.ErpGoodsReceiptLine;

/**
 * 收货单Mapper接口
 *
 * @author erp
 */
public interface ErpGoodsReceiptMapper
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
     * 删除收货单
     */
    public int deleteErpGoodsReceiptByReceiptId(Long receiptId);

    /**
     * 批量删除收货单
     */
    public int deleteErpGoodsReceiptByReceiptIds(Long[] receiptIds);

    /**
     * 批量删除收货单明细
     */
    public int deleteErpGoodsReceiptLineByReceiptIds(Long[] receiptIds);

    /**
     * 删除收货单明细信息
     */
    public int deleteErpGoodsReceiptLineByReceiptId(Long receiptId);

    /**
     * 批量新增收货单明细
     */
    public int batchErpGoodsReceiptLine(List<ErpGoodsReceiptLine> erpGoodsReceiptLineList);
}
