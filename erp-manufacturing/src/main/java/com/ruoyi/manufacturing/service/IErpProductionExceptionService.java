package com.ruoyi.manufacturing.service;

import java.util.List;
import com.ruoyi.manufacturing.domain.ErpProductionException;

/**
 * 生产异常Service接口
 *
 * @author erp
 */
public interface IErpProductionExceptionService
{
    /**
     * 查询生产异常
     */
    public ErpProductionException selectErpProductionExceptionByExceptionId(Long exceptionId);

    /**
     * 查询生产异常列表
     */
    public List<ErpProductionException> selectErpProductionExceptionList(ErpProductionException erpProductionException);

    /**
     * 新增生产异常
     */
    public int insertErpProductionException(ErpProductionException erpProductionException);

    /**
     * 修改生产异常
     */
    public int updateErpProductionException(ErpProductionException erpProductionException);

    /**
     * 批量删除生产异常
     */
    public int deleteErpProductionExceptionByExceptionIds(Long[] exceptionIds);

    /**
     * 删除生产异常信息
     */
    public int deleteErpProductionExceptionByExceptionId(Long exceptionId);
}
