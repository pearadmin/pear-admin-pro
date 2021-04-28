import http from '../http'

/** 接口 */
const Api = {
  add: '/api/sys/user/add',
  page: '/api/sys/user/page',
  menu: '/api/sys/user/menu',
  power: '/api/sys/user/power',
  remove: '/api/sys/user/remove',
  removeBatch: '/apu/sys/user/removeBatch',
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

/** 用户删除 */
export const remove = data => {
  return http.request({
    url: Api.remove,
    params: data,
    method: 'DELETE'
  })
}

/** 批量删除 */
export const removeBatch = data => {
  return http.request({
    url: Api.removeBatch,
    params: data,
    method: 'DELETE'
  })
}