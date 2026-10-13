import request from '@/utils/request'

// 查询生产工单列表
export function listErpProductionOrder(query) {
  return request({
    url: '/manufacturing/mo/list',
    method: 'get',
    params: query
  })
}

// 查询生产工单详细
export function getErpProductionOrder(moId) {
  return request({
    url: '/manufacturing/mo/' + moId,
    method: 'get'
  })
}

// 新增生产工单
export function addErpProductionOrder(data) {
  return request({
    url: '/manufacturing/mo',
    method: 'post',
    data: data
  })
}

// 修改生产工单
export function updateErpProductionOrder(data) {
  return request({
    url: '/manufacturing/mo',
    method: 'put',
    data: data
  })
}

// 删除生产工单
export function delErpProductionOrder(moId) {
  return request({
    url: '/manufacturing/mo/' + moId,
    method: 'delete'
  })
}
