package com.ruoyi.finance.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.finance.domain.ErpReceivable;
import com.ruoyi.finance.mapper.ErpReceivableMapper;
import com.ruoyi.finance.service.IErpReceivableService;

/**
 * 应收单Service业务层处理
 *
 * @author erp
 */
@Service
public class ErpReceivableServiceImpl implements IErpReceivableService
{
    @Autowired
    private ErpReceivableMapper erpReceivableMapper;

    /**
     * 查询应收单
     */
    @Override
    public ErpReceivable selectErpReceivableByArId(Long arId)
    {
        return erpReceivableMapper.selectErpReceivableByArId(arId);
    }

    /**
     * 查询应收单列表
     */
    @Override
    public List<ErpReceivable> selectErpReceivableList(ErpReceivable erpReceivable)
    {
        return erpReceivableMapper.selectErpReceivableList(erpReceivable);
    }

    /**
     * 新增应收单
     */
    @Override
    public int insertErpReceivable(ErpReceivable erpReceivable)
    {
        erpReceivable.setCreateTime(DateUtils.getNowDate());
        return erpReceivableMapper.insertErpReceivable(erpReceivable);
    }

    /**
     * 修改应收单
     */
    @Override
    public int updateErpReceivable(ErpReceivable erpReceivable)
    {
        erpReceivable.setUpdateTime(DateUtils.getNowDate());
        return erpReceivableMapper.updateErpReceivable(erpReceivable);
    }

    /**
     * 批量删除应收单
     */
    @Override
    public int deleteErpReceivableByArIds(Long[] arIds)
    {
        return erpReceivableMapper.deleteErpReceivableByArIds(arIds);
    }

    /**
     * 删除应收单信息
     */
    @Override
    public int deleteErpReceivableByArId(Long arId)
    {
        return erpReceivableMapper.deleteErpReceivableByArId(arId);
    }
}
