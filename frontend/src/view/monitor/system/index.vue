<template>
  <div>
    <page-layout>
      <a-row :gutter="[10,10]">
        <a-col :span="24">
          <a-card title="概览">
            <a-row>
              <a-col :span="6">
                <div>使用率</div>
              </a-col>
              <a-col :span="6">
                <div>使用率</div>
              </a-col>
              <a-col :span="6">
                <div>使用率</div>
              </a-col>
              <a-col :span="6">
                <div>使用率</div>
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
          <a-card title="磁盘">
            <a-table :dataSource="dataSource" :columns="columns" />
          </a-card>
        </a-col>
      </a-row>
    </page-layout>
  </div>
</template>
<script>
export default {
  setup(){
     return {
        dataSource: [
          {
            key: '1',
            name: '胡彦斌',
            age: 32,
            address: '西湖区湖底公园1号',
          },
          {
            key: '2',
            name: '胡彦祖',
            age: 42,
            address: '西湖区湖底公园1号',
          },
        ],

        columns: [
          {
            title: '姓名',
            dataIndex: 'name',
            key: 'name',
          },
          {
            title: '年龄',
            dataIndex: 'age',
            key: 'age',
          },
          {
            title: '住址',
            dataIndex: 'address',
            key: 'address',
          },
        ],
      };
  }
};
</script>
<style>
</style>