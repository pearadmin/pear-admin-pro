import http from '../http'

const Api = {
    operate: '/sys/log/operate',
    login: '/sys/log/login'
}

export const operate = data => {
    return http.request({
        url: Api.operate,
        params: data,
        method: 'get'
    })
}

export const login = data => {
    return http.request({
        url: Api.login,
        params: data,
        method: 'get'
    })
}