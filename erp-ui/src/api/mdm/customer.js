import request from '@/utils/request'

// 查询客户列表
export function listErpCustomer(query) {
  return request({
    url: '/mdm/customer/list',
    method: 'get',
    params: query
  })
}

// 查询客户详细
export function getErpCustomer(customerId) {
  return request({
    url: '/mdm/customer/' + customerId,
    method: 'get'
  })
}

// 新增客户
export function addErpCustomer(data) {
  return request({
    url: '/mdm/customer',
    method: 'post',
    data: data
  })
}

// 修改客户
export function updateErpCustomer(data) {
  return request({
    url: '/mdm/customer',
    method: 'put',
    data: data
  })
}

// 删除客户
export function delErpCustomer(customerId) {
  return request({
    url: '/mdm/customer/' + customerId,
    method: 'delete'
  })
}
