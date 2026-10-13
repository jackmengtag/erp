package com.ruoyi.platform.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.ruoyi.platform.domain.ErpCodeSerial;

/**
 * 编码流水Mapper接口
 *
 * @author erp
 */
public interface ErpCodeSerialMapper
{
    /**
     * 查询编码流水
     */
    public ErpCodeSerial selectErpCodeSerialBySerialId(Long serialId);

    /**
     * 按规则编码与周期键查询编码流水(加锁)
     */
    public ErpCodeSerial selectErpCodeSerialForUpdate(@Param("ruleCode") String ruleCode, @Param("periodKey") String periodKey);

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
     * 删除编码流水
     */
    public int deleteErpCodeSerialBySerialId(Long serialId);

    /**
     * 批量删除编码流水
     */
    public int deleteErpCodeSerialBySerialIds(Long[] serialIds);
}
