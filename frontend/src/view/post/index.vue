<template>
  <div>
    <page-layout>
      <a-row :gutter="[10, 10]">
        <a-col :span="24">
          <a-card>
            <a-form layout="inline" :model="param" @submit="reload">
              <a-form-item label="角色名">
                <a-input
                  v-model:value="param.name"
                  type="text"
                  placeholder="角色名"
                >
                </a-input>
              </a-form-item>
              <a-form-item label="标识">
                <a-input
                  v-model:value="param.code"
                  type="text"
                  placeholder="标识"
                >
                </a-input>
              </a-form-item>
              <a-form-item>
                <button-container>
                  <a-button type="primary" html-type="submit"> 查询 </a-button>
                  <a-button> 默认 </a-button>
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
              <a-button>导出</a-button>
            </button-container>
            <a-table
              :loading="loading"
              :pagination="pagination"
              :columns="columns"
              :data-source="data"
            >
              <template #enable="{ text }">
                <a-switch :checked="text" />
              </template>
              <template v-slot:action="{ record }">
                <span>
                  <a @click="info(record)">查看</a>
                  <a-divider type="vertical" />
                  <a>修改</a>
                  <a-divider type="vertical" />
                  <a @click="showDrawer">分配</a>
                  <a-divider type="vertical" />
                  <a @click="remove">删除</a>
                </span>
              </template>
            </a-table>
          </a-card>
        </a-col>
      </a-row>
    </page-layout>
    <a-drawer
      title="权限分配"
      placement="right"
      :closable="false"
      v-model:visible="visible"
      :after-visible-change="afterVisibleChange"
      width="350"
    >
      <p>Some contents...</p>
      <p>Some contents...</p>
      <p>Some contents...</p>
    </a-drawer>
  </div>
</template>
<script>
import { SyncOutlined } from "@ant-design/icons-vue";
import { page } from "@/api/modules/role";
import { reactive, ref } from "vue";

export default {
  components: {
    SyncOutlined,
  },
  setup() {

    const visible = ref(false);

    const afterVisibleChange = (bool) => {
      console.log('visible', bool);
    };

    const showDrawer = () => {
      visible.value = true;
    };


    const columns = [
      { dataIndex: "name", key: "name", title: "名称" },
      { dataIndex: "code", key: "code", title: "账号" },
      {
        dataIndex: "enable",
        key: "enable",
        title: "状态",
        slots: { customRender: "enable" },
      },
      { dataIndex: "remark", key: "remark", title: "描述" },
      { dataIndex: "sort", key: "sort", title: "排序" },
      {
        title: "操作",
        key: "action",
        slots: { customRender: "action" },
        fixed: "right",
      },
    ];

    const data = ref();
    const loading = ref(true);
    const param = reactive({ current: 1, size: 10, username: "" });
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

    loadData(param);

    return {
      pagination,
      loading,
      columns,
      search,
      param,
      data,

      visible,
      afterVisibleChange,
      showDrawer,
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