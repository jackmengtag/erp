import request from '@/utils/request'

// 查询物料分类列表
export function listErpMaterialCategory(query) {
  return request({
    url: '/mdm/category/list',
    method: 'get',
    params: query
  })
}

// 查询物料分类详细
export function getErpMaterialCategory(categoryId) {
  return request({
    url: '/mdm/category/' + categoryId,
    method: 'get'
  })
}

// 新增物料分类
export function addErpMaterialCategory(data) {
  return request({
    url: '/mdm/category',
    method: 'post',
    data: data
  })
}

// 修改物料分类
export function updateErpMaterialCategory(data) {
  return request({
    url: '/mdm/category',
    method: 'put',
    data: data
  })
}

// 删除物料分类
export function delErpMaterialCategory(categoryId) {
  return request({
    url: '/mdm/category/' + categoryId,
    method: 'delete'
  })
}
