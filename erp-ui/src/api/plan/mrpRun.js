import request from '@/utils/request'

// 查询MRP运算列表
export function listErpMrpRun(query) {
  return request({
    url: '/plan/mrpRun/list',
    method: 'get',
    params: query
  })
}

// 查询MRP运算详细
export function getErpMrpRun(runId) {
  return request({
    url: '/plan/mrpRun/' + runId,
    method: 'get'
  })
}

// 新增MRP运算
export function addErpMrpRun(data) {
  return request({
    url: '/plan/mrpRun',
    method: 'post',
    data: data
  })
}

// 修改MRP运算
export function updateErpMrpRun(data) {
  return request({
    url: '/plan/mrpRun',
    method: 'put',
    data: data
  })
}

// 删除MRP运算
export function delErpMrpRun(runId) {
  return request({
    url: '/plan/mrpRun/' + runId,
    method: 'delete'
  })
}
