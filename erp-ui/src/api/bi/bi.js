import request from '@/utils/request'

// 经营驾驶舱核心指标
export function getDashboard() {
  return request({
    url: '/bi/dashboard',
    method: 'get'
  })
}

// 订单交付报表
export function listOrderDelivery(query) {
  return request({
    url: '/bi/orderDelivery',
    method: 'get',
    params: query
  })
}

// 库存库龄报表
export function listInventoryAge(query) {
  return request({
    url: '/bi/inventoryAge',
    method: 'get',
    params: query
  })
}
