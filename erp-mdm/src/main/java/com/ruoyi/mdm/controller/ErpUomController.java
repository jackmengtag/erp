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
import com.ruoyi.mdm.domain.ErpUom;
import com.ruoyi.mdm.service.IErpUomService;

/**
 * 计量单位Controller
 *
 * @author erp
 */
@RestController
@RequestMapping("/mdm/uom")
public class ErpUomController extends BaseController
{
    @Autowired
    private IErpUomService erpUomService;

    /**
     * 查询计量单位列表
     */
    @PreAuthorize("@ss.hasPermi('mdm:uom:list')")
    @GetMapping("/list")
    public TableDataInfo list(ErpUom erpUom)
    {
        startPage();
        List<ErpUom> list = erpUomService.selectErpUomList(erpUom);
        return getDataTable(list);
    }

    /**
     * 导出计量单位列表
     */
    @PreAuthorize("@ss.hasPermi('mdm:uom:export')")
    @Log(title = "计量单位", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ErpUom erpUom)
    {
        List<ErpUom> list = erpUomService.selectErpUomList(erpUom);
        ExcelUtil<ErpUom> util = new ExcelUtil<ErpUom>(ErpUom.class);
        util.exportExcel(response, list, "计量单位数据");
    }

    /**
     * 获取计量单位详细信息
     */
    @PreAuthorize("@ss.hasPermi('mdm:uom:query')")
    @GetMapping(value = "/{uomId}")
    public AjaxResult getInfo(@PathVariable("uomId") Long uomId)
    {
        return success(erpUomService.selectErpUomByUomId(uomId));
    }

    /**
     * 新增计量单位
     */
    @PreAuthorize("@ss.hasPermi('mdm:uom:add')")
    @Log(title = "计量单位", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ErpUom erpUom)
    {
        erpUom.setCreateBy(getUsername());
        return toAjax(erpUomService.insertErpUom(erpUom));
    }

    /**
     * 修改计量单位
     */
    @PreAuthorize("@ss.hasPermi('mdm:uom:edit')")
    @Log(title = "计量单位", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ErpUom erpUom)
    {
        erpUom.setUpdateBy(getUsername());
        return toAjax(erpUomService.updateErpUom(erpUom));
    }

    /**
     * 删除计量单位
     */
    @PreAuthorize("@ss.hasPermi('mdm:uom:remove')")
    @Log(title = "计量单位", businessType = BusinessType.DELETE)
    @DeleteMapping("/{uomIds}")
    public AjaxResult remove(@PathVariable Long[] uomIds)
    {
        return toAjax(erpUomService.deleteErpUomByUomIds(uomIds));
    }
}
