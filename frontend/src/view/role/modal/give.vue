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
import { message } from 'ant-design-vue';
import { tree } from "@/api/module/power";
import { power, give } from "@/api/module/role";
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

    /// 监听数据
    watch(props,(props) => {
        let powerIds = [];
        power({"roleId":props.record.id}).then((response)=>{
          response.data.forEach(element => {
            powerIds.push(element.id);
          });
          state.checkedKeys = powerIds;  
        })
    })

    /// 加载权限
    loadData();

    const submit = e => {
        give({"roleId":props.record.id,"powerIds":state.checkedKeys}).then((response)=>{
            if(response.success){
                message.success({ content: '保存成功', duration: 1 }).then(()=>{
                  cancel();
                });
            }else{
                message.error({ content: '保存失败', duration: 1 }).then(()=>{
                  cancel();
                });
            }
          })
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