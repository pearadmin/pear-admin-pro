<template>
  <div>
    <page-layout>
      <a-row :gutter="[10, 10]">
        <a-col :span="6">
          <a-card>
            <a-row>
              <a-col :span="12" style="padding-top:5px;">
          
              </a-col>
              <a-col :span="12">
                <a-progress type="circle" strokeColor="#36b368" :width="80" :percent="cpu.used" class="float-right"/>
              </a-col>
            </a-row>
          </a-card>
        </a-col>
        <a-col :span="6">
          <a-card>
            <a-row>
              <a-col :span="12"  style="padding-top:5px;">
         
              </a-col>
              <a-col :span="12">
                <a-progress type="circle" strokeColor="#36b368" :width="80" :percent="cpu.sys"  class="float-right"/>
              </a-col>
            </a-row>
          </a-card>
        </a-col>
        <a-col :span="6">
          <a-card>
            <a-row>
              <a-col :span="12"  style="padding-top:5px;">
           
              </a-col>
              <a-col :span="12">
                <a-progress type="circle" strokeColor="#36b368" :width="80" :percent="mem.usage"  class="float-right"/>
              </a-col>
            </a-row>
          </a-card>
        </a-col>
        <a-col :span="6">
          <a-card>
            <a-row>
              <a-col :span="12"  style="padding-top:5px;">
           
              </a-col>
              <a-col :span="12">
                <a-progress type="circle" strokeColor="#36b368" :width="80" :percent="jvm.usage"  class="float-right"/>
              </a-col>
            </a-row>
          </a-card>
        </a-col>
        <a-col :span="12">
          <a-card title="CPU">
            <a-row :gutter="[20, 20]">
              <a-col :span="12">
                <div class="custom-card">
                  <a-statistic
                    title="核心数"
                    :value="cpu.cpuNum"
                    :precision="2"
                    suffix="个"
                    :value-style="{ color: '#3f8600' }"
                    style="margin-right: 50px"
                  >
                  </a-statistic>
                </div>
              </a-col>
              <a-col :span="12">
                <div class="custom-card">
                  <a-statistic
                    title="空闲率"
                    :value="cpu.free"
                    :precision="2"
                    suffix="%"
                    :value-style="{ color: '#3f8600' }"
                    style="margin-right: 50px"
                  >
                  </a-statistic>
                </div>
              </a-col>
              <a-col :span="12">
                <div class="custom-card">
                  <a-statistic
                    title="等待率"
                    :value="cpu.wait"
                    :precision="2"
                    suffix="%"
                    :value-style="{ color: '#3f8600' }"
                    style="margin-right: 50px"
                  >
                  </a-statistic>
                </div>
              </a-col>
              <a-col :span="12">
                <div class="custom-card">
                  <a-statistic
                    title="使用率"
                    :value="cpu.used"
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
          <a-card title="内存">
            <a-row :gutter="[20, 20]">
              <a-col :span="12">
                <div class="custom-card">
                  <a-statistic
                    title="大小"
                    :value="mem.total"
                    :precision="2"
                    suffix="GB"
                    :value-style="{ color: '#3f8600' }"
                    style="margin-right: 50px"
                  >
                  </a-statistic>
                </div>
              </a-col>
              <a-col :span="12">
                <div class="custom-card">
                  <a-statistic
                    title="空闲"
                    :value="mem.free"
                    :precision="2"
                    suffix="GB"
                    :value-style="{ color: '#3f8600' }"
                    style="margin-right: 50px"
                  >
                  </a-statistic>
                </div>
              </a-col>
              <a-col :span="12">
                <div class="custom-card">
                  <a-statistic
                    title="已使用"
                    :value="mem.used"
                    :precision="2"
                    suffix="GB"
                    :value-style="{ color: '#3f8600' }"
                    style="margin-right: 50px"
                  >
                  </a-statistic>
                </div>
              </a-col>
              <a-col :span="12">
                <div class="custom-card">
                  <a-statistic
                    title="使用率"
                    :value="mem.usage"
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
      jvm,
    };
  },
};
</script>
<style>
.custom-card {
  background-color: whitesmoke;
  padding: 20px;
  border-radius: 4px;
}
.float-right{
  float:right;
}
.float-left{
  float:left;
}
</style>