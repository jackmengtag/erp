import request from '@/utils/request'

// 查询工作流定义列表
export function listErpWorkflowDef(query) {
  return request({
    url: '/platform/workflowDef/list',
    method: 'get',
    params: query
  })
}

// 查询工作流定义详细
export function getErpWorkflowDef(wfId) {
  return request({
    url: '/platform/workflowDef/' + wfId,
    method: 'get'
  })
}

// 新增工作流定义
export function addErpWorkflowDef(data) {
  return request({
    url: '/platform/workflowDef',
    method: 'post',
    data: data
  })
}

// 修改工作流定义
export function updateErpWorkflowDef(data) {
  return request({
    url: '/platform/workflowDef',
    method: 'put',
    data: data
  })
}

// 删除工作流定义
export function delErpWorkflowDef(wfId) {
  return request({
    url: '/platform/workflowDef/' + wfId,
    method: 'delete'
  })
}
