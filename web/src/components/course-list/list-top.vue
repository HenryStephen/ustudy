<template>
  <div class="list_top">
    <!-- <div class="breadcrumb">
      <Breadcrumb separator=">">
        <BreadcrumbItem to="/">首页</BreadcrumbItem>
        <BreadcrumbItem to="">全部课程</BreadcrumbItem>
        <BreadcrumbItem to="" v-if="category">{{category.name}}</BreadcrumbItem>
      </Breadcrumb>
    </div> -->
    <div class="offcn_lits">
      <a href="">首页</a>
      <a>>全部课程</a>
      >互联网营销
    </div>
    <div class="xk_fl">
      <dl>
        <dt>分　　类：</dt>
        <dd>
          <a v-for="(item, index) in classData" :key="item.id" :class="index==curClassIndex? 'xk_click':''" @click="getCourseByclass(item.id, index)">{{item.name}}</a>
        </dd>
      </dl>
      <dl v-if="isHaveSubject">
        <dt>科　　目：</dt>
        <dd>
          <a  v-for="(item, index) in subjectData" :key="item.id" :class="index==curSubIndex? 'xk_click':''" @click="getCourseBySubject(item.id, index)">{{item.name}}</a>
        </dd>
      </dl>
      <dl>
        <dt>授课方式：</dt>
        <dd>
          <a id="0" :class="curTechMed == 0? 'xk_click':''" @click="getCourseByMed(0)">全部</a>
          <a id="1" :class="curTechMed == 1? 'xk_click':''" @click="getCourseByMed(1)">录播</a>
          <a id="2" :class="curTechMed == 2? 'xk_click':''" @click="getCourseByMed(2)">直播</a>
          <a id="3" :class="curTechMed == 3? 'xk_click':''" @click="getCourseByMed(3)">面授</a>
        </dd>
      </dl>
    </div>

    <div class="offcnkclb_main">
      <div class="xk_nav">
        <span :class="curSortIndex == 'all' ? 'xk_zh':''" @click="sortCourseByAll">综合排序</span>
        <span :class="curSortIndex == 'number' ? 'xk_xl_selected':'xk_xp'" @click="sortCourseByNum('students_number')">销量</span>
        <span :class="curSortIndex == 'new' ? 'xk_xl_selected':'xk_xp'" @click="getNewCourse()">新品</span>
        <span :class="curSortIndex == 'price' ? 'xk_jg1':'xk_jg'" @click="sortCourseByPrice('present_price')">价格</span>
        <div class="jgqj">
          <a  class="jiage" @click="isPriceRange = !isPriceRange"> 价格区间 <i class="arrow-down"></i></a>
          <div v-if="isPriceRange" class="price-select">
            <ul>
              <li><a href="list-180">不限</a></li>
              <li><a >￥1-99</a></li>
              <li><a >￥100-499</a>
              </li>
              <li><a href="/list-180/?price_begin=50000&amp;price_end=99900">￥500-999</a>
              </li>
              <li><a href="/list-180/?price_begin=100000">￥1000及以上</a></li>
            </ul>
            <div class="price-input">
              <form>
                  <input type="text" name="price_min" class="price-input" /> - <input
                      type="text" name="price_max" class="price-input" />
                  <a href="javascript:void(0);" url="/list-180/" class="btn-s siftprice" >确定</a></form>
            </div>
          </div>
        </div>
        <div class="priceCheckbox">
          <Checkbox v-model="free" @on-change="getFreeCourse()">只看免费课程</Checkbox>
          <Checkbox v-model="pay" @on-change="getPaidCourse">只看付费课程</Checkbox>
        </div>
      </div>
      <ul class="xk_list">
        <li v-for="item in courses" :key="item.id">
          <dl>
            <dt>
              <a target="_blank">
                <img src="../../assets/images/course-list/F682091147347RZY.jpg?x-oss-process=image%252Fresize,w_285"
                      width="100%" alt="互联网营销培训课程-互联网营销培训在线课程-培训-视频-教程-优就业"
                      title="互联网营销培训课程-互联网营销培训在线课程-培训-视频-教程-优就业">
              </a>
            </dt>
            <dd>
              <a @click="clickName(item.id)" :title="item.name" target="_blank">{{item.name}}</a>
              <p>
                <span>共有{{item.studentsNumber}}人在学</span>
                <em>|</em>
                <span class='orange'>￥{{item.presentPrice}}</span>
              </p>
            </dd>
          </dl>
        </li>
      </ul>
    </div>
  </div>
</template>

<script>
import { getRequest } from '@/api/index.js'
export default {
  name: 'ListTop',
  props: {
    categoryId: {
      // type: String
    },
    subjectId: {
      // type: String
    }
  },
  // props: {
  //   categoryId: {
  //     default: ''
  //   },
  //   subjectId: {
  //     default: ''
  //   }
  // },
  data () {
    return {
      courses: [],
      curSortIndex: 'number', // 排序选项，默认为全部排序
      isPriceRange: false,
      free: false,
      pay: false,
      curClassIndex: 0, // 当前循环中分类序号，用于控制样式
      curClassId: '',  // 当前分类id，用于请求数据
      curSubIndex: 0, // 当前循环中科目序号，用于控制样式
      curSubId: '', // 当前科目id，用于请求数据
      curTechMed: 0, // 当前循环中教学方式id，用于请求数据
      classData: [],
      subjectData: [],
      allClassTab: {
        id: 0,
        level: '0',
        name: '全部',
        childern: []
      },
      allSubTab: {
        id: 0,
        level: '1',
        name: '全部'
      },
      isMedAll: true, // 授课方式样式控制数据
      isMedVideo: false,
      isMedLive: false,
      isMedFace: false
    }
  },
  computed: {
    isHaveSubject () {
      return this.curClassIndex !== 0
    }
  },
  watch: {
    categoryId () {
      this.getClassData()
      this.curClassId = this.categoryId
      this.curSubId = this.subjectId
      // console.log(this.subjectData)
      this.curClassIndex = 1
      this.curSubIndex = 0
      // this.subjectData = this.classData[this.curClassIndex].childern
      var params = this.getParams()
      this.getCourse(params)
    }
  },
  mounted () {
    // 获取课程
    // this.curClassId = this.categoryId
    // this.curSubId = this.subjectId
    // console.log(this.categoryId)
    // var params = this.getParams()
    // this.getCourse(params)
    // 获取分类和科目
    // getRequest('/course/category').then(res => {
    //   if (res.status === 200) {
    //     this.classData.push(this.allClassTab) // 添加“全部”
    //     for (const index1 in res.data.data) {
    //       var item = res.data.data[index1]
    //       item.childern = [] // 为分类添加children属性
    //       item.childern.push(this.allSubTab)
    //       if (item.level === '0') { // 找到分类
    //         for (const index2 in res.data.data) {
    //           if (res.data.data[index2].parentId === item.id) { // 找到分类对应的科目
    //             item.childern.push(res.data.data[index2])
    //           }
    //         }
    //         this.classData.push(item)
    //       }
    //     }
    //     this.$forceUpdate()
    //   }
    // })
  },
  methods: {
    clickName (id) {
      getRequest('/course/' + id).then(res => {
        if (res.data.status === 200) {
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
    },
    getClassData () {
      getRequest('/course/category').then(res => {
        if (res.status === 200) {
          this.classData.push(this.allClassTab) // 添加“全部”
          for (const index1 in res.data.data) {
            var item = res.data.data[index1]
            item.childern = [] // 为分类添加children属性
            item.childern.push(this.allSubTab)
            if (item.level === '0') { // 找到分类
              for (const index2 in res.data.data) {
                if (res.data.data[index2].parentId === item.id) { // 找到分类对应的科目
                  item.childern.push(res.data.data[index2])
                }
              }
              this.classData.push(item)
            }
          }
          this.$forceUpdate()
        }
      })
    },
    // 整合获取课程参数
    getParams () {
      var params = {}
      params.categoryId =  this.curClassId == 0 ? null : this.curClassId
      params.subjectId =  this.curSubId == 0 ? null : this.curSubId
      params.teachingMethods = this.curTechMed === 0 ? null : this.curTechMed
      // var order = []
      // order.push({column: this.curSortIndex})
      // params.orders = order
      params.free = this.free
      params.pay = this.pay
      console.log(params)
      return params
    },
    // 获取课程
    getCourse (data) {
      getRequest('/course', data).then(res => {
        console.log(res)
        if (res.data.status === 200) {
          this.courses = res.data.data.records
        }
      })
    },
    // 点击分类后更新科目数组，查询课程
    getCourseByclass (id, index) {
      this.curClassId = id
      this.curClassIndex = index
      this.subjectData = this.classData[this.curClassIndex].childern
      var params = this.getParams()
      this.getCourse(params)
    },
    // 点击科目后查询课程
    getCourseBySubject (id, index) {
      this.curSubIndex = index
      this.curSubId = id
      var params = this.getParams()
      // var params = index === 0 ? {} : { categoryId: this.curClassId, subjectId: id }
      this.getCourse(params)
    },
    // 根据授课方式获取课程id,参数为方式代号
    getCourseByMed (id) {
      this.curTechMed = id
      // var params = id === 0 ? {} : { teachingMethods: id }
      // params.categoryId =  this.curClassId == 0 ? '' : this.curClassId
      // params.subjectId =  this.curSubIndex == 0 ? '' : this.curSubIndex
      var params = this.getParams()
      this.getCourse(params)
    },
    /* 综合排序 */
    sortCourseByAll () {
      this.curSortIndex = 'all'
      this.getCourse({}) 
    },
    /* 获取新品 */
    getNewCourse () {
      this.curSortIndex = 'new'
      this.getCourse({}) 
    },
    /* 排序获取课程 */
    sortCourse (method) {
      getRequest('/course', {
        'orders[0].column': method
      }).then(res => {
        console.log(res)
        if (res.data.status === 200) {
          this.courses = res.data.data.records
        }
      })
    },
    /* 按销量排序获取课程 */
    sortCourseByNum (method) {
      this.curSortIndex = 'number'
      this.sortCourse(method)
      // var params = this.getParams()
      // this.getCourse(params)
    },
    /* 按价格排序获取课程 */
    sortCourseByPrice (method) {
      this.curSortIndex = 'price'
      this.sortCourse(method)
    },
    /* 只获取免费课程 */
    getFreeCourse (state) {
      if (state === true) {
        this.pay = false
      }
      var params = this.getParams()
      this.getCourse(params)
    },
    /* 只获取付费课程 */
    getPaidCourse (state) {
      if (state === true) {
        this.free = false
      }
      var params = this.getParams()
      this.getCourse(params)
    }
  }
}
</script>

<style lang="less">
@import './list.less';
</style>
