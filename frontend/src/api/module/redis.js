import http from '../http'

const Api = {
    info: '/api/sys/redis/info',
    size: '/api/sys/redis/size',
}

/** 缓存详情 */
export const info = data => {
    return http.request({
        url: Api.info,
        params: data,
        method: 'GET'
    })
}

/** 缓存数量 */
export const size = data => {
    return http.request({
        url: Api.size,
        params: data,
        method: 'GET'
    })
}