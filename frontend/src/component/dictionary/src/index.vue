<template>
  <div id="pro-dictionary">
    <template v-if="type === 'radio'">
      <a-radio-group @change="changeRadio" :value="modelValue">
        <a-radio v-for="d in data" :key="d" :value="d.value">{{
          d.label
        }}</a-radio>
      </a-radio-group>
    </template>
    <template v-if="type === 'select'">
      <a-select @change="changeSelect" :value="modelValue">
        <a-select-option v-for="d in data" :key="d" :value="d.value">{{
          d.label
        }}</a-select-option>
      </a-select>
    </template>
    <template v-if="type === 'checkbox'">
      <a-checkbox-group @change="changeCheckbox" :value="modelValue">
        <a-checkbox v-for="d in data" :key="d" :value="d.value">{{
          d.label
        }}</a-checkbox>
      </a-checkbox-group>
    </template>
  </div>
</template>
<script>
import { ref } from "@vue/reactivity";
import { list } from "../../../api/module/dictData";

export default {
  name: "pro-dictionary",
  props: {
    modelValue: {
      type: [String, Array],
      require: true,
    },
    code: {
      type: String,
      require: true,
    },
    type: {
      type: String,
      require: true,
    },
  },
  setup(props, context) {
    const data = ref([]);

    list({ code: props.code }).then((response) => {
      data.value = response.data;
    });

    const changeSelect = function (value) {
      context.emit("update:modelValue", value);
    };

    const changeRadio = function (value) {
      context.emit("update:modelValue", value.target.value);
    };

    const changeCheckbox = function(value) {
        context.emit("update:modelValue", value);
    }

    return {
      data,
      changeCheckbox,
      changeSelect,
      changeRadio,
    };
  },
};
</script>