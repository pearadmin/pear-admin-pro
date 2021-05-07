import http from '../http'

const Api = {
    page: '/api/sys/file/page',
    upload: '/api/sys/file/upload',
}

/** 文件列表 */
export const page = data => {
    return http.request({
        url: Api.page,
        params: data,
        method: 'get'
    })
}

/** 文件上传 */
export const upload = data => {
    
}