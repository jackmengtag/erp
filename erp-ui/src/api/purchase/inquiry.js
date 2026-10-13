import request from '@/utils/request'

// 查询采购询价列表
export function listErpPurchaseInquiry(query) {
  return request({
    url: '/purchase/inquiry/list',
    method: 'get',
    params: query
  })
}

// 查询采购询价详细
export function getErpPurchaseInquiry(inquiryId) {
  return request({
    url: '/purchase/inquiry/' + inquiryId,
    method: 'get'
  })
}

// 新增采购询价
export function addErpPurchaseInquiry(data) {
  return request({
    url: '/purchase/inquiry',
    method: 'post',
    data: data
  })
}

// 修改采购询价
export function updateErpPurchaseInquiry(data) {
  return request({
    url: '/purchase/inquiry',
    method: 'put',
    data: data
  })
}

// 删除采购询价
export function delErpPurchaseInquiry(inquiryId) {
  return request({
    url: '/purchase/inquiry/' + inquiryId,
    method: 'delete'
  })
}
