import permissionRoutes from './module/main-routes'
import NProgress from "nprogress";
import store from "@/store";
import router from "@/route/index";
import { inCloudRoute } from "@/tools/router";

/** 路由信息 */
export const generateRoute = (menus) => {
  const userRoutes = menus.map(menu => {
    const { parent, icon, component, children = [], path, hidden = false, title, i18n, id } = menu
    const currentMenu = { path, component, hidden, parent, meta: { key: id, title, i18n, icon }, children: children.length === 0 ? [] : generateRoute(children) }
    if (children.length <= 0) {
      delete currentMenu.children
    }
    return currentMenu
  })
  return userRoutes
}

/** 权限信息 */
export const generatePower = (powers) => {
  const userPowers = powers.map(power => {
     return power.code
  })
  return userPowers;
}


export const setUserRouteComponent = routes => {
  routes.forEach(r => {
    r.component = r.parent == '0' ? permissionRoutes.Layout : permissionRoutes[r.component]
    if (r.children && r.children.length > 0) {
      setUserRouteComponent(r.children)
    }
  })
}

const setDocumentTitle = title => {
  document.title = `PRO - ${title}`
}

/**
 * 前置拦截器
 * 
 * @param to
 * @param from
 * @param next
 * @returns {Promise<void>}
 */
export const permissionController = async (to, from, next) => {
  
  NProgress.start();
  const { meta: { title = '' } } = to
  setDocumentTitle(title)
  await store.dispatch('app/execCancelToken')

  if (!to.fullPath.includes('login') && !localStorage.getItem('token') && !localStorage.getItem('token_key')) {
    next({path: '/login'})
  } else {

    if (!router.getRoutes().map(it => it.path).includes(to.fullPath)) {
    
      await store.dispatch('user/addRoute')
      await store.dispatch('user/addPower')

      const userRoutes = JSON.parse(JSON.stringify(store.getters.menu))
      const hasRoute = inCloudRoute(userRoutes, to.fullPath)

      if (hasRoute) {
        setUserRouteComponent(userRoutes)
        userRoutes.forEach(r => { router.addRoute(r) })
        next(to.fullPath)
      } else {
        next('/error/404')
      }
    } else {
      next()
    }
  }
}
