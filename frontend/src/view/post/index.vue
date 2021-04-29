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
    </page-layout>
</template>

<script>
import add from './module/add';
import { message } from 'ant-design-vue';
import { page, remove } from "@/api/module/post";
import { reactive } from 'vue';

export default {
  components: {
    add
  },
  setup() {

    /// 开关
    const switchFormat = { yes: true, no: false };

    /// 列配置
    const columns = [
      { dataIndex: "name", key: "name", title: "名称" },
      { dataIndex: "code", key: "code", title: "标识" },
      { dataIndex: "remark", key: "remark", title: "备注" },
      { dataIndex: "enable", key: "enable", title: "状态", switch: switchFormat},
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
      { label: "新增", event: function () { state.addModal = true }},
      { label: "删除", event: function () {  }},
    ];

    /// 行操作
    const operate = [
      { label: "查看", event: function (record) { alert("查看详情:" + JSON.stringify(record))}},
      { label: "修改", event: function (record) { alert("修改事件:" + JSON.stringify(record))}},
      { label: "删除", event: function (record) { removeHandler(record) }},
    ];

    /// 分页参数
    const pagination = {
      pageNum: 1,
      pageSize: 10,
    }

    /// 外置参数 - 当参数改变时, 重新触发 fetch 函数
    const state = reactive({
      selectedRowKeys: [],
      param: {
        name: "", // 名称
        code: ""  // 标识
      },
      addModal: false
    })

    /// 查询参数
    const searchParam = [
        { key: "name", type: "input", label: "名称"},
        { key: "code", type: "input", label: "描述"},
        { key: "state", type: "select", label: "状态", value: "0",
          hidden: true ,
          options: [
            { text: "全部", value: "0"},
            { text: "开启", value: "1"},
            { text: "关闭", value: "2"}
          ]
        }
    ]

    /// 查询操作
    const search = function(value) {
      
      /// 通过动态修改入参, 触发表格刷新
      state.param = value
    }

    const closeAdd = function(){
      state.addModal = false
    }

    const onSelectChange = selectedRowKeys => {
      state.selectedRowKeys = selectedRowKeys;
    };

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
      onSelectChange
    };
  },
};
</script>