<style lang='less'>
  @import './course-list.less';
</style>

<template>
  <div class="courses">
    <div v-for="course in courseData" :key="course.id" class="course_out_div">
      <Card class="course_card">
        <div class="zg_jpkcu">
          <div class="zg_jpkclt">
            <a>
                <img :src="course.image" :title="course.name">
            </a>
          </div>
          <h6>
            <a @click="getCourseDetail(course.id)">{{course.name}}</a>
          </h6>
          <p>
            <span>{{course.classHour}}课时</span>
            <font><i>￥</i>{{course.presentPrice}}</font>
          </p>
        </div>
      </Card>
    </div>
  </div>
</template>

<script>
import { getRequest } from '@/api/index.js'
export default {
  props: {
    courseData: {
      type: Array,
      default: function () {
        return []
      }
    }
  },
  data () {
    return {
    }
  },
  methods: {
    /* 获取课程详情 */
    getCourseDetail (id) {
      getRequest('/course/' + id).then(res => {
        if (res.data.status === 200) {
          console.log('jump detail')
          console.log(res.data.data)
          this.$router.push({
            name: 'Detail',
            params: {
              id: id,
              data: res.data.data
            }
          })
        }
      })
    }
  }
}
</script>
