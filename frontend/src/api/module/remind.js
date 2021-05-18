import http from '../http'

/** 接口管理 */
const Api = {
    page: '/api/sys/remind/page',
}

/** 提醒列表 (分页) */
export const page = data => {
    return http.request({
        url: Api.page,
        params: data,
        method: 'GET'
    })
}