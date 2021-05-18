<template>
  <a-modal
    :visible="visible"
    title="修改模板"
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
      <a-form-item ref="subscribe" label="类型" name="subscribe">
        <a-input v-model:value="formState.subscribe" />
      </a-form-item>
      <a-form-item name="template" label="模板" >
        <a-textarea v-model:value="formState.template" />
      </a-form-item>
      <a-form-item name="remark" label="备注" >
        <a-textarea v-model:value="formState.remark" />
      </a-form-item>
    </a-form>
  </a-modal>
</template>
<script>
import { message } from 'ant-design-vue';
import { edit } from "@/api/module/remindTemplate";
import { defineComponent, reactive, ref, toRaw, watch } from "vue";
export default defineComponent({
  props: {
    visible: {
      type: Boolean,
    },
    record: {
      type: Object,
    }
  },
  emit: ["close"],
  setup(props, context) {

    const formRef = ref();
    
    const formState = reactive({});

    watch(props, (props) => {
        formState.id = props.record.id
        formState.subscribe = props.record.subscribe
        formState.template = props.record.template
        formState.remark = props.record.remark
    })

    const formRules = {
      subscribe: [ { required: true, message: '请输入订阅类型', trigger: 'blur'} ],
      template: [ { required: true, message: '请输入订阅模板', trigger: 'blur'} ]
    };

    const editKey = "add";

    const submit = (e) => {
      message.loading({ content: '提交中...', key: editKey });
      formRef.value
        .validate()
        .then(() => {
          edit(toRaw(formState)).then((response)=>{
              if(response.success){
                message.success({ content: '保存成功', key: editKey, duration: 1 }).then(()=>{
                  cancel();
                });
              }else{
                message.success({ content: '保存失败', key: editKey, duration: 1 }).then(()=>{
                  cancel();
                });
              }
          });
        })
        .catch(error => {
          console.log('error', error);
        });
    };

    const cancel = (e) => {
      formRef.value.resetFields();
      context.emit("close", false);
    };

    return {

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