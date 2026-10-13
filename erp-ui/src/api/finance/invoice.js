import request from '@/utils/request'

// 查询发票列表
export function listErpInvoice(query) {
  return request({
    url: '/finance/invoice/list',
    method: 'get',
    params: query
  })
}

// 查询发票详细
export function getErpInvoice(invoiceId) {
  return request({
    url: '/finance/invoice/' + invoiceId,
    method: 'get'
  })
}

// 新增发票
export function addErpInvoice(data) {
  return request({
    url: '/finance/invoice',
    method: 'post',
    data: data
  })
}

// 修改发票
export function updateErpInvoice(data) {
  return request({
    url: '/finance/invoice',
    method: 'put',
    data: data
  })
}

// 删除发票
export function delErpInvoice(invoiceId) {
  return request({
    url: '/finance/invoice/' + invoiceId,
    method: 'delete'
  })
}
