import request from '@/utils/request'

// 查询核销记录列表
export function listErpSettlement(query) {
  return request({
    url: '/finance/settlement/list',
    method: 'get',
    params: query
  })
}

// 查询核销记录详细
export function getErpSettlement(settleId) {
  return request({
    url: '/finance/settlement/' + settleId,
    method: 'get'
  })
}

// 新增核销记录
export function addErpSettlement(data) {
  return request({
    url: '/finance/settlement',
    method: 'post',
    data: data
  })
}

// 修改核销记录
export function updateErpSettlement(data) {
  return request({
    url: '/finance/settlement',
    method: 'put',
    data: data
  })
}

// 删除核销记录
export function delErpSettlement(settleId) {
  return request({
    url: '/finance/settlement/' + settleId,
    method: 'delete'
  })
}
