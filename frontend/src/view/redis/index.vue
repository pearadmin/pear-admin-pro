<template>
  <page-header
    title="缓 存 监 控"
    describe="提 供 Redis 的 可 视 化 监 控 与 键 值 操 作."
  ></page-header>
  <page-layout>
    <div>
      <a-row :gutter="[10, 10]">
        <a-col :span="24">
          <a-card> </a-card>
        </a-col>
        <a-col :span="12">
          <a-card title="数量"></a-card>
        </a-col>
        <a-col :span="12">
          <a-card title="内存"></a-card>
        </a-col>
        <a-col :span="24">
          <a-card title="运行日志"> </a-card>
        </a-col>
      </a-row>
    </div>
  </page-layout>
</template>
<script>
import { info } from "@/api/modules/ops/redis";
import { ref } from "vue";
export default {
  setup() {
    const loading = ref(false);

    const loadData = async function () {
      var response = await info();
      loading.value = false;
    };

    loadData();
  },
};
</script>
<style>
</style>