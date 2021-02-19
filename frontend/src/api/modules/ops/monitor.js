import http from '../../http'

const Api = {
    server: '/ops/monitor/server',
}

export const server = data => {
    return http.request({
        url: Api.server,
        params: data,
        method: 'get'
    })
}