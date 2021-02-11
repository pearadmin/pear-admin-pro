import http from '../http'

const Api = {
  create: '/captcha/create',
}

export const create = data => {
  return http.request({
    url: Api.create,
    method: 'get'
  })
}

