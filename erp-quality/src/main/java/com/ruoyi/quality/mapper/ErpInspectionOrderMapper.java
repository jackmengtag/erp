package com.ruoyi.quality.mapper;

import java.util.List;
import com.ruoyi.quality.domain.ErpInspectionOrder;
import com.ruoyi.quality.domain.ErpInspectionResult;

/**
 * 检验单Mapper接口
 *
 * @author erp
 */
public interface ErpInspectionOrderMapper
{
    /**
     * 查询检验单
     */
    public ErpInspectionOrder selectErpInspectionOrderByInspectId(Long inspectId);

    /**
     * 查询检验单列表
     */
    public List<ErpInspectionOrder> selectErpInspectionOrderList(ErpInspectionOrder erpInspectionOrder);

    /**
     * 新增检验单
     */
    public int insertErpInspectionOrder(ErpInspectionOrder erpInspectionOrder);

    /**
     * 修改检验单
     */
    public int updateErpInspectionOrder(ErpInspectionOrder erpInspectionOrder);

    /**
     * 删除检验单
     */
    public int deleteErpInspectionOrderByInspectId(Long inspectId);

    /**
     * 批量删除检验单
     */
    public int deleteErpInspectionOrderByInspectIds(Long[] inspectIds);

    /**
     * 批量删除检验结果明细
     */
    public int deleteErpInspectionResultByInspectIds(Long[] inspectIds);

    /**
     * 删除检验结果明细信息
     */
    public int deleteErpInspectionResultByInspectId(Long inspectId);

    /**
     * 批量新增检验结果明细
     */
    public int batchErpInspectionResult(List<ErpInspectionResult> erpInspectionResultList);
}
