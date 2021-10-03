import axios from "axios";
import { message as antdMessage } from "ant-design-vue";
import store from "../store";
import qs from 'qs';
import router from '@/route/index'

class Request {

  constructor(config) {
    this.config = config || {
      timeout: 8000,
      withCredentials: true,
      baseURL: process.env.VUE_APP_API_BASE_URL,
      headers: {
        "Content-Type": "application/json; charset=utf-8"
      }
    };
  }

  interceptors(instance) {
    
    /// 请求拦截
    instance.interceptors.request.use(
      config => {
        
        /// 权鉴相关
        const tokenKey = localStorage.getItem("token_key");
        const token = localStorage.getItem("token")
        if(token) config.headers["Authorization"] = token;
        if(tokenKey) config.headers["Authorization-key"] = tokenKey;
        config.cancelToken = new axios.CancelToken(async cancel => {
          await store.dispatch("app/execCancelToken", { cancelToken: cancel });
        });
        
        /// 格式化 []
        if (config.method === 'delete') {
          config.paramsSerializer = (params) => {
            return qs.stringify(params, {arrayFormat: 'repeat'})
          }
        }

        return config;
      },
      error => {
        return Promise.reject(error);
      }
    );

    /// 响应拦截
    instance.interceptors.response.use(
      response => {
        if(response.data.code === 502) {
          antdMessage.error("登录过期").then(() => router.push("/login"))
        }
        return response.data;
      },
      error => {
        this.errorHandle(error); 
        return Promise.reject(error);
      }
    );
  }

  errorHandle = function(error) {
    var response = error.response
    var message = error.message
    if (response) {
      if (response.status === 404)  antdMessage.error("接口不存在");
    } else {
      if (message === "Network Error") antdMessage.error("连接异常")
      
      if (message.includes("timeout")) antdMessage.error("请求超时");
      
      if (message.includes("Request failed with status code")) antdMessage.error("接口异常");
    }
  }

  request(options) {
    const instance = axios.create();
    const requestOptions = Object.assign({}, this.config, options);
    this.interceptors(instance);
    return instance(requestOptions);
  }
}

const request = new Request();
export default new Request();
