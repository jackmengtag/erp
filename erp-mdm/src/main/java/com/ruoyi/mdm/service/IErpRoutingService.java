package com.ruoyi.mdm.service;

import java.util.List;
import com.ruoyi.mdm.domain.ErpRouting;

/**
 * 工艺路线主表Service接口
 *
 * @author erp
 */
public interface IErpRoutingService
{
    /**
     * 查询工艺路线主表
     */
    public ErpRouting selectErpRoutingByRoutingId(Long routingId);

    /**
     * 查询工艺路线主表列表
     */
    public List<ErpRouting> selectErpRoutingList(ErpRouting erpRouting);

    /**
     * 新增工艺路线主表
     */
    public int insertErpRouting(ErpRouting erpRouting);

    /**
     * 修改工艺路线主表
     */
    public int updateErpRouting(ErpRouting erpRouting);

    /**
     * 批量删除工艺路线主表
     */
    public int deleteErpRoutingByRoutingIds(Long[] routingIds);

    /**
     * 删除工艺路线主表信息
     */
    public int deleteErpRoutingByRoutingId(Long routingId);
}
