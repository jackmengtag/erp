package com.ruoyi.finance.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.finance.domain.ErpPayable;
import com.ruoyi.finance.mapper.ErpPayableMapper;
import com.ruoyi.finance.service.IErpPayableService;

/**
 * 应付单Service业务层处理
 *
 * @author erp
 */
@Service
public class ErpPayableServiceImpl implements IErpPayableService
{
    @Autowired
    private ErpPayableMapper erpPayableMapper;

    /**
     * 查询应付单
     */
    @Override
    public ErpPayable selectErpPayableByApId(Long apId)
    {
        return erpPayableMapper.selectErpPayableByApId(apId);
    }

    /**
     * 查询应付单列表
     */
    @Override
    public List<ErpPayable> selectErpPayableList(ErpPayable erpPayable)
    {
        return erpPayableMapper.selectErpPayableList(erpPayable);
    }

    /**
     * 新增应付单
     */
    @Override
    public int insertErpPayable(ErpPayable erpPayable)
    {
        erpPayable.setCreateTime(DateUtils.getNowDate());
        return erpPayableMapper.insertErpPayable(erpPayable);
    }

    /**
     * 修改应付单
     */
    @Override
    public int updateErpPayable(ErpPayable erpPayable)
    {
        erpPayable.setUpdateTime(DateUtils.getNowDate());
        return erpPayableMapper.updateErpPayable(erpPayable);
    }

    /**
     * 批量删除应付单
     */
    @Override
    public int deleteErpPayableByApIds(Long[] apIds)
    {
        return erpPayableMapper.deleteErpPayableByApIds(apIds);
    }

    /**
     * 删除应付单信息
     */
    @Override
    public int deleteErpPayableByApId(Long apId)
    {
        return erpPayableMapper.deleteErpPayableByApId(apId);
    }
}
