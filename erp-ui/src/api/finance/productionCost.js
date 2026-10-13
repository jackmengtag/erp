import request from '@/utils/request'

// 查询生产成本列表
export function listErpProductionCost(query) {
  return request({
    url: '/finance/productionCost/list',
    method: 'get',
    params: query
  })
}

// 查询生产成本详细
export function getErpProductionCost(costId) {
  return request({
    url: '/finance/productionCost/' + costId,
    method: 'get'
  })
}

// 新增生产成本
export function addErpProductionCost(data) {
  return request({
    url: '/finance/productionCost',
    method: 'post',
    data: data
  })
}

// 修改生产成本
export function updateErpProductionCost(data) {
  return request({
    url: '/finance/productionCost',
    method: 'put',
    data: data
  })
}

// 删除生产成本
export function delErpProductionCost(costId) {
  return request({
    url: '/finance/productionCost/' + costId,
    method: 'delete'
  })
}
