import {createRouter, createWebHashHistory} from 'vue-router';
import { permissionController } from "@/route/permission";
import routes from './module/base-routes';
import NProgress from "nprogress";
import "nprogress/nprogress.css";

const router = createRouter({
  history: createWebHashHistory(),
  routes
})

// 前置拦截器
router.beforeEach(permissionController)

// 后置拦截器
router.afterEach((to, from) => {
  NProgress.done();
})

export default router
