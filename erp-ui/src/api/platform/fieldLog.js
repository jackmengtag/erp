import request from '@/utils/request'

// 查询字段变更审计日志列表
export function listErpFieldChangeLog(query) {
  return request({
    url: '/platform/fieldLog/list',
    method: 'get',
    params: query
  })
}

// 查询字段变更审计日志详细
export function getErpFieldChangeLog(logId) {
  return request({
    url: '/platform/fieldLog/' + logId,
    method: 'get'
  })
}

// 新增字段变更审计日志
export function addErpFieldChangeLog(data) {
  return request({
    url: '/platform/fieldLog',
    method: 'post',
    data: data
  })
}

// 修改字段变更审计日志
export function updateErpFieldChangeLog(data) {
  return request({
    url: '/platform/fieldLog',
    method: 'put',
    data: data
  })
}

// 删除字段变更审计日志
export function delErpFieldChangeLog(logId) {
  return request({
    url: '/platform/fieldLog/' + logId,
    method: 'delete'
  })
}
