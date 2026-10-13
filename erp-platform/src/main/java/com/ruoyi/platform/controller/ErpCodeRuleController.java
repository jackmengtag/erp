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
import com.ruoyi.platform.domain.ErpCodeRule;
import com.ruoyi.platform.service.IErpCodeRuleService;

/**
 * 编码规则Controller
 *
 * @author erp
 */
@RestController
@RequestMapping("/platform/codeRule")
public class ErpCodeRuleController extends BaseController
{
    @Autowired
    private IErpCodeRuleService erpCodeRuleService;

    /**
     * 查询编码规则列表
     */
    @PreAuthorize("@ss.hasPermi('platform:codeRule:list')")
    @GetMapping("/list")
    public TableDataInfo list(ErpCodeRule erpCodeRule)
    {
        startPage();
        List<ErpCodeRule> list = erpCodeRuleService.selectErpCodeRuleList(erpCodeRule);
        return getDataTable(list);
    }

    /**
     * 导出编码规则列表
     */
    @PreAuthorize("@ss.hasPermi('platform:codeRule:export')")
    @Log(title = "编码规则", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ErpCodeRule erpCodeRule)
    {
        List<ErpCodeRule> list = erpCodeRuleService.selectErpCodeRuleList(erpCodeRule);
        ExcelUtil<ErpCodeRule> util = new ExcelUtil<ErpCodeRule>(ErpCodeRule.class);
        util.exportExcel(response, list, "编码规则数据");
    }

    /**
     * 获取编码规则详细信息
     */
    @PreAuthorize("@ss.hasPermi('platform:codeRule:query')")
    @GetMapping(value = "/{ruleId}")
    public AjaxResult getInfo(@PathVariable("ruleId") Long ruleId)
    {
        return success(erpCodeRuleService.selectErpCodeRuleByRuleId(ruleId));
    }

    /**
     * 新增编码规则
     */
    @PreAuthorize("@ss.hasPermi('platform:codeRule:add')")
    @Log(title = "编码规则", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ErpCodeRule erpCodeRule)
    {
        erpCodeRule.setCreateBy(getUsername());
        return toAjax(erpCodeRuleService.insertErpCodeRule(erpCodeRule));
    }

    /**
     * 修改编码规则
     */
    @PreAuthorize("@ss.hasPermi('platform:codeRule:edit')")
    @Log(title = "编码规则", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ErpCodeRule erpCodeRule)
    {
        erpCodeRule.setUpdateBy(getUsername());
        return toAjax(erpCodeRuleService.updateErpCodeRule(erpCodeRule));
    }

    /**
     * 删除编码规则
     */
    @PreAuthorize("@ss.hasPermi('platform:codeRule:remove')")
    @Log(title = "编码规则", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ruleIds}")
    public AjaxResult remove(@PathVariable Long[] ruleIds)
    {
        return toAjax(erpCodeRuleService.deleteErpCodeRuleByRuleIds(ruleIds));
    }
}
