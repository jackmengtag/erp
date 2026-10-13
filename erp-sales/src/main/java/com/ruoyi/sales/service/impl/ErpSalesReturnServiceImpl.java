package com.ruoyi.sales.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.sales.domain.ErpSalesReturn;
import com.ruoyi.sales.domain.ErpSalesReturnLine;
import com.ruoyi.sales.mapper.ErpSalesReturnMapper;
import com.ruoyi.sales.service.IErpSalesReturnService;

/**
 * 销售退货单Service业务层处理
 *
 * @author erp
 */
@Service
public class ErpSalesReturnServiceImpl implements IErpSalesReturnService
{
    @Autowired
    private ErpSalesReturnMapper erpSalesReturnMapper;

    /**
     * 查询销售退货单
     */
    @Override
    public ErpSalesReturn selectErpSalesReturnByReturnId(Long returnId)
    {
        return erpSalesReturnMapper.selectErpSalesReturnByReturnId(returnId);
    }

    /**
     * 查询销售退货单列表
     */
    @Override
    public List<ErpSalesReturn> selectErpSalesReturnList(ErpSalesReturn erpSalesReturn)
    {
        return erpSalesReturnMapper.selectErpSalesReturnList(erpSalesReturn);
    }

    /**
     * 新增销售退货单
     */
    @Transactional
    @Override
    public int insertErpSalesReturn(ErpSalesReturn erpSalesReturn)
    {
        erpSalesReturn.setCreateTime(DateUtils.getNowDate());
        int rows = erpSalesReturnMapper.insertErpSalesReturn(erpSalesReturn);
        insertErpSalesReturnLine(erpSalesReturn);
        return rows;
    }

    /**
     * 修改销售退货单
     */
    @Transactional
    @Override
    public int updateErpSalesReturn(ErpSalesReturn erpSalesReturn)
    {
        erpSalesReturn.setUpdateTime(DateUtils.getNowDate());
        erpSalesReturnMapper.deleteErpSalesReturnLineByReturnId(erpSalesReturn.getReturnId());
        insertErpSalesReturnLine(erpSalesReturn);
        return erpSalesReturnMapper.updateErpSalesReturn(erpSalesReturn);
    }

    /**
     * 批量删除销售退货单
     */
    @Transactional
    @Override
    public int deleteErpSalesReturnByReturnIds(Long[] returnIds)
    {
        erpSalesReturnMapper.deleteErpSalesReturnLineByReturnIds(returnIds);
        return erpSalesReturnMapper.deleteErpSalesReturnByReturnIds(returnIds);
    }

    /**
     * 删除销售退货单信息
     */
    @Transactional
    @Override
    public int deleteErpSalesReturnByReturnId(Long returnId)
    {
        erpSalesReturnMapper.deleteErpSalesReturnLineByReturnId(returnId);
        return erpSalesReturnMapper.deleteErpSalesReturnByReturnId(returnId);
    }

    /**
     * 新增销售退货单明细信息
     */
    public void insertErpSalesReturnLine(ErpSalesReturn erpSalesReturn)
    {
        List<ErpSalesReturnLine> erpSalesReturnLineList = erpSalesReturn.getErpSalesReturnLineList();
        if (StringUtils.isNotNull(erpSalesReturnLineList))
        {
            for (ErpSalesReturnLine line : erpSalesReturnLineList)
            {
                line.setReturnId(erpSalesReturn.getReturnId());
            }
            if (erpSalesReturnLineList.size() > 0)
            {
                erpSalesReturnMapper.batchErpSalesReturnLine(erpSalesReturnLineList);
            }
        }
    }
}
