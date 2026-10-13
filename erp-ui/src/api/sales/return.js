import request from '@/utils/request'

// 查询销售退货单列表
export function listErpSalesReturn(query) {
  return request({
    url: '/sales/return/list',
    method: 'get',
    params: query
  })
}

// 查询销售退货单详细
export function getErpSalesReturn(returnId) {
  return request({
    url: '/sales/return/' + returnId,
    method: 'get'
  })
}

// 新增销售退货单
export function addErpSalesReturn(data) {
  return request({
    url: '/sales/return',
    method: 'post',
    data: data
  })
}

// 修改销售退货单
export function updateErpSalesReturn(data) {
  return request({
    url: '/sales/return',
    method: 'put',
    data: data
  })
}

// 删除销售退货单
export function delErpSalesReturn(returnId) {
  return request({
    url: '/sales/return/' + returnId,
    method: 'delete'
  })
}
