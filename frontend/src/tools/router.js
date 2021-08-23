
/**
 * 路由是否存在
 * 
 * @param routes 集合
 * @param path 路由
 */
export const inCloudRoute = (routes, routePath) => {
    let hasRoute = false

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