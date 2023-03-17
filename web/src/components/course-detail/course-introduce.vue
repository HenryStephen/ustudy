<style lang="less">
@import './detail.less';
</style>

<template>
  <div>
    <div class="offcn19_wrap offcn19_wrap1">
      <p class="offcn19_kcjstit">课程介绍</p>
      <div class="offcn19_kcjsbot">
        <div>
          <p>
            <img src='@/assets/images/course-detail/F2H20407622858TQ.jpg' />
            <img src="@/assets/images/course-detail/F2H1039597727EYY.jpg" />
            <img src="@/assets/images/course-detail/F2H1801054621JTH.jpg" />
            <img src="@/assets/images/course-detail/F2H1212635500WQC.jpg" />
          </p>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { getRequest } from '@/api/index.js'
export default {
  name: 'CourseIntroduce',
  props: {
    courseData: {
      type: Object,
      default: function () {
        return {}
      }
    }
  },
  data () {
    return {
      introduction: []   // 课程介绍内容（文字+图片）
    }
  },
  mounted () {
    /* 获取课程介绍 */
    getRequest('/course/' + this.courseData.id + '/description').then(res => {
      console.log(res)
      if (res.data.status === 200) {
        this.introduction = res.data.data
        console.log(this.data)
      }
    })
  }
}
</script>
