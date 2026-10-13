package com.ruoyi.quality.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.quality.domain.ErpDefectDisposal;
import com.ruoyi.quality.mapper.ErpDefectDisposalMapper;
import com.ruoyi.quality.service.IErpDefectDisposalService;

/**
 * 不合格品处理Service业务层处理
 *
 * @author erp
 */
@Service
public class ErpDefectDisposalServiceImpl implements IErpDefectDisposalService
{
    @Autowired
    private ErpDefectDisposalMapper erpDefectDisposalMapper;

    /**
     * 查询不合格品处理
     */
    @Override
    public ErpDefectDisposal selectErpDefectDisposalByDisposalId(Long disposalId)
    {
        return erpDefectDisposalMapper.selectErpDefectDisposalByDisposalId(disposalId);
    }

    /**
     * 查询不合格品处理列表
     */
    @Override
    public List<ErpDefectDisposal> selectErpDefectDisposalList(ErpDefectDisposal erpDefectDisposal)
    {
        return erpDefectDisposalMapper.selectErpDefectDisposalList(erpDefectDisposal);
    }

    /**
     * 新增不合格品处理
     */
    @Override
    public int insertErpDefectDisposal(ErpDefectDisposal erpDefectDisposal)
    {
        erpDefectDisposal.setCreateTime(DateUtils.getNowDate());
        return erpDefectDisposalMapper.insertErpDefectDisposal(erpDefectDisposal);
    }

    /**
     * 修改不合格品处理
     */
    @Override
    public int updateErpDefectDisposal(ErpDefectDisposal erpDefectDisposal)
    {
        erpDefectDisposal.setUpdateTime(DateUtils.getNowDate());
        return erpDefectDisposalMapper.updateErpDefectDisposal(erpDefectDisposal);
    }

    /**
     * 批量删除不合格品处理
     */
    @Override
    public int deleteErpDefectDisposalByDisposalIds(Long[] disposalIds)
    {
        return erpDefectDisposalMapper.deleteErpDefectDisposalByDisposalIds(disposalIds);
    }

    /**
     * 删除不合格品处理信息
     */
    @Override
    public int deleteErpDefectDisposalByDisposalId(Long disposalId)
    {
        return erpDefectDisposalMapper.deleteErpDefectDisposalByDisposalId(disposalId);
    }
}
