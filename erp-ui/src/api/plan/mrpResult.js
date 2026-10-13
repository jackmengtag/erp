import request from '@/utils/request'

// 查询MRP结果列表
export function listErpMrpResult(query) {
  return request({
    url: '/plan/mrpResult/list',
    method: 'get',
    params: query
  })
}

// 查询MRP结果详细
export function getErpMrpResult(resultId) {
  return request({
    url: '/plan/mrpResult/' + resultId,
    method: 'get'
  })
}

// 新增MRP结果
export function addErpMrpResult(data) {
  return request({
    url: '/plan/mrpResult',
    method: 'post',
    data: data
  })
}

// 修改MRP结果
export function updateErpMrpResult(data) {
  return request({
    url: '/plan/mrpResult',
    method: 'put',
    data: data
  })
}

// 删除MRP结果
export function delErpMrpResult(resultId) {
  return request({
    url: '/plan/mrpResult/' + resultId,
    method: 'delete'
  })
}
