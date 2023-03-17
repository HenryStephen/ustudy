<template>
  <div>
    <Modal
      v-model="modal1"
      @on-ok="deleteCourse"
      @on-cancel="modal1 = false"
      width="400px"
      class-name="vertical-center-modal">
      <p>是否确认删除？</p>
    </Modal>
    <Modal
      v-model="modal2"
      @on-ok="deleteSelect"
      @on-cancel="moda2 = false"
      width="400px"
      class-name="vertical-center-modal">
      <p>是否确认删除这些课程？</p>
    </Modal>
    <div class="offcngwc_kongbox">
	    <div class="offcngwc_kongcon1">
        <div class="offcngwc_kongcon1_0">
          <dl id="Car">
            <dt>我的购物车 <em></em></dt>
            <dd class="car_1" id="car_1">
            <div class="stepBar1">
              &nbsp;
              </div>
            <div><span class="gwc_tit1">我的购物车</span><span class="gwc_tit2">填写核对订单</span><span class="gwc_tit3">成功提交订单</span></div></dd>
          </dl>
        </div>
        <div class="offcngwc_liucheng1_2">
        <!--购物车列表表头信息-->
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tbody>
            <tr>
              <th width="8%" height="42" align="left" bgcolor="#f8f8f8">
                <label for="checkbox">
                  <input v-model="selectAll" @click="allClick" type="checkbox" class="J_checkall" >全选
                </label>
              </th>
              <th width="32%" height="42" bgcolor="#f8f8f8">课程</th>
              <th width="41%" height="42" bgcolor="#f8f8f8">价格（元）</th>
              <th width="19%" height="42" bgcolor="#f8f8f8">操作</th>
            </tr>
          </tbody>
        </table>
         <!--购物车列表信息-->
          <table width="100%" border="0" cellspacing="0" cellpadding="0" id="cartTable">
            <tbody>
              <tr id="cart_shop_26487" class="checkboxs_selected" v-for="item in courseData" :key="item.id">
                <td width="8%" height="42" align="center">
                  <input type="checkbox" v-model="item.checked" @click="singleCheckChange" class="J_checkitem" id="checkitem_26487" data-recm="15960.00">
                </td>
                <td width="32%" height="42" align="left">
                  <dl>
                    <dt>
                      <img src="@/assets/images/shopcart/F651460955154B5F.jpg?x-oss-process=image/resize,w_300">
                    </dt>
                    <dd>
                      <a style="color: #666" target="_blank">{{item.courseName}}</a>
                    </dd>
                  </dl>
                </td>
                <td width="41%" height="42" align="center"><strong class="orange"><font class="FontFA orange">¥</font> {{item.coursePrice}}</strong></td>
                <td width="19%" height="42" align="center">
                  <span><a id="J_del_one_cart_shop" data-id="26487" @click="clickDelete(item.courseId)">删除</a></span>
                </td>
              </tr>        
              <!--购物车操作-->
              <tr class="borderbf60">
              <td height="42" align="center" bgcolor="#f8f8f8"></td>
              <td height="42" align="left" bgcolor="#f8f8f8">
                <a @click="modal2 = true" id="J_del_selected_cart_shop" class="gwc_lcdel">删除选中的商品</a>
                <a href="/courseList/" class="gwc_lcxk">继续选课</a>
              </td>
              <td height="42" colspan="2" align="right" bgcolor="#f8f8f8">
                <p class="gwc_lcjs">
                  <input type="hidden" id="old_cart_total" value="0">
                  <input type="hidden" id="old_cart_total_price" value="0.00">
                  共<span id="cart_total" class="orange">{{checkedNum}}</span>个课程 金额总计：<strong id="cart_total_price" class="orange"><font class="FontFA orange">¥</font>{{totalMoney}}</strong>
                  <a href="javascript:void(0);" class="gwc_buttonjs orange_bg" id="J_confrim_selected_cart_shop">结算</a>
                </p>
              </td>
              </tr>
            </tbody>
          </table>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { getRequest, deleteRequest } from '@/api/index.js'
import axios from 'axios'
export default {
  name: 'ShopCart',
  props: {
    userId: {
      type: Number,
      default: 1
    }
  },
  data () {
    return {
      modal1: false,
      modal2: false,
      curCourseId: '',
      courseData: [],
      checkedNum: 0,
      selectAll: false,
      selectCount: 0,
      selectGroup: []
    }
  },
  computed: {
    totalMoney: function () {
      let money = 0
      this.courseData.forEach(item => {
        if (item.checked) money = money + item.money
      })
      return money
    }
  },
  mounted: function () {
    this.getCart()
  },
  methods: {
    getCart () {
      getRequest('/cart').then(res => {
        console.log(res)
        if (res.data.status === 200) {
          this.courseData = res.data.data
          for (const item in this.courseData) {
            this.courseData[item].checked = false
          }
          console.log(this.courseData)
        }
      })
    },
    // 点击删除按钮
    clickDelete (courseId) {
        this.curCourseId = courseId
        this.modal1 = true
    },
    // 删除购物车中单个课程
    deleteCourse () {
      deleteRequest('/cart?courseId=' + this.curCourseId).then (res => {
        if (res.data.status === 200) {          
          this.$Message.success('删除成功')
          this.getCart()
        }
      })
    },
    deleteSelect () {
      let flag = false
      this.courseData.forEach(item => {
        if (item.checked === true) {
          deleteRequest('/cart?courseId=' + item.courseId).then(res => {
            console.log(res)
            if (res.data.status !== 200) {
              flag = true  
            }
            else this.getCart()
          })
        }
      })
      if (!flag) this.$Message.success('删除成功')
    },
    // 全选按钮点击事件
    allClick () {
      this.checkedNum = this.selectAll ? 0 : this.courseData.length
      this.courseData.forEach(item => {
        if (!this.selectAll) item.checked = true
        else item.checked = false
      })
    },
    // 单选按钮点击事件
    singleCheckChange () {
      var that = this
      var to = setTimeout(function () {
        that.checkedNum = 0
        that.courseData.forEach(item => {
          if (item.checked) {
            that.checkedNum++
          }
        })
        if (that.checkedNum === that.courseData.length) that.selectAll = true
        else that.selectAll = false
      }, 100)         
    }
  }
}
</script>

<style lang="less">
@import './shopcart.less';
</style>
