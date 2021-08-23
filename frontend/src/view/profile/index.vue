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
            <div class="username"> {{ userInfo.nickname }} </div>
            <div class="address">China</div>
            <a-divider />
            <div class="desc">被岁月镂空, 亦受其雕琢</div>
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
                <a-form
                  ref="ruleForm"
                  :model="userInfo"
                  :label-col="labelCol"
                  :wrapper-col="wrapperCol"
                  style="margin-top: 20px"
                >
                  <a-form-item ref="username" label="账号" name="username">
                    <a-input v-model:value="userInfo.username" />
                  </a-form-item>
                 <a-form-item ref="nickname" label="名称" name="nickname">
                    <a-input v-model:value="userInfo.nickname" />
                  </a-form-item>
                  <a-form-item ref="email" label="邮箱" name="email">
                    <a-input v-model:value="userInfo.email" />
                  </a-form-item>
                  <a-form-item label="异地" name="delivery">
                    <a-switch checked="true" />
                  </a-form-item>
                  <a-form-item label="状态" name="type">
                    <a-checkbox-group value="1">
                      <a-checkbox value="1" name="type"> 在线 </a-checkbox>
                      <a-checkbox value="2" name="type"> 隐身 </a-checkbox>
                      <a-checkbox value="3" name="type"> 离线 </a-checkbox>
                    </a-checkbox-group>
                  </a-form-item>
                  <a-form-item label="签名" name="desc">
                    <a-textarea value="被岁月镂空, 亦受其雕琢" />
                  </a-form-item>
                  <a-form-item :wrapper-col="{ span: 14, offset: 4 }">
                    <a-button type="primary" @click="onSubmit"> 保存 </a-button>
                    <a-button style="margin-left: 10px" @click="resetForm">
                      重置
                    </a-button>
                  </a-form-item>
                </a-form>
              </a-tab-pane>
              <a-tab-pane key="2" tab="账号绑定" force-render>
                空
              </a-tab-pane>
            </a-tabs>
          </a-card>
        </a-col>
      </a-row>
    </page-layout>
  </div>
</template>
<script>
import { profile } from "@/api/module/user";
import { ref } from '@vue/reactivity';
export default {
  methods: {
    onSubmit() {
      this.$refs.ruleForm
        .validate()
        .then(() => {
          console.log("values", this.form);
        })
        .catch((error) => {
          console.log("error", error);
        });
    },
    resetForm() {
      this.$refs.ruleForm.resetFields();
    },
  },
  setup() {

    const userInfo = ref({
    });

    profile({}).then((response) => {
      alert(JSON.stringify(response.data))
      userInfo.value = response.data;
    });

    return {
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
