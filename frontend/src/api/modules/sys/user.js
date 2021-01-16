import http from '../../http'

const Api = {
  login: '/login',
  logout: '/logout',
  menuList: '/api/sys/user/menu',
  menuTree: '/api/sys/user/menu',
  list: '/api/sys/user/list',
  page: '/api/sys/user/page'
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

export const getUserMenusArray = data => {
  return http.request({
    url: Api.menuList,
    data: data,
    method: 'get'
  })
}

export const getUserMenusTree = data => {
  return http.request({
    url: Api.menuTree,
    data: data,
    method: 'get'
  })
}

export const list = data => {
  return http.request({
    url: Api.list,
    params: data,
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
