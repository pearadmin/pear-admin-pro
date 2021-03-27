/**
 * 动态路由
 */
import Layout from "@/layout";

export default {
  Layout,
  'account-center': () => import('@/view/account/center.vue'),
  'dashboard-console': () => import('@/view/dashboard/console.vue'),
  'dashboard-workspace': () => import('@/view/dashboard/workspace.vue'),
  'advanced-form': () => import('@/view/form/advancedForm.vue'),
  'base-form': () => import('@/view/form/baseForm.vue'),
  'step-form': () => import('@/view/form/stepForm.vue'),
  'base-list': () => import('@/view/list/baseList.vue'),
  'card-list': () => import('@/view/list/cardList.vue'),
  'news-list': () => import('@/view/list/newsList.vue'),
  'table-list': () => import('@/view/list/tableList.vue'),
  'result-success': () => import('@/view/result/success.vue'),
  'result-failure': () => import('@/view/result/failure.vue'),
  'error-403': () => import('@/view/error/403.vue'),
  'error-404': () => import('@/view/error/404.vue'),
  'error-500': () => import('@/view/error/500.vue'),
  'permissionTest': () => import('@/view/permission/permission.vue'),
  'user-list': () => import('@/view/user/index.vue'),
  'role-list': () => import('@/view/role/index.vue'),
  'operate-list': () => import('@/view/log/operate.vue'),
  'login-list': () => import('@/view/log/login.vue'),
  'power-list': () => import('@/view/power/index.vue'),
  'config-list': () => import('@/view/config/index.vue'),
  'dict-list': () => import('@/view/dict/index.vue'),
  'post-list': () => import('@/view/post/index.vue'),
  'dept-list': () => import('@/view/dept/index.vue'),
  'server-list': () => import('@/view/monitor/server/index.vue'),
  'online-list': () => import('@/view/monitor/online/index.vue'),
  'redis-list': () => import('@/view/monitor/redis/index.vue'),
  'doc-list': () => import('@/view/doc/index.vue'),
}
