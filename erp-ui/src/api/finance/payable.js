import request from '@/utils/request'

// 查询应付单列表
export function listErpPayable(query) {
  return request({
    url: '/finance/payable/list',
    method: 'get',
    params: query
  })
}

// 查询应付单详细
export function getErpPayable(apId) {
  return request({
    url: '/finance/payable/' + apId,
    method: 'get'
  })
}

// 新增应付单
export function addErpPayable(data) {
  return request({
    url: '/finance/payable',
    method: 'post',
    data: data
  })
}

// 修改应付单
export function updateErpPayable(data) {
  return request({
    url: '/finance/payable',
    method: 'put',
    data: data
  })
}

// 删除应付单
export function delErpPayable(apId) {
  return request({
    url: '/finance/payable/' + apId,
    method: 'delete'
  })
}
