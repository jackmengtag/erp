import request from '@/utils/request'

// 查询生产异常列表
export function listErpProductionException(query) {
  return request({
    url: '/manufacturing/exception/list',
    method: 'get',
    params: query
  })
}

// 查询生产异常详细
export function getErpProductionException(exceptionId) {
  return request({
    url: '/manufacturing/exception/' + exceptionId,
    method: 'get'
  })
}

// 新增生产异常
export function addErpProductionException(data) {
  return request({
    url: '/manufacturing/exception',
    method: 'post',
    data: data
  })
}

// 修改生产异常
export function updateErpProductionException(data) {
  return request({
    url: '/manufacturing/exception',
    method: 'put',
    data: data
  })
}

// 删除生产异常
export function delErpProductionException(exceptionId) {
  return request({
    url: '/manufacturing/exception/' + exceptionId,
    method: 'delete'
  })
}
