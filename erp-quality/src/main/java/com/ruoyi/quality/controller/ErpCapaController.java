package com.ruoyi.quality.controller;

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
import com.ruoyi.quality.domain.ErpCapa;
import com.ruoyi.quality.service.IErpCapaService;

/**
 * CAPA与客诉Controller
 *
 * @author erp
 */
@RestController
@RequestMapping("/quality/capa")
public class ErpCapaController extends BaseController
{
    @Autowired
    private IErpCapaService erpCapaService;

    /**
     * 查询CAPA与客诉列表
     */
    @PreAuthorize("@ss.hasPermi('quality:capa:list')")
    @GetMapping("/list")
    public TableDataInfo list(ErpCapa erpCapa)
    {
        startPage();
        List<ErpCapa> list = erpCapaService.selectErpCapaList(erpCapa);
        return getDataTable(list);
    }

    /**
     * 导出CAPA与客诉列表
     */
    @PreAuthorize("@ss.hasPermi('quality:capa:export')")
    @Log(title = "CAPA与客诉", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ErpCapa erpCapa)
    {
        List<ErpCapa> list = erpCapaService.selectErpCapaList(erpCapa);
        ExcelUtil<ErpCapa> util = new ExcelUtil<ErpCapa>(ErpCapa.class);
        util.exportExcel(response, list, "CAPA与客诉数据");
    }

    /**
     * 获取CAPA与客诉详细信息
     */
    @PreAuthorize("@ss.hasPermi('quality:capa:query')")
    @GetMapping(value = "/{capaId}")
    public AjaxResult getInfo(@PathVariable("capaId") Long capaId)
    {
        return success(erpCapaService.selectErpCapaByCapaId(capaId));
    }

    /**
     * 新增CAPA与客诉
     */
    @PreAuthorize("@ss.hasPermi('quality:capa:add')")
    @Log(title = "CAPA与客诉", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ErpCapa erpCapa)
    {
        erpCapa.setCreateBy(getUsername());
        return toAjax(erpCapaService.insertErpCapa(erpCapa));
    }

    /**
     * 修改CAPA与客诉
     */
    @PreAuthorize("@ss.hasPermi('quality:capa:edit')")
    @Log(title = "CAPA与客诉", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ErpCapa erpCapa)
    {
        erpCapa.setUpdateBy(getUsername());
        return toAjax(erpCapaService.updateErpCapa(erpCapa));
    }

    /**
     * 删除CAPA与客诉
     */
    @PreAuthorize("@ss.hasPermi('quality:capa:remove')")
    @Log(title = "CAPA与客诉", businessType = BusinessType.DELETE)
    @DeleteMapping("/{capaIds}")
    public AjaxResult remove(@PathVariable Long[] capaIds)
    {
        return toAjax(erpCapaService.deleteErpCapaByCapaIds(capaIds));
    }
}
