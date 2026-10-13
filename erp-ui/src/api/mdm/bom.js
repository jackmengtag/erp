import request from '@/utils/request'

// 查询BOM列表
export function listErpBom(query) {
  return request({
    url: '/mdm/bom/list',
    method: 'get',
    params: query
  })
}

// 查询BOM详细
export function getErpBom(bomId) {
  return request({
    url: '/mdm/bom/' + bomId,
    method: 'get'
  })
}

// 新增BOM
export function addErpBom(data) {
  return request({
    url: '/mdm/bom',
    method: 'post',
    data: data
  })
}

// 修改BOM
export function updateErpBom(data) {
  return request({
    url: '/mdm/bom',
    method: 'put',
    data: data
  })
}

// 删除BOM
export function delErpBom(bomId) {
  return request({
    url: '/mdm/bom/' + bomId,
    method: 'delete'
  })
}
