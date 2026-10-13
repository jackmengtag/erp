package com.ruoyi.finance.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.finance.domain.ErpReceipt;
import com.ruoyi.finance.mapper.ErpReceiptMapper;
import com.ruoyi.finance.service.IErpReceiptService;

/**
 * 收款单Service业务层处理
 *
 * @author erp
 */
@Service
public class ErpReceiptServiceImpl implements IErpReceiptService
{
    @Autowired
    private ErpReceiptMapper erpReceiptMapper;

    /**
     * 查询收款单
     */
    @Override
    public ErpReceipt selectErpReceiptByReceiptId(Long receiptId)
    {
        return erpReceiptMapper.selectErpReceiptByReceiptId(receiptId);
    }

    /**
     * 查询收款单列表
     */
    @Override
    public List<ErpReceipt> selectErpReceiptList(ErpReceipt erpReceipt)
    {
        return erpReceiptMapper.selectErpReceiptList(erpReceipt);
    }

    /**
     * 新增收款单
     */
    @Override
    public int insertErpReceipt(ErpReceipt erpReceipt)
    {
        erpReceipt.setCreateTime(DateUtils.getNowDate());
        return erpReceiptMapper.insertErpReceipt(erpReceipt);
    }

    /**
     * 修改收款单
     */
    @Override
    public int updateErpReceipt(ErpReceipt erpReceipt)
    {
        erpReceipt.setUpdateTime(DateUtils.getNowDate());
        return erpReceiptMapper.updateErpReceipt(erpReceipt);
    }

    /**
     * 批量删除收款单
     */
    @Override
    public int deleteErpReceiptByReceiptIds(Long[] receiptIds)
    {
        return erpReceiptMapper.deleteErpReceiptByReceiptIds(receiptIds);
    }

    /**
     * 删除收款单信息
     */
    @Override
    public int deleteErpReceiptByReceiptId(Long receiptId)
    {
        return erpReceiptMapper.deleteErpReceiptByReceiptId(receiptId);
    }
}
