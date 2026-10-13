package com.ruoyi.finance.mapper;

import java.util.List;
import com.ruoyi.finance.domain.ErpPayable;

/**
 * 应付单Mapper接口
 *
 * @author erp
 */
public interface ErpPayableMapper
{
    /**
     * 查询应付单
     */
    public ErpPayable selectErpPayableByApId(Long apId);

    /**
     * 查询应付单列表
     */
    public List<ErpPayable> selectErpPayableList(ErpPayable erpPayable);

    /**
     * 新增应付单
     */
    public int insertErpPayable(ErpPayable erpPayable);

    /**
     * 修改应付单
     */
    public int updateErpPayable(ErpPayable erpPayable);

    /**
     * 删除应付单
     */
    public int deleteErpPayableByApId(Long apId);

    /**
     * 批量删除应付单
     */
    public int deleteErpPayableByApIds(Long[] apIds);
}
