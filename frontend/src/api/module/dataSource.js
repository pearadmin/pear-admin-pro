import http from '../http'

const Api = {
    page: '/api/sys/dataSource/page',
    save: '/api/sys/dataSource/save',
    remove: '/api/sys/dataSource/remove',
}

/** 多库列表 */
export const page = data => {
    return http.request({
        url: Api.page,
        params: data,
        method: 'get'
    })
}

/** 新增多库 */
export const save = data => {
    return http.request({
        url: Api.save,
        data: data,
        method: 'POST'
    })
}

/** 删除角色 */
export const remove = data => {
    return http.request({
        url: Api.remove,
        params: data,
        method: 'DELETE'
    })
}