package com.ruoyi.mdm.controller;

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
import com.ruoyi.mdm.domain.ErpWorkCenter;
import com.ruoyi.mdm.service.IErpWorkCenterService;

/**
 * 工作中心Controller
 *
 * @author erp
 */
@RestController
@RequestMapping("/mdm/workCenter")
public class ErpWorkCenterController extends BaseController
{
    @Autowired
    private IErpWorkCenterService erpWorkCenterService;

    /**
     * 查询工作中心列表
     */
    @PreAuthorize("@ss.hasPermi('mdm:workCenter:list')")
    @GetMapping("/list")
    public TableDataInfo list(ErpWorkCenter erpWorkCenter)
    {
        startPage();
        List<ErpWorkCenter> list = erpWorkCenterService.selectErpWorkCenterList(erpWorkCenter);
        return getDataTable(list);
    }

    /**
     * 导出工作中心列表
     */
    @PreAuthorize("@ss.hasPermi('mdm:workCenter:export')")
    @Log(title = "工作中心", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ErpWorkCenter erpWorkCenter)
    {
        List<ErpWorkCenter> list = erpWorkCenterService.selectErpWorkCenterList(erpWorkCenter);
        ExcelUtil<ErpWorkCenter> util = new ExcelUtil<ErpWorkCenter>(ErpWorkCenter.class);
        util.exportExcel(response, list, "工作中心数据");
    }

    /**
     * 获取工作中心详细信息
     */
    @PreAuthorize("@ss.hasPermi('mdm:workCenter:query')")
    @GetMapping(value = "/{wcId}")
    public AjaxResult getInfo(@PathVariable("wcId") Long wcId)
    {
        return success(erpWorkCenterService.selectErpWorkCenterByWcId(wcId));
    }

    /**
     * 新增工作中心
     */
    @PreAuthorize("@ss.hasPermi('mdm:workCenter:add')")
    @Log(title = "工作中心", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ErpWorkCenter erpWorkCenter)
    {
        erpWorkCenter.setCreateBy(getUsername());
        return toAjax(erpWorkCenterService.insertErpWorkCenter(erpWorkCenter));
    }

    /**
     * 修改工作中心
     */
    @PreAuthorize("@ss.hasPermi('mdm:workCenter:edit')")
    @Log(title = "工作中心", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ErpWorkCenter erpWorkCenter)
    {
        erpWorkCenter.setUpdateBy(getUsername());
        return toAjax(erpWorkCenterService.updateErpWorkCenter(erpWorkCenter));
    }

    /**
     * 删除工作中心
     */
    @PreAuthorize("@ss.hasPermi('mdm:workCenter:remove')")
    @Log(title = "工作中心", businessType = BusinessType.DELETE)
    @DeleteMapping("/{wcIds}")
    public AjaxResult remove(@PathVariable Long[] wcIds)
    {
        return toAjax(erpWorkCenterService.deleteErpWorkCenterByWcIds(wcIds));
    }
}
