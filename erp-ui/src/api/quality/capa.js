import request from '@/utils/request'

// 查询CAPA与客诉列表
export function listErpCapa(query) {
  return request({
    url: '/quality/capa/list',
    method: 'get',
    params: query
  })
}

// 查询CAPA与客诉详细
export function getErpCapa(capaId) {
  return request({
    url: '/quality/capa/' + capaId,
    method: 'get'
  })
}

// 新增CAPA与客诉
export function addErpCapa(data) {
  return request({
    url: '/quality/capa',
    method: 'post',
    data: data
  })
}

// 修改CAPA与客诉
export function updateErpCapa(data) {
  return request({
    url: '/quality/capa',
    method: 'put',
    data: data
  })
}

// 删除CAPA与客诉
export function delErpCapa(capaId) {
  return request({
    url: '/quality/capa/' + capaId,
    method: 'delete'
  })
}
