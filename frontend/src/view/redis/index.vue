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
                <div id="cmd" style="width: 100%"></div>
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
      registerShape("point", "image", {
        draw(cfg, container) {
          cfg.points = this.parsePoints(cfg.points);
          const coord = this.coordinate;
          container.addShape("line", {
            attrs: {
              x1: cfg.points[0].x,
              y1: cfg.points[0].y,
              x2: cfg.points[0].x,
              y2: coord.start.y,
              stroke: "#ccc",
              lineWidth: 1,
              lineDash: [4, 2],
            },
          });
          return container.addShape("image", {
            attrs: {
              x: cfg.points[0].x - (12 * cfg.size) / 2,
              y: cfg.points[0].y - 12 * cfg.size,
              width: 12 * cfg.size,
              height: 12 * cfg.size,
              img: cfg.shape[1],
            },
          });
        },
      });
      const data = [
        { name: "Internet", value: 26 },
        { name: "Chrome", value: 40 },
        { name: "Firefox", value: 30 },
        { name: "Safari", value: 24 },
        { name: "Opera", value: 15 },
        { name: "Undetectable", value: 8 },
      ];
      const imageMap = {
        Internet: "https://gw.alipayobjects.com/zos/rmsportal/eOYRaLPOmkieVvjyjTzM.png",
        Chrome: "https://gw.alipayobjects.com/zos/rmsportal/dWJWRLWfpOEbwCyxmZwu.png",
        Firefox: "https://gw.alipayobjects.com/zos/rmsportal/ZEPeDluKmAoTioCABBTc.png",
        Safari: "https://gw.alipayobjects.com/zos/rmsportal/eZYhlLzqWLAYwOHQAXmc.png",
        Opera: "https://gw.alipayobjects.com/zos/rmsportal/vXiGOWCGZNKuVVpVYQAw.png",
        Undetectable: "https://gw.alipayobjects.com/zos/rmsportal/NjApYXminrnhBgOXyuaK.png",
      };
      const cmdChart = new Chart({
        container: "cmd",
        autoFit: true,
        height: 320,
      });
      cmdChart.data(data);
      cmdChart.scale("value", {
        nice: false,
        max: 60,
        min: 0,
      });
      cmdChart.legend(false);
      cmdChart.axis("value", false);
      cmdChart.tooltip({
        showMarkers: false,
      });
      cmdChart
        .point()
        .position("name*value")
        .size("value")
        .color("name")
        .shape("name", (name) => {
          return ["image", imageMap[name]];
        })
        .label("value", {
          offset: -20,
          style: {
            fontSize: 16, // 文本大小
          },
        });

      cmdChart.render();

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