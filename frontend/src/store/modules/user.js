import { menu, login, logout } from "@/api/modules/user";
import {
  generatorUserMenuForList, generatorUserMenuForTree
} from "@/router/permission";
import { message } from "ant-design-vue";

const state = {
  token: '',
  userInfo: localStorage.getItem('user_info') ? JSON.parse(localStorage.getItem('user_info')) : null,
  userRoutes: []
}

const mutations = {
  SET_USER_TOKEN(state, token) {
    if (token) {
      state.token = token;
      localStorage.setItem('pear_admin_ant_token', token);
    } else {
      state.token = '';
      localStorage.removeItem('pear_admin_ant_token')
    }
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
  // 用户注销
  async logout({ commit }) {
    await logout()
    commit('SET_USER_TOKEN')
    commit('SET_USER_MENU')
    commit('layout/closeAllTab', [], { root: true })
    return Promise.resolve()
  },
  // 用户登录
  async login( {commit} , data) {
      const { code, msg, token } = await login(data);
      if (code === 200) {
        commit('SET_USER_TOKEN', token);
        message.success(msg);
        return Promise.resolve();
      } else {
        return Promise.reject(msg);
      }
  },
  // 动态路由
  async addRoute( {commit} ) {
    const { data } = await menu()
    const dynamicRoutes = generatorUserMenuForList(data)
    commit('SET_USER_MENU', dynamicRoutes)
  }
}
export default {
  namespaced: true,
  mutations,
  actions,
  state,
}
