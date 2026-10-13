package com.ruoyi.platform.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.platform.domain.ErpFieldChangeLog;
import com.ruoyi.platform.mapper.ErpFieldChangeLogMapper;
import com.ruoyi.platform.service.IErpFieldChangeLogService;

/**
 * 字段变更审计日志Service业务层处理
 *
 * @author erp
 */
@Service
public class ErpFieldChangeLogServiceImpl implements IErpFieldChangeLogService
{
    @Autowired
    private ErpFieldChangeLogMapper erpFieldChangeLogMapper;

    /**
     * 查询字段变更审计日志
     */
    @Override
    public ErpFieldChangeLog selectErpFieldChangeLogByLogId(Long logId)
    {
        return erpFieldChangeLogMapper.selectErpFieldChangeLogByLogId(logId);
    }

    /**
     * 查询字段变更审计日志列表
     */
    @Override
    public List<ErpFieldChangeLog> selectErpFieldChangeLogList(ErpFieldChangeLog erpFieldChangeLog)
    {
        return erpFieldChangeLogMapper.selectErpFieldChangeLogList(erpFieldChangeLog);
    }

    /**
     * 新增字段变更审计日志
     */
    @Override
    public int insertErpFieldChangeLog(ErpFieldChangeLog erpFieldChangeLog)
    {
        return erpFieldChangeLogMapper.insertErpFieldChangeLog(erpFieldChangeLog);
    }

    /**
     * 修改字段变更审计日志
     */
    @Override
    public int updateErpFieldChangeLog(ErpFieldChangeLog erpFieldChangeLog)
    {
        return erpFieldChangeLogMapper.updateErpFieldChangeLog(erpFieldChangeLog);
    }

    /**
     * 批量删除字段变更审计日志
     */
    @Override
    public int deleteErpFieldChangeLogByLogIds(Long[] logIds)
    {
        return erpFieldChangeLogMapper.deleteErpFieldChangeLogByLogIds(logIds);
    }

    /**
     * 删除字段变更审计日志信息
     */
    @Override
    public int deleteErpFieldChangeLogByLogId(Long logId)
    {
        return erpFieldChangeLogMapper.deleteErpFieldChangeLogByLogId(logId);
    }
}
