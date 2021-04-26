import http from '../http'

const Api = {
    tree: '/api/sys/dept/tree',
}

export const tree = data => {
    return http.request({
        url: Api.tree,
        params: data,
        method: 'get'
    })
}