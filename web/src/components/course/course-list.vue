<style lang='less'>
  @import './course-list.less';
</style>

<template>
  <div class="course_list">
    <div class="zg_jpbox">
      <div class="zg_jpkbt">
        <a target="_blank" class="zg_jpbta" @click='getCourses({})'>更多</a>
        <ul class="zg_jpnav">
          <li class="zg_jpn11">精品网课</li>
          <li><a @mousemove='handleMove1' target="_blank">互联网营销</a></li>
          <li><a target="_blank">设计</a></li>
          <li><a target="_blank">Web前端</a></li>
          <li><a target="_blank">程序开发</a></li>
          <li><a target="_blank">Office</a></li>
        </ul>
      </div>
      <course :courseData="fineCourses"></course>
    </div>

    <!--    免费课程-->
    <div class="zg_jpkc">
      <div class="zg_jpkbt">
        <a target="_blank" class="zg_jpbta" @click='getCourses({ free: true })'>更多</a>
        <ul class="zg_jpnav">
          <li class="zg_jpn11">免费课程</li>
          <li><a target="_blank">互联网营销</a></li>
          <li><a target="_blank">设计</a></li>
          <li><a target="_blank">Web前端</a></li>
          <li><a target="_blank">程序开发</a></li>
        </ul>
      </div>
      <course :courseData="freeCourses"></course>
    </div>

    <div class="zg_jpkc">
      <div class="zg_jpkbt">
        <a target="_blank" class="zg_jpbta" @click='getCourses({ teachingMethod: 3 })'>更多</a>
        <ul class="zg_jpnav">
          <li class="zg_jpn11">就业面授班</li>
          <li><a target="_blank">互联网营销</a></li>
          <li><a target="_blank">Java</a></li>
          <li><a target="_blank">大数据开发</a></li>
          <li><a target="_blank">软件测试</a></li>
        </ul>
      </div>
      <course :courseData="employCourses"></course>
    </div>
  </div>
</template>

<script>
import Course from '@/components/course/course.vue'
import { getRequest } from '@/api/index.js'
export default {
  name: 'CourseList',
  components: {
    Course
  },
  data () {
    return {
      fineCourses: [],
      freeCourses: [],
      employCourses: [],
      courses: []
    }
  },
  created: function () {
    getRequest('/course', { size: 8 }).then(res => {
      if (res.data.status === 200) {
        this.fineCourses = res.data.data.records
      }
    })
    getRequest('/course', {
      size: 4,
      free: true
    }).then(res => {
      if (res.data.status === 200) {
        this.freeCourses = res.data.data.records
      }
    })
    getRequest('/course', {
      size: 4,
      teachingMethods: 3
    }).then(res => {
      if (res.data.status === 200) {
        this.employCourse = res.data.data.records
      }
    })
  },
  methods: {
    handleMove1 () {
      getRequest('/course', {
        categoryId: 1,
        size: 8
      }).then(res => {
        if (res.data.status === 200) {
          this.courses = res.data.records
        }
      })
    },
    getCourses (data) {
      getRequest('/course', data).then(res => {
        if (res.data.status === 200) {
          // TODO
          this.$router.push({
            name: 'CourseList',
            params: {
              // TODO
            }
          })
        }
      })
    }
  }
}
</script>
