import request from '@/utils/request'

// 查询采购订单列表
export function listErpPurchaseOrder(query) {
  return request({
    url: '/purchase/order/list',
    method: 'get',
    params: query
  })
}

// 查询采购订单详细
export function getErpPurchaseOrder(poId) {
  return request({
    url: '/purchase/order/' + poId,
    method: 'get'
  })
}

// 新增采购订单
export function addErpPurchaseOrder(data) {
  return request({
    url: '/purchase/order',
    method: 'post',
    data: data
  })
}

// 修改采购订单
export function updateErpPurchaseOrder(data) {
  return request({
    url: '/purchase/order',
    method: 'put',
    data: data
  })
}

// 删除采购订单
export function delErpPurchaseOrder(poId) {
  return request({
    url: '/purchase/order/' + poId,
    method: 'delete'
  })
}
