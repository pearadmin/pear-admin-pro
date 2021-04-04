export default [
  {
    path: '/',
    redirect: "/dashboard/console",
    hidden: true,
  },
  {
    path: '/login',
    component: () => import('@/view/login/index.vue'),
    meta: {
      title: '登录'
    },
    hidden: true,
  }
]
