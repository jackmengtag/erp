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
import com.ruoyi.mdm.domain.ErpRouting;
import com.ruoyi.mdm.service.IErpRoutingService;

/**
 * 工艺路线主表Controller
 *
 * @author erp
 */
@RestController
@RequestMapping("/mdm/routing")
public class ErpRoutingController extends BaseController
{
    @Autowired
    private IErpRoutingService erpRoutingService;

    /**
     * 查询工艺路线主表列表
     */
    @PreAuthorize("@ss.hasPermi('mdm:routing:list')")
    @GetMapping("/list")
    public TableDataInfo list(ErpRouting erpRouting)
    {
        startPage();
        List<ErpRouting> list = erpRoutingService.selectErpRoutingList(erpRouting);
        return getDataTable(list);
    }

    /**
     * 导出工艺路线主表列表
     */
    @PreAuthorize("@ss.hasPermi('mdm:routing:export')")
    @Log(title = "工艺路线主表", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ErpRouting erpRouting)
    {
        List<ErpRouting> list = erpRoutingService.selectErpRoutingList(erpRouting);
        ExcelUtil<ErpRouting> util = new ExcelUtil<ErpRouting>(ErpRouting.class);
        util.exportExcel(response, list, "工艺路线主表数据");
    }

    /**
     * 获取工艺路线主表详细信息
     */
    @PreAuthorize("@ss.hasPermi('mdm:routing:query')")
    @GetMapping(value = "/{routingId}")
    public AjaxResult getInfo(@PathVariable("routingId") Long routingId)
    {
        return success(erpRoutingService.selectErpRoutingByRoutingId(routingId));
    }

    /**
     * 新增工艺路线主表
     */
    @PreAuthorize("@ss.hasPermi('mdm:routing:add')")
    @Log(title = "工艺路线主表", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ErpRouting erpRouting)
    {
        erpRouting.setCreateBy(getUsername());
        return toAjax(erpRoutingService.insertErpRouting(erpRouting));
    }

    /**
     * 修改工艺路线主表
     */
    @PreAuthorize("@ss.hasPermi('mdm:routing:edit')")
    @Log(title = "工艺路线主表", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ErpRouting erpRouting)
    {
        erpRouting.setUpdateBy(getUsername());
        return toAjax(erpRoutingService.updateErpRouting(erpRouting));
    }

    /**
     * 删除工艺路线主表
     */
    @PreAuthorize("@ss.hasPermi('mdm:routing:remove')")
    @Log(title = "工艺路线主表", businessType = BusinessType.DELETE)
    @DeleteMapping("/{routingIds}")
    public AjaxResult remove(@PathVariable Long[] routingIds)
    {
        return toAjax(erpRoutingService.deleteErpRoutingByRoutingIds(routingIds));
    }
}
