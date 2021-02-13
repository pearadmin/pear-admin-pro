<template>
  <div>
    <page-header
      title="角色列表"
      describe="系 统 角 色 基 础 信 息 展 示"
    ></page-header>
    <page-layout>
      <a-card>
        <a-row>
          <a-col span="12">
            <div class="tool-bar">
              <a-button type="primary" @click="add">新增</a-button>
              <a-button @click="batchRemove">删除</a-button>
              <a-button @click="reload">刷新</a-button>
            </div>
          </a-col>
          <a-col span="12">
            <div class="query-bar">
              <a-form layout="inline" :model="param" @submit="reload">
                <a-form-item>
                  <a-input
                    v-model:value="param.username"
                    type="text"
                    placeholder="用户名">
                  </a-input>
                </a-form-item>
                <a-form-item>
                  <a-button type="primary" html-type="submit"> 查询 </a-button>
                </a-form-item>
              </a-form>
            </div>
          </a-col>
        </a-row>
        <a-table
          :loading="loading"
          :pagination="pagination"
          :columns="columns"
          :data-source="data">
         
          <template v-slot:action="{ record }">
            <span>
              <a @click="info(record)">查看</a>
              <a-divider type="vertical" />
              <a>修改</a>
              <a-divider type="vertical" />
              <a>分配</a>
              <a-divider type="vertical" />
              <a @click="remove">删除</a>
            </span>
          </template>
        </a-table>
      </a-card>
    </page-layout>
  </div>
</template>
<script>
import { page} from "@/api/modules/role";
import { reactive, ref } from "vue";

export default {
  setup() {
    const columns = [
      { dataIndex: "name", key: "name", title: "名称" },
      { dataIndex: "code", key: "code", title: "账号" },
      { dataIndex: "enable", key: "enable", title: "状态"},
      { dataIndex: "sort",key: "sort",title: "锁定"},
      { title: "操作", key: "action", slots: { customRender: "action" } },
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

    loadData(param);

    return {
      pagination,
      loading,
      columns,
      param,
      data
    };
  },
};
</script>
<style lang="less">
.tool-bar {
  position: relative;
  margin-bottom: 10px;
  .ant-btn {
    margin-right: 6px;
  }
}
.query-bar {
  position: absolute;
  right: 0px;
  margin-bottom: 10px;
  .ant-btn {
    margin-left: 4px;
  }
}
</style>