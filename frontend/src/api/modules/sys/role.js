import http from '../../http'

const Api = {
  list: '/api/sys/role/list',
  page: '/api/sys/role/page'
}

export const list = data => {
  return http.request({
    url: Api.list,
    data: data,
    method: 'get'
  })
}

export const page = data => {
  return http.request({
    url: Api.page,
    data: data,
    method: 'get'
  })
}
