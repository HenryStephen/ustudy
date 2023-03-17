<template>
  <div id="account">
    <div id="left-menu">
      <left-menu></left-menu>
    </div>
    <div id="right-information" >
      <right-information></right-information>
    </div>
  </div>
</template>
<script>
import LeftMenu from '@/components/account/left-menu.vue'
import RightInformation from '@/components/account/right-information.vue'
export default {
  name: 'Account',
  components: {
    LeftMenu,
    RightInformation
  },
  data () {
    return {
      id: '',
      data: {}
    }
  },
  methods: {

  }
}
</script>
<style lang="less">
#left-menu {
  float: left;
  position: relative;
  left: 150px;
}
#right-information {
  float: left;
  position: relative;
   left: 180px;
   top: 0px;
}
</style>
