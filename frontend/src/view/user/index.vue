<template>
  <div>
    <page-header
      title="用户列表"
      describe="系 统 用 户 基 础 信 息 展 示"
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
                  <a-button type="primary" html-type="submit" @click="search"> 查询 </a-button>
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
          <template #gender="{ gender }">
            <span v-if="gender === '0'">男</span>
            <span v-else>女</span>
          </template>
          <template #locked="{ locked }">
            <span v-if="locked === 0">锁定</span>
            <span v-else>正常</span>
          </template>
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
import { page} from "@/api/modules/user";
import { reactive, ref } from "vue";

export default {
  setup() {
    const columns = [
      { dataIndex: "nickname", key: "nickname", title: "名称" },
      { dataIndex: "username", key: "username", title: "账号" },
      { dataIndex: "gender", key: "gender", title: "性别", slots: { customRender: "gender" }},
      { dataIndex: "locked",key: "locked",title: "锁定",slots: { customRender: "locked" }},
      { dataIndex: "email", key: "email", title: "邮箱" },
      { dataIndex: "phone", key: "phone", title: "电话" },
      { dataIndex: "createTime", key: "createTime", title: "注册时间" },
      { title: "操作", key: "action", slots: { customRender: "action" } },
    ];

    const data = ref();
    const loading = ref(true);
    const param = reactive({ current: 1, size: 10, username:null});
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

    const search = function(){
        loadData(param);
    }

    search(param);

    return {
      pagination,
      loading,
      columns,
      search,
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