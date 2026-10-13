package com.ruoyi.mdm.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.mdm.domain.ErpWorkCenter;
import com.ruoyi.mdm.mapper.ErpWorkCenterMapper;
import com.ruoyi.mdm.service.IErpWorkCenterService;

/**
 * 工作中心Service业务层处理
 *
 * @author erp
 */
@Service
public class ErpWorkCenterServiceImpl implements IErpWorkCenterService
{
    @Autowired
    private ErpWorkCenterMapper erpWorkCenterMapper;

    /**
     * 查询工作中心
     */
    @Override
    public ErpWorkCenter selectErpWorkCenterByWcId(Long wcId)
    {
        return erpWorkCenterMapper.selectErpWorkCenterByWcId(wcId);
    }

    /**
     * 查询工作中心列表
     */
    @Override
    public List<ErpWorkCenter> selectErpWorkCenterList(ErpWorkCenter erpWorkCenter)
    {
        return erpWorkCenterMapper.selectErpWorkCenterList(erpWorkCenter);
    }

    /**
     * 新增工作中心
     */
    @Override
    public int insertErpWorkCenter(ErpWorkCenter erpWorkCenter)
    {
        erpWorkCenter.setCreateTime(DateUtils.getNowDate());
        return erpWorkCenterMapper.insertErpWorkCenter(erpWorkCenter);
    }

    /**
     * 修改工作中心
     */
    @Override
    public int updateErpWorkCenter(ErpWorkCenter erpWorkCenter)
    {
        erpWorkCenter.setUpdateTime(DateUtils.getNowDate());
        return erpWorkCenterMapper.updateErpWorkCenter(erpWorkCenter);
    }

    /**
     * 批量删除工作中心
     */
    @Override
    public int deleteErpWorkCenterByWcIds(Long[] wcIds)
    {
        return erpWorkCenterMapper.deleteErpWorkCenterByWcIds(wcIds);
    }

    /**
     * 删除工作中心信息
     */
    @Override
    public int deleteErpWorkCenterByWcId(Long wcId)
    {
        return erpWorkCenterMapper.deleteErpWorkCenterByWcId(wcId);
    }
}
