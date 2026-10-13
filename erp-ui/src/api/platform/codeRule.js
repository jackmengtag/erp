import request from '@/utils/request'

// 查询编码规则列表
export function listErpCodeRule(query) {
  return request({
    url: '/platform/codeRule/list',
    method: 'get',
    params: query
  })
}

// 查询编码规则详细
export function getErpCodeRule(ruleId) {
  return request({
    url: '/platform/codeRule/' + ruleId,
    method: 'get'
  })
}

// 新增编码规则
export function addErpCodeRule(data) {
  return request({
    url: '/platform/codeRule',
    method: 'post',
    data: data
  })
}

// 修改编码规则
export function updateErpCodeRule(data) {
  return request({
    url: '/platform/codeRule',
    method: 'put',
    data: data
  })
}

// 删除编码规则
export function delErpCodeRule(ruleId) {
  return request({
    url: '/platform/codeRule/' + ruleId,
    method: 'delete'
  })
}
