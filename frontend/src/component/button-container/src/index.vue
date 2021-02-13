<template>
    <div class="button-container">
        <slot></slot>
    </div>
</template>
<script>
export default{
    name: "button-container"
}
</script>
<style lang="less">
.button-container{
    .ant-btn{
       margin: 3px; 
       margin-bottom: 8px;
    }
}
</style>