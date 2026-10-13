import request from '@/utils/request'

// 查询应收单列表
export function listErpReceivable(query) {
  return request({
    url: '/finance/receivable/list',
    method: 'get',
    params: query
  })
}

// 查询应收单详细
export function getErpReceivable(arId) {
  return request({
    url: '/finance/receivable/' + arId,
    method: 'get'
  })
}

// 新增应收单
export function addErpReceivable(data) {
  return request({
    url: '/finance/receivable',
    method: 'post',
    data: data
  })
}

// 修改应收单
export function updateErpReceivable(data) {
  return request({
    url: '/finance/receivable',
    method: 'put',
    data: data
  })
}

// 删除应收单
export function delErpReceivable(arId) {
  return request({
    url: '/finance/receivable/' + arId,
    method: 'delete'
  })
}
