package com.ruoyi.platform.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.platform.domain.ErpCodeSerial;
import com.ruoyi.platform.mapper.ErpCodeSerialMapper;
import com.ruoyi.platform.service.IErpCodeSerialService;

/**
 * 编码流水Service业务层处理
 *
 * @author erp
 */
@Service
public class ErpCodeSerialServiceImpl implements IErpCodeSerialService
{
    @Autowired
    private ErpCodeSerialMapper erpCodeSerialMapper;

    /**
     * 查询编码流水
     */
    @Override
    public ErpCodeSerial selectErpCodeSerialBySerialId(Long serialId)
    {
        return erpCodeSerialMapper.selectErpCodeSerialBySerialId(serialId);
    }

    /**
     * 查询编码流水列表
     */
    @Override
    public List<ErpCodeSerial> selectErpCodeSerialList(ErpCodeSerial erpCodeSerial)
    {
        return erpCodeSerialMapper.selectErpCodeSerialList(erpCodeSerial);
    }

    /**
     * 新增编码流水
     */
    @Override
    public int insertErpCodeSerial(ErpCodeSerial erpCodeSerial)
    {
        erpCodeSerial.setUpdateTime(DateUtils.getNowDate());
        return erpCodeSerialMapper.insertErpCodeSerial(erpCodeSerial);
    }

    /**
     * 修改编码流水
     */
    @Override
    public int updateErpCodeSerial(ErpCodeSerial erpCodeSerial)
    {
        erpCodeSerial.setUpdateTime(DateUtils.getNowDate());
        return erpCodeSerialMapper.updateErpCodeSerial(erpCodeSerial);
    }

    /**
     * 批量删除编码流水
     */
    @Override
    public int deleteErpCodeSerialBySerialIds(Long[] serialIds)
    {
        return erpCodeSerialMapper.deleteErpCodeSerialBySerialIds(serialIds);
    }

    /**
     * 删除编码流水信息
     */
    @Override
    public int deleteErpCodeSerialBySerialId(Long serialId)
    {
        return erpCodeSerialMapper.deleteErpCodeSerialBySerialId(serialId);
    }
}
