import http from '../http'

/** 接口 */
const Api = {
  page: '/api/sys/user/page',
  menu: '/api/sys/user/menu',
  power: '/api/sys/user/power',
  role: '/api/sys/user/role',
  login: '/api/login',
  logout: '/api/logout',
}

/** 登录 */
export const login = data => {
  return http.request({
    url: Api.login,
    params: data,
    method: 'POST'
  })
}

/** 注销 */
export const logout = data => {
  return http.request({
    url: Api.logout,
    data: data,
    method: 'GET'
  })
}

/** 用户菜单 */
export const menu = data => {
  return http.request({
    url: Api.menu,
    params: data,
    method: 'GET'
  })
}

/** 用户角色 */
export const role = data => {
  return http.request({
    url: Api.role,
    params: data,
    method: 'GET'
  })
}

/** 用户权限 */
export const power = data => {
  return http.request({
    url: Api.power,
    params: data,
    method: 'GET'
  })
}

/** 用户列表 */
export const page = data => {
  return http.request({
    url: Api.page,
    params: data,
    method: 'GET'
  })
}

/** 用户新增 */
export const add = data => {
  return http.request({
    url: Api.add,
    data: data,
    method: 'POST'
  })
}