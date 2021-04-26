import http from '../http'

const Api = {
    page: '/api/sys/post/page',
    list: '/api/sys/post/list',
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

