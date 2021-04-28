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
import { page, remove } from "@/api/module/config";
import { reactive } from 'vue';

export default {
  components: {
    add
  },
  setup() {

    /// 列配置
    const columns = [
      { dataIndex: "name", key: "name", title: "名称" },
      { dataIndex: "key", key: "key", title: "键" },
      { dataIndex: "value", key: "value", title: "值"},
      { dataIndex: "remark", key: "remark", title: "描述" },
      { dataIndex: "createTime", key: "createTime", title: "创建时间" },
      { dataIndex: "updateTime", key: "updateTime", title: "修改时间" },
    ];

    /// 数据来源
    const fetch = async (param) => {
      var response = await page(param);
      return {
        total: response.data.total,
        data: response.data.record,
      };
    };

    /// 删除配置
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
      { label: "新增", event: function (keys) { state.addModal = true }},
      { label: "删除", event: function (keys) { alert("批量删除:" + JSON.stringify(keys))}},
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
      param: {
        name: "", // 名称
        code: ""  // 标识
      },
      addModal: false,
      editModal: false
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
        state.addModal = false;
    }

    const closeEdit = function(){
        state.editModal = false;
    }

    return {
      state, 
      fetch, 
      toolbar,
      columns, 
      operate, 
      pagination,

      search,
      searchParam, 

      closeAdd,
      closeEdit
    };
  },
};
</script>  