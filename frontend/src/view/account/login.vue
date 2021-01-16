<template>
  <div id="login">
    <div class="login-form">
      <a-form :label-col="labelCol" :wrapper-col="wrapperCol">
        <a-form-item>
          <img class="logo" src="../../assets/image/logo.png" />
          <div class="head">Pear Admin</div>
          <div class="desc">明 湖 区 最 具 影 响 力 的 设 计 规 范 之 一</div>
        </a-form-item>
        <a-form-item v-bind="validateInfos.username">
          <a-input placeholder="账 户 : admin" v-model:value="param.username" />
        </a-form-item>
        <a-form-item v-bind="validateInfos.password">
          <a-input placeholder="密 码 : admin" v-model:value="param.password" />
        </a-form-item>
        <a-form-item v-bind="validateInfos.captchaCode">
          <a-input
            placeholder="例如 : abcd"
            v-model:value="param.captchaCode"
          />
          <img :src="captchaImage" />
        </a-form-item>
        <a-form-item>
          <a-input placeholder="例如 : abcd" v-model:value="param.captchaKey" />
        </a-form-item>
        <a-form-item>
          <a-checkbox :checked="true"> 记住密码 </a-checkbox>
          <a class="forgot" href=""> 忘记密码 </a>
        </a-form-item>
        <a-form-item :wrapper-col="{ span: 24 }">
          <a-button :loading="load" type="primary" @click="onSubmit">
            登录
          </a-button>
        </a-form-item>
      </a-form>
    </div>
  </div>
</template>
<script>
import { message, notification } from "ant-design-vue";
import { createCaptcha } from "@/api/modules/ops/captcha";
import { login } from "@/api/modules/sys/user";
import { onMounted, reactive, ref, toRaw } from "vue";
import { useForm } from "@ant-design-vue/use";
import { useRouter } from "vue-router";
import { useStore } from "vuex";
export default {
  setup() {
    const router = useRouter();
    const store = useStore();
    const load = ref(false);
    const captchaImage = ref();

    const param = reactive({
      username: "admin",
      password: "admin",
      captchaKey: "",
      captchaCode: "",
    });

    const { resetFields, validate, validateInfos } = useForm(
      param,
      reactive({
        username: [{ required: true, message: "请输入账户" }],
        password: [{ required: true, message: "请输入密码" }],
        captchaCode: [{ required: true, message: "请输入验证码" }],
      })
    );

    const onSubmit = async (e) => {
      load.value = true;
      try {
        if (await validate()) {
          const result = await login(param);
          if (result.success) {
            store.commit("user/SET_USER_TOKEN", result.token);
            notification["success"]({
              message: result.msg,
              description: "欢迎你使用系统",
              duration: 2,
            });
            router.push("/");
          } else {
            notification["error"]({
              message: result.msg,
              description: "请检查账户密码",
              duration: 2,
            });
            initCaptcha();
          }
        }
      } catch (e) {
        console.log("error", e);
      } finally {
        load.value = false;
      }
    };

    onMounted(() => {
      initCaptcha();
    });

    const initCaptcha = async function () {
      const response = await createCaptcha();
      param.captchaKey = response.data.key;
      param.captchaCode = response.data.code;
    };

    return {
      labelCol: { span: 6 },
      wrapperCol: { span: 24 },
      validateInfos,
      onSubmit,
      param,
      load,
    };
  },
};
</script>
<style lang="scss">
#login {
  width: 100%;
  height: 100%;
  background: url(../../assets/image/background.svg);
  background-size: cover;
  .login-form {
    margin: auto;
    width: 350px;
    min-height: 20px;
    padding-top: 150px;
    .ant-input {
      border-radius: 4px;
      line-height: 42px;
      height: 42px;
    }
    .ant-btn {
      width: 100%;
      height: 42px;
      line-height: 42px;
    }
  }
  .logo {
    width: 60px !important;
    margin-top: 10px !important;
    margin-bottom: 10px !important;
    margin-left: 20px !important;
    border: none;
    background-color: transparent;
  }
  .head {
    width: 300px;
    font-size: 30px !important;
    font-weight: 700 !important;
    margin-left: 20px !important;
    line-height: 60px !important;
    margin-top: 10px !important;
    position: absolute !important;
    display: inline-block !important;
    height: 60px !important;
    color: #36b368;
  }
  .desc {
    width: 100% !important;
    text-align: center !important;
    color: gray !important;
    height: 60px !important;
    line-height: 60px !important;
  }
  .forgot {
    float: right;
  }
}
</style>
