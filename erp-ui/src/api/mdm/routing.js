import request from '@/utils/request'

// 查询工艺路线列表
export function listErpRouting(query) {
  return request({
    url: '/mdm/routing/list',
    method: 'get',
    params: query
  })
}

// 查询工艺路线详细
export function getErpRouting(routingId) {
  return request({
    url: '/mdm/routing/' + routingId,
    method: 'get'
  })
}

// 新增工艺路线
export function addErpRouting(data) {
  return request({
    url: '/mdm/routing',
    method: 'post',
    data: data
  })
}

// 修改工艺路线
export function updateErpRouting(data) {
  return request({
    url: '/mdm/routing',
    method: 'put',
    data: data
  })
}

// 删除工艺路线
export function delErpRouting(routingId) {
  return request({
    url: '/mdm/routing/' + routingId,
    method: 'delete'
  })
}
