package com.ruoyi.quality.service;

import java.util.List;
import com.ruoyi.quality.domain.ErpCapa;

/**
 * CAPA与客诉Service接口
 *
 * @author erp
 */
public interface IErpCapaService
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
     * 批量删除CAPA与客诉
     */
    public int deleteErpCapaByCapaIds(Long[] capaIds);

    /**
     * 删除CAPA与客诉信息
     */
    public int deleteErpCapaByCapaId(Long capaId);
}
