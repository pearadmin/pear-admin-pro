<template>
  <a-modal :visible="visible" title="分配权限" cancelText="取消" okText="保存" @ok="submit" @cancel="cancel">
    <a-tree
      checkable
      :tree-data="state.treeData"
      :replace-fields="state.replaceFields"
    />
  </a-modal>
</template>
<script>
import { tree } from "@/api/module/power";
import { defineComponent, reactive } from "vue";
export default defineComponent({
  props: {
    visible: {
      type: Boolean,
    },
  },
  emit: ["close"],
  setup(props, context) {
    const state = reactive({
      treeData: [],
      replaceFields: { key: "id" },
    });

    const loadData = async () => {
      var response = await tree();
      state.treeData = response.data;
    };

    loadData();

    const submit = (e) => {
      context.emit("close", false);
    };

    const cancel = (e) => {
      context.emit("close", false);
    };

    return {
      submit,
      cancel,
      state,
    };
  },
});
</script>