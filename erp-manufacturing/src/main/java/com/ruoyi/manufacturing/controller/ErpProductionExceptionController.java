package com.ruoyi.manufacturing.controller;

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
import com.ruoyi.manufacturing.domain.ErpProductionException;
import com.ruoyi.manufacturing.service.IErpProductionExceptionService;

/**
 * 生产异常Controller
 *
 * @author erp
 */
@RestController
@RequestMapping("/manufacturing/exception")
public class ErpProductionExceptionController extends BaseController
{
    @Autowired
    private IErpProductionExceptionService erpProductionExceptionService;

    /**
     * 查询生产异常列表
     */
    @PreAuthorize("@ss.hasPermi('manufacturing:exception:list')")
    @GetMapping("/list")
    public TableDataInfo list(ErpProductionException erpProductionException)
    {
        startPage();
        List<ErpProductionException> list = erpProductionExceptionService.selectErpProductionExceptionList(erpProductionException);
        return getDataTable(list);
    }

    /**
     * 导出生产异常列表
     */
    @PreAuthorize("@ss.hasPermi('manufacturing:exception:export')")
    @Log(title = "生产异常", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ErpProductionException erpProductionException)
    {
        List<ErpProductionException> list = erpProductionExceptionService.selectErpProductionExceptionList(erpProductionException);
        ExcelUtil<ErpProductionException> util = new ExcelUtil<ErpProductionException>(ErpProductionException.class);
        util.exportExcel(response, list, "生产异常数据");
    }

    /**
     * 获取生产异常详细信息
     */
    @PreAuthorize("@ss.hasPermi('manufacturing:exception:query')")
    @GetMapping(value = "/{exceptionId}")
    public AjaxResult getInfo(@PathVariable("exceptionId") Long exceptionId)
    {
        return success(erpProductionExceptionService.selectErpProductionExceptionByExceptionId(exceptionId));
    }

    /**
     * 新增生产异常
     */
    @PreAuthorize("@ss.hasPermi('manufacturing:exception:add')")
    @Log(title = "生产异常", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ErpProductionException erpProductionException)
    {
        erpProductionException.setCreateBy(getUsername());
        return toAjax(erpProductionExceptionService.insertErpProductionException(erpProductionException));
    }

    /**
     * 修改生产异常
     */
    @PreAuthorize("@ss.hasPermi('manufacturing:exception:edit')")
    @Log(title = "生产异常", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ErpProductionException erpProductionException)
    {
        erpProductionException.setUpdateBy(getUsername());
        return toAjax(erpProductionExceptionService.updateErpProductionException(erpProductionException));
    }

    /**
     * 删除生产异常
     */
    @PreAuthorize("@ss.hasPermi('manufacturing:exception:remove')")
    @Log(title = "生产异常", businessType = BusinessType.DELETE)
    @DeleteMapping("/{exceptionIds}")
    public AjaxResult remove(@PathVariable Long[] exceptionIds)
    {
        return toAjax(erpProductionExceptionService.deleteErpProductionExceptionByExceptionIds(exceptionIds));
    }
}
