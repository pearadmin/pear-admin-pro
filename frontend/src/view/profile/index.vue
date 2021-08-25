<template>
  <div class="center">
    <page-layout>
      <a-row :gutter="[15, 15]">
        <a-col :lg="6" :md="6" :sm="24" :xs="24">
          <a-card style="position: relative">
            <a-avatar
              class="avatar"
              :size="64"
              src="https://portrait.gitee.com/uploads/avatars/user/2813/8441097_shaynas_1610801433.png!avatar200"
            />
            <div class="username">{{ userInfo.nickname }}</div>
            <div class="address">China</div>
            <a-divider />
            <div class="desc">{{ userInfo.sign }}</div>
          </a-card>
          <a-card style="margin-top: 15px">
            标签
            <br />
            <br />
            <a-tag>很有想法</a-tag>
            <a-tag>专注设计</a-tag>
            <a-tag>帅</a-tag>
            <a-tag>海纳百川</a-tag>
            <br />
            <br />
            <br />
            团队
            <br />
            <br />
            <a-row :gutter="[20, 20]">
              <a-col span="12"
                ><a-avatar>科</a-avatar>&nbsp;&nbsp;&nbsp;科学搬砖组</a-col
              >
              <a-col span="12"
                ><a-avatar>程</a-avatar>&nbsp;&nbsp;&nbsp;程序员日常</a-col
              >
              <a-col span="12"
                ><a-avatar>中</a-avatar>&nbsp;&nbsp;&nbsp;中二少年团</a-col
              >
              <a-col span="12"
                ><a-avatar>计</a-avatar>&nbsp;&nbsp;&nbsp;计算机天团</a-col
              >
            </a-row>
          </a-card>
        </a-col>
        <a-col :lg="18" :md="18" :sm="24" :xs="24">
          <a-card>
            <a-tabs @change="callback">
              <a-tab-pane key="1" tab="基本信息">
                <br/>
                <a-form
                  :model="userInfo"
                  :label-col="labelCol"
                  :wrapper-col="wrapperCol">
                  <a-form-item ref="username" label="账号" name="username">
                    <a-input v-model:value="userInfo.username" />
                  </a-form-item>
                  <a-form-item ref="nickname" label="名称" name="nickname">
                    <a-input v-model:value="userInfo.nickname" />
                  </a-form-item>
                  <a-form-item ref="email" label="邮箱" name="email">
                    <a-input v-model:value="userInfo.email" />
                  </a-form-item>
                  <a-form-item label="签名" name="sign">
                    <a-textarea v-model:value="userInfo.sign" />
                  </a-form-item>
                  <a-form-item :wrapper-col="{ span: 14, offset: 4 }">
                    <a-button type="primary" @click="editInfo"> 保存 </a-button>
                    <a-button style="margin-left: 10px" @click="resetForm">
                      重置
                    </a-button>
                      <a-button style="margin-left: 10px" @click="showPassword">
                      修改密码
                    </a-button>
                  </a-form-item>
                </a-form>
              </a-tab-pane>
              <a-tab-pane key="2" tab="账号绑定" force-render> 空 </a-tab-pane>
            </a-tabs>
            <a-modal v-model:visible="passwordState" title="修改密码" @ok="onSubmit" okText="确认" cancelText="取消">
              <a-form-item label="旧密码">
                <a-input
                  v-model:value="password.oldPassword"
                  placeholder="请输入旧密码"
                />
              </a-form-item>
              <a-form-item label="新密码">
                <a-input
                  v-model:value="password.newPassword"
                  placeholder="请输入新密码"
                />
              </a-form-item>
            </a-modal>
          </a-card>
        </a-col>
      </a-row>
    </page-layout>
  </div>
</template>
<script>
import { message } from 'ant-design-vue';
import { profile, editPassword, edit } from "@/api/module/user";
import { reactive, ref } from "@vue/reactivity";
export default {
  setup() {
    const userInfo = reactive({});
    const password = reactive({});
    const passwordState = ref(false);

    profile({}).then((response) => {
      userInfo.id = response.data.id;
      userInfo.sign = response.data.sign;
      userInfo.email = response.data.email;
      userInfo.username = response.data.username;
      userInfo.nickname = response.data.nickname;
    });

    const showPassword = function() {
      passwordState.value = true;
      password.newPassword = "";
      password.oldPassword = "";
      password.userId =  userInfo.value.id;
    }

    const onSubmit = function() {
      editPassword(password).then((response) => {
          if(response.success){
                message.success({ content: '保存成功', duration: 1 }).then(()=>{
                  passwordState.value = false;
                });
              }else{
                message.error({ content: '保存失败', duration: 1 }).then(()=>{
                  passwordState.value = false;
                });
              }
      })
    }

    const editInfo = function() {
      edit(userInfo).then((response) => {
        if(response.success){
            message.success({ content: '保存成功', duration: 1 });
        }else{
            message.error({ content: '保存失败', duration: 1 })
        }
      })
    }
    
    return {
      onSubmit,
      password,
      editInfo,
      showPassword,
      passwordState,
      labelCol: { span: 4 },
      wrapperCol: { span: 12 },
      userInfo,
    };
  },
};
</script>
<style lang="less">
.center {
  .avatar {
    margin-left: 38%;
    margin-top: 40px;
  }
  .username {
    width: 100%;
    text-align: center;
    margin-top: 15px;
    font-size: 16px;
  }
  .address {
    width: 100%;
    text-align: center;
    font-size: 14px;
    margin-top: 10px;
  }
  .desc {
    width: 100%;
    text-align: center;
  }
}
</style>
