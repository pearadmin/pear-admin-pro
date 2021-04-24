import http from '../http'

/** 接口管理 */
const Api = {
    page: '/sys/role/page',
    list: '/sys/role/list'
}

/** 角色列表 (分页) */
export const page = data => {
    return http.request({
        url: Api.page,
        params: data,
        method: 'GET'
    })
}

/** 角色列表 */
export const list = data => {
    return http.request({
        url: Api.list,
        params: data,
        method: 'GET'
    })
}