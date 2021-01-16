import http from '../http'

const Api = {
  list: '/api/ops/token/list'
}

export const list = data => {
  return http.request({
    url: Api.list,
    data: data,
    method: 'get'
  })
}
