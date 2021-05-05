import http from '../http'

const Api = {
    page: '/api/sys/dict/data/page',
    save: '/api/sys/dict/data/save',
    edit: '/api/sys/dict/data/edit',
    remove: '/api/sys/dict/data/remove',
    removeBatch: '/api/sys/dict/data/removeBatch',
}

export const page = data => {
    return http.request({
        url: Api.page,
        params: data,
        method: 'get'
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