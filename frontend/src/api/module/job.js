import http from '../http'

const Api = {
    page: '/api/sys/job/page',
    remove: '/api/sys/job/remove',
    removeBatch: '/api/sys/job/removeBatch',
    save: '/api/sys/job/save',
    edit: '/api/sys/job/edit',
}

/** 岗位列表 */
export const page = data => {
    return http.request({
        url: Api.page,
        params: data,
        method: 'GET'
    })
}


/** 新增岗位 */
export const save = data => {
    return http.request({
        url: Api.save,
        data: data,
        method: 'POST'
    })
}

/** 修改岗位 */
export const edit = data => {
    return http.request({
        url: Api.edit,
        data: data,
        method: 'PUT'
    })
}

/** 删除岗位 */
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
