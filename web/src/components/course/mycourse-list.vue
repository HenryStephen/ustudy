<template>
  <div>
    <div class="zg_wdkecheng">
      <div class="zg_gerenmain_right_bt">
        <a :class="outDate == true ? 'none' : 'hover'" @click="clickStuding">学习中</a>
        <a :class="outDate == true ? 'hover' : 'none'" @click="clickOutDate">已过期</a>
      </div>
      <div class="zg_center_kc">
        <dl class="zg_center_kcdl" v-for="item in courseData" :key="item.id">
          <dt>
            <a target="_blank">
              <img :src="item.previewPicture" :alt="item.name">
            </a>
          </dt>
          <dd>
            <div class="zg_kcdlbt">
              <div class="zg_kcdz">
                <h3><a target="_blank">{{item.name}}</a></h3>
                <p>有效期剩余{{item.validDays}}天&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;共{{item.classHour}}课时</p>
              </div>
              <div class="zg_kcan">
                <a class="zg_jxxx">继续学习</a>
                <a class="zg_tuichu" @click="deleteCourse(item.id)">删除课程</a>
              </div>
            </div>
          </dd>
        </dl>
      </div>
      <!-- <div class="zg_page">
        <a class="pag">首页</a><a class="pag">上一页</a>
        <span>1</span>
        <a class="pag">下一页</a>
        <a class="pag">尾页</a>
      </div> -->
	  </div>
  </div>
</template>

<script>
import { getRequest, deleteRequest } from '@/api/index.js'
export default {
  name: 'MyCourseList',
  data () {
    return {
      outDate: false,
      courseData: []
    }
  },
  mounted () {
    this.getLearnCourse()
  },
  methods: {
    clickOutDate () {
      this.outDate = true
      getRequest('/course/expired').then(res => {
        console.log(res)
        if (res.data.status === 200) {
          this.courseData = res.data.data
        }
      })
    },
    clickStuding () {
      this.outDate = false
      this.getLearnCourse()
    },
    getLearnCourse () {
      getRequest('/course/learning').then(res => {
        if (res.data.status === 200) {
          this.courseData = res.data.data
          console.log(this.courseData)
        }
      })
    },
    deleteCourse (id) {
      var courseIdList = []
      deleteRequest('/course/learning', {
        courseIdList: courseIdList
      }).then(res => {
        if (res.data.status === 200) {
          this.$Message.success('删除成功')
          this.getLearnCourse()
        }
      })
    }
  }
}
</script>

<style lang="less">
@import './my-course.less';
</style>
