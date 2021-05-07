<template>
  <div>
    <page-header
      title="缓 存 监 控"
      describe="提 供 Redis 的 可 视 化 监 控 与 键 值 操 作."
    ></page-header>
    <page-layout>
      <a-row :gutter="[10, 10]">
        <a-col :span="24">
          <a-card>
            <a-row>
              <a-col :span="6">
                <h2>Redis</h2>
              </a-col>
              <a-col :span="12"></a-col>
            </a-row>
          </a-card>
        </a-col>
        <a-col :span="24">
          <a-card title="数量监控">
            <div id="size" style="width: 100%"></div>
          </a-card>
        </a-col>
      </a-row>
    </page-layout>
  </div>
</template>
<script>
import { Chart } from "@antv/g2";
import { info, size } from "@/api/module/redis";
import { computed, onMounted, ref } from "vue";
import { useStore } from 'vuex';
export default {
  setup() {

    const infoData = ref({});

    const sizeData = [{date: 'Start',value: 0}];

    /// 加载详情
    const loadInfo = async function () {
      var response = await info();
      infoData.value = response.data;
    };

    loadInfo();

    /// 加载报表
    onMounted(async () =>  {

      const store = useStore()
      const color = computed(() => store.getters.color);

      const sizeChart = new Chart({
        container: "size",
        autoFit: true,
        height: 320,
      });

      sizeChart.data(sizeData);
      sizeChart.scale({
        year: {
          range: [0, 1],
        },
        value: {
          min: 0,
          nice: true,
        },
      });

      sizeChart.tooltip({ showCrosshairs: true, shared: true });

      sizeChart.line().position("date*value").label("value");
      sizeChart.point().position("date*value");
      sizeChart.theme({ styleSheet: { brandColor: color.value } });
      sizeChart.render();

      setInterval(()=>{
        size().then((response)=>{
          if(sizeData.length >= 8) {sizeData.shift();}
          sizeData.push({ date: new Date(), value: response.data });
          sizeChart.changeData(sizeData);
        });
      }, 3000);
      
    });

    return {
      infoData,
    };
  },
};
</script>
<style>
</style>