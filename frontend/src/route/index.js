import {createRouter, createWebHashHistory} from 'vue-router';
import { permissionController } from "@/route/permission";
import routes from './module/base-routes';
import NProgress from "nprogress";
import "nprogress/nprogress.css";

const router = createRouter({
  history: createWebHashHistory(),
  routes
})

// To 拦截
router.beforeEach(permissionController)

// Next 拦截
router.afterEach((to, from) => {
  NProgress.done();
})

export default router
