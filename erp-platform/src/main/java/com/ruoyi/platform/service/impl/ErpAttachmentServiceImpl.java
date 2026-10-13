package com.ruoyi.platform.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.platform.domain.ErpAttachment;
import com.ruoyi.platform.mapper.ErpAttachmentMapper;
import com.ruoyi.platform.service.IErpAttachmentService;

/**
 * 附件Service业务层处理
 *
 * @author erp
 */
@Service
public class ErpAttachmentServiceImpl implements IErpAttachmentService
{
    @Autowired
    private ErpAttachmentMapper erpAttachmentMapper;

    /**
     * 查询附件
     */
    @Override
    public ErpAttachment selectErpAttachmentByAttachmentId(Long attachmentId)
    {
        return erpAttachmentMapper.selectErpAttachmentByAttachmentId(attachmentId);
    }

    /**
     * 查询附件列表
     */
    @Override
    public List<ErpAttachment> selectErpAttachmentList(ErpAttachment erpAttachment)
    {
        return erpAttachmentMapper.selectErpAttachmentList(erpAttachment);
    }

    /**
     * 新增附件
     */
    @Override
    public int insertErpAttachment(ErpAttachment erpAttachment)
    {
        erpAttachment.setCreateTime(DateUtils.getNowDate());
        return erpAttachmentMapper.insertErpAttachment(erpAttachment);
    }

    /**
     * 修改附件
     */
    @Override
    public int updateErpAttachment(ErpAttachment erpAttachment)
    {
        erpAttachment.setUpdateTime(DateUtils.getNowDate());
        return erpAttachmentMapper.updateErpAttachment(erpAttachment);
    }

    /**
     * 批量删除附件
     */
    @Override
    public int deleteErpAttachmentByAttachmentIds(Long[] attachmentIds)
    {
        return erpAttachmentMapper.deleteErpAttachmentByAttachmentIds(attachmentIds);
    }

    /**
     * 删除附件信息
     */
    @Override
    public int deleteErpAttachmentByAttachmentId(Long attachmentId)
    {
        return erpAttachmentMapper.deleteErpAttachmentByAttachmentId(attachmentId);
    }
}
