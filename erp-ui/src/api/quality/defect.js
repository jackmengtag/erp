import request from '@/utils/request'

// 查询不合格品处理列表
export function listErpDefectDisposal(query) {
  return request({
    url: '/quality/defect/list',
    method: 'get',
    params: query
  })
}

// 查询不合格品处理详细
export function getErpDefectDisposal(disposalId) {
  return request({
    url: '/quality/defect/' + disposalId,
    method: 'get'
  })
}

// 新增不合格品处理
export function addErpDefectDisposal(data) {
  return request({
    url: '/quality/defect',
    method: 'post',
    data: data
  })
}

// 修改不合格品处理
export function updateErpDefectDisposal(data) {
  return request({
    url: '/quality/defect',
    method: 'put',
    data: data
  })
}

// 删除不合格品处理
export function delErpDefectDisposal(disposalId) {
  return request({
    url: '/quality/defect/' + disposalId,
    method: 'delete'
  })
}
