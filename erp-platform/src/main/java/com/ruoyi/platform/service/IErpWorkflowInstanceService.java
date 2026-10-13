package com.ruoyi.platform.service;

import java.util.List;
import com.ruoyi.platform.domain.ErpWorkflowInstance;

/**
 * 工作流实例Service接口
 *
 * @author erp
 */
public interface IErpWorkflowInstanceService
{
    /**
     * 查询工作流实例
     */
    public ErpWorkflowInstance selectErpWorkflowInstanceByInstanceId(Long instanceId);

    /**
     * 查询工作流实例列表
     */
    public List<ErpWorkflowInstance> selectErpWorkflowInstanceList(ErpWorkflowInstance erpWorkflowInstance);

    /**
     * 新增工作流实例
     */
    public int insertErpWorkflowInstance(ErpWorkflowInstance erpWorkflowInstance);

    /**
     * 修改工作流实例
     */
    public int updateErpWorkflowInstance(ErpWorkflowInstance erpWorkflowInstance);

    /**
     * 批量删除工作流实例
     */
    public int deleteErpWorkflowInstanceByInstanceIds(Long[] instanceIds);

    /**
     * 删除工作流实例信息
     */
    public int deleteErpWorkflowInstanceByInstanceId(Long instanceId);
}
