import request from '@/utils/request'

// 查询计量单位列表
export function listErpUom(query) {
  return request({
    url: '/mdm/uom/list',
    method: 'get',
    params: query
  })
}

// 查询计量单位详细
export function getErpUom(uomId) {
  return request({
    url: '/mdm/uom/' + uomId,
    method: 'get'
  })
}

// 新增计量单位
export function addErpUom(data) {
  return request({
    url: '/mdm/uom',
    method: 'post',
    data: data
  })
}

// 修改计量单位
export function updateErpUom(data) {
  return request({
    url: '/mdm/uom',
    method: 'put',
    data: data
  })
}

// 删除计量单位
export function delErpUom(uomId) {
  return request({
    url: '/mdm/uom/' + uomId,
    method: 'delete'
  })
}
