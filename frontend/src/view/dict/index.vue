<template>
  <div>
    <page-header
      title="数 据 字 典"
      describe="用 户 Online 列 表，用 于 系 统 在 线 用 户 监 控."
    ></page-header>
    <page-layout>
      <a-row :gutter="[10, 10]">
        <a-col :span="12">
          <a-card>
            <div class="table-tool">
              <div class="table-tool-prev">
                <a-button type="primary"> 新增 </a-button>
              </div>
              <div class="table-tool-next"></div>
            </div>
            <a-table :dataSource="dataSource" :columns="columns">
              <template v-slot:action="{ record }">
                <span>
                  <a>修改</a>
                  <a-divider type="vertical" />
                  <a @click="remove(record)">删除</a>
                </span>
              </template>
            </a-table>
          </a-card>
        </a-col>
        <a-col :span="12">
          <a-card>
            <div class="table-tool">
              <div class="table-tool-prev">
                <a-button type="primary"> 新增 </a-button>
              </div>
              <div class="table-tool-next"></div>
            </div>
            <a-table :dataSource="dataSource" :columns="columnsData">
              <template v-slot:action="{ record }">
                <span>
                  <a>修改</a>
                  <a-divider type="vertical" />
                  <a @click="remove(record)">删除</a>
                </span>
              </template>
            </a-table>
          </a-card>
        </a-col>
      </a-row>
    </page-layout>
  </div>
</template>

<script>
export default {
  name: "dict-index",
  setup() {
    const dataSource = [
      {
        key: "1",
        name: "胡彦斌",
        age: 32,
        address: "西湖区湖底公园1号",
      },
      {
        key: "2",
        name: "胡彦祖",
        age: 42,
        address: "西湖区湖底公园1号",
      },
      {
        key: "3",
        name: "胡彦祖",
        age: 42,
        address: "西湖区湖底公园1号",
      },
      {
        key: "4",
        name: "胡彦祖",
        age: 42,
        address: "西湖区湖底公园1号",
      },
      {
        key: "5",
        name: "胡彦祖",
        age: 42,
        address: "西湖区湖底公园1号",
      },
      {
        key: "6",
        name: "胡彦祖",
        age: 42,
        address: "西湖区湖底公园1号",
      },
    ];

    const columns = [
      {
        title: "名称",
        dataIndex: "name",
        key: "name",
      },
      {
        title: "类型",
        dataIndex: "age",
        key: "age",
      },
      {
        title: "描述",
        dataIndex: "address",
        key: "address",
      },
      {
        dataIndex: "action",
        key: "action",
        title: "操作",
        slots: { customRender: "action" },
        fixed: "right",
      },
    ];

    const columnsData = [
      {
        title: "名称",
        dataIndex: "name",
        key: "name",
      },
      {
        title: "值",
        dataIndex: "age",
        key: "age",
      },
      {
        title: "描述",
        dataIndex: "address",
        key: "address",
      },
      {
        dataIndex: "action",
        key: "action",
        title: "操作",
        slots: { customRender: "action" },
        fixed: "right",
      },
    ];

    return {
      columnsData,
      dataSource,
      columns,
    };
  },
};
</script>

<style lang="scss">
.table-tool {
  .table-tool-prev {
    > * {
      margin: 3px;
      margin-bottom: 8px;
    }
    float: left;
  }
  .table-tool-next {
    > * {
      margin: 3px;
      margin-bottom: 8px;
    }
    float: right;
  }
}
</style>