package com.ruoyi.platform.controller;

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
import com.ruoyi.platform.domain.ErpCodeSerial;
import com.ruoyi.platform.service.IErpCodeSerialService;

/**
 * 编码流水Controller
 *
 * @author erp
 */
@RestController
@RequestMapping("/platform/codeSerial")
public class ErpCodeSerialController extends BaseController
{
    @Autowired
    private IErpCodeSerialService erpCodeSerialService;

    /**
     * 查询编码流水列表
     */
    @PreAuthorize("@ss.hasPermi('platform:codeSerial:list')")
    @GetMapping("/list")
    public TableDataInfo list(ErpCodeSerial erpCodeSerial)
    {
        startPage();
        List<ErpCodeSerial> list = erpCodeSerialService.selectErpCodeSerialList(erpCodeSerial);
        return getDataTable(list);
    }

    /**
     * 导出编码流水列表
     */
    @PreAuthorize("@ss.hasPermi('platform:codeSerial:export')")
    @Log(title = "编码流水", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ErpCodeSerial erpCodeSerial)
    {
        List<ErpCodeSerial> list = erpCodeSerialService.selectErpCodeSerialList(erpCodeSerial);
        ExcelUtil<ErpCodeSerial> util = new ExcelUtil<ErpCodeSerial>(ErpCodeSerial.class);
        util.exportExcel(response, list, "编码流水数据");
    }

    /**
     * 获取编码流水详细信息
     */
    @PreAuthorize("@ss.hasPermi('platform:codeSerial:query')")
    @GetMapping(value = "/{serialId}")
    public AjaxResult getInfo(@PathVariable("serialId") Long serialId)
    {
        return success(erpCodeSerialService.selectErpCodeSerialBySerialId(serialId));
    }

    /**
     * 新增编码流水
     */
    @PreAuthorize("@ss.hasPermi('platform:codeSerial:add')")
    @Log(title = "编码流水", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ErpCodeSerial erpCodeSerial)
    {
        erpCodeSerial.setCreateBy(getUsername());
        return toAjax(erpCodeSerialService.insertErpCodeSerial(erpCodeSerial));
    }

    /**
     * 修改编码流水
     */
    @PreAuthorize("@ss.hasPermi('platform:codeSerial:edit')")
    @Log(title = "编码流水", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ErpCodeSerial erpCodeSerial)
    {
        erpCodeSerial.setUpdateBy(getUsername());
        return toAjax(erpCodeSerialService.updateErpCodeSerial(erpCodeSerial));
    }

    /**
     * 删除编码流水
     */
    @PreAuthorize("@ss.hasPermi('platform:codeSerial:remove')")
    @Log(title = "编码流水", businessType = BusinessType.DELETE)
    @DeleteMapping("/{serialIds}")
    public AjaxResult remove(@PathVariable Long[] serialIds)
    {
        return toAjax(erpCodeSerialService.deleteErpCodeSerialBySerialIds(serialIds));
    }
}
