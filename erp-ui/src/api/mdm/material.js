import request from '@/utils/request'

// 查询物料主数据列表
export function listErpMaterial(query) {
  return request({
    url: '/mdm/material/list',
    method: 'get',
    params: query
  })
}

// 查询物料主数据详细
export function getErpMaterial(materialId) {
  return request({
    url: '/mdm/material/' + materialId,
    method: 'get'
  })
}

// 新增物料主数据
export function addErpMaterial(data) {
  return request({
    url: '/mdm/material',
    method: 'post',
    data: data
  })
}

// 修改物料主数据
export function updateErpMaterial(data) {
  return request({
    url: '/mdm/material',
    method: 'put',
    data: data
  })
}

// 删除物料主数据
export function delErpMaterial(materialId) {
  return request({
    url: '/mdm/material/' + materialId,
    method: 'delete'
  })
}
