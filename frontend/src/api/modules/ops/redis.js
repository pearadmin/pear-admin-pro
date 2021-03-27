import http from '../../http'

const Api = {
    info: '/sys/redis/info',
    log: '/sys/redis/log',
}

export const info = data => {
    return http.request({
        url: Api.info,
        params: data,
        method: 'get'
    })
}

export const log = data => {
    return http.request({
        url: Api.log,
        params: data,
        method: 'get'
    })
}