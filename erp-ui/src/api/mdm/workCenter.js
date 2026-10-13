import request from '@/utils/request'

// 查询工作中心列表
export function listErpWorkCenter(query) {
  return request({
    url: '/mdm/workCenter/list',
    method: 'get',
    params: query
  })
}

// 查询工作中心详细
export function getErpWorkCenter(wcId) {
  return request({
    url: '/mdm/workCenter/' + wcId,
    method: 'get'
  })
}

// 新增工作中心
export function addErpWorkCenter(data) {
  return request({
    url: '/mdm/workCenter',
    method: 'post',
    data: data
  })
}

// 修改工作中心
export function updateErpWorkCenter(data) {
  return request({
    url: '/mdm/workCenter',
    method: 'put',
    data: data
  })
}

// 删除工作中心
export function delErpWorkCenter(wcId) {
  return request({
    url: '/mdm/workCenter/' + wcId,
    method: 'delete'
  })
}
