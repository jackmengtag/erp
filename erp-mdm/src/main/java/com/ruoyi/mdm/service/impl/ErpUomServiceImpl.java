package com.ruoyi.mdm.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.mdm.domain.ErpUom;
import com.ruoyi.mdm.mapper.ErpUomMapper;
import com.ruoyi.mdm.service.IErpUomService;

/**
 * 计量单位Service业务层处理
 *
 * @author erp
 */
@Service
public class ErpUomServiceImpl implements IErpUomService
{
    @Autowired
    private ErpUomMapper erpUomMapper;

    /**
     * 查询计量单位
     */
    @Override
    public ErpUom selectErpUomByUomId(Long uomId)
    {
        return erpUomMapper.selectErpUomByUomId(uomId);
    }

    /**
     * 查询计量单位列表
     */
    @Override
    public List<ErpUom> selectErpUomList(ErpUom erpUom)
    {
        return erpUomMapper.selectErpUomList(erpUom);
    }

    /**
     * 新增计量单位
     */
    @Override
    public int insertErpUom(ErpUom erpUom)
    {
        erpUom.setCreateTime(DateUtils.getNowDate());
        return erpUomMapper.insertErpUom(erpUom);
    }

    /**
     * 修改计量单位
     */
    @Override
    public int updateErpUom(ErpUom erpUom)
    {
        erpUom.setUpdateTime(DateUtils.getNowDate());
        return erpUomMapper.updateErpUom(erpUom);
    }

    /**
     * 批量删除计量单位
     */
    @Override
    public int deleteErpUomByUomIds(Long[] uomIds)
    {
        return erpUomMapper.deleteErpUomByUomIds(uomIds);
    }

    /**
     * 删除计量单位信息
     */
    @Override
    public int deleteErpUomByUomId(Long uomId)
    {
        return erpUomMapper.deleteErpUomByUomId(uomId);
    }
}
