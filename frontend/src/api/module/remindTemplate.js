import http from '../http'

/** 接口管理 */
const Api = {
    page: '/api/sys/remind/template/page',
    save: '/api/sys/remind/template/save',
    edit: '/api/sys/remind/template/edit',
    remove: '/api/sys/remind/template/remove',
    removeBatch: '/api/sys/remind/template/removeBatch'
}

/** 提醒列表 (分页) */
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