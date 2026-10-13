package com.ruoyi.mdm.service;

import java.util.List;
import com.ruoyi.mdm.domain.ErpUom;

/**
 * 计量单位Service接口
 *
 * @author erp
 */
public interface IErpUomService
{
    /**
     * 查询计量单位
     */
    public ErpUom selectErpUomByUomId(Long uomId);

    /**
     * 查询计量单位列表
     */
    public List<ErpUom> selectErpUomList(ErpUom erpUom);

    /**
     * 新增计量单位
     */
    public int insertErpUom(ErpUom erpUom);

    /**
     * 修改计量单位
     */
    public int updateErpUom(ErpUom erpUom);

    /**
     * 批量删除计量单位
     */
    public int deleteErpUomByUomIds(Long[] uomIds);

    /**
     * 删除计量单位信息
     */
    public int deleteErpUomByUomId(Long uomId);
}
