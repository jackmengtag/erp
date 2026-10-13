package com.ruoyi.manufacturing.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.manufacturing.domain.ErpProductionException;
import com.ruoyi.manufacturing.mapper.ErpProductionExceptionMapper;
import com.ruoyi.manufacturing.service.IErpProductionExceptionService;

/**
 * 生产异常Service业务层处理
 *
 * @author erp
 */
@Service
public class ErpProductionExceptionServiceImpl implements IErpProductionExceptionService
{
    @Autowired
    private ErpProductionExceptionMapper erpProductionExceptionMapper;

    /**
     * 查询生产异常
     */
    @Override
    public ErpProductionException selectErpProductionExceptionByExceptionId(Long exceptionId)
    {
        return erpProductionExceptionMapper.selectErpProductionExceptionByExceptionId(exceptionId);
    }

    /**
     * 查询生产异常列表
     */
    @Override
    public List<ErpProductionException> selectErpProductionExceptionList(ErpProductionException erpProductionException)
    {
        return erpProductionExceptionMapper.selectErpProductionExceptionList(erpProductionException);
    }

    /**
     * 新增生产异常
     */
    @Override
    public int insertErpProductionException(ErpProductionException erpProductionException)
    {
        erpProductionException.setCreateTime(DateUtils.getNowDate());
        return erpProductionExceptionMapper.insertErpProductionException(erpProductionException);
    }

    /**
     * 修改生产异常
     */
    @Override
    public int updateErpProductionException(ErpProductionException erpProductionException)
    {
        erpProductionException.setUpdateTime(DateUtils.getNowDate());
        return erpProductionExceptionMapper.updateErpProductionException(erpProductionException);
    }

    /**
     * 批量删除生产异常
     */
    @Override
    public int deleteErpProductionExceptionByExceptionIds(Long[] exceptionIds)
    {
        return erpProductionExceptionMapper.deleteErpProductionExceptionByExceptionIds(exceptionIds);
    }

    /**
     * 删除生产异常信息
     */
    @Override
    public int deleteErpProductionExceptionByExceptionId(Long exceptionId)
    {
        return erpProductionExceptionMapper.deleteErpProductionExceptionByExceptionId(exceptionId);
    }
}
