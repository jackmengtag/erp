package com.ruoyi.platform.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.platform.domain.ErpWorkflowDef;
import com.ruoyi.platform.mapper.ErpWorkflowDefMapper;
import com.ruoyi.platform.service.IErpWorkflowDefService;

/**
 * 工作流定义Service业务层处理
 *
 * @author erp
 */
@Service
public class ErpWorkflowDefServiceImpl implements IErpWorkflowDefService
{
    @Autowired
    private ErpWorkflowDefMapper erpWorkflowDefMapper;

    /**
     * 查询工作流定义
     */
    @Override
    public ErpWorkflowDef selectErpWorkflowDefByWfId(Long wfId)
    {
        return erpWorkflowDefMapper.selectErpWorkflowDefByWfId(wfId);
    }

    /**
     * 查询工作流定义列表
     */
    @Override
    public List<ErpWorkflowDef> selectErpWorkflowDefList(ErpWorkflowDef erpWorkflowDef)
    {
        return erpWorkflowDefMapper.selectErpWorkflowDefList(erpWorkflowDef);
    }

    /**
     * 新增工作流定义
     */
    @Override
    public int insertErpWorkflowDef(ErpWorkflowDef erpWorkflowDef)
    {
        erpWorkflowDef.setCreateTime(DateUtils.getNowDate());
        return erpWorkflowDefMapper.insertErpWorkflowDef(erpWorkflowDef);
    }

    /**
     * 修改工作流定义
     */
    @Override
    public int updateErpWorkflowDef(ErpWorkflowDef erpWorkflowDef)
    {
        erpWorkflowDef.setUpdateTime(DateUtils.getNowDate());
        return erpWorkflowDefMapper.updateErpWorkflowDef(erpWorkflowDef);
    }

    /**
     * 批量删除工作流定义
     */
    @Override
    public int deleteErpWorkflowDefByWfIds(Long[] wfIds)
    {
        return erpWorkflowDefMapper.deleteErpWorkflowDefByWfIds(wfIds);
    }

    /**
     * 删除工作流定义信息
     */
    @Override
    public int deleteErpWorkflowDefByWfId(Long wfId)
    {
        return erpWorkflowDefMapper.deleteErpWorkflowDefByWfId(wfId);
    }
}
