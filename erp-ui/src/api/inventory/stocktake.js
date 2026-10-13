import request from '@/utils/request'

// 查询盘点单列表
export function listErpStocktake(query) {
  return request({
    url: '/inventory/stocktake/list',
    method: 'get',
    params: query
  })
}

// 查询盘点单详细
export function getErpStocktake(stocktakeId) {
  return request({
    url: '/inventory/stocktake/' + stocktakeId,
    method: 'get'
  })
}

// 新增盘点单
export function addErpStocktake(data) {
  return request({
    url: '/inventory/stocktake',
    method: 'post',
    data: data
  })
}

// 修改盘点单
export function updateErpStocktake(data) {
  return request({
    url: '/inventory/stocktake',
    method: 'put',
    data: data
  })
}

// 删除盘点单
export function delErpStocktake(stocktakeId) {
  return request({
    url: '/inventory/stocktake/' + stocktakeId,
    method: 'delete'
  })
}
