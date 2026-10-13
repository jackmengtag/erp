package com.ruoyi.quality.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.quality.domain.ErpInspectionOrder;
import com.ruoyi.quality.domain.ErpInspectionResult;
import com.ruoyi.quality.mapper.ErpInspectionOrderMapper;
import com.ruoyi.quality.service.IErpInspectionOrderService;

/**
 * 检验单Service业务层处理
 *
 * @author erp
 */
@Service
public class ErpInspectionOrderServiceImpl implements IErpInspectionOrderService
{
    @Autowired
    private ErpInspectionOrderMapper erpInspectionOrderMapper;

    /**
     * 查询检验单
     */
    @Override
    public ErpInspectionOrder selectErpInspectionOrderByInspectId(Long inspectId)
    {
        return erpInspectionOrderMapper.selectErpInspectionOrderByInspectId(inspectId);
    }

    /**
     * 查询检验单列表
     */
    @Override
    public List<ErpInspectionOrder> selectErpInspectionOrderList(ErpInspectionOrder erpInspectionOrder)
    {
        return erpInspectionOrderMapper.selectErpInspectionOrderList(erpInspectionOrder);
    }

    /**
     * 新增检验单
     */
    @Transactional
    @Override
    public int insertErpInspectionOrder(ErpInspectionOrder erpInspectionOrder)
    {
        erpInspectionOrder.setCreateTime(DateUtils.getNowDate());
        int rows = erpInspectionOrderMapper.insertErpInspectionOrder(erpInspectionOrder);
        insertErpInspectionResult(erpInspectionOrder);
        return rows;
    }

    /**
     * 修改检验单
     */
    @Transactional
    @Override
    public int updateErpInspectionOrder(ErpInspectionOrder erpInspectionOrder)
    {
        erpInspectionOrder.setUpdateTime(DateUtils.getNowDate());
        erpInspectionOrderMapper.deleteErpInspectionResultByInspectId(erpInspectionOrder.getInspectId());
        insertErpInspectionResult(erpInspectionOrder);
        return erpInspectionOrderMapper.updateErpInspectionOrder(erpInspectionOrder);
    }

    /**
     * 批量删除检验单
     */
    @Transactional
    @Override
    public int deleteErpInspectionOrderByInspectIds(Long[] inspectIds)
    {
        erpInspectionOrderMapper.deleteErpInspectionResultByInspectIds(inspectIds);
        return erpInspectionOrderMapper.deleteErpInspectionOrderByInspectIds(inspectIds);
    }

    /**
     * 删除检验单信息
     */
    @Transactional
    @Override
    public int deleteErpInspectionOrderByInspectId(Long inspectId)
    {
        erpInspectionOrderMapper.deleteErpInspectionResultByInspectId(inspectId);
        return erpInspectionOrderMapper.deleteErpInspectionOrderByInspectId(inspectId);
    }

    /**
     * 新增检验结果明细信息
     */
    public void insertErpInspectionResult(ErpInspectionOrder erpInspectionOrder)
    {
        List<ErpInspectionResult> erpInspectionResultList = erpInspectionOrder.getErpInspectionResultList();
        if (StringUtils.isNotNull(erpInspectionResultList))
        {
            for (ErpInspectionResult result : erpInspectionResultList)
            {
                result.setInspectId(erpInspectionOrder.getInspectId());
            }
            if (erpInspectionResultList.size() > 0)
            {
                erpInspectionOrderMapper.batchErpInspectionResult(erpInspectionResultList);
            }
        }
    }
}
