package com.ruoyi.platform.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.platform.domain.ErpWorkflowInstance;
import com.ruoyi.platform.mapper.ErpWorkflowInstanceMapper;
import com.ruoyi.platform.service.IErpWorkflowInstanceService;

/**
 * 工作流实例Service业务层处理
 *
 * @author erp
 */
@Service
public class ErpWorkflowInstanceServiceImpl implements IErpWorkflowInstanceService
{
    @Autowired
    private ErpWorkflowInstanceMapper erpWorkflowInstanceMapper;

    /**
     * 查询工作流实例
     */
    @Override
    public ErpWorkflowInstance selectErpWorkflowInstanceByInstanceId(Long instanceId)
    {
        return erpWorkflowInstanceMapper.selectErpWorkflowInstanceByInstanceId(instanceId);
    }

    /**
     * 查询工作流实例列表
     */
    @Override
    public List<ErpWorkflowInstance> selectErpWorkflowInstanceList(ErpWorkflowInstance erpWorkflowInstance)
    {
        return erpWorkflowInstanceMapper.selectErpWorkflowInstanceList(erpWorkflowInstance);
    }

    /**
     * 新增工作流实例
     */
    @Override
    public int insertErpWorkflowInstance(ErpWorkflowInstance erpWorkflowInstance)
    {
        erpWorkflowInstance.setCreateTime(DateUtils.getNowDate());
        return erpWorkflowInstanceMapper.insertErpWorkflowInstance(erpWorkflowInstance);
    }

    /**
     * 修改工作流实例
     */
    @Override
    public int updateErpWorkflowInstance(ErpWorkflowInstance erpWorkflowInstance)
    {
        erpWorkflowInstance.setUpdateTime(DateUtils.getNowDate());
        return erpWorkflowInstanceMapper.updateErpWorkflowInstance(erpWorkflowInstance);
    }

    /**
     * 批量删除工作流实例
     */
    @Override
    public int deleteErpWorkflowInstanceByInstanceIds(Long[] instanceIds)
    {
        return erpWorkflowInstanceMapper.deleteErpWorkflowInstanceByInstanceIds(instanceIds);
    }

    /**
     * 删除工作流实例信息
     */
    @Override
    public int deleteErpWorkflowInstanceByInstanceId(Long instanceId)
    {
        return erpWorkflowInstanceMapper.deleteErpWorkflowInstanceByInstanceId(instanceId);
    }
}
