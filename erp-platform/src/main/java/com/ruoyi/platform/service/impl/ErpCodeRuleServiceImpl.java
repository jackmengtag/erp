package com.ruoyi.platform.service.impl;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.platform.domain.ErpCodeRule;
import com.ruoyi.platform.domain.ErpCodeSerial;
import com.ruoyi.platform.mapper.ErpCodeRuleMapper;
import com.ruoyi.platform.mapper.ErpCodeSerialMapper;
import com.ruoyi.platform.service.IErpCodeRuleService;

/**
 * 编码规则Service业务层处理
 *
 * @author erp
 */
@Service
public class ErpCodeRuleServiceImpl implements IErpCodeRuleService
{
    @Autowired
    private ErpCodeRuleMapper erpCodeRuleMapper;

    @Autowired
    private ErpCodeSerialMapper erpCodeSerialMapper;

    /**
     * 查询编码规则
     */
    @Override
    public ErpCodeRule selectErpCodeRuleByRuleId(Long ruleId)
    {
        return erpCodeRuleMapper.selectErpCodeRuleByRuleId(ruleId);
    }

    /**
     * 查询编码规则列表
     */
    @Override
    public List<ErpCodeRule> selectErpCodeRuleList(ErpCodeRule erpCodeRule)
    {
        return erpCodeRuleMapper.selectErpCodeRuleList(erpCodeRule);
    }

    /**
     * 新增编码规则
     */
    @Override
    public int insertErpCodeRule(ErpCodeRule erpCodeRule)
    {
        erpCodeRule.setCreateTime(DateUtils.getNowDate());
        return erpCodeRuleMapper.insertErpCodeRule(erpCodeRule);
    }

    /**
     * 修改编码规则
     */
    @Override
    public int updateErpCodeRule(ErpCodeRule erpCodeRule)
    {
        erpCodeRule.setUpdateTime(DateUtils.getNowDate());
        return erpCodeRuleMapper.updateErpCodeRule(erpCodeRule);
    }

    /**
     * 批量删除编码规则
     */
    @Override
    public int deleteErpCodeRuleByRuleIds(Long[] ruleIds)
    {
        return erpCodeRuleMapper.deleteErpCodeRuleByRuleIds(ruleIds);
    }

    /**
     * 删除编码规则信息
     */
    @Override
    public int deleteErpCodeRuleByRuleId(Long ruleId)
    {
        return erpCodeRuleMapper.deleteErpCodeRuleByRuleId(ruleId);
    }

    /**
     * 根据业务对象生成编码
     */
    @Override
    @Transactional
    public String generateCode(String bizObject)
    {
        ErpCodeRule rule = erpCodeRuleMapper.selectErpCodeRuleByBizObject(bizObject);
        if (rule == null)
        {
            return null;
        }
        String dateStr = "";
        String periodKey = "";
        String datePattern = rule.getDatePattern();
        if (StringUtils.isNotEmpty(datePattern) && !"none".equalsIgnoreCase(datePattern))
        {
            dateStr = DateUtils.parseDateToStr(datePattern, new Date());
            periodKey = dateStr;
        }

        long seq = 1L;
        ErpCodeSerial serial = erpCodeSerialMapper.selectErpCodeSerialForUpdate(rule.getRuleCode(), periodKey);
        if (serial == null)
        {
            serial = new ErpCodeSerial();
            serial.setRuleCode(rule.getRuleCode());
            serial.setPeriodKey(periodKey);
            serial.setCurrentSeq(1L);
            serial.setUpdateTime(DateUtils.getNowDate());
            erpCodeSerialMapper.insertErpCodeSerial(serial);
            seq = 1L;
        }
        else
        {
            seq = serial.getCurrentSeq() == null ? 1L : serial.getCurrentSeq() + 1L;
            serial.setCurrentSeq(seq);
            serial.setUpdateTime(DateUtils.getNowDate());
            erpCodeSerialMapper.updateErpCodeSerial(serial);
        }

        int seqLength = rule.getSeqLength() == null ? 4 : rule.getSeqLength();
        String seqStr = StringUtils.padl(seq, seqLength);

        StringBuilder code = new StringBuilder();
        if (StringUtils.isNotEmpty(rule.getPrefix()))
        {
            code.append(rule.getPrefix());
        }
        code.append(dateStr);
        if (StringUtils.isNotEmpty(rule.getSeparator()))
        {
            code.append(rule.getSeparator());
        }
        code.append(seqStr);
        return code.toString();
    }
}
