package com.ruoyi.platform.service;

import java.util.List;
import com.ruoyi.platform.domain.ErpWorkflowDef;

/**
 * 工作流定义Service接口
 *
 * @author erp
 */
public interface IErpWorkflowDefService
{
    /**
     * 查询工作流定义
     */
    public ErpWorkflowDef selectErpWorkflowDefByWfId(Long wfId);

    /**
     * 查询工作流定义列表
     */
    public List<ErpWorkflowDef> selectErpWorkflowDefList(ErpWorkflowDef erpWorkflowDef);

    /**
     * 新增工作流定义
     */
    public int insertErpWorkflowDef(ErpWorkflowDef erpWorkflowDef);

    /**
     * 修改工作流定义
     */
    public int updateErpWorkflowDef(ErpWorkflowDef erpWorkflowDef);

    /**
     * 批量删除工作流定义
     */
    public int deleteErpWorkflowDefByWfIds(Long[] wfIds);

    /**
     * 删除工作流定义信息
     */
    public int deleteErpWorkflowDefByWfId(Long wfId);
}
