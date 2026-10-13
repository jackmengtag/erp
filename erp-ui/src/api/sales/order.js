import request from '@/utils/request'

// 查询销售订单列表
export function listErpSalesOrder(query) {
  return request({
    url: '/sales/order/list',
    method: 'get',
    params: query
  })
}

// 查询销售订单详细
export function getErpSalesOrder(orderId) {
  return request({
    url: '/sales/order/' + orderId,
    method: 'get'
  })
}

// 新增销售订单
export function addErpSalesOrder(data) {
  return request({
    url: '/sales/order',
    method: 'post',
    data: data
  })
}

// 修改销售订单
export function updateErpSalesOrder(data) {
  return request({
    url: '/sales/order',
    method: 'put',
    data: data
  })
}

// 删除销售订单
export function delErpSalesOrder(orderId) {
  return request({
    url: '/sales/order/' + orderId,
    method: 'delete'
  })
}
