<template>
  <div>
    <page-layout>
      <a-row :gutter="[10, 10]">
        <a-col :span="24">
          <a-card title="概览">
            <a-row>
              <a-col :span="6">
                <div id="container">使用率</div>
              </a-col>
              <a-col :span="6">
                <div id="container">使用率</div>
              </a-col>
              <a-col :span="6">
                <div id="container">使用率</div>
              </a-col>
              <a-col :span="6">
                <div id="container">使用率</div>
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
            <a-table :dataSource="datasource" :columns="columns" />
          </a-card>
        </a-col>
      </a-row>
    </page-layout>
  </div>
</template>
<script>
import { server } from "@/api/modules/ops/monitor";
import { ref } from 'vue';
export default {
  setup() {
    const datasource = ref();

    const loadData = async function(){
        var response = await server();
        datasource.value = response.data.sysFiles;
    }

    loadData();


    return {
      
      datasource,

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
        },
        {
          title: "剩余",
          dataIndex: "free",
          key: "free",
        },
        {
          title: "使用",
          dataIndex: "used",
          key: "used",
        },
        {
          title: "大小",
          dataIndex: "total",
          key: "total",
        },
      ],
    };
  },
};
</script>
<style>
</style>