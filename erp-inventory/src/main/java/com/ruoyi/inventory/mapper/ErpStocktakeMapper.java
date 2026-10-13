package com.ruoyi.inventory.mapper;

import java.util.List;
import com.ruoyi.inventory.domain.ErpStocktake;
import com.ruoyi.inventory.domain.ErpStocktakeLine;

/**
 * 盘点单Mapper接口
 *
 * @author erp
 */
public interface ErpStocktakeMapper
{
    /**
     * 查询盘点单
     */
    public ErpStocktake selectErpStocktakeByStocktakeId(Long stocktakeId);

    /**
     * 查询盘点单列表
     */
    public List<ErpStocktake> selectErpStocktakeList(ErpStocktake erpStocktake);

    /**
     * 新增盘点单
     */
    public int insertErpStocktake(ErpStocktake erpStocktake);

    /**
     * 修改盘点单
     */
    public int updateErpStocktake(ErpStocktake erpStocktake);

    /**
     * 删除盘点单
     */
    public int deleteErpStocktakeByStocktakeId(Long stocktakeId);

    /**
     * 批量删除盘点单
     */
    public int deleteErpStocktakeByStocktakeIds(Long[] stocktakeIds);

    /**
     * 批量删除盘点单明细
     */
    public int deleteErpStocktakeLineByStocktakeIds(Long[] stocktakeIds);

    /**
     * 删除盘点单明细信息
     */
    public int deleteErpStocktakeLineByStocktakeId(Long stocktakeId);

    /**
     * 批量新增盘点单明细
     */
    public int batchErpStocktakeLine(List<ErpStocktakeLine> erpStocktakeLineList);
}
