import http from '../http'

const Api = {
    info: '/api/sys/redis/info',
}

export const info = data => {
    return http.request({
        url: Api.info,
        params: data,
        method: 'get'
    })
}