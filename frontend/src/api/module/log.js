import request from '../request'

const Api = {
    page: '/api/sys/log/page',
    clean: '/api/sys/log/clean',
}

/** 查询日志 */
export const page = data => {
    return request.request({
        url: Api.page,
        params: data,
        method: 'GET'
    })
}

/** 清空日志 */
export const clean = data => {
    return request.request({
        url: Api.clean,
        params: data,
        method: 'DELETE'
    })
}