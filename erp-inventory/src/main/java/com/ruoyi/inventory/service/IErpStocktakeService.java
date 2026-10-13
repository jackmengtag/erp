package com.ruoyi.inventory.service;

import java.util.List;
import com.ruoyi.inventory.domain.ErpStocktake;

/**
 * 盘点单Service接口
 *
 * @author erp
 */
public interface IErpStocktakeService
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
     * 批量删除盘点单
     */
    public int deleteErpStocktakeByStocktakeIds(Long[] stocktakeIds);

    /**
     * 删除盘点单信息
     */
    public int deleteErpStocktakeByStocktakeId(Long stocktakeId);
}
