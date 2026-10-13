import request from '@/utils/request'

// 查询价格条件列表
export function listErpPrice(query) {
  return request({
    url: '/mdm/price/list',
    method: 'get',
    params: query
  })
}

// 查询价格条件详细
export function getErpPrice(priceId) {
  return request({
    url: '/mdm/price/' + priceId,
    method: 'get'
  })
}

// 新增价格条件
export function addErpPrice(data) {
  return request({
    url: '/mdm/price',
    method: 'post',
    data: data
  })
}

// 修改价格条件
export function updateErpPrice(data) {
  return request({
    url: '/mdm/price',
    method: 'put',
    data: data
  })
}

// 删除价格条件
export function delErpPrice(priceId) {
  return request({
    url: '/mdm/price/' + priceId,
    method: 'delete'
  })
}
