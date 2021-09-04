<template>
    <component :is="$antIcons[type]" :style="{  fontSize: size }"/>
</template>
<script>
export default {
    props: {
        type: {
            type: String,
            required: true
        },
        size: {
            type: String,
            required: false,
            default: "16px"
        }
    },
    name: "pro-icon"
}
</script>