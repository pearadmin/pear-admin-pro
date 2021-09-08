<template>
  <template v-if="!item.hidden">

    <!-- if item.children is not null 渲染 a-sub-menu -->
    <a-sub-menu
      :key="item.path"
      v-if="item.children && item.children.length > 0"
    >
      <template v-slot:title>
        <span>
          <component v-if="level === 0" :is="$antIcons[item.meta.icon]" />
          <span v-else><div class="indent"></div></span>
          <span>{{ t(item.meta.i18n) }}</span>
        </span>
      </template>
      <!-- 递归 item.children -->
      <sub-menu
        v-for="child in item.children"
        :key="child.path"
        :item="child"
        :level="level + 1"
      />
    </a-sub-menu>
    <!-- if item.chilren is null 渲染 a-menu-item -->
    <a-menu-item
      v-bind="$attrs"
      :key="item.path"
      v-else
    >
      <router-link :to="item.path">
        <component v-if="level === 0" :is="$antIcons[item.meta.icon]" />
        <span v-else><div class="indent"></div></span>
        <span>{{ t(item.meta.i18n) }}</span>
      </router-link>
    </a-menu-item>
  </template>
</template>
<script>
import path from "path";
import { computed } from "vue";
import { useStore } from "vuex";
import { useI18n } from "vue-i18n";
export default {
  emits: ["click"],
  name: "SubMenu",
  props: {
    item: {
      type: Object,
      required: true
    },
    basePath: {
      type: String,
      default: ""
    },
    level: {
      type: Number,
      required: true
    }
  },
  setup(props) {
    const { commit, getters } = useStore();
    
    const resolvePath = (routePath, single) => {
      if (/^(https?:|mailto:|tel:)/.test(routePath)) {
        return routePath;
      }
      if (single) {
        return props.basePath;
      }
      return path.resolve(props.basePath, routePath);
    };

    const foldSide = () => {
      const isComputedMobile = computed(() => getters.isMobile);
      if (isComputedMobile.value) {
        commit("app/UPDATE_COLLAPSED", true);
      }
    };

    // i18n
    const { t } = useI18n()

    return {
      foldSide,
      resolvePath,
      t
    };
  }
};
</script>
<style>
.indent {
  width: 15px;
  display: inline-block;
}
</style>