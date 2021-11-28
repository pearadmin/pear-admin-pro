import request from '../request'

/** 接口管理 */
const Api = {
    page: '/api/gen/page',
    list: '/api/gen/list',
    DBPage: '/api/gen/db/page'
}

/** 配置列表 (分页) */
export const page = data => {
    return request.request({
        url: Api.page,
        params: data,
        method: 'GET'
    })
}

export const DBPage = data => {
    return request.request({
        url: Api.DBPage,
        params: data,
        method: 'GET'
    })
}

/** 配置列表 */
export const list = data => {
    return request.request({
        url: Api.list,
        params: data,
        method: 'GET'
    })
}
