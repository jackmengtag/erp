package com.ruoyi.finance.service;

import java.util.List;
import com.ruoyi.finance.domain.ErpReceipt;

/**
 * 收款单Service接口
 *
 * @author erp
 */
public interface IErpReceiptService
{
    /**
     * 查询收款单
     */
    public ErpReceipt selectErpReceiptByReceiptId(Long receiptId);

    /**
     * 查询收款单列表
     */
    public List<ErpReceipt> selectErpReceiptList(ErpReceipt erpReceipt);

    /**
     * 新增收款单
     */
    public int insertErpReceipt(ErpReceipt erpReceipt);

    /**
     * 修改收款单
     */
    public int updateErpReceipt(ErpReceipt erpReceipt);

    /**
     * 批量删除收款单
     */
    public int deleteErpReceiptByReceiptIds(Long[] receiptIds);

    /**
     * 删除收款单信息
     */
    public int deleteErpReceiptByReceiptId(Long receiptId);
}
