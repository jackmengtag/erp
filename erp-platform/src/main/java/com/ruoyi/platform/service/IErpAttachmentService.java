package com.ruoyi.platform.service;

import java.util.List;
import com.ruoyi.platform.domain.ErpAttachment;

/**
 * 附件Service接口
 *
 * @author erp
 */
public interface IErpAttachmentService
{
    /**
     * 查询附件
     */
    public ErpAttachment selectErpAttachmentByAttachmentId(Long attachmentId);

    /**
     * 查询附件列表
     */
    public List<ErpAttachment> selectErpAttachmentList(ErpAttachment erpAttachment);

    /**
     * 新增附件
     */
    public int insertErpAttachment(ErpAttachment erpAttachment);

    /**
     * 修改附件
     */
    public int updateErpAttachment(ErpAttachment erpAttachment);

    /**
     * 批量删除附件
     */
    public int deleteErpAttachmentByAttachmentIds(Long[] attachmentIds);

    /**
     * 删除附件信息
     */
    public int deleteErpAttachmentByAttachmentId(Long attachmentId);
}
