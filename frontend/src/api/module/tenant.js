import request from '../request'

const Api = {
    page: '/api/sys/tenant/page',
    save: '/api/sys/tenant/save',
    edit: '/api/sys/tenant/edit',
    remove: '/api/sys/tenant/remove',
    removeBatch: '/api/sys/tenant/removeBatch'
}

/** 租户列表 */
export const page = data => {
    return request.request({
        url: Api.page,
        params: data,
        method: 'GET'
    })
}

/** 新增租户 */
export const save = data => {
    return request.request({
        url: Api.save,
        data: data,
        method: 'POST'
    })
}

/** 修改租户 */
export const edit = data => {
    return request.request({
        url: Api.edit,
        data: data,
        method: 'PUT'
    })
}

/** 删除租户 */
export const remove = data => {
    return request.request({
        url: Api.remove,
        params: data,
        method: 'DELETE'
    })
}

/** 批量租户 */
export const removeBatch = data => {
    return request.request({
        url: Api.removeBatch,
        params: data,
        method: 'DELETE'
    })
}