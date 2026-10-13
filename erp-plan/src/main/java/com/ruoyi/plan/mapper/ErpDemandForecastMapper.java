package com.ruoyi.plan.mapper;

import java.util.List;
import com.ruoyi.plan.domain.ErpDemandForecast;
import com.ruoyi.plan.domain.ErpDemandForecastLine;

/**
 * 需求预测Mapper接口
 *
 * @author erp
 */
public interface ErpDemandForecastMapper
{
    /**
     * 查询需求预测
     */
    public ErpDemandForecast selectErpDemandForecastByForecastId(Long forecastId);

    /**
     * 查询需求预测列表
     */
    public List<ErpDemandForecast> selectErpDemandForecastList(ErpDemandForecast erpDemandForecast);

    /**
     * 新增需求预测
     */
    public int insertErpDemandForecast(ErpDemandForecast erpDemandForecast);

    /**
     * 修改需求预测
     */
    public int updateErpDemandForecast(ErpDemandForecast erpDemandForecast);

    /**
     * 删除需求预测
     */
    public int deleteErpDemandForecastByForecastId(Long forecastId);

    /**
     * 批量删除需求预测
     */
    public int deleteErpDemandForecastByForecastIds(Long[] forecastIds);

    /**
     * 批量删除需求预测明细
     */
    public int deleteErpDemandForecastLineByForecastIds(Long[] forecastIds);

    /**
     * 删除需求预测明细信息
     */
    public int deleteErpDemandForecastLineByForecastId(Long forecastId);

    /**
     * 批量新增需求预测明细
     */
    public int batchErpDemandForecastLine(List<ErpDemandForecastLine> erpDemandForecastLineList);
}
