package com.ruoyi.quality.mapper;

import java.util.List;
import com.ruoyi.quality.domain.ErpCapa;

/**
 * CAPA与客诉Mapper接口
 *
 * @author erp
 */
public interface ErpCapaMapper
{
    /**
     * 查询CAPA与客诉
     */
    public ErpCapa selectErpCapaByCapaId(Long capaId);

    /**
     * 查询CAPA与客诉列表
     */
    public List<ErpCapa> selectErpCapaList(ErpCapa erpCapa);

    /**
     * 新增CAPA与客诉
     */
    public int insertErpCapa(ErpCapa erpCapa);

    /**
     * 修改CAPA与客诉
     */
    public int updateErpCapa(ErpCapa erpCapa);

    /**
     * 删除CAPA与客诉
     */
    public int deleteErpCapaByCapaId(Long capaId);

    /**
     * 批量删除CAPA与客诉
     */
    public int deleteErpCapaByCapaIds(Long[] capaIds);
}
