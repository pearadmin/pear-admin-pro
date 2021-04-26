import http from '../http'

// 接口
const Api = {
  create: '/api/captcha/create',
  verify: '/api/captcha/verify'
}

// 创建
export const create = () =>{
  return http.request({
    url: Api.create,
    method: 'get'
  })
}

// 验证
export const verify = () =>{
  return http.request({
    url: Api.verify,
    method: 'post'
  })
}
  


