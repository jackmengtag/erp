package com.ruoyi.mdm.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.mdm.domain.ErpRouting;
import com.ruoyi.mdm.domain.ErpRoutingOperation;
import com.ruoyi.mdm.mapper.ErpRoutingMapper;
import com.ruoyi.mdm.service.IErpRoutingService;

/**
 * 工艺路线主表Service业务层处理
 *
 * @author erp
 */
@Service
public class ErpRoutingServiceImpl implements IErpRoutingService
{
    @Autowired
    private ErpRoutingMapper erpRoutingMapper;

    /**
     * 查询工艺路线主表
     */
    @Override
    public ErpRouting selectErpRoutingByRoutingId(Long routingId)
    {
        return erpRoutingMapper.selectErpRoutingByRoutingId(routingId);
    }

    /**
     * 查询工艺路线主表列表
     */
    @Override
    public List<ErpRouting> selectErpRoutingList(ErpRouting erpRouting)
    {
        return erpRoutingMapper.selectErpRoutingList(erpRouting);
    }

    /**
     * 新增工艺路线主表
     */
    @Transactional
    @Override
    public int insertErpRouting(ErpRouting erpRouting)
    {
        erpRouting.setCreateTime(DateUtils.getNowDate());
        int rows = erpRoutingMapper.insertErpRouting(erpRouting);
        insertErpRoutingOperation(erpRouting);
        return rows;
    }

    /**
     * 修改工艺路线主表
     */
    @Transactional
    @Override
    public int updateErpRouting(ErpRouting erpRouting)
    {
        erpRouting.setUpdateTime(DateUtils.getNowDate());
        erpRoutingMapper.deleteErpRoutingOperationByRoutingId(erpRouting.getRoutingId());
        insertErpRoutingOperation(erpRouting);
        return erpRoutingMapper.updateErpRouting(erpRouting);
    }

    /**
     * 批量删除工艺路线主表
     */
    @Transactional
    @Override
    public int deleteErpRoutingByRoutingIds(Long[] routingIds)
    {
        erpRoutingMapper.deleteErpRoutingOperationByRoutingIds(routingIds);
        return erpRoutingMapper.deleteErpRoutingByRoutingIds(routingIds);
    }

    /**
     * 删除工艺路线主表信息
     */
    @Transactional
    @Override
    public int deleteErpRoutingByRoutingId(Long routingId)
    {
        erpRoutingMapper.deleteErpRoutingOperationByRoutingId(routingId);
        return erpRoutingMapper.deleteErpRoutingByRoutingId(routingId);
    }

    /**
     * 新增工序明细信息
     */
    public void insertErpRoutingOperation(ErpRouting erpRouting)
    {
        List<ErpRoutingOperation> erpRoutingOperationList = erpRouting.getErpRoutingOperationList();
        if (StringUtils.isNotNull(erpRoutingOperationList))
        {
            for (ErpRoutingOperation operation : erpRoutingOperationList)
            {
                operation.setRoutingId(erpRouting.getRoutingId());
            }
            if (erpRoutingOperationList.size() > 0)
            {
                erpRoutingMapper.batchErpRoutingOperation(erpRoutingOperationList);
            }
        }
    }
}
