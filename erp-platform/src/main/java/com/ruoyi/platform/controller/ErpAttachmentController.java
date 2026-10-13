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
import com.ruoyi.platform.domain.ErpAttachment;
import com.ruoyi.platform.service.IErpAttachmentService;

/**
 * 附件Controller
 *
 * @author erp
 */
@RestController
@RequestMapping("/platform/attachment")
public class ErpAttachmentController extends BaseController
{
    @Autowired
    private IErpAttachmentService erpAttachmentService;

    /**
     * 查询附件列表
     */
    @PreAuthorize("@ss.hasPermi('platform:attachment:list')")
    @GetMapping("/list")
    public TableDataInfo list(ErpAttachment erpAttachment)
    {
        startPage();
        List<ErpAttachment> list = erpAttachmentService.selectErpAttachmentList(erpAttachment);
        return getDataTable(list);
    }

    /**
     * 导出附件列表
     */
    @PreAuthorize("@ss.hasPermi('platform:attachment:export')")
    @Log(title = "附件", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ErpAttachment erpAttachment)
    {
        List<ErpAttachment> list = erpAttachmentService.selectErpAttachmentList(erpAttachment);
        ExcelUtil<ErpAttachment> util = new ExcelUtil<ErpAttachment>(ErpAttachment.class);
        util.exportExcel(response, list, "附件数据");
    }

    /**
     * 获取附件详细信息
     */
    @PreAuthorize("@ss.hasPermi('platform:attachment:query')")
    @GetMapping(value = "/{attachmentId}")
    public AjaxResult getInfo(@PathVariable("attachmentId") Long attachmentId)
    {
        return success(erpAttachmentService.selectErpAttachmentByAttachmentId(attachmentId));
    }

    /**
     * 新增附件
     */
    @PreAuthorize("@ss.hasPermi('platform:attachment:add')")
    @Log(title = "附件", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ErpAttachment erpAttachment)
    {
        erpAttachment.setCreateBy(getUsername());
        return toAjax(erpAttachmentService.insertErpAttachment(erpAttachment));
    }

    /**
     * 修改附件
     */
    @PreAuthorize("@ss.hasPermi('platform:attachment:edit')")
    @Log(title = "附件", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ErpAttachment erpAttachment)
    {
        erpAttachment.setUpdateBy(getUsername());
        return toAjax(erpAttachmentService.updateErpAttachment(erpAttachment));
    }

    /**
     * 删除附件
     */
    @PreAuthorize("@ss.hasPermi('platform:attachment:remove')")
    @Log(title = "附件", businessType = BusinessType.DELETE)
    @DeleteMapping("/{attachmentIds}")
    public AjaxResult remove(@PathVariable Long[] attachmentIds)
    {
        return toAjax(erpAttachmentService.deleteErpAttachmentByAttachmentIds(attachmentIds));
    }
}
