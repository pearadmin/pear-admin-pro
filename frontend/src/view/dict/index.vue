<template>
  <div>
    <page-header
      title="数 据 字 典"
      describe="用 户 Online 列 表，用 于 系 统 在 线 用 户 监 控."
    ></page-header>
    <page-layout>
      <a-row :gutter="[10, 10]">
        <a-col :span="12">
          <a-card>
          <!-- 列表 -->
          <pro-table
            rowKey="id"
            :fetch="fetch"
            :columns="columns"
            :toolbar="toolbar"
            :operate="operate"
            :param="state.param"
            :pagination="pagination"
          >
          </pro-table>
          </a-card>
        </a-col>
        <a-col :span="12">
          <a-card>
     
          </a-card>
        </a-col>
      </a-row>
    </page-layout>
  </div>
</template>
<script>
import { page } from "@/api/module/dict";
import { reactive } from "vue";

export default {
  setup() {

    const switchFormat = { yes: true, no: false, event: function(value,record){
      record.enable = !record.enable;
      return value;
    }}

    const columns = [
      { dataIndex: "name", key: "name", title: "名称" },
      { dataIndex: "code", key: "code", title: "标识" },
      { dataIndex: "enable", key: "enable", title: "状态", switch: switchFormat },
    ];

    /// 数据来源 [模拟]
    const fetch = async (param) => {
      var response = await page(param);
      return {
        total: response.data.total,
        data: response.data.record,
      };
    };

    /// 工具栏
    const toolbar = [
      { label: "新增", event: function (keys) { state.addVisible = true }},
      { label: "删除", event: function (keys) { alert("批量删除:" + JSON.stringify(keys)); }},
    ];

    /// 行操作
    const operate = [
      /// 子表赋值
      { label: "查看", event: function (record) { state.dataParam = record.code }},
      { label: "修改", event: function (record) { alert("修改事件:" + JSON.stringify(record)); }},
      { label: "删除", event: function (record) { alert("删除事件:" + JSON.stringify(record)); }},
    ];

    /// 分页参数
    const pagination = { pageNum: 1, pageSize: 10 };

    /// 外置参数
    const state = reactive({
      param: { name: "", code: "" },
    });

    /// 声明抛出
    return {
      state: state, // 状态共享
      fetch: fetch, // 数据回调
      toolbar: toolbar, // 工具栏
      columns: columns, // 列配置
      operate: operate, // 行操作
      pagination: pagination, // 分页配置
    };
  },
};
</script>