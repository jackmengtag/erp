package com.ruoyi.mdm.mapper;

import java.util.List;
import com.ruoyi.mdm.domain.ErpCustomer;

/**
 * 客户Mapper接口
 *
 * @author erp
 */
public interface ErpCustomerMapper
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
     * 删除客户
     */
    public int deleteErpCustomerByCustomerId(Long customerId);

    /**
     * 批量删除客户
     */
    public int deleteErpCustomerByCustomerIds(Long[] customerIds);
}
