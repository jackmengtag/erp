package com.ruoyi.purchase.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.purchase.domain.ErpGoodsReceipt;
import com.ruoyi.purchase.domain.ErpGoodsReceiptLine;
import com.ruoyi.purchase.mapper.ErpGoodsReceiptMapper;
import com.ruoyi.purchase.service.IErpGoodsReceiptService;

/**
 * 收货单Service业务层处理
 *
 * @author erp
 */
@Service
public class ErpGoodsReceiptServiceImpl implements IErpGoodsReceiptService
{
    @Autowired
    private ErpGoodsReceiptMapper erpGoodsReceiptMapper;

    /**
     * 查询收货单
     */
    @Override
    public ErpGoodsReceipt selectErpGoodsReceiptByReceiptId(Long receiptId)
    {
        return erpGoodsReceiptMapper.selectErpGoodsReceiptByReceiptId(receiptId);
    }

    /**
     * 查询收货单列表
     */
    @Override
    public List<ErpGoodsReceipt> selectErpGoodsReceiptList(ErpGoodsReceipt erpGoodsReceipt)
    {
        return erpGoodsReceiptMapper.selectErpGoodsReceiptList(erpGoodsReceipt);
    }

    /**
     * 新增收货单
     */
    @Transactional
    @Override
    public int insertErpGoodsReceipt(ErpGoodsReceipt erpGoodsReceipt)
    {
        erpGoodsReceipt.setCreateTime(DateUtils.getNowDate());
        int rows = erpGoodsReceiptMapper.insertErpGoodsReceipt(erpGoodsReceipt);
        insertErpGoodsReceiptLine(erpGoodsReceipt);
        return rows;
    }

    /**
     * 修改收货单
     */
    @Transactional
    @Override
    public int updateErpGoodsReceipt(ErpGoodsReceipt erpGoodsReceipt)
    {
        erpGoodsReceipt.setUpdateTime(DateUtils.getNowDate());
        erpGoodsReceiptMapper.deleteErpGoodsReceiptLineByReceiptId(erpGoodsReceipt.getReceiptId());
        insertErpGoodsReceiptLine(erpGoodsReceipt);
        return erpGoodsReceiptMapper.updateErpGoodsReceipt(erpGoodsReceipt);
    }

    /**
     * 批量删除收货单
     */
    @Transactional
    @Override
    public int deleteErpGoodsReceiptByReceiptIds(Long[] receiptIds)
    {
        erpGoodsReceiptMapper.deleteErpGoodsReceiptLineByReceiptIds(receiptIds);
        return erpGoodsReceiptMapper.deleteErpGoodsReceiptByReceiptIds(receiptIds);
    }

    /**
     * 删除收货单信息
     */
    @Transactional
    @Override
    public int deleteErpGoodsReceiptByReceiptId(Long receiptId)
    {
        erpGoodsReceiptMapper.deleteErpGoodsReceiptLineByReceiptId(receiptId);
        return erpGoodsReceiptMapper.deleteErpGoodsReceiptByReceiptId(receiptId);
    }

    /**
     * 新增收货单明细信息
     */
    public void insertErpGoodsReceiptLine(ErpGoodsReceipt erpGoodsReceipt)
    {
        List<ErpGoodsReceiptLine> erpGoodsReceiptLineList = erpGoodsReceipt.getErpGoodsReceiptLineList();
        if (StringUtils.isNotNull(erpGoodsReceiptLineList))
        {
            for (ErpGoodsReceiptLine line : erpGoodsReceiptLineList)
            {
                line.setReceiptId(erpGoodsReceipt.getReceiptId());
            }
            if (erpGoodsReceiptLineList.size() > 0)
            {
                erpGoodsReceiptMapper.batchErpGoodsReceiptLine(erpGoodsReceiptLineList);
            }
        }
    }
}
