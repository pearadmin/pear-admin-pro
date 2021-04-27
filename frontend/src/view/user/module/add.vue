<template>
  <a-modal
    :visible="visible"
    title="新增用户"
    cancelText="取消"
    okText="提交"
    @ok="submit"
    @cancel="cancel"
  >
    <a-form
       ref="formRef"
      :model="formState"
      :rules="formRules"
      :label-col="labelCol"
      :wrapper-col="wrapperCol"
    >
      <a-form-item ref="nickname" label="昵称" name="nickname">
        <a-input v-model:value="formState.nickname" />
      </a-form-item>
      <a-form-item ref="username" label="账户" name="username">
        <a-input v-model:value="formState.username" />
      </a-form-item>
      <a-form-item ref="email" label="邮箱" name="email">
        <a-input v-model:value="formState.email" />
      </a-form-item>
      <a-form-item ref="phone" label="电话" name="phone">
        <a-input v-model:value="formState.phone" />
      </a-form-item>
      <a-form-item label="性别" name="gender">
        <a-radio-group v-model:value="formState.gender">
          <a-radio value="0">男</a-radio>
          <a-radio value="1">女</a-radio>
        </a-radio-group>
      </a-form-item>
      <a-form-item label="状态" name="enable">
        <a-switch v-model:checked="formState.enable" />
      </a-form-item>
      <a-form-item label="岗位" name="postId">
          <a-select v-model:value="formState.postId">
            <a-select-option :value="post.id" v-bind:key="index" v-for="(post,index) in state.posts">{{post.name}}</a-select-option>
          </a-select>
      </a-form-item>
      <a-form-item label="备注" name="remark">
        <a-textarea v-model:value="formState.remark" />
      </a-form-item>
    </a-form>
  </a-modal>
</template>
<script>
import { message } from 'ant-design-vue';
import { list } from "@/api/module/post";
import { add } from "@/api/module/user";
import { defineComponent, reactive, ref, toRaw } from "vue";
export default defineComponent({
  props: {
    visible: {
      type: Boolean,
    },
  },
  emit: ["close"],
  setup(props, context) {

    const formRef = ref();

    const state = reactive({
      posts: []
    })
    
    const formState = reactive({
      username: '',
      enable: true,
      gender:  "0",
    });

    const formRules = {
      username: [
        { required: true, message: '请输入用户名', trigger: 'blur'},
        { min: 3, max: 5, message: '长度应为 3 到 5', trigger: 'blur'},
      ],
      nickname: [
        { required: true, message: '请输入用户名', trigger: 'blur'},
        { min: 3, max: 5, message: '长度应为 3 到 5', trigger: 'blur'},
      ]
    };

    const loadData = () => {
      list({}).then((response)=>{
        state.posts = response.data;
      }) 
    }

    loadData();
     
    const modalKey = "add";

    const submit = (e) => {
        message.loading({ content: '提交中...', modalKey });
        formRef.value.validate().then(() => {
          add(toRaw(formState)).then((response)=>{
            if(response.success){
                message.success({ content: '保存成功', modalKey, duration: 1 }).then(()=>{
                cancel();
                });
            }else{
                message.error({ content: '保存失败', modalKey, duration: 1 }).then(()=>{
                  cancel();
                });
            }
          })

          console.log('values', formState, toRaw(formState));
      }).catch(error => {
          console.log('error', error);
      });
      context.emit("close", false);
    };

    const cancel = (e) => {
            formRef.value.resetFields();
      context.emit("close", false);
    };

    return {
      state,
      submit,
      cancel,
      formRef,
      formState,
      formRules,

      labelCol: { span: 6 },
      wrapperCol: { span: 18 },
    };
  },
});
</script>