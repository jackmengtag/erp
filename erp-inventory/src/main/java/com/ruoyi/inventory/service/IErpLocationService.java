package com.ruoyi.inventory.service;

import java.util.List;
import com.ruoyi.inventory.domain.ErpLocation;

/**
 * 库位Service接口
 *
 * @author erp
 */
public interface IErpLocationService
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
     * 批量删除库位
     */
    public int deleteErpLocationByLocationIds(Long[] locationIds);

    /**
     * 删除库位信息
     */
    public int deleteErpLocationByLocationId(Long locationId);
}
