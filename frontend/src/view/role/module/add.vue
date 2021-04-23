<template>
    <a-modal :visible="visible" title="新增角色" cancelText="取消" okText="提交" @ok="submit" @cancel="cancel">
      <p>Some contents...</p>
      <p>Some contents...</p>
      <p>Some contents...</p>
    </a-modal>
</template>
<script>
import { defineComponent } from 'vue';
export default defineComponent({
  props: {
      visible: {
          type: Boolean
      }
  },
  emit: ['close'],
  setup(props, context) {

    const submit = e => {
      context.emit('close', false);
    };

    const cancel = e => {
      context.emit('close', false);  
    }

    return {
      submit,
      cancel
    };
  },
});
</script>