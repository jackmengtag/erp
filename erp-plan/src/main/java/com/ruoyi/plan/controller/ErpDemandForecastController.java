package com.ruoyi.plan.controller;

import java.util.List;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.plan.domain.ErpDemandForecast;
import com.ruoyi.plan.service.IErpDemandForecastService;

/**
 * 需求预测Controller
 *
 * @author erp
 */
@RestController
@RequestMapping("/plan/forecast")
public class ErpDemandForecastController extends BaseController
{
    @Autowired
    private IErpDemandForecastService erpDemandForecastService;

    /**
     * 查询需求预测列表
     */
    @PreAuthorize("@ss.hasPermi('plan:forecast:list')")
    @GetMapping("/list")
    public TableDataInfo list(ErpDemandForecast erpDemandForecast)
    {
        startPage();
        List<ErpDemandForecast> list = erpDemandForecastService.selectErpDemandForecastList(erpDemandForecast);
        return getDataTable(list);
    }

    /**
     * 导出需求预测列表
     */
    @PreAuthorize("@ss.hasPermi('plan:forecast:export')")
    @Log(title = "需求预测", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ErpDemandForecast erpDemandForecast)
    {
        List<ErpDemandForecast> list = erpDemandForecastService.selectErpDemandForecastList(erpDemandForecast);
        ExcelUtil<ErpDemandForecast> util = new ExcelUtil<ErpDemandForecast>(ErpDemandForecast.class);
        util.exportExcel(response, list, "需求预测数据");
    }

    /**
     * 获取需求预测详细信息
     */
    @PreAuthorize("@ss.hasPermi('plan:forecast:query')")
    @GetMapping(value = "/{forecastId}")
    public AjaxResult getInfo(@PathVariable("forecastId") Long forecastId)
    {
        return success(erpDemandForecastService.selectErpDemandForecastByForecastId(forecastId));
    }

    /**
     * 新增需求预测
     */
    @PreAuthorize("@ss.hasPermi('plan:forecast:add')")
    @Log(title = "需求预测", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ErpDemandForecast erpDemandForecast)
    {
        erpDemandForecast.setCreateBy(getUsername());
        return toAjax(erpDemandForecastService.insertErpDemandForecast(erpDemandForecast));
    }

    /**
     * 修改需求预测
     */
    @PreAuthorize("@ss.hasPermi('plan:forecast:edit')")
    @Log(title = "需求预测", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ErpDemandForecast erpDemandForecast)
    {
        erpDemandForecast.setUpdateBy(getUsername());
        return toAjax(erpDemandForecastService.updateErpDemandForecast(erpDemandForecast));
    }

    /**
     * 删除需求预测
     */
    @PreAuthorize("@ss.hasPermi('plan:forecast:remove')")
    @Log(title = "需求预测", businessType = BusinessType.DELETE)
    @DeleteMapping("/{forecastIds}")
    public AjaxResult remove(@PathVariable Long[] forecastIds)
    {
        return toAjax(erpDemandForecastService.deleteErpDemandForecastByForecastIds(forecastIds));
    }
}
