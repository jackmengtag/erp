package com.ruoyi.manufacturing.service;

import java.util.List;
import com.ruoyi.manufacturing.domain.ErpProductionReport;

/**
 * 工序报工Service接口
 *
 * @author erp
 */
public interface IErpProductionReportService
{
    /**
     * 查询工序报工
     */
    public ErpProductionReport selectErpProductionReportByReportId(Long reportId);

    /**
     * 查询工序报工列表
     */
    public List<ErpProductionReport> selectErpProductionReportList(ErpProductionReport erpProductionReport);

    /**
     * 新增工序报工
     */
    public int insertErpProductionReport(ErpProductionReport erpProductionReport);

    /**
     * 修改工序报工
     */
    public int updateErpProductionReport(ErpProductionReport erpProductionReport);

    /**
     * 批量删除工序报工
     */
    public int deleteErpProductionReportByReportIds(Long[] reportIds);

    /**
     * 删除工序报工信息
     */
    public int deleteErpProductionReportByReportId(Long reportId);
}
