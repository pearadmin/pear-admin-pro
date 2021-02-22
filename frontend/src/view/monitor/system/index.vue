<template>
  <div>
    <page-layout>
      <a-row :gutter="[10, 10]">
        <a-col :span="24">
          <a-card title="概览">
            <a-row>
              <a-col :span="6">
                <div id="container">
                  <a-statistic
                    title="CPU使用率"
                    :value="cpu.used"
                    :precision="2"
                    suffix="%"
                    :value-style="{ color: '#3f8600' }"
                    style="margin-right: 50px"
                  >
                  </a-statistic>
                </div>
              </a-col>
              <a-col :span="6">
                <div id="container">
                  <a-statistic
                    title="系统使用率"
                    :value="cpu.sys"
                    :precision="2"
                    suffix="%"
                    :value-style="{ color: '#3f8600' }"
                    style="margin-right: 50px"
                  >
                  </a-statistic>
                </div>
              </a-col>
              <a-col :span="6">
                <div id="container">
                  <a-statistic
                    title="内存使用率"
                    :value="mem.usage"
                    :precision="2"
                    suffix="%"
                    :value-style="{ color: '#3f8600' }"
                    style="margin-right: 50px"
                  >
                  </a-statistic>
                </div>
              </a-col>
              <a-col :span="6">
                <div id="container">
                  <a-statistic
                    title="JVM使用率"
                    :value="jvm.usage"
                    :precision="2"
                    suffix="%"
                    :value-style="{ color: '#3f8600' }"
                    style="margin-right: 50px"
                  >
                  </a-statistic>
                </div>
              </a-col>
            </a-row>
          </a-card>
        </a-col>
        <a-col :span="12">
          <a-card title="核心"></a-card>
        </a-col>
        <a-col :span="12">
          <a-card title="内存"></a-card>
        </a-col>
        <a-col :span="24">
          <a-card>
            <a-table
              :dataSource="datasource"
              :columns="columns"
              :loading="loading"
            >
              <template #usage="{ text }">
                <a-progress :show-info="true" :percent="text" />
              </template>
            </a-table>
          </a-card>
        </a-col>
      </a-row>
    </page-layout>
  </div>
</template>
<script>
import { server } from "@/api/modules/ops/monitor";
import { ref } from "vue";
export default {
  setup() {
    const datasource = ref();
    const loading = ref(true);
    const cpu = ref({});
    const mem = ref({});
    const jvm = ref({});

    const loadData = async function () {
      var response = await server();
      datasource.value = response.data.disk;
      mem.value = response.data.mem;
      cpu.value = response.data.cpu;
      jvm.value = response.data.jvm;
      loading.value = false;
    };

    loadData();

    return {
      datasource,
      loading,
      columns: [
        {
          title: "磁盘",
          dataIndex: "typeName",
          key: "typeName",
        },
        {
          title: "路径",
          dataIndex: "dirName",
          key: "dirName",
          align: "center",
        },
        {
          title: "剩余",
          dataIndex: "free",
          key: "free",
          align: "center",
        },
        {
          title: "使用",
          dataIndex: "used",
          key: "used",
          align: "center",
        },
        {
          title: "大小",
          dataIndex: "total",
          key: "total",
          align: "center",
        },
        {
          title: "使用率",
          dataIndex: "usage",
          key: "usage",
          slots: { customRender: "usage" },
          align: "center",
        },
      ],
      cpu,
      mem,
      jvm
    };
  },
};
</script>
<style>
</style>