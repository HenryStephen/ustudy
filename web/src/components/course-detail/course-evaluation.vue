<template>
  <div>
    <Form :model="formData" :label-width="130" class="form">
      <FormItem label="留下你的宝贵打分" prop='stars' class="formitem">
        <Rate show-text v-model="formData.stars" />
      </FormItem>
      <FormItem label="留言评价" prop="comment" class="formitem">
        <Input v-model="formData.comment" maxlength="500" show-word-limit type="textarea"
          placeholder="购买课程后才能评价~" style="width: 500px;" />
      </FormItem>
      <FormItem prop="single" class="formitem">
       <Checkbox v-model="formData.single">匿名评价</Checkbox>
      </FormItem>
      <FormItem>
        <Button type="primary" @click="handleSubmit">评价</Button>
      </FormItem>
    </Form>
    <div class="zg19_comments" v-if="isComment">
      <p class="zg19_nocomments">暂无评价</p>
    </div>
    <div class="zg19_valuation" v-if="!isComment">
      <a>全部</a>
      <a>5星</a>
      <a>4星</a>
      <a>3星</a>
      <a>2星</a>
      <a>1星</a>
    </div>
    <div class="list">
      <List>
        <ListItem v-for="item in records" :key="item.id" class="listItem">
            <ListItemMeta avatar="https://dev-file.iviewui.com/userinfoPDvn9gKWYihR24SpgC319vXY8qniCqj4/avatar"
             :title="item.userId + ''"
             :description="item.comment" />
            <template slot="action">
                <li>
                    <span>{{item.date}}</span>
                </li>
            </template>
        </ListItem>
      </List>
    </div>
  </div>
</template>

<script>
import { postRequest, getRequest } from '@/api/index.js'
export default {
  name: 'CourseEvaluation',
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
      courseId: '',
      userId: '',
      formData: {
        stars: 5,
        comment: ''
      },
      records: []
    }
  },
  mounted () {
    getRequest('/course/' + this.courseData.id + '/comment').then(res => {
      if (res.data.status === 200) { 
        this.records = res.data.data.records
        this.allTransTime()
      }
    })
  },
  computed: {
    isComment () {
      return this.records === []
    }
  },
  methods: {
    handleSubmit () {
      var data = {  // 写这里了
        id: this.courseData.id,
        userId: 15645740000,
        stars: this.formData.stars,
        time: this.getDate(),
        comment: this.formData.comment
      }
      console.log(data)
      postRequest('/course/' + data.id + '/comment', data).then(res => {
        if (res.status === 200) {
          this.$Message.success('评价成功')
        }
      })
    },
    getDate () {
      const nowDate = new Date()
      const year = nowDate.getFullYear()
      let month = nowDate.getMonth() + 1
      let day = nowDate.getDate()
      if (month < 10) month = '0' + month
      if (day < 10) day = '0' + day
      return year + '/' + month + '/' + day
    },
    allTransTime () {
      for (let item in this.records) {
        var arr = []
        arr = this.records[item].time
        this.records[item].date = arr.slice(0,3).join('-')
      }
    },
  }
}
</script>

<style lang="less">
.form {
  margin-top: 30px;
  margin-left: 120px;
}
.formitem {
  height: 60px;
  text-align: left;
}
.form textarea {
  height: 100px;
}
.form button {
  margin-top: -160px;
  margin-right: 500px;
}
.list {
  width: 480px;
  margin-top: 30px;
  margin-left: 180px;
}
.listItem {
  text-align: left;
}
.zg19_comments {margin-left: -750px;margin-top: -100px;}
.zg19_nocomments{ text-align:center; padding:172px 0 0; font-size:16px; line-height: 20px; color:#999; background: url(../../assets/images/course-detail/zg19_nocomments.png) no-repeat center top; margin-top:50px;  }
.zg19_valuation{margin-top:-65px; margin-left: -880px;font-size: 14px;}
.no_active{display: inline-block;min-width:70px;height: 34px;line-height: 34px;text-align: center;padding:0 15px;background:#ffe7df;border-radius:25px;border:1px solid #ffe7df;color:#010101;margin-right:14px;}
.no_active a i{display: none;width:14px;height: 10px;background: url(../../assets/images/course-detail/pjicon.png) no-repeat;margin-right:12px;}
.no_active a.on{color:#ff5400;background:#fff;border:1px solid #ff5d0d}
.no_active a.on i{display: inline-block;}
.active {color:#ff5400;background:#fff;border:1px solid #ff5d0d}
</style>
