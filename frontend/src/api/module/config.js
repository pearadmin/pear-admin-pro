import http from '../http'

const Api = {
    page: '/api/sys/config/page',
    add: '/api/sys/config/add',
    edit: '/api/sys/config/edit',
    remove: '/api/sys/config/remove'
}

/** 配置列表 */
export const page = data => {
    return http.request({
        url: Api.page,
        params: data,
        method: 'GET'
    })
}

/** 新增配置 */
export const add = data => {
    return http.request({
        url: Api.add,
        data: data,
        method: 'POST'
    })
}

/** 修改配置 */
export const edit = data => {
    return http.request({
        url: Api.edit,
        data: data,
        method: 'PUT'
    })
}

/** 删除配置 */
export const remove = data => {
    return http.request({
        url: Api.remove,
        params: data,
        method: 'DELETE'
    })
}