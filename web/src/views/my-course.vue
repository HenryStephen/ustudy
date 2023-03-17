<template>
  <div>
    <collection-menu :activeName='activeName'></collection-menu>
    <my-course-list></my-course-list>
  </div>
</template>

<script>
import MyCourseList from '@/components/course/mycourse-list.vue'
import CollectionMenu from '@/components/collection/collection-menu.vue'
export default {
  components: {
    MyCourseList,
    CollectionMenu
  },
  data () {
    return {
      activeName: '1'
    }
  }
}
</script>
