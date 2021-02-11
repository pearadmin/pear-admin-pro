import http from '../http'

const Api = {
  create: '/captcha/create',
  verify: '/captcha/verify'
}

// 创建 验证码
export const create = () =>{
  return http.request({
    url: Api.create,
    method: 'get'
  })
}

// 异步验证
export const verify = () =>{
  return http.request({
    url: Api.verify,
    method: 'post'
  })
}
  


