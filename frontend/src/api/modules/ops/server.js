import http from '../../http'

const Api = {
    info: '/ops/server/info',
}

export const info = data => {
    return http.request({
        url: Api.info,
        params: data,
        method: 'get'
    })
}