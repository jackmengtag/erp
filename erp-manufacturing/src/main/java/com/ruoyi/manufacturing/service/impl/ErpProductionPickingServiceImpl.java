package com.ruoyi.manufacturing.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.manufacturing.domain.ErpProductionPicking;
import com.ruoyi.manufacturing.domain.ErpProductionPickingLine;
import com.ruoyi.manufacturing.mapper.ErpProductionPickingMapper;
import com.ruoyi.manufacturing.service.IErpProductionPickingService;

/**
 * 生产领料单Service业务层处理
 *
 * @author erp
 */
@Service
public class ErpProductionPickingServiceImpl implements IErpProductionPickingService
{
    @Autowired
    private ErpProductionPickingMapper erpProductionPickingMapper;

    /**
     * 查询生产领料单
     */
    @Override
    public ErpProductionPicking selectErpProductionPickingByPickingId(Long pickingId)
    {
        return erpProductionPickingMapper.selectErpProductionPickingByPickingId(pickingId);
    }

    /**
     * 查询生产领料单列表
     */
    @Override
    public List<ErpProductionPicking> selectErpProductionPickingList(ErpProductionPicking erpProductionPicking)
    {
        return erpProductionPickingMapper.selectErpProductionPickingList(erpProductionPicking);
    }

    /**
     * 新增生产领料单
     */
    @Transactional
    @Override
    public int insertErpProductionPicking(ErpProductionPicking erpProductionPicking)
    {
        erpProductionPicking.setCreateTime(DateUtils.getNowDate());
        int rows = erpProductionPickingMapper.insertErpProductionPicking(erpProductionPicking);
        insertErpProductionPickingLine(erpProductionPicking);
        return rows;
    }

    /**
     * 修改生产领料单
     */
    @Transactional
    @Override
    public int updateErpProductionPicking(ErpProductionPicking erpProductionPicking)
    {
        erpProductionPicking.setUpdateTime(DateUtils.getNowDate());
        erpProductionPickingMapper.deleteErpProductionPickingLineByPickingId(erpProductionPicking.getPickingId());
        insertErpProductionPickingLine(erpProductionPicking);
        return erpProductionPickingMapper.updateErpProductionPicking(erpProductionPicking);
    }

    /**
     * 批量删除生产领料单
     */
    @Transactional
    @Override
    public int deleteErpProductionPickingByPickingIds(Long[] pickingIds)
    {
        erpProductionPickingMapper.deleteErpProductionPickingLineByPickingIds(pickingIds);
        return erpProductionPickingMapper.deleteErpProductionPickingByPickingIds(pickingIds);
    }

    /**
     * 删除生产领料单信息
     */
    @Transactional
    @Override
    public int deleteErpProductionPickingByPickingId(Long pickingId)
    {
        erpProductionPickingMapper.deleteErpProductionPickingLineByPickingId(pickingId);
        return erpProductionPickingMapper.deleteErpProductionPickingByPickingId(pickingId);
    }

    /**
     * 新增生产领料明细信息
     */
    public void insertErpProductionPickingLine(ErpProductionPicking erpProductionPicking)
    {
        List<ErpProductionPickingLine> erpProductionPickingLineList = erpProductionPicking.getErpProductionPickingLineList();
        if (StringUtils.isNotNull(erpProductionPickingLineList))
        {
            for (ErpProductionPickingLine line : erpProductionPickingLineList)
            {
                line.setPickingId(erpProductionPicking.getPickingId());
            }
            if (erpProductionPickingLineList.size() > 0)
            {
                erpProductionPickingMapper.batchErpProductionPickingLine(erpProductionPickingLineList);
            }
        }
    }
}
