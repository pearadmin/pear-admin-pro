import http from '../../http'

const Api = {
  createCaptcha: '/api/captcha/createCaptcha',
}

export const createCaptcha = data => {
    return http.request({
      url: Api.createCaptcha,
      data: data,
      method: 'get'
    })
}