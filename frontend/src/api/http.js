import axios from 'axios'
import store from '../store'
class Http {
  constructor(config) {
    this.config = config || {
      timeout: 6000,
      withCredentials: true,
      baseURL: process.env.VUE_APP_API_BASE_URL,
    }
  }

  interceptors(instance) {
    /**
     * 请求拦截器
     */
    instance.interceptors.request.use(config => {
      const token = localStorage.getItem('PEAR_ADMIN_TOKEN')
      if (token) {
        config.headers['authorization'] = token
      }
      config.cancelToken = new axios.CancelToken(async cancel => {
        await store.dispatch('app/execCancelToken', { cancelToken: cancel })
      })
      return config
    }, error => {
      return Promise.reject(error)
    })
    instance.interceptors.response.use(response => {
      return response.data
    }, error => {
      return Promise.reject(error)
    })
  }

  request (options) {
    const instance = axios.create()
    const requestOptions = Object.assign({}, this.config, options)
    this.interceptors(instance)
    return instance(requestOptions)
  }
}

const resultEnum = [
  {code: 400, msg: ''}
]

const http = new Http()
export default http
