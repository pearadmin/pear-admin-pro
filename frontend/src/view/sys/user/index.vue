<template>
  <div>
    <page-header
      title="用户列表"
      describe="系 统 用 户 基 础 信 息 展 示"
    ></page-header>
    <page-body>
      <a-card>
        <a-table
          :loading="loading"
          :pagination="pagination"
          :columns="columns"
          :data-source="data"
        >
          <template #gender="{ gender }">
            <span v-if="gender === '0'">男</span>
            <span v-else>女</span>
          </template>
          <template #enable="{ enable }">
            <a-switch :checked="enable"></a-switch>
          </template>
          <template #locked="{ locked }">
            <span v-if="locked === 0">锁定</span>
            <span v-else>正常</span>
          </template>
          <template v-slot:action="{ record }">
            <span>
              <a :href="record.nickname">查看</a>
              <a-divider type="vertical" />
              <a>修改</a>
              <a-divider type="vertical" />
              <a>分配</a>
              <a-divider type="vertical" />
              <a>删除</a>
            </span>
          </template>
        </a-table>
      </a-card>
    </page-body>
  </div>
</template>
<script>
import { SmileOutlined, DownOutlined } from "@ant-design/icons-vue";
import { list, page } from "@/api/modules/sys/user";
import { reactive, ref } from "vue";

export default {
  setup() {
    const columns = [
      { dataIndex: "nickname", key: "nickname", title: "名称" },
      { dataIndex: "username", key: "username", title: "账号" },
      { dataIndex: "gender", key: "gender", title: "性别", slots: { customRender: "gender" }},
      { dataIndex: "enable", key: "enable", title: "启用", slots: { customRender: "enable" }},
      { dataIndex: "locked", key: "locked", title: "锁定", slots: { customRender: "locked" }},
      { dataIndex: "email", key: "email", title: "邮箱" },
      { dataIndex: "phone", key: "phone", title: "电话" },
      { dataIndex: "createTime", key: "createTime", title: "注册时间" },
      { title: "操作", key: "action", slots: { customRender: "action" } },
    ];

    const data = ref();
    const loading = ref(true);
    const param = reactive({ current: 1, size: 10 });
    const pagination = reactive({
      total: 0,
      pageSize: 10,
      showSizeChanger: true,
      showTotal: (total) => `共有 ${total} 条`,
    });

    const query = async function (param) {
      const response = await page(param);
      data.value = response.data.records;
      pagination.total = response.data.total;
      loading.value = false;
    };

    const reload = function () {
      query(param);
    };

    const remove = function () {

    }

    const add = function () {};

    query(param);

    return {
      pagination,
      loading,
      columns,
      reload,
      data,
      add,
    };
  },
};
</script>