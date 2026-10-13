import request from '@/utils/request'

// 查询仓库列表
export function listErpWarehouse(query) {
  return request({
    url: '/inventory/warehouse/list',
    method: 'get',
    params: query
  })
}

// 查询仓库详细
export function getErpWarehouse(warehouseId) {
  return request({
    url: '/inventory/warehouse/' + warehouseId,
    method: 'get'
  })
}

// 新增仓库
export function addErpWarehouse(data) {
  return request({
    url: '/inventory/warehouse',
    method: 'post',
    data: data
  })
}

// 修改仓库
export function updateErpWarehouse(data) {
  return request({
    url: '/inventory/warehouse',
    method: 'put',
    data: data
  })
}

// 删除仓库
export function delErpWarehouse(warehouseId) {
  return request({
    url: '/inventory/warehouse/' + warehouseId,
    method: 'delete'
  })
}
