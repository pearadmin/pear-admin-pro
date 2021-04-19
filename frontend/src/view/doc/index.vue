<template>
  <div id="doc">
    <page-layout>
      <a-card>
        <iframe frameborder="0" height="545px" width="100%" src="http://localhost:9191/swagger-ui.html"></iframe>
      </a-card>
    </page-layout>
    <page-footer></page-footer>
  </div>
</template>
<script>
export default {
  setup() {
 
  }
};
</script>
<style scoped>
iframe{
    border-width:0px!important;
}
</style>
