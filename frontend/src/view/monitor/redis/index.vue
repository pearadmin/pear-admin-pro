<template>
  <page-layout>
    <div>
      <a-row :gutter="[10, 10]">
        <a-col :span="24">
          <a-card></a-card>
        </a-col>
        <a-col :span="12">
          <a-card></a-card>
        </a-col>
        <a-col :span="12">
          <a-card></a-card>
        </a-col>
        <a-col :span="24">
          <a-card :title="日志"> </a-card>
        </a-col>
      </a-row>
    </div>
  </page-layout>
</template>
<script>
import { log,info } from "@/api/modules/ops/redis";
import { ref } from 'vue';
export default {
  setup() {

    const loading = ref(false)

    const loadData = async function () {
      var response = await log();
      var respons = await info();
      loading.value = false;
    };

    loadData();
  },
};
</script>
<style>
</style>