package com.ruoyi.mdm.mapper;

import java.util.List;
import com.ruoyi.mdm.domain.ErpRouting;
import com.ruoyi.mdm.domain.ErpRoutingOperation;

/**
 * 工艺路线主表Mapper接口
 *
 * @author erp
 */
public interface ErpRoutingMapper
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
     * 删除工艺路线主表
     */
    public int deleteErpRoutingByRoutingId(Long routingId);

    /**
     * 批量删除工艺路线主表
     */
    public int deleteErpRoutingByRoutingIds(Long[] routingIds);

    /**
     * 批量删除工序明细
     */
    public int deleteErpRoutingOperationByRoutingIds(Long[] routingIds);

    /**
     * 删除工序明细信息
     */
    public int deleteErpRoutingOperationByRoutingId(Long routingId);

    /**
     * 批量新增工序明细
     */
    public int batchErpRoutingOperation(List<ErpRoutingOperation> erpRoutingOperationList);
}
