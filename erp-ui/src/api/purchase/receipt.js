import request from '@/utils/request'

// 查询收货单列表
export function listErpGoodsReceipt(query) {
  return request({
    url: '/purchase/receipt/list',
    method: 'get',
    params: query
  })
}

// 查询收货单详细
export function getErpGoodsReceipt(receiptId) {
  return request({
    url: '/purchase/receipt/' + receiptId,
    method: 'get'
  })
}

// 新增收货单
export function addErpGoodsReceipt(data) {
  return request({
    url: '/purchase/receipt',
    method: 'post',
    data: data
  })
}

// 修改收货单
export function updateErpGoodsReceipt(data) {
  return request({
    url: '/purchase/receipt',
    method: 'put',
    data: data
  })
}

// 删除收货单
export function delErpGoodsReceipt(receiptId) {
  return request({
    url: '/purchase/receipt/' + receiptId,
    method: 'delete'
  })
}
