package com.ruoyi.platform.mapper;

import java.util.List;
import com.ruoyi.platform.domain.ErpAttachment;

/**
 * 附件Mapper接口
 *
 * @author erp
 */
public interface ErpAttachmentMapper
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
     * 删除附件
     */
    public int deleteErpAttachmentByAttachmentId(Long attachmentId);

    /**
     * 批量删除附件
     */
    public int deleteErpAttachmentByAttachmentIds(Long[] attachmentIds);
}
