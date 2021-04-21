<template>
    <template v-if="hasAuthority">
        <slot></slot>
    </template>
</template>
<script>
import { computed, defineComponent } from "vue";
import { useStore } from 'vuex';

export default defineComponent({
	name: 'pro-authority',
	props: {
		value: {
			type: String,
			default: () => '',
		},
	},
	setup() {
		const store = useStore();

        /// 根据权限判定是否渲染组件
		const hasAuthority = computed(() => {
			return true;
		});
		return {
			hasAuthority,
		};
	},
});
</script>