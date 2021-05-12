import http from '../http'

/** 接口管理 */
const Api = {
    page: '/api/sys/announce/page',
    save: '/api/sys/announce/save',
    edit: '/api/sys/announce/edit',
    remove: '/api/sys/announce/remove',
    removeBatch: '/api/sys/announce/removeBatch'
}

/** 公告列表 (分页) */
export const page = data => {
    return http.request({
        url: Api.page,
        params: data,
        method: 'GET'
    })
}

export const save = data => {
    return http.request({
        url: Api.save,
        data: data,
        method: 'POST'
    })
}

export const edit = data => {
    return http.request({
        url: Api.edit,
        data: data,
        method: 'PUT'
    })
}

export const remove = data => {
    return http.request({
        url: Api.remove,
        params: data,
        method: 'DELETE'
    })
}

export const removeBatch = data => {
    return http.request({
        url: Api.removeBatch,
        params: data,
        method: 'DELETE'
    })
}
