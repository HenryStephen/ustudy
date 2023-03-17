<template>
  <div id="collection">
    <div id="collect_menu">
      <collection-menu :activeName='activeName'></collection-menu>
    </div>
    <div id="collect_table">
      <collection-table></collection-table>
    </div>
  </div>
</template>
<script>
import CollectionMenu from '@/components/collection/collection-menu.vue'
import CollectionTable from '@/components/collection/collection-table.vue'
export default {
  name: 'Collection',
  components: {
    CollectionMenu,
    CollectionTable
  },
  data () {
    return {
      id: '',
      activeName: '2',
      data: {}
    }
  },
  methods: {

  }
}
</script>
<style lang="less" scoped>
#collect_table {
  float: left;
  position: relative;
   left: 380px;
   top: 50px;
}
</style>
