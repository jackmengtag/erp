package com.ruoyi.mdm.service;

import java.util.List;
import com.ruoyi.mdm.domain.ErpCustomer;

/**
 * 客户Service接口
 *
 * @author erp
 */
public interface IErpCustomerService
{
    /**
     * 查询客户
     */
    public ErpCustomer selectErpCustomerByCustomerId(Long customerId);

    /**
     * 查询客户列表
     */
    public List<ErpCustomer> selectErpCustomerList(ErpCustomer erpCustomer);

    /**
     * 新增客户
     */
    public int insertErpCustomer(ErpCustomer erpCustomer);

    /**
     * 修改客户
     */
    public int updateErpCustomer(ErpCustomer erpCustomer);

    /**
     * 批量删除客户
     */
    public int deleteErpCustomerByCustomerIds(Long[] customerIds);

    /**
     * 删除客户信息
     */
    public int deleteErpCustomerByCustomerId(Long customerId);
}
