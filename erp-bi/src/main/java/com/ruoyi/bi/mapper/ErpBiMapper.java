package com.ruoyi.bi.mapper;

import java.util.List;
import java.util.Map;

/**
 * 报表与经营分析Mapper接口
 *
 * @author erp
 */
public interface ErpBiMapper
{
    /**
     * 经营驾驶舱核心指标
     */
    public Map<String, Object> selectDashboard();

    /**
     * 订单交付明细
     */
    public List<Map<String, Object>> selectOrderDelivery(Map<String, Object> params);

    /**
     * 库存库龄分析
     */
    public List<Map<String, Object>> selectInventoryAge(Map<String, Object> params);

    /**
     * 采购订单准时交付统计(按供应商)
     */
    public List<Map<String, Object>> selectSupplierDelivery(Map<String, Object> params);

    /**
     * 生产达成率统计(按工单)
     */
    public List<Map<String, Object>> selectProductionAchievement(Map<String, Object> params);
}
