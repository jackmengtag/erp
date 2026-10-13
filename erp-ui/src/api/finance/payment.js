import request from '@/utils/request'

// 查询付款单列表
export function listErpPayment(query) {
  return request({
    url: '/finance/payment/list',
    method: 'get',
    params: query
  })
}

// 查询付款单详细
export function getErpPayment(paymentId) {
  return request({
    url: '/finance/payment/' + paymentId,
    method: 'get'
  })
}

// 新增付款单
export function addErpPayment(data) {
  return request({
    url: '/finance/payment',
    method: 'post',
    data: data
  })
}

// 修改付款单
export function updateErpPayment(data) {
  return request({
    url: '/finance/payment',
    method: 'put',
    data: data
  })
}

// 删除付款单
export function delErpPayment(paymentId) {
  return request({
    url: '/finance/payment/' + paymentId,
    method: 'delete'
  })
}
