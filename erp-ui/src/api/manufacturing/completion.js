import request from '@/utils/request'

// 查询完工入库列表
export function listErpProductionCompletion(query) {
  return request({
    url: '/manufacturing/completion/list',
    method: 'get',
    params: query
  })
}

// 查询完工入库详细
export function getErpProductionCompletion(completionId) {
  return request({
    url: '/manufacturing/completion/' + completionId,
    method: 'get'
  })
}

// 新增完工入库
export function addErpProductionCompletion(data) {
  return request({
    url: '/manufacturing/completion',
    method: 'post',
    data: data
  })
}

// 修改完工入库
export function updateErpProductionCompletion(data) {
  return request({
    url: '/manufacturing/completion',
    method: 'put',
    data: data
  })
}

// 删除完工入库
export function delErpProductionCompletion(completionId) {
  return request({
    url: '/manufacturing/completion/' + completionId,
    method: 'delete'
  })
}
