import http from '../http'

const Api = {
    page: '/sys/power/page',
}

export const page = data => {
    return http.request({
        url: Api.page,
        params: data,
        method: 'get'
    })
}