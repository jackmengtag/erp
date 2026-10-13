package com.ruoyi.quality.service;

import java.util.List;
import com.ruoyi.quality.domain.ErpDefectDisposal;

/**
 * 不合格品处理Service接口
 *
 * @author erp
 */
public interface IErpDefectDisposalService
{
    /**
     * 查询不合格品处理
     */
    public ErpDefectDisposal selectErpDefectDisposalByDisposalId(Long disposalId);

    /**
     * 查询不合格品处理列表
     */
    public List<ErpDefectDisposal> selectErpDefectDisposalList(ErpDefectDisposal erpDefectDisposal);

    /**
     * 新增不合格品处理
     */
    public int insertErpDefectDisposal(ErpDefectDisposal erpDefectDisposal);

    /**
     * 修改不合格品处理
     */
    public int updateErpDefectDisposal(ErpDefectDisposal erpDefectDisposal);

    /**
     * 批量删除不合格品处理
     */
    public int deleteErpDefectDisposalByDisposalIds(Long[] disposalIds);

    /**
     * 删除不合格品处理信息
     */
    public int deleteErpDefectDisposalByDisposalId(Long disposalId);
}
