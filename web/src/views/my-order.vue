<template>
  <div id="order">
    <div id="collect_menu">
      <collection-menu :activeName='activeName'></collection-menu>
    </div>
    <div id="order-table" >
      <order-table></order-table>
    </div>
  </div>
</template>
<script>
import CollectionMenu from '@/components/collection/collection-menu.vue'
import OrderTable from '@/components/order/order-table.vue'
export default {
  name: 'Order',
  components: {
    CollectionMenu,
    OrderTable
  },
  data () {
    return {
      id: '',
      data: {},
      activeName: '3'
    }
  },
  methods: {

  }
}
</script>
<style lang="less">
#order-table {
  float: left;
  position: relative;
   left: 180px;
   top: 50px;
}
</style>
