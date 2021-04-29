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
    </page-layout>
</template>

<script>
import { tree } from "@/api/module/power";
import { reactive } from 'vue';

export default {
  setup() {

    const switchFormat = { yes: true, no: false, event: function(value,record){
      record.enable = !record.enable;
      return value;
    }}

    const converFormat = [
      {label:'目录',value:'0'},
      {label:'菜单',value:'1'},
      {label:'按钮',value:'2'}
    ]

    /// 列配置
    const columns = [
      { dataIndex: "title", key: "title", title: "权限名" },
      { dataIndex: "name", key: "name", title: "组件" },
      { dataIndex: "type", key: "type", title: "类型", conver: converFormat},
      { dataIndex: "path", key: "path", title: "路径" },
      { dataIndex: "i18n", key: "i18n", title: "国际化"},
      { dataIndex: "enable", key: "enable", title: "状态", switch: switchFormat},
      { dataIndex: "sort", key: "sort", title: "排序" },
    ];

    /// 数据来源 [模拟]
    const fetch = async (param) => {
      var response = await tree(param);
      return {
        data: response.data,
      };
    };

    /// 工具栏
    const toolbar = [
      { label: "新增", event: function (keys) { alert("新增操作:" + JSON.stringify(keys))}},
      { label: "删除", event: function (keys) { alert("批量删除:" + JSON.stringify(keys))}},
    ];

    /// 行操作
    const operate = [
      { label: "查看", event: function (record) { alert("查看详情:" + JSON.stringify(record))}},
      { label: "修改", event: function (record) { alert("修改事件:" + JSON.stringify(record))}},
      { label: "删除", event: function (record) { alert("删除事件:" + JSON.stringify(record))}},
    ];

    /// 分页参数
    const pagination = false;
    
    /// 外置参数 - 当参数改变时, 重新触发 fetch 函数
    const state = reactive({
      selectedRowKeys: [],
      param: {
        name: "", // 名称
        code: ""  // 标识
      }
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

      onSelectChange
    };
  },
};
</script>