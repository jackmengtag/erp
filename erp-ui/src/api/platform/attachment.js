import request from '@/utils/request'

// 查询附件列表
export function listErpAttachment(query) {
  return request({
    url: '/platform/attachment/list',
    method: 'get',
    params: query
  })
}

// 查询附件详细
export function getErpAttachment(attachmentId) {
  return request({
    url: '/platform/attachment/' + attachmentId,
    method: 'get'
  })
}

// 新增附件
export function addErpAttachment(data) {
  return request({
    url: '/platform/attachment',
    method: 'post',
    data: data
  })
}

// 修改附件
export function updateErpAttachment(data) {
  return request({
    url: '/platform/attachment',
    method: 'put',
    data: data
  })
}

// 删除附件
export function delErpAttachment(attachmentId) {
  return request({
    url: '/platform/attachment/' + attachmentId,
    method: 'delete'
  })
}
