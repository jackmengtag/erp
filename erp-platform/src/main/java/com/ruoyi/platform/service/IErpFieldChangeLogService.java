package com.ruoyi.platform.service;

import java.util.List;
import com.ruoyi.platform.domain.ErpFieldChangeLog;

/**
 * 字段变更审计日志Service接口
 *
 * @author erp
 */
public interface IErpFieldChangeLogService
{
    /**
     * 查询字段变更审计日志
     */
    public ErpFieldChangeLog selectErpFieldChangeLogByLogId(Long logId);

    /**
     * 查询字段变更审计日志列表
     */
    public List<ErpFieldChangeLog> selectErpFieldChangeLogList(ErpFieldChangeLog erpFieldChangeLog);

    /**
     * 新增字段变更审计日志
     */
    public int insertErpFieldChangeLog(ErpFieldChangeLog erpFieldChangeLog);

    /**
     * 修改字段变更审计日志
     */
    public int updateErpFieldChangeLog(ErpFieldChangeLog erpFieldChangeLog);

    /**
     * 批量删除字段变更审计日志
     */
    public int deleteErpFieldChangeLogByLogIds(Long[] logIds);

    /**
     * 删除字段变更审计日志信息
     */
    public int deleteErpFieldChangeLogByLogId(Long logId);
}
