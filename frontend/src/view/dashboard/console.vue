<template>
  <div id="home">
    <page-layout>
      <a-row :gutter="[10, 10]">
        <a-col :xs="24" :sm="12" :md="12" :lg="6" :xl="6">
          <a-card>
            <p-count
              startVal="5500"
              endVal="6334.32"
              decimals="2"
              speed="300"
            />
            <a-progress
              :show-info="false"
              style="margin-top: 16px"
              :percent="30"
              size="small"
            />
            <a-divider />
            <div class="card-left">访问量</div>
            <div class="card-right">68 万</div>
          </a-card>
        </a-col>
        <a-col :xs="24" :sm="12" :md="12" :lg="6" :xl="6">
          <a-card>
            <p-count
              startVal="4500"
              endVal="5303.10"
              decimals="2"
              speed="300"
            />
            <a-progress
              style="margin-top: 16px"
              :percent="50"
              size="small"
              status="active"
              :show-info="false"
            />
            <a-divider />
            <div class="card-left">销售量</div>
            <div class="card-right">23 万</div>
          </a-card>
        </a-col>
        <a-col :xs="24" :sm="12" :md="12" :lg="6" :xl="6">
          <a-card>
            <p-count
              startVal="6500"
              endVal="7600.00"
              decimals="2"
              speed="300"
            />
            <a-progress
              :show-info="false"
              style="margin-top: 16px"
              :percent="30"
              size="small"
            />
            <a-divider />
            <div class="card-left">转化率</div>
            <div class="card-right">60 %</div>
          </a-card>
        </a-col>
        <a-col :xs="24" :sm="12" :md="12" :lg="6" :xl="6">
          <a-card>
            <p-count
              startVal="4000"
              endVal="4500.00"
              decimals="2"
              speed="300"
            />
            <a-progress
              style="margin-top: 16px"
              :percent="50"
              size="small"
              status="active"
              :show-info="false"
            />
            <a-divider />
            <div class="card-left">用户量</div>
            <div class="card-right">578 人</div>
          </a-card>
        </a-col>
        <a-col :xs="12" :sm="6" :md="6" :lg="3" :xl="3">
          <card-base color="rgb(105, 192, 255)" icon="BuildFilled" title="用户">
          </card-base>
        </a-col>
        <a-col :xs="12" :sm="6" :md="6" :lg="3" :xl="3">
          <card-base color="rgb(149, 222, 100)" icon="CloudFilled" title="首页">
          </card-base>
        </a-col>
        <a-col :xs="12" :sm="6" :md="6" :lg="3" :xl="3">
          <card-base
            color="rgb(255, 156, 110)"
            icon="CustomerServiceFilled"
            title="访问"
          >
          </card-base>
        </a-col>
        <a-col :xs="12" :sm="6" :md="6" :lg="3" :xl="3">
          <card-base color="rgb(179, 127, 235)" icon="MailFilled" title="邮件">
          </card-base>
        </a-col>
        <a-col :xs="12" :sm="6" :md="6" :lg="3" :xl="3">
          <card-base
            color="rgb(255, 214, 102)"
            icon="NotificationFilled"
            title="消息"
          >
          </card-base>
        </a-col>
        <a-col :xs="12" :sm="6" :md="6" :lg="3" :xl="3">
          <card-base color="rgb(92, 219, 211)" icon="SignalFilled" title="统计">
          </card-base>
        </a-col>
        <a-col :xs="12" :sm="6" :md="6" :lg="3" :xl="3">
          <card-base
            color="rgb(255, 133, 192)"
            icon="TrophyFilled"
            title="排名"
          >
          </card-base>
        </a-col>
        <a-col :xs="12" :sm="6" :md="6" :lg="3" :xl="3">
          <card-base color="rgb(255, 192, 105)" icon="BellFilled" title="通知">
          </card-base>
        </a-col>
        <a-col :span="24">
          <a-card>
            <a-tabs>
              <a-tab-pane key="1" tab="销量">
                <a-row>
                  <a-col :xs="24" :sm="24" :md="24" :lg="16" :xl="16">
                    <div id="container" style="width: 100%"></div>
                  </a-col>
                  <a-col
                    :xs="24"
                    :sm="24"
                    :md="24"
                    :lg="{ span: 7, offset: 1 }"
                    :xl="{ span: 7, offset: 1 }"
                  >
                    <a-row style="margin: 22px">
                      <a-col span="2"><div>1</div></a-col>
                      <a-col span="18">高新区1号店</a-col>
                      <a-col span="4">3223.14</a-col>
                    </a-row>
                    <a-row style="margin: 22px">
                      <a-col span="2"><span>2</span></a-col>
                      <a-col span="18">高新区2号店</a-col>
                      <a-col span="4">2433.42</a-col>
                    </a-row>
                    <a-row style="margin: 22px">
                      <a-col span="2"><span>3</span></a-col>
                      <a-col span="18">高新区3号店</a-col>
                      <a-col span="4">6392.15</a-col>
                    </a-row>
                    <a-row style="margin: 22px">
                      <a-col span="2"><span>4</span></a-col>
                      <a-col span="18">高新区4号店</a-col>
                      <a-col span="4">1465.75</a-col>
                    </a-row>
                    <a-row style="margin: 22px">
                      <a-col span="2"><span>5</span></a-col>
                      <a-col span="18">高新区5号店</a-col>
                      <a-col span="4">4324.35</a-col>
                    </a-row>
                    <a-row style="margin: 22px">
                      <a-col span="2"><span>6</span></a-col>
                      <a-col span="18">高新区6号店</a-col>
                      <a-col span="4">3233.22</a-col>
                    </a-row>
                    <a-row style="margin: 22px">
                      <a-col span="2"><span>7</span></a-col>
                      <a-col span="18">高新区7号店</a-col>
                      <a-col span="4">6423.32</a-col>
                    </a-row>
                  </a-col>
                </a-row>
              </a-tab-pane>
              <a-tab-pane key="2" tab="排行" force-render> </a-tab-pane>
            </a-tabs>
          </a-card>
        </a-col>
      </a-row>
    </page-layout>
  </div>
</template>
<script>
import { Chart } from "@antv/g2";
import { onMounted, ref, onUnmounted } from "vue";
export default {
  setup() {
    const chartRef = ref(null);
    onMounted(() => {
      const datass = [
        { year: "1951 年", sales: 38 },
        { year: "1952 年", sales: 52 },
        { year: "1956 年", sales: 80 },
        { year: "1957 年", sales: 135 },
        { year: "1958 年", sales: 80 },
        { year: "1959 年", sales: 70 },
        { year: "1960 年", sales: 60 },
        { year: "1961 年", sales: 55 },
        { year: "1962 年", sales: 38 },
      ];

      const chart = new Chart({
        container: "container",
        autoFit: true,
        forceFit: true,
        height: 320,
      });

      chartRef.value = chart;

      chart.data(datass);
      chart.scale("sales", {
        nice: true,
      });

      chart.axis("sales", false);

      chart
        .interval()
        .position("year*sales")
        .color("year*sales", function (year, sales) {
          return "#36b368";
        });

      chart.interaction("active-region");
      chart.render();

      const e = document.createEvent("Event");
      e.initEvent("resize", true, true);
      window.dispatchEvent(e);
    });
    onUnmounted(() => {
      chartRef.value && chartRef.value.destroy();
    });

    const data = [
      {
        title: "Ant Design Title 1",
      },
      {
        title: "Ant Design Title 2",
      },
      {
        title: "Ant Design Title 3",
      },
      {
        title: "Ant Design Title 4",
      },
    ];

    return {
      data
    };
  },
};
</script>
<style scoped>
.ant-divider-horizontal {
  margin-bottom: 10px !important;
}
.card-left {
  display: inline;
}
.card-right {
  float: right;
}
</style>
