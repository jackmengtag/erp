import request from '@/utils/request'

// 查询需求预测列表
export function listErpDemandForecast(query) {
  return request({
    url: '/plan/forecast/list',
    method: 'get',
    params: query
  })
}

// 查询需求预测详细
export function getErpDemandForecast(forecastId) {
  return request({
    url: '/plan/forecast/' + forecastId,
    method: 'get'
  })
}

// 新增需求预测
export function addErpDemandForecast(data) {
  return request({
    url: '/plan/forecast',
    method: 'post',
    data: data
  })
}

// 修改需求预测
export function updateErpDemandForecast(data) {
  return request({
    url: '/plan/forecast',
    method: 'put',
    data: data
  })
}

// 删除需求预测
export function delErpDemandForecast(forecastId) {
  return request({
    url: '/plan/forecast/' + forecastId,
    method: 'delete'
  })
}
