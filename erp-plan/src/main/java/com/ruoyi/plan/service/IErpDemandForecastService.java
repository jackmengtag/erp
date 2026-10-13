package com.ruoyi.plan.service;

import java.util.List;
import com.ruoyi.plan.domain.ErpDemandForecast;

/**
 * 需求预测Service接口
 *
 * @author erp
 */
public interface IErpDemandForecastService
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
     * 批量删除需求预测
     */
    public int deleteErpDemandForecastByForecastIds(Long[] forecastIds);

    /**
     * 删除需求预测信息
     */
    public int deleteErpDemandForecastByForecastId(Long forecastId);
}
