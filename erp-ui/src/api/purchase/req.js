import request from '@/utils/request'

// 查询采购申请列表
export function listErpPurchaseReq(query) {
  return request({
    url: '/purchase/req/list',
    method: 'get',
    params: query
  })
}

// 查询采购申请详细
export function getErpPurchaseReq(reqId) {
  return request({
    url: '/purchase/req/' + reqId,
    method: 'get'
  })
}

// 新增采购申请
export function addErpPurchaseReq(data) {
  return request({
    url: '/purchase/req',
    method: 'post',
    data: data
  })
}

// 修改采购申请
export function updateErpPurchaseReq(data) {
  return request({
    url: '/purchase/req',
    method: 'put',
    data: data
  })
}

// 删除采购申请
export function delErpPurchaseReq(reqId) {
  return request({
    url: '/purchase/req/' + reqId,
    method: 'delete'
  })
}
