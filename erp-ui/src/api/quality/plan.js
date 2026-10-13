import request from '@/utils/request'

// 查询检验方案列表
export function listErpInspectionPlan(query) {
  return request({
    url: '/quality/plan/list',
    method: 'get',
    params: query
  })
}

// 查询检验方案详细
export function getErpInspectionPlan(planId) {
  return request({
    url: '/quality/plan/' + planId,
    method: 'get'
  })
}

// 新增检验方案
export function addErpInspectionPlan(data) {
  return request({
    url: '/quality/plan',
    method: 'post',
    data: data
  })
}

// 修改检验方案
export function updateErpInspectionPlan(data) {
  return request({
    url: '/quality/plan',
    method: 'put',
    data: data
  })
}

// 删除检验方案
export function delErpInspectionPlan(planId) {
  return request({
    url: '/quality/plan/' + planId,
    method: 'delete'
  })
}
