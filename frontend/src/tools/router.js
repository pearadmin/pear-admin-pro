
/**
 * 路由是否存在
 * 
 * @param routes 集合
 * @param path 路由
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