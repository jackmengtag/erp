package com.ruoyi.mdm.mapper;

import java.util.List;
import com.ruoyi.mdm.domain.ErpSupplier;

/**
 * 供应商Mapper接口
 *
 * @author erp
 */
public interface ErpSupplierMapper
{
    /**
     * 查询供应商
     */
    public ErpSupplier selectErpSupplierBySupplierId(Long supplierId);

    /**
     * 查询供应商列表
     */
    public List<ErpSupplier> selectErpSupplierList(ErpSupplier erpSupplier);

    /**
     * 新增供应商
     */
    public int insertErpSupplier(ErpSupplier erpSupplier);

    /**
     * 修改供应商
     */
    public int updateErpSupplier(ErpSupplier erpSupplier);

    /**
     * 删除供应商
     */
    public int deleteErpSupplierBySupplierId(Long supplierId);

    /**
     * 批量删除供应商
     */
    public int deleteErpSupplierBySupplierIds(Long[] supplierIds);
}
