import request from '@/utils/request'

// 查询检验单列表
export function listErpInspectionOrder(query) {
  return request({
    url: '/quality/inspect/list',
    method: 'get',
    params: query
  })
}

// 查询检验单详细
export function getErpInspectionOrder(inspectId) {
  return request({
    url: '/quality/inspect/' + inspectId,
    method: 'get'
  })
}

// 新增检验单
export function addErpInspectionOrder(data) {
  return request({
    url: '/quality/inspect',
    method: 'post',
    data: data
  })
}

// 修改检验单
export function updateErpInspectionOrder(data) {
  return request({
    url: '/quality/inspect',
    method: 'put',
    data: data
  })
}

// 删除检验单
export function delErpInspectionOrder(inspectId) {
  return request({
    url: '/quality/inspect/' + inspectId,
    method: 'delete'
  })
}
