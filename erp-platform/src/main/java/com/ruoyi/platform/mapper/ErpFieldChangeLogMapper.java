package com.ruoyi.platform.mapper;

import java.util.List;
import com.ruoyi.platform.domain.ErpFieldChangeLog;

/**
 * 字段变更审计日志Mapper接口
 *
 * @author erp
 */
public interface ErpFieldChangeLogMapper
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
     * 删除字段变更审计日志
     */
    public int deleteErpFieldChangeLogByLogId(Long logId);

    /**
     * 批量删除字段变更审计日志
     */
    public int deleteErpFieldChangeLogByLogIds(Long[] logIds);
}
