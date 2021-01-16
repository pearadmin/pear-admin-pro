<template>
  <div>
    <page-header title="权限列表" describe="系 统 权 限 基 础 信 息 展 示"></page-header>
    <page-body>
      <a-card>
        <div class="table-tool">
          <a-button class="editable-add-btn" type="primary">
            新增
          </a-button>
          <a-button class="editable-add-btn">
            删除
          </a-button>
            <a-button class="editable-add-btn">
            刷新
          </a-button>
        </div>
        <div class="table-search">
          <a-button class="editable-add-btn" type="primary">查询</a-button>
        </div>
        <a-table pagination="true" :columns="columns" :data-source="data">
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
import { list } from "@/api/modules/sys/user";
import { reactive, ref } from "vue";

export default {
  setup() {
    const columns = [
      { dataIndex: "nickname", key: "nickname", title: "名称" },
      { dataIndex: "gender", key: "gender", title: "性别", slots: { customRender: "gender" }},
      { dataIndex: "enable", key: "enable", title: "启用", slots: { customRender: "enable" }},
      { dataIndex: "locked", key: "locked", title: "锁定", slots: { customRender: "locked" }},
      { dataIndex: "email", key: "email", title: "邮箱" },
      { dataIndex: "phone", key: "phone", title: "电话" },
      { dataIndex: "createTime", key: "createTime", title: "注册时间" },
      { title: "操作", key: "action", slots: { customRender: "action" } },
    ];

    const data = ref();

    const query = async function () {
      const response = await list();
      data.value = response.data;
    };

    const add = function () {};

    query();

    return {
      columns,
      data,
      add,
    };
  },
};
</script>
<style lang="less">
.table-tool {
  margin-bottom: 10px;
  float: left;
  .ant-btn {
    margin-right: 8px;
  }
}
.table-search {
  margin-bottom: 6px;
  float: right;
  .ant-btn {
    margin-left: 8px;
  }
}
</style>