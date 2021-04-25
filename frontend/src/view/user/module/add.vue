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
      <a-form-item label="Instant deliver" name="delivery">
        <a-switch v-model:checked="formState.delivery" />
      </a-form-item>
      <a-form-item label="Activity type" name="type">
        <a-checkbox-group v-model:value="formState.type">
          <a-checkbox value="1" name="type">Online</a-checkbox>
          <a-checkbox value="2" name="type">Promotion</a-checkbox>
          <a-checkbox value="3" name="type">Offline</a-checkbox>
        </a-checkbox-group>
      </a-form-item>
      <a-form-item label="Resources" name="resource">
        <a-radio-group v-model:value="formState.resource">
          <a-radio value="1">Sponsor</a-radio>
          <a-radio value="2">Venue</a-radio>
        </a-radio-group>
      </a-form-item>
      <a-form-item label="Activity form" name="desc">
        <a-textarea v-model:value="formState.desc" />
      </a-form-item>
      <a-form-item :wrapper-col="{ span: 14, offset: 4 }">
        <a-button type="primary" @click="onSubmit">Create</a-button>
        <a-button style="margin-left: 10px" @click="resetForm">Reset</a-button>
      </a-form-item>
    </a-form>
  </a-modal>
</template>
<script>
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
    const formState = reactive({
      username: ''
    });
    const formRules = {
      username: [
        { required: true, message: '请输入用户名', trigger: 'blur'},
        { min: 3, max: 5, message: '长度应为 3 到 5', trigger: 'blur'},
      ],
      nickname: [
        { required: true, message: '请输入用户名', trigger: 'blur'},
        { min: 3, max: 5, message: '长度应为 3 到 5', trigger: 'blur'},
      ],
      type: [
        {
          type: 'array',
          required: true,
          message: 'Please select at least one activity type',
          trigger: 'change',
        },
      ],
      resource: [
        {
          required: true,
          message: 'Please select activity resource',
          trigger: 'change',
        },
      ],
      desc: [
        {
          required: true,
          message: 'Please input activity form',
          trigger: 'blur',
        },
      ],
    };

    const onSubmit = () => {
      formRef.value
        .validate()
        .then(() => {
          console.log('values', formState, toRaw(formState));
        })
        .catch(error => {
          console.log('error', error);
        });
    };

    const resetForm = () => {
      formRef.value.resetFields();
    };

    const submit = (e) => {
      context.emit("close", false);
    };

    const cancel = (e) => {
      context.emit("close", false);
    };

    return {
      submit,
      cancel,
      formRef,
      labelCol: { span: 6 },
      wrapperCol: { span: 18 },
      other: '',
      formState,
      formRules,
      onSubmit,
      resetForm,
    };
  },
});
</script>