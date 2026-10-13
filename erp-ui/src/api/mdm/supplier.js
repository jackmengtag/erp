import request from '@/utils/request'

// 查询供应商列表
export function listErpSupplier(query) {
  return request({
    url: '/mdm/supplier/list',
    method: 'get',
    params: query
  })
}

// 查询供应商详细
export function getErpSupplier(supplierId) {
  return request({
    url: '/mdm/supplier/' + supplierId,
    method: 'get'
  })
}

// 新增供应商
export function addErpSupplier(data) {
  return request({
    url: '/mdm/supplier',
    method: 'post',
    data: data
  })
}

// 修改供应商
export function updateErpSupplier(data) {
  return request({
    url: '/mdm/supplier',
    method: 'put',
    data: data
  })
}

// 删除供应商
export function delErpSupplier(supplierId) {
  return request({
    url: '/mdm/supplier/' + supplierId,
    method: 'delete'
  })
}
