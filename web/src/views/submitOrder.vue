<template>
 <div>
   <submit-list :courseData='courseData'></submit-list>
 </div>
</template>

<script>
import SubmitList from '@/components/order/submit-list.vue'
export default {
  components: {
    SubmitList
  },
  mounted () {
    this.courseData.push(this.$route.params.courseDetail)
  },
  data () {
    return {
      courseData: []
    }
  }
}
</script>
