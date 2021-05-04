<template>
  <a-modal :visible="visible" title="分配权限" cancelText="取消" okText="保存" @ok="submit" @cancel="cancel">
    <a-tree
       checkable
      :tree-data="state.powers"
      :replace-fields="state.replaceFields"
       v-model:checkedKeys="state.checkedKeys"
    />
  </a-modal>
</template>
<script>
import { tree } from "@/api/module/power";
import { power } from "@/api/module/role";
import { defineComponent, reactive, watch } from "vue";
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
    const state = reactive({
      powers: [],
      checkedKeys: [],
      replaceFields: { key: "id" },
    });

    const loadData = async () => {
      var response = await tree();
      state.powers = response.data;
    };

    watch(props,(props) => {
        state.checkedKeys = [];
        power({"roleId":props.record.id}).then((response)=>{
          response.data.forEach(element => {
            state.checkedKeys.push(element.id);
          });
        })
    })

    /// 加载权限
    loadData();

    const submit = (e) => {
      context.emit("close", false);
    };

    const cancel = (e) => {
      context.emit("close", false);
    };

    return {
      state,
      submit,
      cancel,
    };
  },
});
</script>