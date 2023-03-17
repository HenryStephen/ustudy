<template>
  <div id="right">
    <div class="pers_tit">
      <h3>完善资料</h3>
    </div>
  <div>
    <Input v-model="value" placeholder="Enter something..." style="width: 300px" />
  </div>
  <Button type="primary" >Primary</Button>
</template>
<script>
  export default {
    data () {
      return {
        value: ''
      }
    }
  }
</script>
<style lang="less">

</style>
