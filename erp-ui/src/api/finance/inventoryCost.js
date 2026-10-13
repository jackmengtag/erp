import request from '@/utils/request'

// 查询存货核算列表
export function listErpInventoryCost(query) {
  return request({
    url: '/finance/inventoryCost/list',
    method: 'get',
    params: query
  })
}

// 查询存货核算详细
export function getErpInventoryCost(costId) {
  return request({
    url: '/finance/inventoryCost/' + costId,
    method: 'get'
  })
}

// 新增存货核算
export function addErpInventoryCost(data) {
  return request({
    url: '/finance/inventoryCost',
    method: 'post',
    data: data
  })
}

// 修改存货核算
export function updateErpInventoryCost(data) {
  return request({
    url: '/finance/inventoryCost',
    method: 'put',
    data: data
  })
}

// 删除存货核算
export function delErpInventoryCost(costId) {
  return request({
    url: '/finance/inventoryCost/' + costId,
    method: 'delete'
  })
}
