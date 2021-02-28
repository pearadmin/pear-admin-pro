<template>
  <div>
    <page-layout>
      <a-row :gutter="[10, 10]">
        <a-col :span="24">
          <a-card>
            <a-form layout="inline" :model="param" @submit="reload">
              <a-form-item label="权限名称">
                <a-input
                  v-model:value="param.name"
                  type="text"
                  placeholder="权限标识"
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
              <a-button @click="reload">刷新</a-button>
              <a-button>导出</a-button>
            </button-container>
            <a-table
              :loading="loading"
              :pagination="false"
              :columns="columns"
              :data-source="data"
            >
              <template #enable="{ enable }">
                <a-switch :checked="enable" />
              </template>
              <template v-slot:action="{ record }">
                <span>
                  <a @click="info(record)">查看</a>
                  <a-divider type="vertical" />
                  <a>修改</a>
                  <a-divider type="vertical" />
                  <a @click="remove">删除</a>
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
import { page } from "@/api/modules/power";
import { reactive, ref } from "vue";

export default {
  setup() {
    const columns = [
      { dataIndex: "title", key: "title", title: "标题" },
      { dataIndex: "name", key: "name", title: "组件" },
      { dataIndex: "path", key: "path", title: "路径" },
      {
        dataIndex: "enable",
        key: "enable",
        title: "状态",
        slots: { customRender: "enable" },
      },
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
    const param = reactive({ current: 1, size: 10 });

    const loadData = async function (param) {
      loading.value = true;
      const response = await page(param);
      data.value = response.data;
      loading.value = false;
    };

    loadData(param);

    return {
      loading,
      columns,
      param,
      data,
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