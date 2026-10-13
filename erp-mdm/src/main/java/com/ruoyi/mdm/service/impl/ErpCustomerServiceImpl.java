package com.ruoyi.mdm.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.mdm.domain.ErpCustomer;
import com.ruoyi.mdm.mapper.ErpCustomerMapper;
import com.ruoyi.mdm.service.IErpCustomerService;

/**
 * 客户Service业务层处理
 *
 * @author erp
 */
@Service
public class ErpCustomerServiceImpl implements IErpCustomerService
{
    @Autowired
    private ErpCustomerMapper erpCustomerMapper;

    /**
     * 查询客户
     */
    @Override
    public ErpCustomer selectErpCustomerByCustomerId(Long customerId)
    {
        return erpCustomerMapper.selectErpCustomerByCustomerId(customerId);
    }

    /**
     * 查询客户列表
     */
    @Override
    public List<ErpCustomer> selectErpCustomerList(ErpCustomer erpCustomer)
    {
        return erpCustomerMapper.selectErpCustomerList(erpCustomer);
    }

    /**
     * 新增客户
     */
    @Override
    public int insertErpCustomer(ErpCustomer erpCustomer)
    {
        erpCustomer.setCreateTime(DateUtils.getNowDate());
        return erpCustomerMapper.insertErpCustomer(erpCustomer);
    }

    /**
     * 修改客户
     */
    @Override
    public int updateErpCustomer(ErpCustomer erpCustomer)
    {
        erpCustomer.setUpdateTime(DateUtils.getNowDate());
        return erpCustomerMapper.updateErpCustomer(erpCustomer);
    }

    /**
     * 批量删除客户
     */
    @Override
    public int deleteErpCustomerByCustomerIds(Long[] customerIds)
    {
        return erpCustomerMapper.deleteErpCustomerByCustomerIds(customerIds);
    }

    /**
     * 删除客户信息
     */
    @Override
    public int deleteErpCustomerByCustomerId(Long customerId)
    {
        return erpCustomerMapper.deleteErpCustomerByCustomerId(customerId);
    }
}
