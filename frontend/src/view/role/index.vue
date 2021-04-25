<template>
  <page-layout>
    <a-row :gutter="[10, 10]">
      <!-- 顶部区域 -->
      <a-col :span="24">
        <a-card>
          <!-- 查询参数 -->
          <pro-query :searchParam="searchParam" @on-search="search"></pro-query>
        </a-card>
      </a-col>
      <!-- 中心区域 -->
      <a-col :span="24">
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
    </a-row>
    <add :visible="state.addModal" @close="closeAdd"></add>
    <edit :visible="state.editModal" @close="closeEdit"></edit>
    <give :visible="state.giveModal" @close="closeGive"></give>
  </page-layout>
</template>
<script>
import add from './module/add';
import edit from './module/edit';
import give from './module/give';
import { page } from "@/api/module/role";
import { reactive } from "vue";

export default {
  components: {
    add,
    edit,
    give,
  },
  setup() {

    const switchFormat = { yes: true, no: false, event: function(value,record){
      record.enable = !record.enable;
      return value;
    }}

    const columns = [
      { dataIndex: "name", key: "name", title: "名称" },
      { dataIndex: "remark", key: "remark", title: "描述" },
      { dataIndex: "enable", key: "enable", title: "状态", switch: switchFormat },
      { dataIndex: "sort", key: "sort", title: "排序" },
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
      { label: "新增", event: function (keys) { state.addModal = true }},
      { label: "删除", event: function (keys) { alert("批量删除:" + JSON.stringify(keys)); }},
    ];

    /// 行操作
    const operate = [
      { label: "查看", event: function (record) { alert("查看详情:" + JSON.stringify(record)); }},
      { label: "修改", event: function (record) { state.editModal = true }},
      { label: "菜单权限", event: function (record) { state.giveModal = true }},
      { label: "数据权限", event: function (record) { state.giveModal = true }},
      { label: "删除", event: function (record) { alert("删除事件:" + JSON.stringify(record)); }},
    ];

    /// 分页参数
    const pagination = { pageNum: 1, pageSize: 10 };

    /// 外置参数
    const state = reactive({
      param: { name: "", code: "" },
      addModal: false,
      editModal: false,
      giveModal: false
    });

    /// 查询参数
    const searchParam = [
      { key: "name", type: "input", label: "名称" },
    ];

    /// 查询操作
    const search = function (value) {
      /// 刷新表格
      state.param = value;
    };

    const closeAdd = function(){
        state.addModal = false;
    }

    const closeEdit = function(){
        state.editModal = false;
    }

    const closeGive = function(){
        state.giveModal = false;
    }

    /// 声明抛出
    return {
      state: state, // 状态共享
      fetch: fetch, // 数据回调
      toolbar: toolbar, // 工具栏
      columns: columns, // 列配置
      operate: operate, // 行操作
      pagination: pagination, // 分页配置

      search: search,
      searchParam: searchParam, // 查询参数

      closeAdd,
      closeEdit,
      closeGive,
    };
  },
};
</script>