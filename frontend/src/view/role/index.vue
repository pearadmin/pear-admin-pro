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
            :row-selection="{ selectedRowKeys: state.selectedRowKeys, onChange: onSelectChange }"
          >
          </pro-table>
        </a-card>
      </a-col>
    </a-row>
    <save :visible="state.visibleSave" @close="closeSave"></save>
    <edit :visible="state.visibleEdit" @close="closeEdit"></edit>
    <give :visible="state.visibleGive" @close="closeGive"></give>
  </page-layout>
</template>
<script>
import save from './modal/save';
import edit from './modal/edit';
import give from './modal/give';
import { message } from 'ant-design-vue';
import { page, remove } from "@/api/module/role";
import { reactive } from "vue";

export default {
  components: {
    save,
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
      { dataIndex: "code", key: "code", title: "标识" },
      { dataIndex: "remark", key: "remark", title: "描述" },
      { dataIndex: "enable", key: "enable", title: "状态", switch: switchFormat },
      { dataIndex: "sort", key: "sort", title: "排序" },
    ];

    /// 数据来源
    const fetch = async (param) => {
      var response = await page(param);
      return {
        total: response.data.total,
        data: response.data.record,
      };
    };

      /// 删除用户
    const removeHandler = (record) => {
      remove({"id":record.id}).then((response) => {
          if(response.success){
              message.success({ content: '删除成功', duration: 1 });
          }else{
              message.error({ content: '删除失败', duration: 1 });
          }
      })
    }

    /// 工具栏
    const toolbar = [
      { label: "新增", event: function (keys) { state.visibleSave = true }},
      { label: "删除", event: function (keys) { alert("批量删除:" + JSON.stringify(keys)); }},
    ];

    /// 行操作
    const operate = [
      { label: "查看", event: function (record) { alert("查看详情:" + JSON.stringify(record)); }},
      { label: "修改", event: function (record) { state.visibleEdit = true }},
      { label: "分配", event: function (record) { state.visibleGive = true }},
      { label: "删除", event: function (record) { removeHandler(record) }},
    ];

    /// 分页参数
    const pagination = { pageNum: 1, pageSize: 10 };

    /// 外置参数
    const state = reactive({
      selectedRowKeys: [],
      param: { name: "", code: "" },
      visibleSave: false,
      visibleEdit: false,
      visibleGive: false
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

    const closeSave = function(){
        state.visibleSave = false;
    }

    const closeEdit = function(){
        state.visibleEdit = false;
    }

    const closeGive = function(){
        state.visibleGive = false;
    }

    const onSelectChange = selectedRowKeys => {
      state.selectedRowKeys = selectedRowKeys;
    };

    /// 声明抛出
    return {
      state, // 状态共享
      fetch, // 数据回调
      toolbar, // 工具栏
      columns, // 列配置
      operate, // 行操作
      pagination, // 分页配置

      search,
      searchParam, // 查询参数

      closeSave,
      closeEdit,
      closeGive,

      onSelectChange
    };
  },
};
</script>