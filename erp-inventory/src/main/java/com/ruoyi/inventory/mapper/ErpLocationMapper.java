package com.ruoyi.inventory.mapper;

import java.util.List;
import com.ruoyi.inventory.domain.ErpLocation;

/**
 * 库位Mapper接口
 *
 * @author erp
 */
public interface ErpLocationMapper
{
    /**
     * 查询库位
     */
    public ErpLocation selectErpLocationByLocationId(Long locationId);

    /**
     * 查询库位列表
     */
    public List<ErpLocation> selectErpLocationList(ErpLocation erpLocation);

    /**
     * 新增库位
     */
    public int insertErpLocation(ErpLocation erpLocation);

    /**
     * 修改库位
     */
    public int updateErpLocation(ErpLocation erpLocation);

    /**
     * 删除库位
     */
    public int deleteErpLocationByLocationId(Long locationId);

    /**
     * 批量删除库位
     */
    public int deleteErpLocationByLocationIds(Long[] locationIds);
}
