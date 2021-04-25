<template>
    <a-modal :visible="visible" title="分配角色" cancelText="取消" okText="提交" @ok="submit" @cancel="cancel">
        <pro-table 
              :fetch="fetch"
              :columns ="columns">
              </pro-table>
    </a-modal>
</template>
<script>
import { list } from "@/api/module/role";
import { role } from "@/api/module/user"; 
import { defineComponent, reactive } from 'vue';
export default defineComponent({
  props: {
      visible: {
          type: Boolean
      }
  },
  emit: ['close'],
  setup(props, context) {

    /// 数据来源 [模拟]
    const fetch = async (param) => {
      var response = await list(param);
      return {
        data: response.data,
      };
    };

    const columns = [
      { dataIndex: "name", key: "name", title: "名称" },
      { dataIndex: "remark", key: "remark", title: "描述" }
    ];

    const submit = e => {
      context.emit('close', false);
    };

    const cancel = e => {
      context.emit('close', false);  
    }

    return {
      fetch, 
      submit,
      cancel,
      columns,
    };
  },
});
</script>