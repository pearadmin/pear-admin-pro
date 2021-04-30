<template>
    <page-layout>
      <!-- 列表页面 -->
      <a-row :gutter="[10, 10]">
        <!-- 查询条件 -->
        <a-col :span="24">
          <a-card>
            <pro-query
              :searchParam="searchParam"
              @on-search ="search"
            ></pro-query>
          </a-card>
        </a-col>
        <!-- 用户列表 -->
        <a-col :span="24">
          <a-card>
            <pro-table
              :fetch="fetch"
              :columns="columns"
              :toolbar="toolbar"
              :operate="operate"
              :param="state.param"
              :pagination="pagination"
              :row-selection="{ selectedRowKeys: state.selectedRowKeys, onChange: onSelectChange }">
            </pro-table>
          </a-card>
        </a-col>
      </a-row>
      <!-- 新增页面 -->
      <save :visible="state.visibleSave" @close="closeAdd" :record="state.record"></save>
      <!-- 修改页面 -->
      <edit :visible="state.visibleEdit" @close="closeEdit" :record="state.record" ></edit>
      <!-- 分配页面 -->
      <give :visible="state.visibleGive" @close="closeGive" :record="state.record"></give>
    </page-layout>
</template>

<script>
import save from './modal/save';
import edit from './modal/edit';
import give from './modal/give';
import { message } from 'ant-design-vue';
import { page, remove } from "@/api/module/user";
import { reactive } from 'vue';

export default {
  components: {
    save,
    edit,
    give,
  },
  setup() {

    /// 查询用户
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

    /// 批量删除

    /// 工具栏
    const toolbar = [
      { label: "新增", event: function () { state.visibleSave = true }},
      { label: "删除", event: function () { }},
    ];

    /// 行操作
    const operate = [
      { label: "查看", event: function (record) { alert("查看详情:" + JSON.stringify(record))}},
      { label: "修改", event: function (record) { state.visibleEdit = true }},
      { label: "分配", event: function (record) { state.visibleGive = true , state.record = record }},
      { label: "删除", event: function (record) { removeHandler(record) }},
    ];

    /// 文本
    const converFormat = [{label:"男", value:0},{label:"女", value:1}];

    /// 开关
    const switchFormat = { yes: true, no: false };

    /// 头像
    const avatarFormat = { size: 36, shape: "square" };

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
      param: {},
      record: {},
      visibleSave: false,
      visibleEdit: false,
      visibleGive: false,
    })

    /// 查询参数
    const searchParam = [
        { key: "name", type: "input", label: "名称"},
        { key: "code", type: "input", label: "描述"},
    ]

    /// 选择操作
    const onSelectChange = selectedRowKeys => {
      state.selectedRowKeys = selectedRowKeys;
    };

    /// 查询操作
    const search = function(value) {
      state.param = value
    }

    /// 关闭新增
    const closeAdd = function(){
      state.visibleSave = false;
    }

    /// 关闭修改
    const closeEdit = function(){
      state.visibleEdit = false;
    }

    /// 关闭分配
    const closeGive = function(){
      state.visibleGive = false;
    }

    return {
      state, // 共享数据
      fetch, // 用户列表
      toolbar, // 工具栏
      columns, // 列配置
      operate, // 行操作
      pagination, // 分页配置
      onSelectChange, // 行选择

      search, // 查询回调
      searchParam, // 查询参数

      closeAdd,  // 新增关闭
      closeEdit, // 修改关闭
      closeGive,// 分配关闭
    };
  },
};
</script>