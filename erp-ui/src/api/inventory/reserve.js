import request from '@/utils/request'

// 查询库存预留列表
export function listErpInventoryReserve(query) {
  return request({
    url: '/inventory/reserve/list',
    method: 'get',
    params: query
  })
}

// 查询库存预留详细
export function getErpInventoryReserve(reserveId) {
  return request({
    url: '/inventory/reserve/' + reserveId,
    method: 'get'
  })
}

// 新增库存预留
export function addErpInventoryReserve(data) {
  return request({
    url: '/inventory/reserve',
    method: 'post',
    data: data
  })
}

// 修改库存预留
export function updateErpInventoryReserve(data) {
  return request({
    url: '/inventory/reserve',
    method: 'put',
    data: data
  })
}

// 删除库存预留
export function delErpInventoryReserve(reserveId) {
  return request({
    url: '/inventory/reserve/' + reserveId,
    method: 'delete'
  })
}
