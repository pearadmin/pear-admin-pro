import http from '../http'

const Api = {
    tree: '/api/sys/dept/tree',
    save: '/api/sys/dept/save',
}

export const tree = data => {
    return http.request({
        url: Api.tree,
        params: data,
        method: 'get'
    })
}

export const save = data => { 
    return http.request({
        url: Api.save,
        data: data,
        method: 'POST'
    })
}