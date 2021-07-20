import { module } from '@/tools/common'
import antLocal from "ant-design-vue/es/locale/zh_CN";

const requireZHContext = require.context('./zh-cn/', false, /[\w+].(js)$/)

const zhCN = module(requireZHContext)

export default {
  ...zhCN,
  antLocal
}