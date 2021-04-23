import permissionRoutes from './module/main-routes'
import NProgress from "nprogress";
import store from "@/store";
import router from "@/route/index";

/**
 * 路由信息
 * @param menus
 * @returns {*}
 */
export const generateRoute = (menus) => {
  const userRoutes = menus.map(menu => {
    const {parent, icon, name, children = [], path, hidden = false, title, i18n, id} = menu
    // todo: 如果后端返回的父级菜单的路径为不带'/'号，则需要拼上 '/', 子菜单不需要，因为vue-router会自动跟据路径拼
    const currentMenu = {
      path, name, hidden, parent,
      meta: { key: id, title, i18n, icon },
      children: children.length === 0 ? [] : generateRoute(children)
    }
    if (children.length <= 0) {
      delete currentMenu.children
    }
    return currentMenu
  })
  return userRoutes
}

export const setUserRouteComponent = routes => {
  /// 路由组件匹配
  routes.forEach(r => {
    r.component = r.parent == '0' ? permissionRoutes.Layout : permissionRoutes[r.name]
    if (r.children && r.children.length > 0) {
      setUserRouteComponent(r.children)
    }
  })
}

/**
 * 用户权限路由中是否包含当前访问路由的路径
 * @param routes
 * @param path
 * @returns {boolean}
 */
export const inCloudRoute = (routes, path) => {
  let hasRoute = false
  const routeArr = path.split('/')
  const routePath = routeArr[routeArr.length - 1]
  for (let i = 0; i < routes.length; i++) {
    const { path, children = [] } = routes[i]
    if (path === routePath) {
      hasRoute = true
    } else if (children.length > 0) {
      hasRoute = inCloudRoute(children, routePath)
    }
    if (hasRoute) {
      break
    }
  }
  return hasRoute
}

const setDocumentTitle = title => {
  // 如有i18n在这里修改
  document.title = `pear-admin-ant-${title}`
}

/**
 * 权限控制
 * @param to
 * @param from
 * @param next
 * @returns {Promise<void>}
 */
export const permissionController = async (to, from, next) => {
  
  NProgress.start();

  const { meta: { title = '' } } = to
  
  setDocumentTitle(title)
  
  // 取消未完成的请求
  await store.dispatch('app/execCancelToken')

  // 是否登录
  if (!to.fullPath.includes('login') && !localStorage.getItem('token') && !localStorage.getItem('token_key')) {
    // 重定向 登录
    next({path: '/login'})
  } else {
    // 如果基本路由中不包含页面前往的路径
    if (!router.getRoutes().map(it => it.path).includes(to.fullPath)) {
    
      // 注册路由
      await store.dispatch('user/addRoute')

      // 路由判定
      const userRoutes = JSON.parse(JSON.stringify(store.getters.menu))
      const hasRoute = inCloudRoute(userRoutes, to.fullPath)

      if (hasRoute) {
        // 注册路由
        setUserRouteComponent(userRoutes)
        userRoutes.forEach(r => { router.addRoute(r) })
        // 目标跳转
        next(to.fullPath)
      } else {
        // 重定向 404
        next('/error/404')
      }
    } else {
      // 放行
      next()
    }
  }
}
