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
            </pro-table>
          </a-card>
        </a-col>
      </a-row>
      <upload :visible="state.visibleUpload" @close="closeUpload"></upload>
    </page-layout>
</template>

<script>
import upload from "./modal/upload";
import { page } from "@/api/module/oss";
import { reactive } from 'vue';

export default {
  components: {
    upload
  },
  setup() {

    /// 文本
    const converFormat = [{label:"本地", value:"local"},{label:"阿里云", value:"aliyun"}];

    /// 预览
    const imageFormat = {width: 34}

    /// 列配置
    const columns = [
      { dataIndex: "name", key: "name", title: "文件名称" },
      { dataIndex: "location", key: "code", title: "存储位置", conver: converFormat},
      { dataIndex: "bucket", key: "bucket", title: "文件仓库" },
      { dataIndex: "suffix", key: "suffix", title: "文件类型"},
      { dataIndex: "size", key: "size", title: "文件大小"},
      { dataIndex: "path", key: "path", title: "预览", image: imageFormat},
      { dataIndex: "id", key: "id", title: "文件标识"},
      { dataIndex: "createTime", key: "createTime", title: "创建时间" },
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
      { label: "上传" , event: function () { state.visibleUpload = true }},
      { label: "删除" , event: function () { }},
    ];

    /// 行操作
    const operate = [
      { label: "查看", event: function (record) { alert("查看详情:" + JSON.stringify(record))}},
      { label: "删除", event: function (record) { alert("删除事件:" + JSON.stringify(record))}},
    ];

    /// 分页参数
    const pagination = {
      pageNum: 1,
      pageSize: 10,
    }

    /// 外置参数 - 当参数改变时, 重新触发 fetch 函数
    const state = reactive({
      param: {},
      selectedRowKeys: [],
      visibleUpload: false,
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

    const closeUpload = function(){
        state.visibleUpload = false;
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

      onSelectChange,
      closeUpload
    };
  },
};
</script>