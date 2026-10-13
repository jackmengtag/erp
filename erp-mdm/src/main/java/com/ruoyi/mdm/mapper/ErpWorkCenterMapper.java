package com.ruoyi.mdm.mapper;

import java.util.List;
import com.ruoyi.mdm.domain.ErpWorkCenter;

/**
 * 工作中心Mapper接口
 *
 * @author erp
 */
public interface ErpWorkCenterMapper
{
    /**
     * 查询工作中心
     */
    public ErpWorkCenter selectErpWorkCenterByWcId(Long wcId);

    /**
     * 查询工作中心列表
     */
    public List<ErpWorkCenter> selectErpWorkCenterList(ErpWorkCenter erpWorkCenter);

    /**
     * 新增工作中心
     */
    public int insertErpWorkCenter(ErpWorkCenter erpWorkCenter);

    /**
     * 修改工作中心
     */
    public int updateErpWorkCenter(ErpWorkCenter erpWorkCenter);

    /**
     * 删除工作中心
     */
    public int deleteErpWorkCenterByWcId(Long wcId);

    /**
     * 批量删除工作中心
     */
    public int deleteErpWorkCenterByWcIds(Long[] wcIds);
}
