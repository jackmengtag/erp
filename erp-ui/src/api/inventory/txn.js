import request from '@/utils/request'

// 查询库存流水列表
export function listErpInventoryTxn(query) {
  return request({
    url: '/inventory/txn/list',
    method: 'get',
    params: query
  })
}

// 查询库存流水详细
export function getErpInventoryTxn(txnId) {
  return request({
    url: '/inventory/txn/' + txnId,
    method: 'get'
  })
}

// 新增库存流水
export function addErpInventoryTxn(data) {
  return request({
    url: '/inventory/txn',
    method: 'post',
    data: data
  })
}

// 修改库存流水
export function updateErpInventoryTxn(data) {
  return request({
    url: '/inventory/txn',
    method: 'put',
    data: data
  })
}

// 删除库存流水
export function delErpInventoryTxn(txnId) {
  return request({
    url: '/inventory/txn/' + txnId,
    method: 'delete'
  })
}
