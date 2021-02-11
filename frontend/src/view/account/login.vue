<template>
  <div id="login">
    <div class="login-form">
      <a-form :label-col="labelCol" :wrapper-col="wrapperCol">
        <a-form-item>
          <img class="logo" src="@/assets/image/logo.png" />
          <div class="head">Pear Admin</div>
          <div class="desc">明 湖 区 最 具 影 响 力 的 设 计 规 范 之 一</div>
        </a-form-item>
        <a-form-item v-bind="validateInfos.username">
          <a-input placeholder="账 户 : admin" v-model:value="param.username" />
        </a-form-item>
        <a-form-item v-bind="validateInfos.password">
          <a-input placeholder="密 码 : admin" type="password" v-model:value="param.password" />
        </a-form-item>
        <a-form-item class="captchaKey">
          <a-input v-model:value="param.captchaKey" />
        </a-form-item>
        <a-form-item>
          <a-row :gutter="10">
            <a-col :span="13">
              <a-input v-model:value="param.captchaCode" />
            </a-col>
            <a-col :span="11">
              <img class="captchaImage" @click="refreshCaptcha" style="margin-top:-3px;" :src="captcha" />
            </a-col>
          </a-row>
        </a-form-item>
        <a-form-item>
          <a-checkbox :checked="true"> 记住我 </a-checkbox>
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
import { reactive, ref } from "vue";
import { useForm } from "@ant-design-vue/use";
import { useRouter } from "vue-router";
import { useStore } from "vuex";
import { create } from "@/api/modules/captcha";
export default {
  setup() {
    const router = useRouter();
    const store = useStore();
    const load = ref(false);

    // 登录参数
    const param = reactive({
      username: "admin",
      password: "admin",
      captchaKey: "key",
      captchaCode: "code",
    });

    const captcha = ref();

    // 参数验证
    const { validate, validateInfos } = useForm(
      param,
      reactive({
        username: [{ required: true, message: "请输入账户" }],
        password: [{ required: true, message: "请输入密码" }],
        captchaKey: [{ required: true, message: "验证码缺失" }],
        captchaCode: [{ required: true, message: "请输入验证码" }],
      })
    );

    // 验证码 初始化
    const refreshCaptcha = async function () {
      const result = await create();
      param.captchaKey = result.data.key;
      param.captchaCode = result.data.code;
      captcha.value = result.data.image;
    };
    refreshCaptcha();

    // 登录 验证
    const onSubmit = async () => {
      try{
        const v = await validate();
        if (v) {
          load.value = true;
          await store.dispatch("user/login", param);
          await router.push("/");
        }
      }catch(e){
        load.value = false
      }
    };
    return {
      labelCol: { span: 6 },
      wrapperCol: { span: 24 },
      refreshCaptcha,
      validateInfos,
      onSubmit,
      captcha,
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
  background: url(https://www.erupt.xyz/demo/login-img.jpg);
  background-size: cover;
  .login-form {
    margin: auto;
    width: 330px;
    min-height: 20px;
    padding-top: 150px;
    .captchaKey {
      display: none;
    }
    .captchaImage{
      border-radius: 4px;
      border: 1px solid #d9d9d9;
    }
    .ant-input {
      border-radius: 4px;
      line-height: 40px;
      height: 40px;
    }
    .ant-btn {
      width: 100%;
      height: 40px;
      line-height: 40px;
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
