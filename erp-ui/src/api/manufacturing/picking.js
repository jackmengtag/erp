import request from '@/utils/request'

// 查询生产领料单列表
export function listErpProductionPicking(query) {
  return request({
    url: '/manufacturing/picking/list',
    method: 'get',
    params: query
  })
}

// 查询生产领料单详细
export function getErpProductionPicking(pickingId) {
  return request({
    url: '/manufacturing/picking/' + pickingId,
    method: 'get'
  })
}

// 新增生产领料单
export function addErpProductionPicking(data) {
  return request({
    url: '/manufacturing/picking',
    method: 'post',
    data: data
  })
}

// 修改生产领料单
export function updateErpProductionPicking(data) {
  return request({
    url: '/manufacturing/picking',
    method: 'put',
    data: data
  })
}

// 删除生产领料单
export function delErpProductionPicking(pickingId) {
  return request({
    url: '/manufacturing/picking/' + pickingId,
    method: 'delete'
  })
}
