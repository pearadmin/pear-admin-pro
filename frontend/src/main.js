import { createApp } from "vue";
import Antd from "ant-design-vue/es";
import App from "./App.vue";
import Router from "./route";
import Store from "./store";
import Pear from "./component";
import i18n from './locale';
import * as antIcons from '@ant-design/icons-vue';
import Design from 'vue-form-create';

import "./assets/css/index.less";
import "ant-design-vue/dist/antd.less";

const app = createApp(App);
app.use(Antd);
app.use(Pear);
app.use(Store);
app.use(Router);
app.use(Design);
app.use(i18n);
app.mount("#app");

// 注册组件
Object.keys(antIcons).forEach(key => {
    app.component(key, antIcons[key])
})

// 添加到全局
app.config.globalProperties.$antIcons = antIcons
  

export default app