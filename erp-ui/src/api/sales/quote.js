import request from '@/utils/request'

// 查询销售报价单列表
export function listErpSalesQuote(query) {
  return request({
    url: '/sales/quote/list',
    method: 'get',
    params: query
  })
}

// 查询销售报价单详细
export function getErpSalesQuote(quoteId) {
  return request({
    url: '/sales/quote/' + quoteId,
    method: 'get'
  })
}

// 新增销售报价单
export function addErpSalesQuote(data) {
  return request({
    url: '/sales/quote',
    method: 'post',
    data: data
  })
}

// 修改销售报价单
export function updateErpSalesQuote(data) {
  return request({
    url: '/sales/quote',
    method: 'put',
    data: data
  })
}

// 删除销售报价单
export function delErpSalesQuote(quoteId) {
  return request({
    url: '/sales/quote/' + quoteId,
    method: 'delete'
  })
}
