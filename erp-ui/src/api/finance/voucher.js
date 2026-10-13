import request from '@/utils/request'

// 查询凭证接口列表
export function listErpVoucher(query) {
  return request({
    url: '/finance/voucher/list',
    method: 'get',
    params: query
  })
}

// 查询凭证接口详细
export function getErpVoucher(voucherId) {
  return request({
    url: '/finance/voucher/' + voucherId,
    method: 'get'
  })
}

// 新增凭证接口
export function addErpVoucher(data) {
  return request({
    url: '/finance/voucher',
    method: 'post',
    data: data
  })
}

// 修改凭证接口
export function updateErpVoucher(data) {
  return request({
    url: '/finance/voucher',
    method: 'put',
    data: data
  })
}

// 删除凭证接口
export function delErpVoucher(voucherId) {
  return request({
    url: '/finance/voucher/' + voucherId,
    method: 'delete'
  })
}
