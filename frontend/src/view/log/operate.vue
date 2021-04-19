<template>
  <div>
    <page-layout>
      <a-row :gutter="[10, 10]">
        <a-col :span="24">
          <a-card>
            <a-form layout="inline" :model="param" @submit="reload">
              <a-form-item label="用户">
                <a-input
                  v-model:value="param.username"
                  type="text"
                  placeholder="用户"
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
              <a-button type="primary" @click="openAddModal">清空</a-button>
              <a-button @click="batchRemove">备份</a-button>
            </button-container>
            <button-container class="tool-right">
              <a-button @click="search">
                <template #icon><SyncOutlined /></template>
              </a-button>
              <a-button>
                <template #icon><AppstoreOutlined /></template>
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
              <template v-slot:action="{ record }">
                <span>
                  <a @click="openInfoModal(record)"> 查看 </a>
                  <a-divider type="vertical" />
                  <a @click="remove(record)"> 删除 </a>
                </span>
              </template>
            </a-table>
          </a-card>
        </a-col>
      </a-row>
    </page-layout>
    <a-modal v-model:visible="addModal" title="新增用户" @ok="handleOk">
      <p>Some contents...</p>
      <p>Some contents...</p>
      <p>Some contents...</p>
    </a-modal>
    <a-modal v-model:visible="editModal" title="编辑信息" @ok="handleOk">
      <p>Some contents...</p>
      <p>Some contents...</p>
      <p>Some contents...</p>
    </a-modal>
    <a-modal v-model:visible="infoModal" title="查看详情" @ok="handleOk">
      <p>{{infoParam.nickname}}</p>
      <p>{{infoParam.username}}</p>
    </a-modal>
  </div>
</template>
<script>
import { AppstoreOutlined, SyncOutlined, ExportOutlined, ExclamationCircleOutlined} from "@ant-design/icons-vue";
import { operate } from "@/api/modules/log";
import { Modal } from "ant-design-vue";
import { createVNode, reactive, ref } from "vue";

export default {
  components: {
    AppstoreOutlined,
    ExportOutlined,
    SyncOutlined,
  },
  setup() {
    const columns = [
      { dataIndex: "title", key: "title", title: "标题" },
      { dataIndex: "describe", key: "describe", title: "描述" },
      { dataIndex: "action", key: "action", title: "动作"},
      { dataIndex: "type", key: "type", title: "方式"},
      { dataIndex: "browser", key: "browser", title: "浏览器" },
      { dataIndex: "system", key: "system", title: "系统" },
      { dataIndex: "address", key: "address", title: "操作地" },
      { dataIndex: "operator", key: "operator", title: "操作人" }, 
      { dataIndex: "createTime", key: "createTime", title: "操作时间" },
      { dataIndex: "action", key: "action",title: "操作", slots: { customRender: "action" }, fixed: "right"}
    ];

    const data = ref();
    const loading = ref(true);
    
    const addModal = ref(false);
    const editModal = ref(false);
    const infoModal = ref(false);
  
    const infoParam = ref({});
    const addParam = ref({});

    const param = reactive({ current: 1, size: 10, username: null });
    const pagination = reactive({
      total: 0,
      pageSize: 10,
      showSizeChanger: true,
      showTotal: (total) => `共有 ${total} 条`,
    });

    const loadData = async function (param) {
      loading.value = true;
      const response = await operate(param);
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
        title: '是否确实要删除此用户？',
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
        title: '是否确实要删除此用户？',
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

    const openInfoModal = (data) => {
      infoModal.value = true;
      infoParam.value = data;
    }

    const openEditModal = (data) => {
      editModal.value = true;
    }

    const openAddModal = () => {
      addModal.value = true;
    }

    return {
      batchRemove,
      pagination,
      /** Modal 状态 */
      editModal,
      infoModal,
      addModal,
      /** 打开 Modal */
      openInfoModal,
      openEditModal,
      openAddModal,

      infoParam,

      /** 其他 */
      loading,
      columns,
      search,
      remove,
      change,
      param,
      data
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