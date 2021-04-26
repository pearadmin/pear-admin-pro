import http from '../http'

const Api = {
    list: '/api/sys/online/list',
}

export const list = data => {
    return http.request({
        url: Api.list,
        params: data,
        method: 'get'
    })
}