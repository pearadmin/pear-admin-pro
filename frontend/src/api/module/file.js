import http from '../http'

const Api = {
    page: '/api/sys/file/page',
}

/** 文件列表 */
export const page = data => {
    return http.request({
        url: Api.page,
        params: data,
        method: 'get'
    })
}