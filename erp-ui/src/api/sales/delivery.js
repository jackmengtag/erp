import request from '@/utils/request'

// 查询销售发货单列表
export function listErpDelivery(query) {
  return request({
    url: '/sales/delivery/list',
    method: 'get',
    params: query
  })
}

// 查询销售发货单详细
export function getErpDelivery(deliveryId) {
  return request({
    url: '/sales/delivery/' + deliveryId,
    method: 'get'
  })
}

// 新增销售发货单
export function addErpDelivery(data) {
  return request({
    url: '/sales/delivery',
    method: 'post',
    data: data
  })
}

// 修改销售发货单
export function updateErpDelivery(data) {
  return request({
    url: '/sales/delivery',
    method: 'put',
    data: data
  })
}

// 删除销售发货单
export function delErpDelivery(deliveryId) {
  return request({
    url: '/sales/delivery/' + deliveryId,
    method: 'delete'
  })
}
