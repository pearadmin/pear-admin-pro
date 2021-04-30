<template>
    <a-modal :visible="visible" title="分配角色" cancelText="取消" okText="提交" @ok="submit" @cancel="cancel">
        <pro-table 
            :fetch="fetch"
            :columns ="columns"
            :row-selection="{ selectedRowKeys: state.selectedRowKeys, onChange: onSelectChange }"
        >
        </pro-table>
    </a-modal>
</template>
<script>
import { list } from "@/api/module/role";
import { role } from "@/api/module/user"; 
import { defineComponent, reactive, watch } from 'vue';
export default defineComponent({
  props: {
      visible: {
        type: Boolean
      },
      record: {
        type: Object
      }
  },
  emit: ['close'],
  setup(props, context) {

    const state = reactive({
      selectedRowKeys: ["1","2"]
    });

    watch(props.record,(oldVal, newVal) => {
        alert("旧值:" + oldVal);
        alert("新值:" + newVal);
    },{deep:true})

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

    const onSelectChange = selectedRowKeys => {
      state.selectedRowKeys = selectedRowKeys;
    };

    return {
      state,
      fetch, 
      submit,
      cancel,
      columns,
      onSelectChange
    };
  },
});
</script>