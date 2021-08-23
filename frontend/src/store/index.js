import {createLogger, createStore} from 'vuex';
import { isNotProduction } from "@/tools/common";

const debug = isNotProduction();
const context = require.context('./module/', false, /([\w+]\.(js))/);

const modules = context.keys().reduce((modules, fileName) => {
  const name = fileName.replace(/(\.\/|\.js)/g, '');
  return {
    ...modules,
    [name]: context(fileName).default
  }
}, {});

const getters = {
  layout: state => state.app.layout,
  theme: state => state.app.theme,
  color: state => state.app.color,
  collapsed: state => state.app.collapsed,
  settingVisible: state => state.app.setting.opened,
  logo: state => state.app.logo,
  tab: state => state.app.tab,
  tabType: state => state.app.tabType,
  routerActive: state => state.app.routerActive,
  openKey: state => state.app.openKey,
  activeKey: state => state.app.activeKey,
  panes: state => state.app.panes,
  fullscreen: state => state.app.fullscreen,
  sideWitch: state => state.app.sideWitch,
  collapsedWidth: state => state.app.collapsedWidth,
  fixedHeader: state => state.app.fixedHeader,
  fixedSide: state => state.app.fixedSide,
  routes: state => state.app.routes,
  colorList: state => state.app.colorList,
  routerAnimate: state => state.app.routerAnimate,
  language: state => state.app.language,
  isMobile: state => state.app.isMobile,
  keepAlive: state=> state.app.keepAlive,
  token: state => state.user.token,
  menu: state => state.user.userRoutes,
  power: state => state.user.userPowers,
  userInfo: state => state.user.userInfo,
};

export default createStore({
  modules,
  getters,
  strict: debug,
  plugins: debug ? [createLogger()] : []
})
