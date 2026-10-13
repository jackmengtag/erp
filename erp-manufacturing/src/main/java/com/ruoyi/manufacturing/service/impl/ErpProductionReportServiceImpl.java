package com.ruoyi.manufacturing.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.manufacturing.domain.ErpProductionReport;
import com.ruoyi.manufacturing.mapper.ErpProductionReportMapper;
import com.ruoyi.manufacturing.service.IErpProductionReportService;

/**
 * 工序报工Service业务层处理
 *
 * @author erp
 */
@Service
public class ErpProductionReportServiceImpl implements IErpProductionReportService
{
    @Autowired
    private ErpProductionReportMapper erpProductionReportMapper;

    /**
     * 查询工序报工
     */
    @Override
    public ErpProductionReport selectErpProductionReportByReportId(Long reportId)
    {
        return erpProductionReportMapper.selectErpProductionReportByReportId(reportId);
    }

    /**
     * 查询工序报工列表
     */
    @Override
    public List<ErpProductionReport> selectErpProductionReportList(ErpProductionReport erpProductionReport)
    {
        return erpProductionReportMapper.selectErpProductionReportList(erpProductionReport);
    }

    /**
     * 新增工序报工
     */
    @Override
    public int insertErpProductionReport(ErpProductionReport erpProductionReport)
    {
        erpProductionReport.setCreateTime(DateUtils.getNowDate());
        return erpProductionReportMapper.insertErpProductionReport(erpProductionReport);
    }

    /**
     * 修改工序报工
     */
    @Override
    public int updateErpProductionReport(ErpProductionReport erpProductionReport)
    {
        erpProductionReport.setUpdateTime(DateUtils.getNowDate());
        return erpProductionReportMapper.updateErpProductionReport(erpProductionReport);
    }

    /**
     * 批量删除工序报工
     */
    @Override
    public int deleteErpProductionReportByReportIds(Long[] reportIds)
    {
        return erpProductionReportMapper.deleteErpProductionReportByReportIds(reportIds);
    }

    /**
     * 删除工序报工信息
     */
    @Override
    public int deleteErpProductionReportByReportId(Long reportId)
    {
        return erpProductionReportMapper.deleteErpProductionReportByReportId(reportId);
    }
}
