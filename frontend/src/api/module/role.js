import http from '../http'

/** 接口管理 */
const Api = {
    page: '/api/sys/role/page',
    list: '/api/sys/role/list',
    save: '/api/sys/role/save',
    edit: '/api/sys/role/edit',
    remove: '/api/sys/role/remove',
    power: '/api/sys/role/power',
}

/** 角色列表 (分页) */
export const page = data => {
    return http.request({
        url: Api.page,
        params: data,
        method: 'GET'
    })
}

/** 角色列表 */
export const list = data => {
    return http.request({
        url: Api.list,
        params: data,
        method: 'GET'
    })
}

/** 新增角色 */
export const save = data => {
    return http.request({
        url: Api.save,
        data: data,
        method: 'POST'
    })
}

/** 修改角色 */
export const edit = data => {
    return http.request({
        url: Api.edit,
        data: data,
        method: 'PUT'
    })
}

/** 删除角色 */
export const remove = data => {
    return http.request({
        url: Api.remove,
        params: data,
        method: 'DELETE'
    })
}

/** 角色权限 */
export const power = data => {
    return http.request({
        url: Api.power,
        params: data,
        method: 'GET'
    })
}