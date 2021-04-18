import permissionRoutes from './module/main-routes'
import NProgress from "nprogress";
import store from "@/store";
import router from "@/route/index";

/**
 * 列表结构 转换 树状结构
 * @param list
 * @returns {[]}
 */
export const listToTree = list => {
  list.sort((a, b) => { return a.sort - b.sort })
  let node
  const map = {}
  const tree = []
  for (let i = 0; i < list.length; i++) {
    map[list[i].name] = i;
  }
  for (let i = 0; i < list.length; i++) {
    node = list[i];
    if (node.parent) {
      const children = list[map[node.parent]].children || []
      list[map[node.parent]].children = [...children, node]
    } else {
      tree.push(node)
    }
  }
  return tree
}

/**
 * 菜单树转换路由树
 * @param menus
 * @returns {*}
 */
export const generateUserMenuForTree = (menus) => {
  const userRoutes = menus.map(menu => {
    const {parent, icon, name, children = [], path, hidden = false, title, i18n} = menu
    // key是唯一的，防止重复，所以拼上父级菜单
    const key = parent ? `${parent}-${name}` : `${name}`
    // todo: 如果后端返回的父级菜单的路径为不带'/'号，则需要拼上 '/', 子菜单不需要，因为vue-router会自动跟据路径拼
    const currentMenu = {
      path, name, hidden, parent,
      meta: { key: key, title, i18n, icon },
      children: children.length === 0 ? [] : generateUserMenuForTree(children)
    }
    if (children.length <= 0) {
      delete currentMenu.children
    }
    return currentMenu
  })
  return userRoutes
}

/**
 * 菜单列表转换路由树
 * @param menus
 * @returns {*}
 */
export const generateUserMenuForList = menus => {
  // 如果后端返回的是纯数组的菜单（即：没有转换成菜单树结构的，要先转化成树结构）
  const tree = listToTree(menus)
  const routes = generateUserMenuForTree(tree)
  return routes
}

export const setUserRouteComponent = routes => {
  routes.forEach(r => {
    r.component = !r.parent ? permissionRoutes.Layout : permissionRoutes[r.name]
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
export const findRouteForUserRoutes = (routes, path) => {
  let hasRoute = false
  const routeArr = path.split('/')
  const routePath = routeArr[routeArr.length - 1]
  for (let i = 0; i < routes.length; i++) {
    const { path, children = [] } = routes[i]
    if (path === routePath) {
      hasRoute = true
    } else if (children.length > 0) {
      hasRoute = findRouteForUserRoutes(children, routePath)
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
  if (!to.fullPath.includes('login') && !localStorage.getItem('pear_admin_ant_token_value') && !localStorage.getItem('pear_admin_ant_token_key')) {
    // 重定向 登录
    next({path: '/login'})
  } else {
    // 如果基本路由中不包含页面前往的路径
    if (!router.getRoutes().map(it => it.path).includes(to.fullPath)) {
    
      // 注册路由
      await store.dispatch('user/addRoute')

      // 用户路由 
      const userRoutes = JSON.parse(JSON.stringify(store.getters.menu))
      
      // 是否存在
      const hasRoute = findRouteForUserRoutes(userRoutes, to.fullPath)
      
      if (hasRoute) {
        // 注册路由 并 跳转
        setUserRouteComponent(userRoutes)
        userRoutes.forEach(r => { router.addRoute(r) })
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
