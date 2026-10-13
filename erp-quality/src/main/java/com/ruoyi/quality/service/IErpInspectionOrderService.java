package com.ruoyi.quality.service;

import java.util.List;
import com.ruoyi.quality.domain.ErpInspectionOrder;

/**
 * 检验单Service接口
 *
 * @author erp
 */
public interface IErpInspectionOrderService
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
     * 批量删除检验单
     */
    public int deleteErpInspectionOrderByInspectIds(Long[] inspectIds);

    /**
     * 删除检验单信息
     */
    public int deleteErpInspectionOrderByInspectId(Long inspectId);
}
