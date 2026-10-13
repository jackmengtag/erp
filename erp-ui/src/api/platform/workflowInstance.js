import request from '@/utils/request'

// 查询工作流实例列表
export function listErpWorkflowInstance(query) {
  return request({
    url: '/platform/workflowInstance/list',
    method: 'get',
    params: query
  })
}

// 查询工作流实例详细
export function getErpWorkflowInstance(instanceId) {
  return request({
    url: '/platform/workflowInstance/' + instanceId,
    method: 'get'
  })
}

// 新增工作流实例
export function addErpWorkflowInstance(data) {
  return request({
    url: '/platform/workflowInstance',
    method: 'post',
    data: data
  })
}

// 修改工作流实例
export function updateErpWorkflowInstance(data) {
  return request({
    url: '/platform/workflowInstance',
    method: 'put',
    data: data
  })
}

// 删除工作流实例
export function delErpWorkflowInstance(instanceId) {
  return request({
    url: '/platform/workflowInstance/' + instanceId,
    method: 'delete'
  })
}
