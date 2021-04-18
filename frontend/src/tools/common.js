/**
 * 是否生产环境
 */
export const isNotProduction = () => {
  return process.env.NODE_ENV !== 'production'
}

/**
 * 获取 Js 集合 
 */
export const getModule = context => {
  return context.keys().reduce((modules, key) => {
    return {
      ...modules,
      ...context(key).default
    }
  }, {})
}
