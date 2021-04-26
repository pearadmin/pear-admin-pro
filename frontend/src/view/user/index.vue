<template>
    <page-layout>
      <a-row :gutter="[10, 10]">
        <!-- 顶部区域 -->
        <a-col :span="24">
          <a-card>
            <!-- 查询参数 -->
            <pro-query
              :searchParam="searchParam"
              @on-search ="search"
            ></pro-query>
          </a-card>
        </a-col>
        <!-- 中心区域 -->
        <a-col :span="24">
          <a-card>
            <!-- 列表 -->
            <pro-table
              :fetch="fetch"
              :columns="columns"
              :toolbar="toolbar"
              :operate="operate"
              :param="state.param"
              :pagination="pagination"
              :row-selection="{ selectedRowKeys: state.selectedRowKeys, onChange: onSelectChange }"
            >
              <!-- 继承至 a-table 的默认插槽 -->
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
import { page } from "@/api/module/user";
import { reactive } from 'vue';

export default {
  components: {
    add,
    edit,
    give
  },
  setup() {

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
      { label: "新增", event: function () { state.addModal = true }},
      { label: "删除", event: function () { }},
    ];

    /// 行操作
    const operate = [
      { label: "查看", event: function (record) { alert("查看详情:" + JSON.stringify(record))}},
      { label: "修改", event: function (record) { state.editModal = true }},
      { label: "分配", event: function (record) { state.giveModal = true }},
      { label: "删除", event: function (record) { alert("删除事件:" + JSON.stringify(record))}},
    ];

    /// 文本
    const converFormat = [{label:"男", value:0},{label:"女", value:1}];

    /// 开关
    const switchFormat = { yes: true, no: false };

    /// 头像
    const avatarFormat = { size: 36, shape: "round" };

    /// 配置
    const columns = [
      { dataIndex: "avatar", key: "avatar", title: "头像", avatar: avatarFormat },
      { dataIndex: "nickname", key: "nickname", title: "名称" },
      { dataIndex: "username", key: "username", title: "账号" },
      { dataIndex: "gender", key: "gender", title: "性别", conver: converFormat},
      { dataIndex: "locked", key: "locked", title: "锁定", switch: switchFormat},
      { dataIndex: "email", key: "email", title: "邮箱" },
      { dataIndex: "phone", key: "phone", title: "电话" },
      { dataIndex: "createTime", key: "createTime", title: "注册时间" }
    ];

    /// 分页参数
    const pagination = { pageNum: 1, pageSize: 10}

    /// 外置参数
    const state = reactive({ 
      selectedRowKeys: [],
      param: { name: "",  code: "", enable: "" }, 
      addModal: false,
      editModal: false,
      giveModal: false,
    })

    /// 查询参数
    const searchParam = [
        { key: "name", type: "input", label: "名称"},
        { key: "code", type: "input", label: "描述"},
    ]

    const onSelectChange = selectedRowKeys => {
      state.selectedRowKeys = selectedRowKeys;
    };

    /// 查询操作
    const search = function(value) {
      state.param = value
    }

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
      onSelectChange: onSelectChange,

      search: search,
      searchParam: searchParam, // 查询参数

      closeAdd,  // 新增关闭回调
      closeEdit, // 修改关闭回调
      closeGive,// 分配关闭回调
    };
  },
};
</script>