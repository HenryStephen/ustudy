<template>
  <div>
    <list-top :categoryId='categoryId' :subjectId='subjectId'></list-top>
    <!-- <course :courseData='courseData'></course> -->
  </div>
</template>

<script>
import Course from '@/components/course/course.vue'
import ListTop from '@/components/course-list/list-top.vue'
export default {
  components: {
    Course,
    ListTop
  },
  mounted () {
    this.categoryId = this.$route.params.categoryId
    this.subjectId = this.$route.params.subjectId
    console.log(this.categoryId)
  },
  data () {
    return {
      categoryId: '',
      subjectId: ''
    }
  }
}
</script>

<style lang="less">

</style>
