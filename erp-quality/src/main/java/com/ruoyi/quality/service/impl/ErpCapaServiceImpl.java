package com.ruoyi.quality.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.quality.domain.ErpCapa;
import com.ruoyi.quality.mapper.ErpCapaMapper;
import com.ruoyi.quality.service.IErpCapaService;

/**
 * CAPA与客诉Service业务层处理
 *
 * @author erp
 */
@Service
public class ErpCapaServiceImpl implements IErpCapaService
{
    @Autowired
    private ErpCapaMapper erpCapaMapper;

    /**
     * 查询CAPA与客诉
     */
    @Override
    public ErpCapa selectErpCapaByCapaId(Long capaId)
    {
        return erpCapaMapper.selectErpCapaByCapaId(capaId);
    }

    /**
     * 查询CAPA与客诉列表
     */
    @Override
    public List<ErpCapa> selectErpCapaList(ErpCapa erpCapa)
    {
        return erpCapaMapper.selectErpCapaList(erpCapa);
    }

    /**
     * 新增CAPA与客诉
     */
    @Override
    public int insertErpCapa(ErpCapa erpCapa)
    {
        erpCapa.setCreateTime(DateUtils.getNowDate());
        return erpCapaMapper.insertErpCapa(erpCapa);
    }

    /**
     * 修改CAPA与客诉
     */
    @Override
    public int updateErpCapa(ErpCapa erpCapa)
    {
        erpCapa.setUpdateTime(DateUtils.getNowDate());
        return erpCapaMapper.updateErpCapa(erpCapa);
    }

    /**
     * 批量删除CAPA与客诉
     */
    @Override
    public int deleteErpCapaByCapaIds(Long[] capaIds)
    {
        return erpCapaMapper.deleteErpCapaByCapaIds(capaIds);
    }

    /**
     * 删除CAPA与客诉信息
     */
    @Override
    public int deleteErpCapaByCapaId(Long capaId)
    {
        return erpCapaMapper.deleteErpCapaByCapaId(capaId);
    }
}
