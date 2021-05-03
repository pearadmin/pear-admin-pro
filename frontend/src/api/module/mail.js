import http from '../http'

const Api = {
    send: '/api/sys/mail/send'
}

export const send = data => {
    return http.request({
        url: Api.send,
        data: data,
        method: 'POST'
    })
}