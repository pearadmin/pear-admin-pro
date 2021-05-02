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
              <a-col :span="12">
                <h2>Redis</h2>
              </a-col>
              <a-col :span="12">
              </a-col>
            </a-row>
          </a-card>
        </a-col>
        <a-col :span="12">
          <a-card title="数量">
            <div id="size" style="width: 100%"></div>
          </a-card>
        </a-col>
        <a-col :span="12">
          <a-card title="内存">
            <div id="memo" style="width: 100%"></div>
          </a-card>
        </a-col>
        <a-col :span="24">
          <a-card title="缓存列表"> </a-card>
        </a-col>
      </a-row>
    </page-layout>
  </div>
</template>
<script>
import { Chart, registerShape } from "@antv/g2";
import { info, size } from "@/api/module/redis";
import { onMounted, ref, onUnmounted } from "vue";
export default {
  setup() {
    const infoData = ref({});

    /// 加载详情
    const loadInfo = async function () {
      var response = await info();
      infoData.value = response.data;
    };

    const loadSize = async function () {
      var response = await size();
      // 推导
    };

    loadInfo();

    /// 加载报表
    onMounted(() => {
    
      const sizeData = [
        { year: "1991", value: 3 },
        { year: "1992", value: 4 },
        { year: "1993", value: 3.5 },
        { year: "1994", value: 5 },
        { year: "1995", value: 4.9 },
        { year: "1996", value: 6 },
        { year: "1997", value: 7 },
        { year: "1998", value: 9 },
        { year: "1999", value: 13 },
      ];

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

      sizeChart.tooltip({
        showCrosshairs: true,
        shared: true,
      });

      sizeChart.line().position("year*value").label("value");
      sizeChart.point().position("year*value");
      sizeChart.theme({ styleSheet: { brandColor: "rgb(45, 140, 240)" } });
      sizeChart.render();

      const memoData = [
        { year: "1991", value: 3 },
        { year: "1992", value: 4 },
        { year: "1993", value: 3.5 },
        { year: "1994", value: 5 },
        { year: "1995", value: 4.9 },
        { year: "1996", value: 6 },
        { year: "1997", value: 7 },
        { year: "1998", value: 9 },
        { year: "1999", value: 13 },
      ];
      const memoChart = new Chart({
        container: "memo",
        autoFit: true,
        height: 320,
      });

      memoChart.data(memoData);
      memoChart.scale({
        year: {
          range: [0, 1],
        },
        value: {
          min: 0,
          nice: true,
        },
      });

      memoChart.tooltip({
        showCrosshairs: true, // 展示 Tooltip 辅助线
        shared: true,
      });

      memoChart.line().position("year*value").label("value");
      memoChart.point().position("year*value");
      memoChart.theme({ styleSheet: { brandColor: "rgb(45, 140, 240)" } });
      memoChart.render();
    });

    /// 加载列表

    return {
      infoData,
    };
  },
};
</script>
<style>
</style>