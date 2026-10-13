package com.ruoyi.inventory.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.inventory.domain.ErpLocation;
import com.ruoyi.inventory.mapper.ErpLocationMapper;
import com.ruoyi.inventory.service.IErpLocationService;

/**
 * 库位Service业务层处理
 *
 * @author erp
 */
@Service
public class ErpLocationServiceImpl implements IErpLocationService
{
    @Autowired
    private ErpLocationMapper erpLocationMapper;

    /**
     * 查询库位
     */
    @Override
    public ErpLocation selectErpLocationByLocationId(Long locationId)
    {
        return erpLocationMapper.selectErpLocationByLocationId(locationId);
    }

    /**
     * 查询库位列表
     */
    @Override
    public List<ErpLocation> selectErpLocationList(ErpLocation erpLocation)
    {
        return erpLocationMapper.selectErpLocationList(erpLocation);
    }

    /**
     * 新增库位
     */
    @Override
    public int insertErpLocation(ErpLocation erpLocation)
    {
        erpLocation.setCreateTime(DateUtils.getNowDate());
        return erpLocationMapper.insertErpLocation(erpLocation);
    }

    /**
     * 修改库位
     */
    @Override
    public int updateErpLocation(ErpLocation erpLocation)
    {
        erpLocation.setUpdateTime(DateUtils.getNowDate());
        return erpLocationMapper.updateErpLocation(erpLocation);
    }

    /**
     * 批量删除库位
     */
    @Override
    public int deleteErpLocationByLocationIds(Long[] locationIds)
    {
        return erpLocationMapper.deleteErpLocationByLocationIds(locationIds);
    }

    /**
     * 删除库位信息
     */
    @Override
    public int deleteErpLocationByLocationId(Long locationId)
    {
        return erpLocationMapper.deleteErpLocationByLocationId(locationId);
    }
}
