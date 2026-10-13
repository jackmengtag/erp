import request from '@/utils/request'

// 查询编码流水列表
export function listErpCodeSerial(query) {
  return request({
    url: '/platform/codeSerial/list',
    method: 'get',
    params: query
  })
}

// 查询编码流水详细
export function getErpCodeSerial(serialId) {
  return request({
    url: '/platform/codeSerial/' + serialId,
    method: 'get'
  })
}

// 新增编码流水
export function addErpCodeSerial(data) {
  return request({
    url: '/platform/codeSerial',
    method: 'post',
    data: data
  })
}

// 修改编码流水
export function updateErpCodeSerial(data) {
  return request({
    url: '/platform/codeSerial',
    method: 'put',
    data: data
  })
}

// 删除编码流水
export function delErpCodeSerial(serialId) {
  return request({
    url: '/platform/codeSerial/' + serialId,
    method: 'delete'
  })
}
