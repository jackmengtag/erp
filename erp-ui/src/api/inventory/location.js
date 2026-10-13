import request from '@/utils/request'

// 查询库位列表
export function listErpLocation(query) {
  return request({
    url: '/inventory/location/list',
    method: 'get',
    params: query
  })
}

// 查询库位详细
export function getErpLocation(locationId) {
  return request({
    url: '/inventory/location/' + locationId,
    method: 'get'
  })
}

// 新增库位
export function addErpLocation(data) {
  return request({
    url: '/inventory/location',
    method: 'post',
    data: data
  })
}

// 修改库位
export function updateErpLocation(data) {
  return request({
    url: '/inventory/location',
    method: 'put',
    data: data
  })
}

// 删除库位
export function delErpLocation(locationId) {
  return request({
    url: '/inventory/location/' + locationId,
    method: 'delete'
  })
}
