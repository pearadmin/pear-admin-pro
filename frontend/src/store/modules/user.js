import { menu, login, logout } from "@/api/modules/user";
import {
  generateUserMenuForList, generateUserMenuForTree
} from "@/router/permission";
import { message } from "ant-design-vue";

const state = {
  tokenKey: '',
  tokenValue: '',
  userInfo: localStorage.getItem('user_info') ? JSON.parse(localStorage.getItem('user_info')) : null,
  userRoutes: []
}

const mutations = {
  /// 存储 Token
  SET_USER_TOKEN(state, token) {
    if (token) {
      state.tokenKey = token.key;
      state.tokenValue = token.value;
      localStorage.setItem('pear_admin_ant_token_key',token.key)
      localStorage.setItem('pear_admin_ant_token_value', token.value)
    } else {
      state.tokenKey = '';
      state.tokenValue = '';
      localStorage.removeItem('pear_admin_ant_token_key')
      localStorage.removeItem('pear_admin_ant_token_value')
    }
  },
  /// 存储用户详情
  SET_USER_INFO(state, userInfo) {
    state.userInfo = userInfo
    localStorage.setItem('user_info', JSON.stringify(userInfo))
  },
  /// 存储用户菜单
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
  /// 注销
  async logout({ commit }) {
    await logout()
    commit('SET_USER_TOKEN')
    commit('SET_USER_MENU')
    commit('layout/closeAllTab', [], { root: true })
    return Promise.resolve()
  },
  // 登录
  async login( {commit} , data) {
      const { code, msg, tokenKey, tokenValue } = await login(data);
      if (code === 200) {
        commit('SET_USER_TOKEN', {key:tokenKey, value:tokenValue});
        message.success(msg);
        return Promise.resolve();
      } else {
        return Promise.reject(msg);
      }
  },
  // 路由
  async addRoute( {commit} ) {
    const { data } = await menu()
    const routes = generateUserMenuForList(data)
    commit('SET_USER_MENU', routes)
  }
}
export default {
  namespaced: true,
  mutations,
  actions,
  state,
}
