<template>
  <div>
    <page-header
      title="邮箱发送"
      describe="表单页用于向用户收集或验证信息，基础表单常见于数据项较少的表单场景。表单域标签也可支持响应式."
    ></page-header>
    <page-layout>
      <a-card>
        <a-row type="flex" justify="center">
          <a-col :xs="24" :sm="24" :md="24" :lg="24" :xl="22" :xxl="19">
            <a-form
              ref="ruleForm"
              :model="form"
              :rules="rules"
              :label-col="labelCol"
              :wrapper-col="wrapperCol"
            >
              <a-form-item name="name" label="收件邮箱">
                <a-input v-model:value="form.name" />
              </a-form-item>
              <a-form-item name="desc" label="邮箱标题">
                <a-input v-model:value="form.name" />
              </a-form-item>
              <a-form-item name="content" label="邮箱内容">
                <a-textarea v-model:value="form.name" />
              </a-form-item>
              <a-form-item :wrapper-col="{ span: 14, offset: 4 }">
                <a-button type="primary" @click="onSubmit"> 发送 </a-button>
                <a-button style="margin-left: 10px" @click="resetForm">
                  重置
                </a-button>
              </a-form-item>
            </a-form>
          </a-col>
        </a-row>
      </a-card>
    </page-layout>
    <page-footer></page-footer>
  </div>
</template>
<script>
export default {
  data() {
    return {
      labelCol: { xs: 4, sm: 3, md: 3, lg: 3, xl: 2, xxl: 3 },
      wrapperCol: { xs: 20, sm: 21, md: 21, lg: 21, xl: 20, xxl: 17 },
      other: "",
      form: {
        name: "",
        region: undefined,
        date1: undefined,
        delivery: false,
        type: [],
        resource: "",
        content: "",
        desc: ""
      },
      rules: {
        name: [
          {
            required: true,
            message: "Please input Activity name",
            trigger: "blur"
          },
          {
            min: 3,
            max: 5,
            message: "Length should be 3 to 5",
            trigger: "blur"
          }
        ],
        region: [
          {
            required: true,
            message: "Please select Activity zone",
            trigger: "change"
          }
        ],
        date1: [
          {
            required: true,
            message: "Please pick a date",
            trigger: "change",
            type: "object"
          }
        ],
        type: [
          {
            type: "array",
            required: true,
            message: "Please select at least one activity type",
            trigger: "change"
          }
        ],
        resource: [
          {
            required: true,
            message: "Please select activity resource",
            trigger: "change"
          }
        ],
        desc: [
          {
            required: true,
            message: "Please input activity form",
            trigger: "blur"
          }
        ]
      }
    };
  },
  methods: {
    onSubmit() {
      this.$refs.ruleForm
        .validate()
        .then(() => {
          console.log("values", this.form);
        })
        .catch(error => {
          console.log("error", error);
        });
    },
    resetForm() {
      this.$refs.ruleForm.resetFields();
    }
  }
};
</script>
