import request from '@/utils/request'

// 查询库存余额列表
export function listErpInventory(query) {
  return request({
    url: '/inventory/inventory/list',
    method: 'get',
    params: query
  })
}

// 查询库存余额详细
export function getErpInventory(inventoryId) {
  return request({
    url: '/inventory/inventory/' + inventoryId,
    method: 'get'
  })
}

// 新增库存余额
export function addErpInventory(data) {
  return request({
    url: '/inventory/inventory',
    method: 'post',
    data: data
  })
}

// 修改库存余额
export function updateErpInventory(data) {
  return request({
    url: '/inventory/inventory',
    method: 'put',
    data: data
  })
}

// 删除库存余额
export function delErpInventory(inventoryId) {
  return request({
    url: '/inventory/inventory/' + inventoryId,
    method: 'delete'
  })
}
