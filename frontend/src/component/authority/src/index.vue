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
	setup(props) {
		const store = useStore();

		const hasAuthority = computed(() => {
			return store.getters.power.indexOf(props.value)
		});

		return {
			hasAuthority,
		};
	},
});
</script>