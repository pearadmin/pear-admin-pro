<template>
  <a-modal
    :visible="visible"
    title="导入配置"
    cancelText="取消"
    okText="导入"
    width="800px"
    height="500px"
    @ok="submit"
    @cancel="cancel"
  >
    <pro-table
      ref="tableRef"
      :fetch="fetch"
      :columns="columns"
      :toolbar="toolbar"
      :operate="operate"
      :param="state.param"
      :pagination="pagination"
      :row-selection="{
        selectedRowKeys: state.selectedRowKeys,
        onChange: onSelectChange,
      }"
    >
    </pro-table>
  </a-modal>
</template>
<script>
import { message } from "ant-design-vue";
import { DBPage } from "@/api/module/generate";
import { defineComponent, reactive, ref } from "vue";

export default defineComponent({
  props: {
    visible: {
      type: Boolean,
    },
  },
  emit: ["close"],
  setup(props, context) {

    const saveKey = "save";

    /// 数据来源 [模拟]
    const fetch = async (param) => {
      var response = await DBPage(param);
      return {
        total: response.data.total,
        data: response.data.record,
      };
    };

    const tableRef = ref();

    /// 列配置
    const columns = [
      { dataIndex: "tableName", key: "content", title: "名称" },
      { dataIndex: "tableComment", key: "createName", title: "描述" },
      { dataIndex: "createTime", key: "createTime", title: "创建时间" }
    ];
        /// 行操作
    const operate = [
      { label: "导入", event: function (record) { alert("导入") }},
    ];

    /// 分页参数
    const pagination = {
      pageNum: 1,
      pageSize: 5,
    }

    const submit = (e) => {
      message.loading({ content: "提交中...", key: saveKey });
    };

    const cancel = (e) => {
      context.emit("close", false);
    };

    /// 外置参数 - 当参数改变时, 重新触发 fetch 函数
    const state = reactive({
      selectedRowKeys: []
    })

    const onSelectChange = selectedRowKeys => {
      state.selectedRowKeys = selectedRowKeys;
    };

    return {
      state,
      submit,
      cancel,
      fetch,
      columns,
      operate,
      pagination,
      onSelectChange
    };
  },
});
</script>