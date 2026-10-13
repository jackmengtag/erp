import request from '@/utils/request'

// 查询收款单列表
export function listErpReceipt(query) {
  return request({
    url: '/finance/receipt/list',
    method: 'get',
    params: query
  })
}

// 查询收款单详细
export function getErpReceipt(receiptId) {
  return request({
    url: '/finance/receipt/' + receiptId,
    method: 'get'
  })
}

// 新增收款单
export function addErpReceipt(data) {
  return request({
    url: '/finance/receipt',
    method: 'post',
    data: data
  })
}

// 修改收款单
export function updateErpReceipt(data) {
  return request({
    url: '/finance/receipt',
    method: 'put',
    data: data
  })
}

// 删除收款单
export function delErpReceipt(receiptId) {
  return request({
    url: '/finance/receipt/' + receiptId,
    method: 'delete'
  })
}
