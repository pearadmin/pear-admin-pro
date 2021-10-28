<template>
  <div class="pro-icon-picker">
    <a-dropdown>
      <a-input v-model:value="selected" readonly>
        <template #addonAfter>
          <pro-icon :type="selected"></pro-icon>
        </template>
      </a-input>
      <template #overlay>
        <a-menu class="pro-icon-picker-content">
          <a-menu-item :key="icon" v-for="icon in icons" @click="select(icon)">
            <pro-icon :type="icon"></pro-icon>
          </a-menu-item>
        </a-menu>
      </template>
    </a-dropdown>
  </div>
</template>
<script>
import icons from "./index.js";
import { watch, ref } from "vue";

export default {
  name: "pro-icon-picker",
  props: {
    value: {
      type: String,
      require: false,
    },
  },
  setup(props, context) {
    const selected = ref(props.value ? props.value : icons[0]);

    const select = function (icon) {
      selected.value = icon;
      context.emit("update:modelValue", icon);
    };

    watch(props, (props) => {
      selected.value = props.modelValue;
    });

    return {
      icons,
      select,
      selected,
    };
  },
};
</script>

<style>
.pro-icon-picker-content {
  height: 300px;
  overflow-y: scroll;
  width: 310px;
}

.pro-icon-picker-content .ant-dropdown-menu-item {
  display: inline-block;
}

.pro-icon-picker-content
  .ant-dropdown-menu-item
  .ant-dropdown-menu-title-content
  span {
  margin-right: 0px !important;
}
</style>