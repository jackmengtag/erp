package com.ruoyi.platform.service;

import java.util.List;
import com.ruoyi.platform.domain.ErpCodeRule;

/**
 * 编码规则Service接口
 *
 * @author erp
 */
public interface IErpCodeRuleService
{
    /**
     * 查询编码规则
     */
    public ErpCodeRule selectErpCodeRuleByRuleId(Long ruleId);

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
     * 批量删除编码规则
     */
    public int deleteErpCodeRuleByRuleIds(Long[] ruleIds);

    /**
     * 删除编码规则信息
     */
    public int deleteErpCodeRuleByRuleId(Long ruleId);

    /**
     * 根据业务对象生成编码
     *
     * @param bizObject 业务对象
     * @return 生成的编码
     */
    public String generateCode(String bizObject);
}
