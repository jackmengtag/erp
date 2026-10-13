package com.ruoyi.platform.service;

import java.util.List;
import com.ruoyi.platform.domain.ErpCodeSerial;

/**
 * 编码流水Service接口
 *
 * @author erp
 */
public interface IErpCodeSerialService
{
    /**
     * 查询编码流水
     */
    public ErpCodeSerial selectErpCodeSerialBySerialId(Long serialId);

    /**
     * 查询编码流水列表
     */
    public List<ErpCodeSerial> selectErpCodeSerialList(ErpCodeSerial erpCodeSerial);

    /**
     * 新增编码流水
     */
    public int insertErpCodeSerial(ErpCodeSerial erpCodeSerial);

    /**
     * 修改编码流水
     */
    public int updateErpCodeSerial(ErpCodeSerial erpCodeSerial);

    /**
     * 批量删除编码流水
     */
    public int deleteErpCodeSerialBySerialIds(Long[] serialIds);

    /**
     * 删除编码流水信息
     */
    public int deleteErpCodeSerialBySerialId(Long serialId);
}
