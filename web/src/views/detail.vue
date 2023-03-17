<template>
  <div>
    <detail-card :courseData='courseData'></detail-card>
    <detail-top :courseData='courseData'></detail-top>
    <detail-description :courseData='courseData'></detail-description>
  </div>
</template>

<script>
import DetailCard from '@/components/course-detail/detail-card.vue'
import DetailTop from '@/components/course-detail/detail-top.vue'
import DetailDescription from '@/components/course-detail/detail-description.vue'
export default {
  components: {
    DetailCard,
    DetailTop,
    DetailDescription
  },
  data () {
    return {
      courseData: {}
    }
  },
  mounted () {
    this.courseData = this.$route.params.data // 根据id查询的课程详情数据
    this.courseData.id = this.$route.params.id
    console.log(this.courseData)
    this.$router.push('/detail/introduce')
  }
}
</script>
