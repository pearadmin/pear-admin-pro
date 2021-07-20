import { module } from '@/tools/common'
import antLocal from 'ant-design-vue/es/locale/en_US'

const requireENContext = require.context('./en-us/', false, /[\w+].(js)$/)
const enUS = module(requireENContext)

export default {
  ...enUS,
  antLocal
}
