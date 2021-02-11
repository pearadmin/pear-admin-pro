import http from '../http'

const Api = {
  menu: '/sys/user/menu',
  login: '/login',
  logout: '/logout'
}

export const login = data => {
  return http.request({
    url: Api.login,
    params: data,
    method: 'post'
  })
}

export const logout = data => {
  return http.request({
    url: Api.logout,
    data: data,
    method: 'post'
  })
}

export const menu = data => {
  return http.request({
    url: Api.menu,
    data: data,
    method: 'get'
  })
}