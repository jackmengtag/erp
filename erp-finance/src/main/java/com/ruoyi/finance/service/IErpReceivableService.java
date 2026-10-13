package com.ruoyi.finance.service;

import java.util.List;
import com.ruoyi.finance.domain.ErpReceivable;

/**
 * 应收单Service接口
 *
 * @author erp
 */
public interface IErpReceivableService
{
    /**
     * 查询应收单
     */
    public ErpReceivable selectErpReceivableByArId(Long arId);

    /**
     * 查询应收单列表
     */
    public List<ErpReceivable> selectErpReceivableList(ErpReceivable erpReceivable);

    /**
     * 新增应收单
     */
    public int insertErpReceivable(ErpReceivable erpReceivable);

    /**
     * 修改应收单
     */
    public int updateErpReceivable(ErpReceivable erpReceivable);

    /**
     * 批量删除应收单
     */
    public int deleteErpReceivableByArIds(Long[] arIds);

    /**
     * 删除应收单信息
     */
    public int deleteErpReceivableByArId(Long arId);
}
