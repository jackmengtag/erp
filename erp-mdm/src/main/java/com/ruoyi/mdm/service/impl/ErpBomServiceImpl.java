package com.ruoyi.mdm.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.mdm.domain.ErpBom;
import com.ruoyi.mdm.domain.ErpBomLine;
import com.ruoyi.mdm.mapper.ErpBomMapper;
import com.ruoyi.mdm.service.IErpBomService;

/**
 * BOM主表Service业务层处理
 *
 * @author erp
 */
@Service
public class ErpBomServiceImpl implements IErpBomService
{
    @Autowired
    private ErpBomMapper erpBomMapper;

    /**
     * 查询BOM主表
     */
    @Override
    public ErpBom selectErpBomByBomId(Long bomId)
    {
        return erpBomMapper.selectErpBomByBomId(bomId);
    }

    /**
     * 查询BOM主表列表
     */
    @Override
    public List<ErpBom> selectErpBomList(ErpBom erpBom)
    {
        return erpBomMapper.selectErpBomList(erpBom);
    }

    /**
     * 新增BOM主表
     */
    @Transactional
    @Override
    public int insertErpBom(ErpBom erpBom)
    {
        erpBom.setCreateTime(DateUtils.getNowDate());
        int rows = erpBomMapper.insertErpBom(erpBom);
        insertErpBomLine(erpBom);
        return rows;
    }

    /**
     * 修改BOM主表
     */
    @Transactional
    @Override
    public int updateErpBom(ErpBom erpBom)
    {
        erpBom.setUpdateTime(DateUtils.getNowDate());
        erpBomMapper.deleteErpBomLineByBomId(erpBom.getBomId());
        insertErpBomLine(erpBom);
        return erpBomMapper.updateErpBom(erpBom);
    }

    /**
     * 批量删除BOM主表
     */
    @Transactional
    @Override
    public int deleteErpBomByBomIds(Long[] bomIds)
    {
        erpBomMapper.deleteErpBomLineByBomIds(bomIds);
        return erpBomMapper.deleteErpBomByBomIds(bomIds);
    }

    /**
     * 删除BOM主表信息
     */
    @Transactional
    @Override
    public int deleteErpBomByBomId(Long bomId)
    {
        erpBomMapper.deleteErpBomLineByBomId(bomId);
        return erpBomMapper.deleteErpBomByBomId(bomId);
    }

    /**
     * 新增BOM子件明细信息
     */
    public void insertErpBomLine(ErpBom erpBom)
    {
        List<ErpBomLine> erpBomLineList = erpBom.getErpBomLineList();
        if (StringUtils.isNotNull(erpBomLineList))
        {
            for (ErpBomLine line : erpBomLineList)
            {
                line.setBomId(erpBom.getBomId());
            }
            if (erpBomLineList.size() > 0)
            {
                erpBomMapper.batchErpBomLine(erpBomLineList);
            }
        }
    }
}
