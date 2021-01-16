import {getUserMenusArray, getUserMenusTree, login, logout} from "@/api/modules/sys/user";
import { generatorUserMenuForList, generatorUserMenuForTree} from "@/router/permission";

const state = {
  token: '',
  userInfo: null,
  userRoutes: []
}

const mutations = {
  SET_USER_TOKEN(state, token) {
      state.token = token;
      localStorage.setItem('PEAR_ADMIN_TOKEN', token);
  },
  SET_USER_INFO(state, userInfo) {
    state.userInfo = userInfo
    localStorage.setItem('user_info', JSON.stringify(userInfo))
  },
  SET_USER_MENU(state, menuList) {
    if (menuList && menuList.length === 0) {
      state.userRoutes = []
      localStorage.removeItem('user_routes')
    } else {
      const finalMenu = menuList
      state.userRoutes = finalMenu
      localStorage.setItem('user_routes', JSON.stringify(finalMenu))
    }
  }
}

const actions = {
  setUserToken({commit}, token) {
    return new Promise(resolve => {
      commit('SET_USER_TOKEN', token);
      resolve()
    })
  },
  async logout({commit}) {
    await logout()
    commit('SET_USER_TOKEN', '')
    commit('SET_USER_MENU')
    commit('layout/closeAllTab', [], { root: true })
    return Promise.resolve()
  },
  async login({commit}, data) {
    const response = await login(data);
    const {token} = response; 
    commit('SET_USER_TOKEN', token);
    return Promise.resolve()
  },
  // addUserRouteForArray, addUserRouteForTree 跟据后端返回数据结构来决定走哪个方法。
  async addUserRouteForArray ({ state: { userRoutes }, commit }) {
    // 如果菜单不是单独的接口
    // const routes = JSON.parse(JSON.stringify(userRoutes))
    // addUserRoutes(routes)
    // return Promise.resolve()
    // 如果菜单是单独的接口
    const { data } = await getUserMenusArray()
    const dynamicRoutes = generatorUserMenuForList(data)
    commit('SET_USER_MENU', dynamicRoutes)
  },
  async addUserRouteForTree ({ state: { userRoutes }, commit }) {
    // 如果菜单不是单独的接口
    // const routes = JSON.parse(JSON.stringify(userRoutes))
    // addUserRoutes(routes)
    // return Promise.resolve()
    // 如果菜单是单独的接口
    const { result: menuList } = await getUserMenusTree()
    const dynamicRoutes = generatorUserMenuForTree(menuList)
    commit('SET_USER_MENU', dynamicRoutes)
  }
}

export default {
  namespaced: true,
  state,
  mutations,
  actions
}
