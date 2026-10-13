package com.ruoyi.plan.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.plan.domain.ErpDemandForecast;
import com.ruoyi.plan.domain.ErpDemandForecastLine;
import com.ruoyi.plan.mapper.ErpDemandForecastMapper;
import com.ruoyi.plan.service.IErpDemandForecastService;

/**
 * 需求预测Service业务层处理
 *
 * @author erp
 */
@Service
public class ErpDemandForecastServiceImpl implements IErpDemandForecastService
{
    @Autowired
    private ErpDemandForecastMapper erpDemandForecastMapper;

    /**
     * 查询需求预测
     */
    @Override
    public ErpDemandForecast selectErpDemandForecastByForecastId(Long forecastId)
    {
        return erpDemandForecastMapper.selectErpDemandForecastByForecastId(forecastId);
    }

    /**
     * 查询需求预测列表
     */
    @Override
    public List<ErpDemandForecast> selectErpDemandForecastList(ErpDemandForecast erpDemandForecast)
    {
        return erpDemandForecastMapper.selectErpDemandForecastList(erpDemandForecast);
    }

    /**
     * 新增需求预测
     */
    @Transactional
    @Override
    public int insertErpDemandForecast(ErpDemandForecast erpDemandForecast)
    {
        erpDemandForecast.setCreateTime(DateUtils.getNowDate());
        int rows = erpDemandForecastMapper.insertErpDemandForecast(erpDemandForecast);
        insertErpDemandForecastLine(erpDemandForecast);
        return rows;
    }

    /**
     * 修改需求预测
     */
    @Transactional
    @Override
    public int updateErpDemandForecast(ErpDemandForecast erpDemandForecast)
    {
        erpDemandForecast.setUpdateTime(DateUtils.getNowDate());
        erpDemandForecastMapper.deleteErpDemandForecastLineByForecastId(erpDemandForecast.getForecastId());
        insertErpDemandForecastLine(erpDemandForecast);
        return erpDemandForecastMapper.updateErpDemandForecast(erpDemandForecast);
    }

    /**
     * 批量删除需求预测
     */
    @Transactional
    @Override
    public int deleteErpDemandForecastByForecastIds(Long[] forecastIds)
    {
        erpDemandForecastMapper.deleteErpDemandForecastLineByForecastIds(forecastIds);
        return erpDemandForecastMapper.deleteErpDemandForecastByForecastIds(forecastIds);
    }

    /**
     * 删除需求预测信息
     */
    @Transactional
    @Override
    public int deleteErpDemandForecastByForecastId(Long forecastId)
    {
        erpDemandForecastMapper.deleteErpDemandForecastLineByForecastId(forecastId);
        return erpDemandForecastMapper.deleteErpDemandForecastByForecastId(forecastId);
    }

    /**
     * 新增需求预测明细信息
     */
    public void insertErpDemandForecastLine(ErpDemandForecast erpDemandForecast)
    {
        List<ErpDemandForecastLine> erpDemandForecastLineList = erpDemandForecast.getErpDemandForecastLineList();
        if (StringUtils.isNotNull(erpDemandForecastLineList))
        {
            for (ErpDemandForecastLine line : erpDemandForecastLineList)
            {
                line.setForecastId(erpDemandForecast.getForecastId());
            }
            if (erpDemandForecastLineList.size() > 0)
            {
                erpDemandForecastMapper.batchErpDemandForecastLine(erpDemandForecastLineList);
            }
        }
    }
}
