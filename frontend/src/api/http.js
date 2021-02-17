import axios from "axios";
import { message as Msg, message } from "ant-design-vue";
import store from "../store";
class Http {
  constructor(config) {
    this.config = config || {
      timeout: 6000,
      withCredentials: true,
      baseURL: process.env.VUE_APP_API_BASE_URL,
      headers: {
        "Content-Type": "application/json; charset=utf-8"
      }
    };
  }

  interceptors(instance) {
    /**
     * 请求拦截器
     */
    instance.interceptors.request.use(
      config => {
        const token = localStorage.getItem("pear_admin_ant_token");
        if (token) {
          config.headers["Authorization"] = token;
        }
        // 请求时缓存该请求，路由跳转时取消, 如果timeout值过大，可能在上一个次请求还没完成时，切换了页面。
        config.cancelToken = new axios.CancelToken(async cancel => {
          await store.dispatch("app/execCancelToken", { cancelToken: cancel });
        });
        return config;
      },
      error => {
        return Promise.reject(error);
      }
    );

    /**
     * 响应拦截器 
     */
    instance.interceptors.response.use(
      response => {
        if (response.data.code !== 200) {
          message.error(response.data.msg);
          return response.data;
        } else {
          return response.data;
        }
      },
      error => {
        if (error.response) {
          const {status} = error.response;
          if (status === 404) {
            Msg.error("接口不存在");
          }
        } else {
          let { message } = error;
          if (message === "Network Error") {
            message = "连接异常";
          }
          if (message.includes("timeout")) {
            message = "请求超时";
          }
          if (message.includes("Request failed with status code")) {
            const code = message.substr(message.length - 3);
            message = "接口" + code + "异常";
          }
          Msg.error(message);
        }
        return Promise.reject(error);
      }
    );
  }

  request(options) {
    const instance = axios.create();
    const requestOptions = Object.assign({}, this.config, options);
    this.interceptors(instance);
    return instance(requestOptions);
  }
}

const http = new Http();
export default http;
