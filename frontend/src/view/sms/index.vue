<template>
  <div>
    <page-header
      title="短信发送"
      describe="集成 Aliyun 短信服务, 给用户给予移动消息提醒."
    ></page-header>
    <page-layout>
      <a-card>
        <a-row type="flex" justify="center">
          <a-col :xs="24" :sm="24" :md="24" :lg="24" :xl="22" :xxl="19">
            <a-form
              ref="ruleForm"
              :model="form"
              :rules="rules"
              :label-col="labelCol"
              :wrapper-col="wrapperCol"
            >
              <a-form-item name="phone" label="接收手机">
                <a-input v-model:value="form.phone" />
              </a-form-item>
              <a-form-item name="message" label="短信内容">
                <a-textarea v-model:value="form.message" :rows="12" />
              </a-form-item>
              <a-form-item :wrapper-col="{ span: 14, offset: 4 }">
                <a-button type="primary" @click="onSubmit"> 发送 </a-button>
                <a-button style="margin-left: 10px" @click="resetForm">
                  重置
                </a-button>
              </a-form-item>
            </a-form>
          </a-col>
        </a-row>
      </a-card>
    </page-layout>
    <page-footer></page-footer>
  </div>
</template>
<script>
import { message } from 'ant-design-vue';
import { send } from '@/api/module/sms'
import { toRaw } from 'vue';

const sendKey = "sendKey";

export default {
  data() {
    return {
      labelCol: { xs: 4, sm: 3, md: 3, lg: 3, xl: 2, xxl: 3 },
      wrapperCol: { xs: 20, sm: 21, md: 21, lg: 21, xl: 20, xxl: 17 },
      form: {
        phone: "",
        message: undefined
      },
      rules: {
        phone: [{required: true,message: "请输入手机号",trigger: "blur"}],
        message: [{required: true, message: "请输入短信内容", trigger: "blur"}]
      }
    };
  },
  methods: {
    onSubmit() {
      this.$refs.ruleForm
        .validate()
        .then(() => {

          message.loading({
            content: "提交中...",
            key: sendKey,
          });

          send(toRaw(this.form)).then((response) => {
              if(response.success){
                message.success({ content: '发送成功',key: sendKey, duration: 1 }).then(()=>{
                  this.resetForm();
                });
              }else{
                message.error({ content: '发送失败',key: sendKey, duration: 1 }).then(()=>{
                  this.resetForm();
                });
              }
          })

          console.log("values", this.form);
        })
        .catch(error => {
          console.log("error", error);
        });
    },
    resetForm() {
      this.$refs.ruleForm.resetFields();
    }
  }
};
</script>
