package com.ruoyi.mdm.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.mdm.domain.ErpSupplier;
import com.ruoyi.mdm.mapper.ErpSupplierMapper;
import com.ruoyi.mdm.service.IErpSupplierService;

/**
 * 供应商Service业务层处理
 *
 * @author erp
 */
@Service
public class ErpSupplierServiceImpl implements IErpSupplierService
{
    @Autowired
    private ErpSupplierMapper erpSupplierMapper;

    /**
     * 查询供应商
     */
    @Override
    public ErpSupplier selectErpSupplierBySupplierId(Long supplierId)
    {
        return erpSupplierMapper.selectErpSupplierBySupplierId(supplierId);
    }

    /**
     * 查询供应商列表
     */
    @Override
    public List<ErpSupplier> selectErpSupplierList(ErpSupplier erpSupplier)
    {
        return erpSupplierMapper.selectErpSupplierList(erpSupplier);
    }

    /**
     * 新增供应商
     */
    @Override
    public int insertErpSupplier(ErpSupplier erpSupplier)
    {
        erpSupplier.setCreateTime(DateUtils.getNowDate());
        return erpSupplierMapper.insertErpSupplier(erpSupplier);
    }

    /**
     * 修改供应商
     */
    @Override
    public int updateErpSupplier(ErpSupplier erpSupplier)
    {
        erpSupplier.setUpdateTime(DateUtils.getNowDate());
        return erpSupplierMapper.updateErpSupplier(erpSupplier);
    }

    /**
     * 批量删除供应商
     */
    @Override
    public int deleteErpSupplierBySupplierIds(Long[] supplierIds)
    {
        return erpSupplierMapper.deleteErpSupplierBySupplierIds(supplierIds);
    }

    /**
     * 删除供应商信息
     */
    @Override
    public int deleteErpSupplierBySupplierId(Long supplierId)
    {
        return erpSupplierMapper.deleteErpSupplierBySupplierId(supplierId);
    }
}
