<template>
  <div>
    <page-layout>
      <a-row :gutter="[10, 10]">
        <a-col :span="24">
          <a-card>
            <a-form layout="inline" :model="param" @submit="reload">
              <a-form-item label="用户名">
                <a-input
                  v-model:value="param.username"
                  type="text"
                  placeholder="用户名"
                >
                </a-input>
              </a-form-item>
              <a-form-item label="邮箱">
                <a-input
                  v-model:value="param.email"
                  type="text"
                  placeholder="邮箱"
                >
                </a-input>
              </a-form-item>
              <a-form-item>
                <button-container>
                  <a-button type="primary" @click="search"> 查询 </a-button>
                  <a-button html-type="submit"> 默认 </a-button>
                </button-container>
              </a-form-item>
            </a-form>
          </a-card>
        </a-col>
        <a-col :span="24">
          <a-card>
            <button-container class="tool-left">
              <a-button type="primary" @click="add">新增</a-button>
              <a-button @click="batchRemove">删除</a-button>
            </button-container>
            <button-container class="tool-right">
              <a-button @click="search">
                <template #icon><SyncOutlined /></template>
              </a-button>
              <a-button>
                <template #icon><ExportOutlined /></template>
              </a-button>
            </button-container>
            <a-table
              :loading="loading"
              :pagination="pagination"
              :columns="columns"
              :data-source="data"
            >
              <template #locked="{ text }">
                <a-switch @change="change(text)" :checked="text" />
              </template>
              <template #gender="{ gender }">
                <span v-if="gender === 0">男</span>
                <span v-else>女</span>
              </template>
              <template v-slot:action="{ record }">
                <span>
                  <a @click="see(record)"> 查看 </a>
                  <a-divider type="vertical" />
                  <a @click="edit(record)"> 编辑 </a>
                  <a-divider type="vertical" />
                  <a @click="remove(record)"> 删除 </a>
                </span>
              </template>
            </a-table>
          </a-card>
        </a-col>
      </a-row>
    </page-layout>
    <a-modal v-model:visible="visible" title="Basic Modal" @ok="handleOk">
      <p>Some contents...</p>
      <p>Some contents...</p>
      <p>Some contents...</p>
    </a-modal>
  </div>
</template>
<script>
import {
  SyncOutlined,
  ExportOutlined,
  ExclamationCircleOutlined,
} from "@ant-design/icons-vue";
import { page } from "@/api/modules/user";
import { Modal } from "ant-design-vue";
import { createVNode, reactive, ref } from "vue";

export default {
  components: {
    ExportOutlined,
    SyncOutlined,
  },
  setup() {
    const columns = [
      { dataIndex: "nickname", key: "nickname", title: "名称" },
      { dataIndex: "username", key: "username", title: "账号" },
      { dataIndex: "gender", key: "gender", title: "性别", slots: { customRender: "gender" }},
      { dataIndex: "locked", key: "locked", title: "锁定", slots: { customRender: "locked" }},
      { dataIndex: "email", key: "email", title: "邮箱" },
      { dataIndex: "phone", key: "phone", title: "电话" },
      { dataIndex: "createTime", key: "createTime", title: "注册时间" },
      { title: "操作", key: "action", slots: { customRender: "action" }, fixed: "right"}
    ];

    const data = ref();
    const loading = ref(true);
    const visible = ref(false);
    const param = reactive({ current: 1, size: 10, username: null });
    const pagination = reactive({
      total: 0,
      pageSize: 10,
      showSizeChanger: true,
      showTotal: (total) => `共有 ${total} 条`,
    });

    const loadData = async function (param) {
      loading.value = true;
      const response = await page(param);
      data.value = response.data.records;
      pagination.total = response.data.total;
      loading.value = false;
    };

    const search = function () {
      loadData(param);
    };

    search(param);

    const change = (text) => {
      Modal.confirm({
        title: text === true ? "是否锁定该用户?" : "是否解锁该用户?",
        icon: createVNode(ExclamationCircleOutlined),
        onOk() {
          console.log("OK");
        },
        onCancel() {
          console.log("Cancel");
        },
      });
    };

    const remove = (data) => {
      Modal.confirm({
        title: 'Are you sure delete this task?',
        icon: createVNode(ExclamationCircleOutlined),
        content: 'Some descriptions',
        okText: 'Yes',
        okType: 'danger',
        cancelText: 'No',
        onOk() {
          console.log('OK');
        },
        onCancel() {
          console.log('Cancel');
        },
      });
    }

    const batchRemove = (data) => {
      Modal.confirm({
        title: 'Are you sure delete this task?',
        icon: createVNode(ExclamationCircleOutlined),
        content: 'Some descriptions',
        okText: 'Yes',
        okType: 'danger',
        cancelText: 'No',
        onOk() {
          console.log('OK');
        },
        onCancel() {
          console.log('Cancel');
        },
      });
    }

    const see = (data) => {
      visible.value = true;
    }

    const edit = (data) => {
      visible.value = true;
    }

    const add = () => {
      visible.value = true;
    }

    return {
      batchRemove,
      pagination,
      loading,
      columns,
      visible,
      search,
      remove,
      change,
      param,
      edit,
      data,
      add,
      see,
    };
  },
};
</script>
<style lang="less">
.tool-left {
  display: inline-block;
}
.tool-right {
  float: right;
}
</style>