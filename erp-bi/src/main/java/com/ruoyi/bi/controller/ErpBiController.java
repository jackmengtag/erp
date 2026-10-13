package com.ruoyi.bi.controller;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.bi.mapper.ErpBiMapper;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;

/**
 * 报表与经营分析Controller
 *
 * @author erp
 */
@RestController
@RequestMapping("/bi")
public class ErpBiController extends BaseController
{
    @Autowired
    private ErpBiMapper erpBiMapper;

    /**
     * 经营驾驶舱核心指标
     */
    @PreAuthorize("@ss.hasPermi('bi:dashboard:list')")
    @GetMapping("/dashboard")
    public AjaxResult dashboard()
    {
        return success(erpBiMapper.selectDashboard());
    }

    /**
     * 订单交付报表
     */
    @PreAuthorize("@ss.hasPermi('bi:orderDelivery:list')")
    @GetMapping("/orderDelivery")
    public AjaxResult orderDelivery(@RequestParam Map<String, Object> params)
    {
        List<Map<String, Object>> list = erpBiMapper.selectOrderDelivery(params);
        return success(list);
    }

    /**
     * 库存库龄报表
     */
    @PreAuthorize("@ss.hasPermi('bi:inventoryAge:list')")
    @GetMapping("/inventoryAge")
    public AjaxResult inventoryAge(@RequestParam Map<String, Object> params)
    {
        List<Map<String, Object>> list = erpBiMapper.selectInventoryAge(params);
        return success(list);
    }

    /**
     * 供应商准时交付统计
     */
    @PreAuthorize("@ss.hasPermi('bi:orderDelivery:list')")
    @GetMapping("/supplierDelivery")
    public AjaxResult supplierDelivery(@RequestParam Map<String, Object> params)
    {
        return success(erpBiMapper.selectSupplierDelivery(params));
    }

    /**
     * 生产计划达成率
     */
    @PreAuthorize("@ss.hasPermi('bi:orderDelivery:list')")
    @GetMapping("/productionAchievement")
    public AjaxResult productionAchievement(@RequestParam Map<String, Object> params)
    {
        return success(erpBiMapper.selectProductionAchievement(params));
    }
}
