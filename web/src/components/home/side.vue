<template>
  <div class='aside'>
    <dl v-for='item in classData' :key='item.id'>
      <dt><a target='_blank' @click="clickClass(item.id)">{{item.name}}</a></dt>
      <dd v-for='child in item.childern' @click="clickSubject" :key='child.id'>
        <a target='_blank'>{{child.name}}</a>
      </dd>
    </dl>
  </div>
</template>

<script>
import { getRequest } from '@/api/index.js'
export default {
  data () {
    return {
      classData: [],
      objectData: []
    }
  },
  mounted: function () {
    // 获取分类和科目
    getRequest('/course/category').then(res => {
      if (res.status === 200) {
        for (const index in res.data.data) {
          if (res.data.data[index].level === '0') {
            this.classData.push(res.data.data[index])
          }
        }
        for (const index in this.classData) {
          var item = this.classData[index]
          getRequest('/course/category/' + item.id).then(res => {
            if (res.status === 200) {
              this.classData[index].childern = res.data.data
              this.$forceUpdate()
            }
          })
        }
        console.log(this.classData)
      }
    })
  },
  methods: {
    clickClass(id) {
      this.$router.push({
        name: 'CourseList',
        params: {
          categoryId: id
        }
      })
    },
    clickSubject() {}
  }
}
</script>

<style lang='less'>
* {
  margin: 0 auto;
  padding: 0;
}
.aside {
  height: 524px;
  background: #ff4a00;
  opacity: 0.9;
  overflow-y: auto;
  width: 230px;
  padding-top: 19px;
  display: inline-block;
  margin: auto;
  position: absolute;
  left: 128px;
  top: 0px;
  // overflow: hidden;
  z-index: 9;
}
.aside::-webkit-scrollbar {/*滚动条整体样式*/
  width: 4px;     /*高宽分别对应横竖滚动条的尺寸*/
  height: 4px;
}
.aside::-webkit-scrollbar-thumb {/*滚动条里面小方块*/
  border-radius: 5px;
  -webkit-box-shadow: inset 0 0 5px rgba(0,0,0,0.2);
  background: rgba(0,0,0,0.2);
}
.aside::-webkit-scrollbar-track {/*滚动条里面轨道*/
  -webkit-box-shadow: inset 0 0 5px rgba(0,0,0,0.2);
  border-radius: 0;
  background: rgba(0,0,0,0.1);
}
.aside a {
  color: #fff;
  font-family: 'Microsoft YaHei';
}
.aside a:hover {
  color: #fff;
  text-decoration: underline;
}
.aside dl a {
  padding: 0;
  padding-left: 14px;
  margin-bottom: 30px;
  font-size: 16px;
}
.aside dt a {
  line-height: 30px;
  font-size: 18px;
  display: block;
  clear: both;
  text-align: left;
}
.aside dd {
  display: inline;
  float: left;
  position: relative;
  top: -23px;
}
</style>
