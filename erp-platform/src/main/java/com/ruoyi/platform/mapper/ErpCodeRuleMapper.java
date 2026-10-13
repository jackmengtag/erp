package com.ruoyi.platform.mapper;

import java.util.List;
import com.ruoyi.platform.domain.ErpCodeRule;

/**
 * 编码规则Mapper接口
 *
 * @author erp
 */
public interface ErpCodeRuleMapper
{
    /**
     * 查询编码规则
     */
    public ErpCodeRule selectErpCodeRuleByRuleId(Long ruleId);

    /**
     * 按业务对象查询编码规则
     */
    public ErpCodeRule selectErpCodeRuleByBizObject(String bizObject);

    /**
     * 查询编码规则列表
     */
    public List<ErpCodeRule> selectErpCodeRuleList(ErpCodeRule erpCodeRule);

    /**
     * 新增编码规则
     */
    public int insertErpCodeRule(ErpCodeRule erpCodeRule);

    /**
     * 修改编码规则
     */
    public int updateErpCodeRule(ErpCodeRule erpCodeRule);

    /**
     * 删除编码规则
     */
    public int deleteErpCodeRuleByRuleId(Long ruleId);

    /**
     * 批量删除编码规则
     */
    public int deleteErpCodeRuleByRuleIds(Long[] ruleIds);
}
