import http from '../http'

const Api = {
  page: '/sys/user/page',
  menu: '/sys/user/menu',
  power: '/sys/user/power',
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

export const power = data => {
  return http.request({
    url: Api.power,
    data: data,
    method: 'get'
  })
}


export const page = data => {
  return http.request({
    url: Api.page,
    params: data,
    method: 'get'
  })
}