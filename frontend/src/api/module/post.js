import http from '../http'

const Api = {
    page: '/api/sys/post/page',
    list: '/api/sys/post/list',
    remove: '/api/sys/post/remove',
    add: '/api/sys/post/add'
}

/** 岗位列表 */
export const page = data => {
    return http.request({
        url: Api.page,
        params: data,
        method: 'GET'
    })
}

/** 岗位列表 */
export const list = data => {
    return http.request({
        url: Api.list,
        params: data,
        method: 'GET'
    })
}

/** 新增岗位 */
export const add = data => {
    return http.request({
        url: Api.add,
        data: data,
        method: 'POST'
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
