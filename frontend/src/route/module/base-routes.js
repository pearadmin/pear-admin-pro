import Layout from "@/layout";

export default [
  {
    path: '/',
    redirect: "/dashboard/workspace",
    hidden: true,
  },
  {
    path: '/login',
    component: () => import('@/view/login/index.vue'),
    meta: {
      title: '登录'
    },
    hidden: true,
  }, 
  {
    path: '/error/403',
    component: () => import('@/view/error/404.vue'),
    meta: { title: '404' },
    hidden: true,
  }, 
  {
    path: '/error/404',
    component: () => import('@/view/error/404.vue'),
    meta: {
      title: '404'
    },
    hidden: true,
  }, 
  {
    path: '/error/500',
    component: () => import('@/view/error/404.vue'),
    meta: {
      title: '500'
    },
    hidden: true,
  },
  {
    path: '/profile',
    component: () => Layout,
    children: [
      {
        path: '/profile/index',
        component: () => import('@/view/profile/index.vue'),
        meta: {
          title: '个人详情',
          i18n: 'profile'
        }
      }
    ],
    hidden: true,
  }
]