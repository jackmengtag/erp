import request from '@/utils/request'

// 查询工单用料列表
export function listErpProductionMaterial(query) {
  return request({
    url: '/manufacturing/material/list',
    method: 'get',
    params: query
  })
}

// 查询工单用料详细
export function getErpProductionMaterial(materialReqId) {
  return request({
    url: '/manufacturing/material/' + materialReqId,
    method: 'get'
  })
}

// 新增工单用料
export function addErpProductionMaterial(data) {
  return request({
    url: '/manufacturing/material',
    method: 'post',
    data: data
  })
}

// 修改工单用料
export function updateErpProductionMaterial(data) {
  return request({
    url: '/manufacturing/material',
    method: 'put',
    data: data
  })
}

// 删除工单用料
export function delErpProductionMaterial(materialReqId) {
  return request({
    url: '/manufacturing/material/' + materialReqId,
    method: 'delete'
  })
}
