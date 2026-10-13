import request from '@/utils/request'

// 查询工序报工列表
export function listErpProductionReport(query) {
  return request({
    url: '/manufacturing/report/list',
    method: 'get',
    params: query
  })
}

// 查询工序报工详细
export function getErpProductionReport(reportId) {
  return request({
    url: '/manufacturing/report/' + reportId,
    method: 'get'
  })
}

// 新增工序报工
export function addErpProductionReport(data) {
  return request({
    url: '/manufacturing/report',
    method: 'post',
    data: data
  })
}

// 修改工序报工
export function updateErpProductionReport(data) {
  return request({
    url: '/manufacturing/report',
    method: 'put',
    data: data
  })
}

// 删除工序报工
export function delErpProductionReport(reportId) {
  return request({
    url: '/manufacturing/report/' + reportId,
    method: 'delete'
  })
}
